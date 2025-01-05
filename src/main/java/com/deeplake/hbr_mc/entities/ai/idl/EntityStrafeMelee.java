package com.deeplake.hbr_mc.entities.ai.idl;

import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.init.util.CommonDef;
import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.init.util.EntityUtil;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.EnumHand;

import static net.minecraft.entity.SharedMonsterAttributes.ATTACK_SPEED;

//Close in, and strafe while cool down
public class EntityStrafeMelee<T extends EntityLiving> extends EntityAIBase {
    private final T entity;
    private final double moveSpeedAmp;
    private int attackCooldown;
    private final float maxAttackDistanceSq;
    private int attackTime = -1;
    private int seeTime;
    private boolean strafingClockwise;
    private boolean strafingBackwards;
    private int strafingTime = -1;
    float targetLostThreshold = 3f;

    float aimingCooldownTimeMin = 0.3f;
    float aimingCooldownTimeDelta = 0.7f;

    int curAimingCooldownThreshold = 0;
    private double maxReach;

    void randomAimingCooldown()
    {
        curAimingCooldownThreshold = (int) (CommonDef.TICK_PER_SECOND *( aimingCooldownTimeMin + this.entity.getRNG().nextFloat() * aimingCooldownTimeDelta));
    }

    public EntityStrafeMelee(T self, double moveSpeedAmp, int attackCd, float maxAttackDistance)
    {
        this.entity = self;
        this.moveSpeedAmp = moveSpeedAmp;
        this.attackCooldown = attackCd;
        this.maxAttackDistanceSq = maxAttackDistance * maxAttackDistance;
        this.setMutexBits(3);
    }

    //This is the time between two attacking-aiming sequence
    public void setAttackCooldown(int attackCooldown)
    {
        this.attackCooldown = attackCooldown;
    }

    //aiming can be interruppted by sight lost
    public void setAimingCooldown(float minVal, float range)
    {
        aimingCooldownTimeMin = minVal;
        aimingCooldownTimeDelta = range;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        return this.entity.getAttackTarget() != null && !this.isRangedWeaponInMainhand();
    }

    protected boolean isRangedWeaponInMainhand()
    {
        return CommonFunctions.isItemRangedWeapon(this.entity.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND));
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting()
    {
        return (this.shouldExecute() || !this.entity.getNavigator().noPath()) && !this.isRangedWeaponInMainhand() && this.entity.getAttackTarget() != this.entity;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        super.startExecuting();
        this.entity.swingArm(EnumHand.MAIN_HAND);
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask()
    {
        super.resetTask();
        this.seeTime = 0;
        this.attackTime = (int) (this.attackCooldown * getAtkSpeed());
        this.entity.resetActiveHand();
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask()
    {
        boolean isReady = attackTime <= 0;


        EntityLivingBase target = this.entity.getAttackTarget();

        if (target != null)
        {
            double sqDistToTarget = this.entity.getDistanceSq(target.posX, target.getEntityBoundingBox().minY, target.posZ);
            boolean inCirclingRange = sqDistToTarget <= (double) this.maxAttackDistanceSq;

            boolean canSee = this.entity.getEntitySenses().canSee(target);
            maxReach = EntityUtil.getAttr(entity, (RegisterAttr.AI_REACH)) + target.width + entity.width;
            boolean inReach = sqDistToTarget <= maxReach * maxReach;
            boolean seeTimePositive = this.seeTime > 0;

            if (canSee != seeTimePositive)
            {
                this.seeTime = 0;
            }

            if (canSee)
            {
                ++this.seeTime;
            }
            else
            {
                --this.seeTime;
            }

            if (inCirclingRange && this.seeTime >= CommonDef.TICK_PER_SECOND
                    && !isReady)
            {
                this.entity.getNavigator().clearPath();
                ++this.strafingTime;
            }
            else
            {
                this.entity.getNavigator().tryMoveToEntityLiving(target, this.moveSpeedAmp);
                this.strafingTime = -1;
            }

            if (this.strafingTime >= CommonDef.TICK_PER_SECOND)
            {
                if ((double)this.entity.getRNG().nextFloat() < 0.3D)
                {
                    this.strafingClockwise = !this.strafingClockwise;
                }

                if ((double)this.entity.getRNG().nextFloat() < 0.3D)
                {
                    this.strafingBackwards = !this.strafingBackwards;
                }

                this.strafingTime = 0;
            }

            if (this.strafingTime > -1)
            {
                if (sqDistToTarget > (double)(this.maxAttackDistanceSq * 0.75F))
                {
                    this.strafingBackwards = false;
                }
                else if (sqDistToTarget < (double)(this.maxAttackDistanceSq * 0.25F))
                {
                    this.strafingBackwards = true;
                }

                this.entity.getMoveHelper().strafe(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
                this.entity.faceEntity(target, 30.0F, 30.0F);
            }
            else
            {
                this.entity.getLookHelper().setLookPositionWithEntity(target, 30.0F, 30.0F);
            }

            if (--this.attackTime <= 0 && this.seeTime >= -targetLostThreshold * CommonDef.TICK_PER_SECOND)
            {
                if (!canSee && this.seeTime < -targetLostThreshold * CommonDef.TICK_PER_SECOND)
                {
                    this.entity.resetActiveHand();
                }
                else if (canSee)
                {
                    this.entity.resetActiveHand();
                    if (isReady && inReach)
                    {
                        this.entity.attackEntityAsMob(target);
                        this.attackTime = (int) (this.attackCooldown * getAtkSpeed());
                    }
                    else {
                        //forced march
                        this.entity.getNavigator().tryMoveToEntityLiving(target, this.moveSpeedAmp);
                        this.strafingTime = -1;
                    }
                }
            }
            else if (--this.attackTime <= 0 && this.seeTime >= -targetLostThreshold * CommonDef.TICK_PER_SECOND)
            {
                if (CommonFunctions.isRangedWeaponItem(entity.getHeldItem(EnumHand.MAIN_HAND)))
                {
                    //note this won't work on swords.
                    entity.setActiveHand(EnumHand.MAIN_HAND);
                }
                else {
                    entity.swingArm(EnumHand.MAIN_HAND);
                }
            }
        }
    }

    private float getAtkSpeed() {
        IAttributeInstance attribute = entity.getEntityAttribute(ATTACK_SPEED);
        float atkSpeed = ((attribute == null) || (attribute.getAttributeValue() == 0)) ? 1 : (float) (attribute.getBaseValue() / attribute.getAttributeValue());
        return atkSpeed;
    }
}
