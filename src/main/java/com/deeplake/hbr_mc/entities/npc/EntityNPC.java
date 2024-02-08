package com.deeplake.hbr_mc.entities.npc;

import com.deeplake.hbr_mc.entities.EntityHumanoid;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.INpc;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityNPC extends EntityHumanoid implements INpc {
    public EntityNPC(World worldIn) {
        super(worldIn);
    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setLeftHanded(false);
        return livingdata;
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }
}
