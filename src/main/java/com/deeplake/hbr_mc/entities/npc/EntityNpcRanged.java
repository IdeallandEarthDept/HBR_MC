package com.deeplake.hbr_mc.entities.npc;

import com.deeplake.hbr_mc.entities.ai.EntityAIStrafeRangedAttack;
import com.deeplake.hbr_mc.entities.ai.EntityAITolerateRevenge;
import com.deeplake.hbr_mc.entities.cancer.EntityCancer;
import com.deeplake.hbr_mc.entities.projectiles.EntityArrowFixed;
import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.init.util.EntityUtil;
import com.deeplake.hbr_mc.items.ItemWIPRanged;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphCannonBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntitySpectralArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityNpcRanged extends EntityNPC implements IRangedAttackMob {
    boolean useFixedArrow = true;//damage unrelated to speed.
    public EntityNpcRanged(World worldIn) {
        super(worldIn);
    }

    protected final EntityAIStrafeRangedAttack<EntityNpcRanged> aiArrowAttack = new EntityAIStrafeRangedAttack<>(this, 1.0D, 18, 12f).setVolley(3,3,1);
    protected final EntityAIAttackMelee aiAttackOnCollide = new EntityAIAttackMelee(this, 1.2D, false)
    {
        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask()
        {
            super.resetTask();
            EntityNpcRanged.this.setSwingingArms(false);
        }
        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting()
        {
            super.startExecuting();
            EntityNpcRanged.this.setSwingingArms(true);
        }
    };
    @Override
    protected void initEntityAI() {
        super.initEntityAI();

        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 0.3D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));

        this.targetTasks.addTask(1, new EntityAITolerateRevenge(this, true, new Class[] {EntityNPC.class}));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityCancer.class, true));
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        //Level 133
        setDPMax(4000);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(134.5f);
        setHealth(getMaxHealth());
        this.getEntityAttribute(RegisterAttr.STR).setBaseValue(459);
        this.getEntityAttribute(RegisterAttr.DEX).setBaseValue(328);
        this.getEntityAttribute(RegisterAttr.END).setBaseValue(375);
        this.getEntityAttribute(RegisterAttr.MEN).setBaseValue(357);
        this.getEntityAttribute(RegisterAttr.INT).setBaseValue(346);
        this.getEntityAttribute(RegisterAttr.LUC).setBaseValue(328);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(393.5f);
        setCombatTask();
        return super.onInitialSpawn(difficulty, livingdata);
    }

    public void attackEntityWithRangedAttackSeraph(EntityLivingBase target, float distanceFactor) {
        int volley = 1;
        ItemSeraphCannonBase.volleyAttack(world, this, volley,
                ModConfig.COMBAT.NORMAL_ATK_POWER, ModConfig.COMBAT.NORMAL_ATK_CAP);

        this.playSound(SoundEvents.ENTITY_BLAZE_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        Item item = getHeldItemMainhand().getItem();
        if (item instanceof ItemSeraphCannonBase || item instanceof ItemWIPRanged) {
            attackEntityWithRangedAttackSeraph(target, distanceFactor);
        } else {
            attackEntityWithAutoArrow(target, distanceFactor);
        }
    }

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
        this.dataManager.set(SWINGING_ARMS, Boolean.valueOf(swingingArms));
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        setCombatTask();
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
            }
            else
            {
                this.tasks.addTask(4, this.aiAttackOnCollide);
            }
        }
    }
}
