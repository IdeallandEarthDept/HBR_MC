package com.deeplake.hbr_mc.entities.ai.phantom;

import com.deeplake.hbr_mc.entities.ai.idl.EnumSlideAttackPhase;
import com.deeplake.hbr_mc.entities.npc.idl.EntityModUnit;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;

public class EntityAIPhantomSweepAttack extends EntityAIPhantomMoveTarget {
    private static final int CAT_SEARCH_TICK_DELAY = 20;

    public EntityAIPhantomSweepAttack(EntityModUnit entityPhantom) {
        super(entityPhantom);
    }

    public boolean shouldExecute() {
        return entityPhantom.getAttackTarget() != null && entityPhantom.attackPhase == EnumSlideAttackPhase.SWOOP;
    }

    public boolean shouldContinueExecuting() {
        EntityLivingBase livingentity = entityPhantom.getAttackTarget();
        if (livingentity == null) {
            return false;
        } else if (!livingentity.isEntityAlive()) {
            return false;
        } else {
            if (livingentity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) livingentity;
                if (((EntityPlayer) livingentity).isSpectator() || player.isCreative()) {
                    return false;
                }
            }

            if (!this.shouldExecute()) {
                return false;
            } else {
//                    if (Phantom.this.ticksExisted > this.catSearchTick) {
//                        this.catSearchTick = Phantom.this.ticksExisted + 20;
//                        List<EntityOcelot> list = Phantom.this.world.getEntities(EntityOcelot.class, Phantom.this.getEntityBoundingBox().expand(16.0D), EntitySelector.ENTITY_STILL_ALIVE);
//
//                        this.isScaredOfCat = !list.isEmpty();
//                    }
//
//                    return !this.isScaredOfCat;
                return true;
            }
        }
    }

    public void startExecuting() {
    }

    public void resetTask() {
        entityPhantom.setAttackTarget(null);
        entityPhantom.attackPhase = EnumSlideAttackPhase.CIRCLE;
    }

    public void updateTask() {
        EntityLivingBase livingentity = entityPhantom.getAttackTarget();
        if (livingentity != null) {
            entityPhantom.moveTargetPoint = new Vec3d(livingentity.posX, livingentity.posY, livingentity.posZ);
            if (entityPhantom.getEntityBoundingBox().expand(0.2F, 0.2F, 0.2F).intersects(livingentity.getEntityBoundingBox())) {
                entityPhantom.attackEntityAsMob(livingentity);
                entityPhantom.attackPhase = EnumSlideAttackPhase.CIRCLE;
                if (!entityPhantom.isSilent()) {
                    entityPhantom.world.playEvent(1039, entityPhantom.getPosition(), 0);
                }
            } else if (entityPhantom.collidedHorizontally || entityPhantom.hurtTime > 0) {
                entityPhantom.attackPhase = EnumSlideAttackPhase.CIRCLE;
            }
        }
    }
}
