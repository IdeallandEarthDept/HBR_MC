package com.deeplake.hbr_mc.entities.cancer;

import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.util.DamageSource;
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

    @Override
    public void handleCancerDrop(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
        super.handleCancerDrop(wasRecentlyHit, lootingModifier, source);
        dropByAttribute(RegisterItem.BOOST_1_C, 1);
    }
}
