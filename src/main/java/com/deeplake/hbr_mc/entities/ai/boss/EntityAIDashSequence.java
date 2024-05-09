package com.deeplake.hbr_mc.entities.ai.boss;

import com.deeplake.hbr_mc.entities.effect.EntityCastLineSnow;
import com.deeplake.hbr_mc.init.util.CombatUtil;
import com.deeplake.hbr_mc.init.util.CommonDef;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class EntityAIDashSequence extends EntityAISequenceBase{
    int triggerTick = 20;
    Vec3d posTarget = Vec3d.ZERO;

    public EntityAIDashSequence(EntityLiving aiOwner) {
        super(aiOwner);
        setMutexBits(CommonDef.AIMutexFlags.MOVE);
    }

    @Override
    public void activate() {
        super.activate();
        if (aiOwner.getAttackTarget() != null) {
            posTarget = aiOwner.getAttackTarget().getPositionVector();
            //todo: protection of range, prevent from dashing too far, or too close
            if (aiOwner.getDistanceSq(aiOwner.getAttackTarget()) > 32)
            {
                Vec3d direction = posTarget.subtract(aiOwner.getPositionVector()).normalize();
                posTarget = aiOwner.getPositionVector().add(direction.scale(32));
            } else if (aiOwner.getDistanceSq(aiOwner.getAttackTarget()) < 4) {
                Vec3d direction = posTarget.subtract(aiOwner.getPositionVector()).normalize();
                posTarget = aiOwner.getPositionVector().add(direction.scale(4));
            }
            EntityCastLineSnow dash = new EntityCastLineSnow(aiOwner.world, 1.2f);
            dash.setPosition(aiOwner.posX, aiOwner.posY, aiOwner.posZ);
            dash.setStart(aiOwner.getPositionVector().addVector(0, 1.5f, 0));
            dash.setEnd(posTarget.addVector(0, 1.5f, 0));
            aiOwner.world.spawnEntity(dash);
        }
    }

    @Override
    public void resetTask() {
        super.resetTask();
        aiOwner.setSneaking(false);
    }

    @Override
    public void updateTask() {
        if (triggerTick == ticksLeft)
        {
            aiOwner.setSneaking(true);
            float range = (float) posTarget.distanceTo(aiOwner.getPositionVector());
            List<EntityLivingBase> targets = aiOwner.world.getEntitiesWithinAABB(EntityLivingBase.class, aiOwner.getEntityBoundingBox().grow(range, range, range));
            for (EntityLivingBase target :
                    targets) {
                if (target == aiOwner)
                {
                    continue;
                }
                Vec3d targetPos = target.getPositionVector();
                if (isPointNearLine(aiOwner.getPositionVector(), posTarget, targetPos, 1f))
                {
                    CombatUtil.attackAsHBR(null, aiOwner, target, CombatUtil.EnumElement.ICE, CombatUtil.EnumAttrType.STANDARD, CombatUtil.EnumDefType.END, 300, 200);
                    break;
                }
            }
            aiOwner.setPositionAndUpdate(posTarget.x, posTarget.y, posTarget.z);
        }
        super.updateTask();
    }

    public boolean isPointNearLine(Vec3d start, Vec3d end, Vec3d point, double distance) {
        // 计算线段的向量以及线段的长度
        Vec3d line = end.subtract(start);
        double lineLength = line.lengthVector();

        // 计算点到线段的投影
        Vec3d pointToStart = point.subtract(start);
        double t = pointToStart.dotProduct(line) / (lineLength * lineLength);
        t = Math.max(0, Math.min(1, t)); // 限制t在0到1之间，确保投影在线段内
        Vec3d projection = start.add(line.scale(t));

        // 计算点到线段的距离
        double distanceToLine = point.distanceTo(projection);

        // 判断点到线段的距离是否小于给定的距离
        return distanceToLine <= distance;
    }

//    @Nullable
//    protected Entity findEntityOnPath(Vec3d start, Vec3d end)
//    {
//        Entity entity = null;
//        List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().expand(this.motionX, this.motionY, this.motionZ).grow(1.0D), ARROW_TARGETS);
//        double d0 = 0.0D;
//
//        for (int i = 0; i < list.size(); ++i)
//        {
//            Entity entity1 = list.get(i);
//
//            if (entity1 != this.shootingEntity || this.ticksInAir >= 5)
//            {
//                AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow(0.30000001192092896D);
//                RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(start, end);
//
//                if (raytraceresult != null)
//                {
//                    double d1 = start.squareDistanceTo(raytraceresult.hitVec);
//
//                    if (d1 < d0 || d0 == 0.0D)
//                    {
//                        entity = entity1;
//                        d0 = d1;
//                    }
//                }
//            }
//        }
//
//        return entity;
//    }
}
