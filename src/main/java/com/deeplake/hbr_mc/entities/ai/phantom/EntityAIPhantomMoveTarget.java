package com.deeplake.hbr_mc.entities.ai.phantom;

import com.deeplake.hbr_mc.entities.ai.idl.EntityAIBaseIDL;
import com.deeplake.hbr_mc.entities.npc.EntityModUnit;

abstract class EntityAIPhantomMoveTarget extends EntityAIBaseIDL {
    protected final EntityModUnit entityPhantom;

    public EntityAIPhantomMoveTarget(EntityModUnit entityPhantom) {
        this.entityPhantom = entityPhantom;
        this.setMutexBits(1);
    }

    protected boolean touchingTarget() {
        return entityPhantom.moveTargetPoint.squareDistanceTo(entityPhantom.posX, entityPhantom.posY, entityPhantom.posZ) < 4.0D;
    }
}
