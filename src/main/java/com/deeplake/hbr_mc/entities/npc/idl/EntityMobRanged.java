package com.deeplake.hbr_mc.entities.npc.idl;

import com.deeplake.hbr_mc.entities.ai.EntityAIStrafeRangedAttack;
import com.deeplake.hbr_mc.entities.projectiles.EntityArrowFixed;
import com.deeplake.hbr_mc.entities.projectiles.EntityIdlProjectile;
import com.deeplake.hbr_mc.entities.projectiles.ProjectileArgs;
import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.init.util.EntityUtil;
import com.deeplake.hbr_mc.items.ItemBase;
import com.deeplake.hbr_mc.items.ItemWIPRanged;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntitySpectralArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityMobRanged extends EntityGeneralCreature implements IRangedAttackMob {
    private float bulletAccel = 0.1f;
    private float errorModifier = 0.2f;
    public boolean useBulletForRanged = true;
    public boolean useFixedArrow = true;//damage unrelated to speed.

    public EntityMobRanged(World worldIn) {
        super(worldIn);
        melee_atk = false;
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setCombatTask();
    }

    protected final EntityAIStrafeRangedAttack<EntityMobRanged> aiArrowAttack = new EntityAIStrafeRangedAttack<>(this, 1.0D, 5, 16.0F);
    protected final EntityAIAttackMelee aiAttackOnCollide = new EntityAIAttackMelee(this, 1.2D, false)
    {
        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask()
        {
            super.resetTask();
            EntityMobRanged.this.setSwingingArms(false);
        }
        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting()
        {
            super.startExecuting();
            EntityMobRanged.this.setSwingingArms(true);
        }
    };


    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        this.setEquipmentBasedOnDifficulty(difficulty);
        this.setEnchantmentBasedOnDifficulty(difficulty);
        this.setCombatTask();
        return super.onInitialSpawn(difficulty, livingdata);
    }

    public void setItemStackToSlot(EntityEquipmentSlot slotIn, ItemStack stack)
    {
        boolean changed = stack.getItem() != getItemStackFromSlot(slotIn).getItem();
        super.setItemStackToSlot(slotIn, stack);

        if (!this.world.isRemote && changed)
        {
            this.setCombatTask();
        }
    }

    /**
     * sets this entity's combat AI.
     */
    public void setCombatTask()
    {
        if (this.world != null && !this.world.isRemote)
        {
            this.tasks.removeTask(this.aiAttackOnCollide);
            this.tasks.removeTask(this.aiArrowAttack);
            ItemStack itemstack = this.getHeldItemMainhand();

            if (CommonFunctions.isItemRangedWeapon(itemstack))
            {
                int i = this.world.getDifficulty() == EnumDifficulty.HARD ? 20 : 40;
                this.aiArrowAttack.setAttackCooldown(i);
                this.tasks.addTask(4, this.aiArrowAttack);
                //auto choose projectile type
                Item item = itemstack.getItem();
                if (item instanceof ItemBow) {
                    useBulletForRanged = false;
                } else if (item instanceof ItemWIPRanged) {
                    useBulletForRanged = true;
                }
            }
            else
            {
                this.tasks.addTask(4, this.aiAttackOnCollide);
            }
        }
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        if (useBulletForRanged) {
            attackEntityWithBullet(target, distanceFactor);
        } else {
            attackEntityWithAutoArrow(target, distanceFactor);
        }
    }

    public void attackEntityWithBullet(EntityLivingBase target, float distanceFactor) {
        double d0 = this.getDistanceSq(target);
        double d1 = target.posX - this.posX;
        double d2 = target.getEntityBoundingBox().minY + (double) (target.height / 2.0F) - (this.posY + (double) (this.height / 2.0F));
        double d3 = target.posZ - this.posZ;

        float dist = MathHelper.sqrt(MathHelper.sqrt(d0));
        float estimateHitTime = MathHelper.sqrt(2 * bulletAccel * dist);

        EntityIdlProjectile entityIdlProjectile = getProjectile(target, d1, d2, d3, estimateHitTime);

        playSound(SoundEvents.BLOCK_PISTON_EXTEND, 1f, 2f);
        world.spawnEntity(entityIdlProjectile);
    }

    private EntityIdlProjectile getProjectile(EntityLivingBase target, double dx, double dy, double dz, float estimateHitTime) {
        Item item = getHeldItemMainhand().getItem();
        double accelX = dx + this.getRNG().nextGaussian() * (double) errorModifier + estimateHitTime * target.motionX;
        double accelY = dy + this.getRNG().nextGaussian() * (double) errorModifier + estimateHitTime * target.motionY;
        double accelZ = dz + this.getRNG().nextGaussian() * (double) errorModifier + estimateHitTime * target.motionZ;
        if (item instanceof ItemBase)
        {
            return ((ItemBase) item).getBullet(this, target, getActiveItemStack(), getEntityWorld(),
                    new ProjectileArgs((float) EntityUtil.getAttack(this)), accelX, accelY, accelZ, bulletAccel);
        }
        else {
            return new EntityIdlProjectile(this.world, new ProjectileArgs((float) EntityUtil.getAttack(this)), this,
                    accelX,
                    accelY,
                    accelZ,
                    bulletAccel);
        }
    }


    /**
     * Attack the specified entity using a ranged attack.
     */
    public void attackEntityWithAutoArrow(EntityLivingBase target, float distanceFactor) {
        EntityArrow entityarrow = this.getArrow(distanceFactor);
        double d0 = target.posX - this.posX;
        double d1 = target.getEntityBoundingBox().minY + (double) (target.height / 3.0F) - entityarrow.posY;
        double d2 = target.posZ - this.posZ;
        double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);
        entityarrow.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float) (14 - this.world.getDifficulty().getDifficultyId() * 4));
        this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(entityarrow);
    }

    protected EntityArrow getArrow(float p_190726_1_) {
        ItemStack itemstack = this.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND);

        if (itemstack.getItem() == Items.SPECTRAL_ARROW) {
            EntitySpectralArrow entityspectralarrow = new EntitySpectralArrow(this.world, this);
            entityspectralarrow.setEnchantmentEffectsFromEntity(this, p_190726_1_);
            return entityspectralarrow;
        } else {
            EntityTippedArrow entityarrow = new EntityArrowFixed(this.world, this);
            if (useFixedArrow) {
                entityarrow.setDamage(EntityUtil.getAttack(this));
            }
            entityarrow.setEnchantmentEffectsFromEntity(this, p_190726_1_);

            if (itemstack.getItem() == Items.TIPPED_ARROW) {
                entityarrow.setPotionEffect(itemstack);
            }

            return entityarrow;
        }
    }

    @Override
    public void setSwingingArms(boolean swingingArms) {
        this.dataManager.set(SWINGING_ARMS, swingingArms);
    }
}