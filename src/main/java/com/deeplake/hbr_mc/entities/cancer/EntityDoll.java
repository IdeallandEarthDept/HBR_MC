package com.deeplake.hbr_mc.entities.cancer;

import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityDoll extends EntityWalkerCancerBase{
    public EntityDoll(World worldIn) {
        super(worldIn);
        setSize(0.8f, 1.4f);
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

    @Override
    public void handleCancerDrop(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
        super.handleCancerDrop(wasRecentlyHit, lootingModifier, source);
        dropByAttribute(RegisterItem.BOOST_1_C, 1);
    }
}
