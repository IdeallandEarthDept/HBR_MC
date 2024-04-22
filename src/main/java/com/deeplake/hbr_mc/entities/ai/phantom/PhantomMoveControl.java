package com.deeplake.hbr_mc.entities.ai.phantom;

import com.deeplake.hbr_mc.entities.npc.idl.EntityModUnit;
import com.deeplake.hbr_mc.init.util.CommonFunctions;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityMoveHelper;

public class PhantomMoveControl extends EntityMoveHelper {
    private final EntityModUnit entityPhantom;
    private float speed = 0.1F;

    public PhantomMoveControl(EntityModUnit entityPhantom, EntityLiving p_33241_) {
        super(p_33241_);
        this.entityPhantom = entityPhantom;
    }

    public void onUpdateMoveHelper() {
        if (entityPhantom.collidedHorizontally) {
            entityPhantom.rotationYaw = (entityPhantom.rotationYaw + 180.0F);
            this.speed = 0.1F;
        }

        double dx = entityPhantom.moveTargetPoint.x - entityPhantom.posX;
        double dy = entityPhantom.moveTargetPoint.y - entityPhantom.posY;
        double dz = entityPhantom.moveTargetPoint.z - entityPhantom.posZ;
        double dist = Math.sqrt(dx * dx + dz * dz);
        if (Math.abs(dist) > (double) 1.0E-5F) {
            double xzRatio = 1.0D - Math.abs(dy * (double) 0.7F) / dist;
            dx *= xzRatio;
            dz *= xzRatio;
            dist = Math.sqrt(dx * dx + dz * dz);
            double changedDist = Math.sqrt(dx * dx + dz * dz + dy * dy);
            float yaw = entityPhantom.rotationYaw;
            float angle = (float) Math.atan2(dz, dx);
            float yawP90 = CommonFunctions.wrapDegrees(entityPhantom.rotationYaw + 90.0F);
            float angleRad = CommonFunctions.wrapDegrees(angle * (180F / (float) Math.PI));
            entityPhantom.rotationYaw = (CommonFunctions.approachDegrees(yawP90, angleRad, 4.0F) - 90.0F);
//                Phantom.this.yBodyRot = Phantom.this.rotationYaw;
            if (CommonFunctions.degreesDifferenceAbs(yaw, entityPhantom.rotationYaw) < 3.0F) {
                this.speed = CommonFunctions.approach(this.speed, 1.8F, 0.005F * (1.8F / this.speed));
            } else {
                this.speed = CommonFunctions.approach(this.speed, 0.2F, 0.025F);
            }

            float f4 = (float) (-(Math.atan2(-dy, dist) * (double) (180F / (float) Math.PI)));
            entityPhantom.rotationPitch = f4;
            float f5 = entityPhantom.rotationYaw + 90.0F;
            double d6 = this.speed * Math.cos(f5 * ((float) Math.PI / 180F)) * Math.abs(dx / changedDist);
            double d7 = this.speed * Math.sin(f5 * ((float) Math.PI / 180F)) * Math.abs(dz / changedDist);
            double d8 = this.speed * Math.sin(f4 * ((float) Math.PI / 180F)) * Math.abs(dy / changedDist);

            entityPhantom.motionX = entityPhantom.motionX + (d6 - entityPhantom.motionX) * 0.2;
            entityPhantom.motionY = entityPhantom.motionY + (d8 - entityPhantom.motionY) * 0.2;
            entityPhantom.motionZ = entityPhantom.motionZ + (d7 - entityPhantom.motionZ) * 0.2;
        }

    }
}
