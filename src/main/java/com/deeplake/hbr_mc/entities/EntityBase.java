package com.deeplake.hbr_mc.entities;

import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.init.util.CombatUtil;
import com.deeplake.hbr_mc.init.util.DShieldUtil;
import com.deeplake.hbr_mc.init.util.IDLNBTDef;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityBase extends EntityCreature {
    public EntityBase(World worldIn) {
        super(worldIn);
    }

    private boolean needFirstTickInLife = true;
    private boolean needFirstTickAfterConstruct = true;

    @Override
    public void onLivingUpdate() {
        if (needFirstTickInLife) {
            onFirstTickInLife();
            needFirstTickInLife = false;
        }

        if (needFirstTickAfterConstruct) {
            onFirstTickAfterConstruct();
            needFirstTickAfterConstruct = false;
        }

        super.onLivingUpdate();
    }

    public void onFirstTickInLife() {
        DShieldUtil.syncDPStatus(this);
    }

    public void onFirstTickAfterConstruct() {
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setBoolean(IDLNBTDef.NEED_FIRST_TICK, needFirstTickInLife);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        needFirstTickInLife = compound.getBoolean(IDLNBTDef.NEED_FIRST_TICK);
    }

    public void setAttr(double sight, double speed, double attack, double armor, double hp) {
        //float modifier = getLevelModifier();
        float modifier = 1f;
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(sight * modifier);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(speed);//don't modify speed, crazy.
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(attack * modifier);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(armor * modifier);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(hp * modifier);
        setHealth(getMaxHealth());
    }

    public void set6Attr(float value)
    {
        this.getEntityAttribute(RegisterAttr.STR).setBaseValue(value);
        this.getEntityAttribute(RegisterAttr.DEX).setBaseValue(value);
        this.getEntityAttribute(RegisterAttr.END).setBaseValue(value);
        this.getEntityAttribute(RegisterAttr.MEN).setBaseValue(value);
        this.getEntityAttribute(RegisterAttr.INT).setBaseValue(value);
        this.getEntityAttribute(RegisterAttr.LUC).setBaseValue(value);
    }

    public float getAttr()
    {
        return (float) this.getEntityAttribute(RegisterAttr.MEN).getAttributeValue();
    }

    public void setDPMax(float value)
    {
        this.setAbsorptionAmount(value);
        this.getEntityAttribute(RegisterAttr.DP_MAX).setBaseValue(value);
        DShieldUtil.syncDPStatus(this);
    }

    //EntityMob
    public boolean attackEntityAsMob(Entity entityIn)
    {
        float f = (float)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
        int i = 0;

        if (entityIn instanceof EntityLivingBase)
        {
            f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), ((EntityLivingBase)entityIn).getCreatureAttribute());
            i += EnchantmentHelper.getKnockbackModifier(this);
        }

        boolean flag = standardAttack(entityIn, f);

        if (flag)
        {
            if (i > 0)
            {
                ((EntityLivingBase)entityIn).knockBack(this, (float)i * 0.5F, (double) MathHelper.sin(this.rotationYaw * 0.017453292F), (double)(-MathHelper.cos(this.rotationYaw * 0.017453292F)));
                this.motionX *= 0.6D;
                this.motionZ *= 0.6D;
            }

            int j = EnchantmentHelper.getFireAspectModifier(this);

            if (j > 0)
            {
                entityIn.setFire(j * 4);
            }

            if (entityIn instanceof EntityPlayer)
            {
                EntityPlayer entityplayer = (EntityPlayer)entityIn;
                ItemStack itemstack = this.getHeldItemMainhand();
                ItemStack itemstack1 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack() : ItemStack.EMPTY;

                if (!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.getItem().canDisableShield(itemstack, itemstack1, entityplayer, this) && itemstack1.getItem().isShield(itemstack1, entityplayer))
                {
                    float f1 = 0.25F + (float)EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;

                    if (this.rand.nextFloat() < f1)
                    {
                        entityplayer.getCooldownTracker().setCooldown(itemstack1.getItem(), 100);
                        this.world.setEntityState(entityplayer, (byte)30);
                    }
                }
            }

            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }

    public boolean standardAttack(Entity target, float damage) {
        if (target instanceof EntityLivingBase)
        {
            CombatUtil.attackAsHBR(this, (EntityLivingBase) target, 150, damage);
        }
        return target.attackEntityFrom(DamageSource.causeMobDamage(this), damage);
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    }

    public boolean isInvulnerableToAttacks()
    {
        return false;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (isInvulnerableToAttacks())
        {
            if (getAbsorptionAmount() > 0 && !CombatUtil.canBreakShield(source))
            {
                if (!world.isRemote && source.getTrueSource() != null)
                {
                    world.playSound(null, posX, posY, posZ, SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.HOSTILE, 1.0f, 1.0f);
                }
            }
        }
        return super.attackEntityFrom(source, amount);
    }

    //Cannot be attacked when it still has shell
    @Override
    public boolean isEntityInvulnerable(DamageSource source) {
        if (isInvulnerableToAttacks())
        {
            if (getAbsorptionAmount() > 0 && !CombatUtil.canBreakShield(source))
            {
                return true;
            }
        }
        return super.isEntityInvulnerable(source);
    }

    @Override
    protected void damageEntity(DamageSource damageSrc, float damageAmount) {
        float lastDP = getAbsorptionAmount();
        if (!this.isEntityInvulnerable(damageSrc))
        {
            damageAmount = net.minecraftforge.common.ForgeHooks.onLivingHurt(this, damageSrc, damageAmount);
            if (damageAmount <= 0) return;
            damageAmount = this.applyArmorCalculations(damageSrc, damageAmount);
            damageAmount = this.applyPotionDamageCalculations(damageSrc, damageAmount);
            float f = damageAmount;
            damageAmount = Math.max(damageAmount - this.getAbsorptionAmount(), 0.0F);
            if (lastDP > 0)
            {
                this.setAbsorptionAmount(this.getAbsorptionAmount() - (f - damageAmount));
                //no damage to HP, no stun on break
            }
            else {
                damageAmount = net.minecraftforge.common.ForgeHooks.onLivingDamage(this, damageSrc, damageAmount);
                if (damageAmount != 0.0F)
                {
                    float f1 = this.getHealth();
                    this.getCombatTracker().trackDamage(damageSrc, f1, damageAmount);
                    this.setHealth(f1 - damageAmount); // Forge: moved to fix MC-121048
                    this.setAbsorptionAmount(this.getAbsorptionAmount() - damageAmount);
                }
            }
            DShieldUtil.syncDPStatus(this);
        }
    }
}
