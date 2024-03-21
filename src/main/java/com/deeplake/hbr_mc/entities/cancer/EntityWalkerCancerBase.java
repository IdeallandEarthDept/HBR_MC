package com.deeplake.hbr_mc.entities.cancer;

import com.deeplake.hbr_mc.entities.npc.EntityNPC;
import com.deeplake.hbr_mc.init.util.EntityUtil;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityWalkerCancerBase extends EntityCancer{
    public EntityWalkerCancerBase(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 0.3D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));

        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {EntityCancer.class}));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 10, true, false, EntityUtil.CANCER_ARMOR_LESS_THAN_2));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityNPC.class, 10, true, false, EntityUtil.CANCER_ARMOR_LESS_THAN_2));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityVillager.class, 5, true, true, EntityUtil.CANCER_ARMOR_LESS_THAN_2));
    }
}
