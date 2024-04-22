package com.deeplake.hbr_mc.entities.ai.phantom;

import com.deeplake.hbr_mc.entities.ai.idl.EnumSlideAttackPhase;
import com.deeplake.hbr_mc.entities.npc.EntityModUnit;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class EntityAIPhantomCircleAroundAnchor extends EntityAIPhantomMoveTarget {
    private float angle;
    private float distance;
    private float height;
    private float clockwise;

    public EntityAIPhantomCircleAroundAnchor(EntityModUnit entityPhantom) {
        super(entityPhantom);
    }

    public boolean shouldExecute() {
        return entityPhantom.getAttackTarget() == null || entityPhantom.attackPhase == EnumSlideAttackPhase.CIRCLE;
    }

    public void startExecuting() {
        this.distance = 5.0F + entityPhantom.getRNG().nextFloat() * 10.0F;
        this.height = -4.0F + entityPhantom.getRNG().nextFloat() * 9.0F;
        this.clockwise = entityPhantom.getRNG().nextBoolean() ? 1.0F : -1.0F;
        this.selectNext();
    }

    public void updateTask() {
        if (entityPhantom.getRNG().nextInt(this.adjustedTickDelay(350)) == 0) {
            this.height = -4.0F + entityPhantom.getRNG().nextFloat() * 9.0F;
        }

        if (entityPhantom.getRNG().nextInt(this.adjustedTickDelay(250)) == 0) {
            ++this.distance;
            if (this.distance > 15.0F) {
                this.distance = 5.0F;
                this.clockwise = -this.clockwise;
            }
        }

        if (entityPhantom.getRNG().nextInt(this.adjustedTickDelay(450)) == 0) {
            this.angle = entityPhantom.getRNG().nextFloat() * 2.0F * (float) Math.PI;
            this.selectNext();
        }

        if (this.touchingTarget()) {
            this.selectNext();
        }

        if (entityPhantom.moveTargetPoint.y < entityPhantom.posY && !entityPhantom.world.isAirBlock(entityPhantom.getPosition().down(1))) {
            this.height = Math.max(1.0F, this.height);
            this.selectNext();
        }

        if (entityPhantom.moveTargetPoint.y > entityPhantom.posY && !entityPhantom.world.isAirBlock(entityPhantom.getPosition().up(1))) {
            this.height = Math.min(-1.0F, this.height);
            this.selectNext();
        }

    }

    private void selectNext() {
        if (BlockPos.ORIGIN.equals(entityPhantom.anchorPoint)) {
            entityPhantom.anchorPoint = entityPhantom.getPosition();
        }

        this.angle += this.clockwise * 15.0F * ((float) Math.PI / 180F);
        //todo: check the 0.5 error here
        entityPhantom.moveTargetPoint = new Vec3d((entityPhantom.anchorPoint).add((double) (this.distance * Math.cos(this.angle)), (double) (-4.0F + this.height), (double) (this.distance * Math.sin(this.angle))));
    }
}
