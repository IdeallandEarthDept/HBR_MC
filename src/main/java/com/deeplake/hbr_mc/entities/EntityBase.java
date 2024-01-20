package com.deeplake.hbr_mc.entities;

import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.init.RegisterEffects;
import com.deeplake.hbr_mc.init.util.CombatUtil;
import com.deeplake.hbr_mc.init.util.IDLNBTDef;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
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
}
