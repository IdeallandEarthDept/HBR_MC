package com.deeplake.hbr_mc.entities.effect;

import com.deeplake.hbr_mc.init.util.CommonDef;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityCastingBase extends Entity {
    public EntityLivingBase shootingEntity;
    public float power = 1f;

    int tickToLive = -1;
    boolean eternal = true;

    public EntityCastingBase(World worldIn) {
        super(worldIn);
    }

    public void setPower(float power) {
        this.power = power;
    }

    public EntityCastingBase(World worldIn, float lifeSeconds) {
        super(worldIn);
        setTickToLive((int) (lifeSeconds * CommonDef.TICK_PER_SECOND));
    }

    public void setCaster(EntityLivingBase caster)
    {
        shootingEntity = caster;
    }

    void upkeep()
    {
        if (world.isRemote || eternal)
        {
            return;
        }

        drainLifeTicks(1);
    }

    public void drainLifeTicks(int tick)
    {
        if (tick >= tickToLive)
        {
            onExpire();
            setDead();
        }
        tickToLive -= tick;
    }

    public void onExpire()
    {
        //currently only server side
    }

    public void setTickToLive(int tick)
    {
        tickToLive = tick;
        eternal = false;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        upkeep();
    }

    @Override
    protected void entityInit() {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        tickToLive = compound.getInteger("tickToLive");
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setInteger("tickToLive", tickToLive);
    }
}
