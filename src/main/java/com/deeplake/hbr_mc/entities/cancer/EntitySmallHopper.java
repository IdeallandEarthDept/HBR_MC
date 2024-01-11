package com.deeplake.hbr_mc.entities.cancer;

import net.minecraft.world.World;

public class EntitySmallHopper extends EntityWalkerCancerBase{
    public EntitySmallHopper(World worldIn) {
        super(worldIn);
        setSize(1.8f, 2f);
    }

    @Override
    public int getLevel() {
        return 3;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        setAttr(32,0.3f,3,0,10);
        set6Attr(20);
        setDPMax(20);
    }


}
