package com.deeplake.hbr_mc.entities.cancer;

import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityMarionette extends EntityWalkerCancerBase{
    public EntityMarionette(World worldIn) {
        super(worldIn);
        setSize(0.6f, 1.2f);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        setAttr(32,0.15f,3,0,5);
        set6Attr(3);
        setDPMax(5);
    }


}
