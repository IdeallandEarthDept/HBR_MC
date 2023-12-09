package com.deeplake.hbr_mc.entities.cancer;

import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityMarionette extends EntityCancer{
    public EntityMarionette(World worldIn) {
        super(worldIn);
        setSize(0.3f, 0.8f);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 0.3D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));

        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {EntityCancer.class}));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
    }
}
