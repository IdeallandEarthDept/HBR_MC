package com.deeplake.hbr_mc.entities.ai;

import com.deeplake.hbr_mc.init.RegisterEffects;
import com.deeplake.hbr_mc.init.util.CommonDef;
import com.deeplake.hbr_mc.init.util.EntityUtil;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIStunned extends EntityAIBase {
    protected final EntityLiving self;

    public EntityAIStunned(EntityLiving self) {
        this.self = self;
        this.setMutexBits(CommonDef.AIMutexFlags.LOOK | CommonDef.AIMutexFlags.MOVE | CommonDef.AIMutexFlags.SWIM_ETC);
    }

    @Override
    public boolean shouldExecute() {
        return EntityUtil.getBuffLevelIDL(self, RegisterEffects.STUNNED) > 0 ||
                EntityUtil.getBuffLevelIDL(self, RegisterEffects.SELF_RECOIL) > 0;
    }
}
