package com.deeplake.hbr_mc.entities.cancer;

import net.minecraft.world.World;

public class EntityCrestHopper extends EntitySmallHopper{
    public EntityCrestHopper(World worldIn) {
        super(worldIn);
        setSize(2f, 4f);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        set6Attr(40);
        setDPMax(40);
    }
}
