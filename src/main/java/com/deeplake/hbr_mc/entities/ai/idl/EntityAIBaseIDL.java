package com.deeplake.hbr_mc.entities.ai.idl;

import net.minecraft.entity.ai.EntityAIBase;

public abstract class EntityAIBaseIDL extends EntityAIBase {
    public boolean requiresUpdateEveryTick() {
        return false;
    }
    protected int adjustedTickDelay(int p_186072_) {
        return this.requiresUpdateEveryTick() ? p_186072_ : reducedTickDelay(p_186072_);
    }

    protected static int reducedTickDelay(int p_186074_) {
        return positiveCeilDiv(p_186074_, 2);
    }

    public static int positiveCeilDiv(int p_184653_, int p_184654_) {
        return -Math.floorDiv(-p_184653_, p_184654_);
    }

}
