package com.deeplake.hbr_mc.entities.cancer;

import net.minecraft.world.World;

public class EntityDoll extends EntityWalkerCancerBase{
    public EntityDoll(World worldIn) {
        super(worldIn);
        setSize(0.6f, 1.4f);
    }

    @Override
    public int getLevel() {
        return 3;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        setAttr(32,0.25f,3,0,5);
        set6Attr(10);
        setDPMax(10);
    }


}
