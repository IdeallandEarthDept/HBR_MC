package com.deeplake.hbr_mc.entities.ai.idl;

import com.deeplake.hbr_mc.init.util.EntityUtil;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;

public class EntityAIWanderAvoidWaterCheckSentry extends EntityAIWanderAvoidWater {
    public EntityAIWanderAvoidWaterCheckSentry(EntityCreature p_i47301_1_, double p_i47301_2_) {
        super(p_i47301_1_, p_i47301_2_);
    }

    public EntityAIWanderAvoidWaterCheckSentry(EntityCreature p_i47302_1_, double p_i47302_2_, float p_i47302_4_) {
        super(p_i47302_1_, p_i47302_2_, p_i47302_4_);
    }

    @Override
    public boolean shouldContinueExecuting() {
        return super.shouldContinueExecuting() && EntityUtil.canWander(entity);
    }
}
