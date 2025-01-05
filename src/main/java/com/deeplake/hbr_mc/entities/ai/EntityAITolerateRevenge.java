package com.deeplake.hbr_mc.entities.ai;

import com.deeplake.hbr_mc.entities.npc.EntityNPC;
import com.deeplake.hbr_mc.init.util.EntityUtil;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.util.math.AxisAlignedBB;

import javax.annotation.Nullable;
import java.util.List;

public class EntityAITolerateRevenge extends EntityAITarget
{
    private final boolean entityCallsForHelp;
    /** Store the previous revengeTimer value */
    private int revengeTimerOld;
    private final Class<?>[] excludedReinforcementTypes;
    public EntityAITolerateRevenge(EntityNPC creatureIn, boolean entityCallsForHelpIn, Class<?>... excludedReinforcementTypes)
    {
        super(creatureIn, true);
        this.entityCallsForHelp = entityCallsForHelpIn;
        this.excludedReinforcementTypes = excludedReinforcementTypes;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        int i = this.taskOwner.getRevengeTimer();
        EntityLivingBase entitylivingbase = this.taskOwner.getRevengeTarget();
        return i != this.revengeTimerOld && entitylivingbase != null && this.isSuitableTarget(entitylivingbase, false);
    }

    @Override
    protected boolean isSuitableTarget(@Nullable EntityLivingBase target, boolean includeInvincibles) {
        if (target == null)
        {
            return false;
        }
        EntityNPC taskOwner1 = (EntityNPC) (this.taskOwner);
        List<EntityNPC> list = EntityUtil.getEntitiesWithinAABB(this.taskOwner.world, EntityNPC.class, new AxisAlignedBB(target.getPosition()).grow(10, 10, 10), EntityUtil.ALL_ALIVE);
        if (taskOwner1.isFriendlyTo(target))
        {
            if (taskOwner1.getAnger() > 500*(1+list.size()))
            {
                return true;
            }
            return false;
        }

        return super.isSuitableTarget(target, includeInvincibles);
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.taskOwner.setAttackTarget(this.taskOwner.getRevengeTarget());
        this.target = this.taskOwner.getAttackTarget();
        this.revengeTimerOld = this.taskOwner.getRevengeTimer();
        this.unseenMemoryTicks = 300;

        if (this.entityCallsForHelp)
        {
            this.alertOthers();
        }

        super.startExecuting();
    }

    protected void alertOthers()
    {
        double d0 = this.getTargetDistance();

        for (EntityCreature entitycreature : this.taskOwner.world.getEntitiesWithinAABB(this.taskOwner.getClass(), (new AxisAlignedBB(this.taskOwner.posX, this.taskOwner.posY, this.taskOwner.posZ, this.taskOwner.posX + 1.0D, this.taskOwner.posY + 1.0D, this.taskOwner.posZ + 1.0D)).grow(d0, 10.0D, d0)))
        {
            if (this.taskOwner != entitycreature && entitycreature.getAttackTarget() == null && (!(this.taskOwner instanceof EntityTameable) || ((EntityTameable)this.taskOwner).getOwner() == ((EntityTameable)entitycreature).getOwner()) && !entitycreature.isOnSameTeam(this.taskOwner.getRevengeTarget()))
            {
                boolean flag = false;

                for (Class<?> oclass : this.excludedReinforcementTypes)
                {
                    if (entitycreature.getClass() == oclass)
                    {
                        flag = true;
                        break;
                    }
                }

                if (!flag)
                {
                    this.setEntityAttackTarget(entitycreature, this.taskOwner.getRevengeTarget());
                }
            }
        }
    }

    protected void setEntityAttackTarget(EntityCreature creatureIn, EntityLivingBase entityLivingBaseIn)
    {
        creatureIn.setAttackTarget(entityLivingBaseIn);
    }
}