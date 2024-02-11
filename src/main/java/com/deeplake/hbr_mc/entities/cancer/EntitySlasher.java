package com.deeplake.hbr_mc.entities.cancer;

import net.minecraft.world.World;

public class EntitySlasher extends EntityWalkerCancerBase{
    public EntitySlasher(World worldIn) {
        super(worldIn);
        setSize(1.5f, 3f);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        setAttr(32,0.3f,20,0,20);
        set6Attr(50);
        setDPMax(50);
    }

    @Override
    public float getEyeHeight() {
        return this.height * 0.5F;
    }
}
