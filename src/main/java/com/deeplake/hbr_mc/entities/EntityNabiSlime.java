package com.deeplake.hbr_mc.entities;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityNabiSlime extends EntitySlime {
    public EntityNabiSlime(World worldIn) {
        super(worldIn);
    }

    @Override
    protected SoundEvent getJumpSound() {
        return null;
    }

    protected SoundEvent getSquishSound()
    {
        return null;
    }

    protected void initEntityAI()
    {
        super.initEntityAI();
        targetTasks.taskEntries.clear();
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }
}
