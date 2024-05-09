package com.deeplake.hbr_mc.entities.ai.boss;

import com.deeplake.hbr_mc.entities.ai.idl.EntityAIBaseIDL;
import net.minecraft.entity.EntityLiving;

public class EntityAISequenceBase extends EntityAIBaseIDL {
    public final EntityLiving aiOwner;
    public int ticksLeft = 0;
    public boolean isActive = false;
    public int maxTicks = 60;


    public EntityAISequenceBase(EntityLiving aiOwner) {
        this.aiOwner = aiOwner;
    }

    public void activate()
    {
        isActive = true;
    }
    public boolean isActivated()
    {
        return isActive;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
        ticksLeft = maxTicks;
    }

    @Override
    public void updateTask() {
        super.updateTask();

        ticksLeft--;
    }

    @Override
    public void resetTask() {
        isActive = false;
        ticksLeft = 0;
        super.resetTask();
    }

    @Override
    public boolean shouldContinueExecuting() {
        return ticksLeft > 0 & super.shouldContinueExecuting();
    }

    @Override
    public boolean shouldExecute() {
        return isActive;
    }

    public boolean ownerHasTarget() {
        return aiOwner.getAttackTarget() != null;
    }
}
