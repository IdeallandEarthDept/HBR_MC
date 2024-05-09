package com.deeplake.hbr_mc.entities.ai.boss;

import com.deeplake.hbr_mc.entities.effect.EntityCastDelayIcePillar;
import com.deeplake.hbr_mc.init.util.CommonDef;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityAIBoomSequence extends EntityAISequenceBase {
    List<Integer> triggerTicks = new ArrayList<>();

    public EntityAIBoomSequence(EntityLiving aiOwner) {
        super(aiOwner);
        setMutexBits(CommonDef.AIMutexFlags.MOVE);
        triggerTicks.add(30);
        triggerTicks.add(20);
        triggerTicks.add(10);
    }

    @Override
    public void updateTask() {
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
        super.updateTask();
    }
}
