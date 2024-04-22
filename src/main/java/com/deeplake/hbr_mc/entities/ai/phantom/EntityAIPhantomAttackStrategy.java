package com.deeplake.hbr_mc.entities.ai.phantom;

import com.deeplake.hbr_mc.entities.ai.idl.EntityAIBaseIDL;
import com.deeplake.hbr_mc.entities.ai.idl.EnumSlideAttackPhase;
import com.deeplake.hbr_mc.entities.npc.idl.EntityModUnit;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;

public class EntityAIPhantomAttackStrategy extends EntityAIBaseIDL {
    private final EntityModUnit entityPhantom;
    private int nextSweepTick;
    boolean onlyAboveSeaLevel = false;

    public EntityAIPhantomAttackStrategy(EntityModUnit entityPhantom) {
        this.entityPhantom = entityPhantom;
    }

    public boolean shouldExecute() {
        EntityLivingBase livingentity = entityPhantom.getAttackTarget();
        return livingentity != null && livingentity.attackable();
    }

    public void startExecuting() {
        this.nextSweepTick = this.adjustedTickDelay(10);
        entityPhantom.attackPhase = EnumSlideAttackPhase.CIRCLE;
        this.setAnchorAboveTarget();
    }

    public void resetTask() {
        entityPhantom.anchorPoint = entityPhantom.world.getHeight(entityPhantom.anchorPoint).up(10 + entityPhantom.getRNG().nextInt(20));
    }

    public void updateTask() {
        if (entityPhantom.attackPhase == EnumSlideAttackPhase.CIRCLE) {
            --this.nextSweepTick;
            if (this.nextSweepTick <= 0) {
                entityPhantom.attackPhase = EnumSlideAttackPhase.SWOOP;
                this.setAnchorAboveTarget();
                this.nextSweepTick = this.adjustedTickDelay((8 + entityPhantom.getRNG().nextInt(4)) * 20);
//                    Phantom.this.playSound(SoundEvents.PHANTOM_SWOOP, 10.0F, 0.95F + Phantom.this.getRNG().nextFloat() * 0.1F);
            }
        }
    }

    private void setAnchorAboveTarget() {
        EntityLivingBase attackTarget = entityPhantom.getAttackTarget();
        if (attackTarget != null)
        {
            entityPhantom.anchorPoint = attackTarget.getPosition().up(20 + entityPhantom.getRNG().nextInt(20));
        }

        if (onlyAboveSeaLevel)
        {
            if (entityPhantom.anchorPoint.getY() < entityPhantom.world.getSeaLevel()) {
                entityPhantom.anchorPoint = new BlockPos(entityPhantom.anchorPoint.getX(), entityPhantom.world.getSeaLevel() + 1, entityPhantom.anchorPoint.getZ());
            }
        }
    }
}
