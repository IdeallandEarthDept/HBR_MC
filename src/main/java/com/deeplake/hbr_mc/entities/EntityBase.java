package com.deeplake.hbr_mc.entities;

import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.init.util.IDLNBTDef;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
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

    public void setDPMax(float value)
    {
        this.setAbsorptionAmount(value);
        this.getEntityAttribute(RegisterAttr.DP_MAX).setBaseValue(value);
    }
}
