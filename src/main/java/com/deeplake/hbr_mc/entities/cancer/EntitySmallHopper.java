package com.deeplake.hbr_mc.entities.cancer;

import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.util.DamageSource;
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

    @Override
    public void handleCancerDrop(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
        super.handleCancerDrop(wasRecentlyHit, lootingModifier, source);
        dropByAttribute(RegisterItem.BOOST_1_A, 1);
    }
}
