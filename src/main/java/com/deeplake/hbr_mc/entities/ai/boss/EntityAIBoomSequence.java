package com.deeplake.hbr_mc.entities.ai.boss;

import com.deeplake.hbr_mc.entities.ai.idl.EntityAIBaseIDL;
import com.deeplake.hbr_mc.entities.effect.EntityCastDelayIcePillar;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityAIBoomSequence extends EntityAIBaseIDL {
    final EntityLiving aiOwner;
    int ticksLeft = 0;
    List<Integer> triggerTicks = new ArrayList<>();
    boolean isActive = false;
    int maxTicks = 60;

    public EntityAIBoomSequence(EntityLiving aiOwner) {
        this.aiOwner = aiOwner;
        triggerTicks.add(10);
        triggerTicks.add(20);
        triggerTicks.add(30);
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
        if (triggerTicks.contains(ticksLeft))
        {
            //Do something
            EntityLivingBase target = aiOwner.getAttackTarget();
            if (target != null)
            {
                aiOwner.world.playSound(null, target.posX, target.posY, target.posZ, SoundEvents.BLOCK_ANVIL_PLACE, null, 1f, 1.3f);
                Random random = aiOwner.getRNG();
                EntityCastDelayIcePillar boomCast = new EntityCastDelayIcePillar(aiOwner.world, 1f, aiOwner);
                boomCast.setPosition(target.posX + random.nextFloat()*0.2f - 0.1f,
                        target.posY,
                        target.posZ + random.nextFloat()*0.2f - 0.1f);
                aiOwner.world.spawnEntity(boomCast);
            }
        }
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
