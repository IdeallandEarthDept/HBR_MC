package com.deeplake.hbr_mc.entities.ai.idl;

import com.deeplake.hbr_mc.Main;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nullable;
import java.util.List;

public class EntityKeepDistance<T extends Entity> extends EntityAIBaseIDL
{
    private final Predicate<T> canBeSeenSelector;
    /** The entity we are attached to */
    protected EntityCreature entity;
    private final double farSpeed;
    private final double nearSpeed;
    protected T closestLivingEntity;
    private final float avoidDistance;
    /** The PathEntity of our entity */
    private Path path;
    /** The PathNavigate of our entity */
    private final PathNavigate navigation;
    /** Class of entity this behavior seeks to avoid */
    private final Class<T> classToAvoid;
    private final Predicate <? super T > avoidTargetSelector;
    private Predicate<? super T > predicateMerged;


    public EntityKeepDistance(EntityCreature entityIn, Class<T> classToAvoidIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn)
    {
        this(entityIn, classToAvoidIn, Predicates.alwaysTrue(), avoidDistanceIn, farSpeedIn, nearSpeedIn);
    }

    public EntityKeepDistance(EntityCreature entityIn, Class<T> classToAvoidIn, Predicate <? super T > avoidTargetSelectorIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn)
    {
        this.canBeSeenSelector = new Predicate<T>()
        {
            public boolean apply(@Nullable Entity p_apply_1_)
            {
                return p_apply_1_.isEntityAlive() && EntityKeepDistance.this.entity.getEntitySenses().canSee(p_apply_1_) && !EntityKeepDistance.this.entity.isOnSameTeam(p_apply_1_);
            }
        };
        this.entity = entityIn;
        this.classToAvoid = classToAvoidIn;
        this.avoidTargetSelector = avoidTargetSelectorIn;
        this.avoidDistance = avoidDistanceIn;
        this.farSpeed = farSpeedIn;
        this.nearSpeed = nearSpeedIn;
        this.navigation = entityIn.getNavigator();
        this.setMutexBits(1);

        predicateMerged = Predicates.and(
                EntitySelectors.CAN_AI_TARGET,
                this.canBeSeenSelector,
                this.avoidTargetSelector);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        try {
            List<T> list = this.entity.world.<T>getEntitiesWithinAABB(this.classToAvoid, this.entity.getEntityBoundingBox().grow((double)this.avoidDistance, 3.0D, (double)this.avoidDistance),
                    predicateMerged);

            List<T> list2 = this.entity.world.<T>getEntitiesWithinAABB(this.classToAvoid, this.entity.getEntityBoundingBox()
                            .grow((double)this.avoidDistance * this.entity.world.getEntities(this.classToAvoid, EntitySelectors.IS_ALIVE).size()+1, 3.0D, (double)this.avoidDistance),
                    predicateMerged);

            if (list.isEmpty())
            {
                return false;
            }
            else
            {
                removeSelf(list);

                if (list.isEmpty())
                {
                    return false;
                }
                this.closestLivingEntity = list.get(0);
                Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.entity, (int) (avoidDistance*(list2.size()+1)), 7, new Vec3d(this.closestLivingEntity.posX, this.closestLivingEntity.posY, this.closestLivingEntity.posZ));

                if (vec3d == null)
                {
                    return false;
                }
                else if (this.closestLivingEntity.getDistanceSq(vec3d.x, vec3d.y, vec3d.z) < this.closestLivingEntity.getDistanceSq(this.entity))
                {
                    return false;
                }
                else
                {
                    this.path = this.navigation.getPathToXYZ(vec3d.x, vec3d.y, vec3d.z);
                    return this.path != null;
                }
            }
        }
        catch (Exception e)
        {
            Main.Log(e.toString());
            return false;
        }

    }


    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting()
    {
        return !this.navigation.noPath();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.navigation.setPath(this.path, this.farSpeed);
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask()
    {
        this.closestLivingEntity = null;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask()
    {
        if (this.entity.getDistanceSq(this.closestLivingEntity) < 49.0D)
        {
            this.entity.getNavigator().setSpeed(this.nearSpeed);
        }
        else
        {
            this.entity.getNavigator().setSpeed(this.farSpeed);
        }
    }

    private void removeSelf(List<T> list) {
        removeEntity(list, this.entity);
    }
}
