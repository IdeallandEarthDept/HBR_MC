package com.deeplake.hbr_mc.entities.npc;

import com.deeplake.hbr_mc.entities.cancer.EntityCancer;
import com.deeplake.hbr_mc.init.RegisterAttr;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityNpcRanged extends EntityNPC{
    public EntityNpcRanged(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(6, new EntityAIAvoidEntity(this, EntityPlayer.class, 12.0F, 0.5D, 2D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 0.3D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));

        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {EntityNPC.class}));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityCancer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        //Level 133
        setDPMax(4000);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(134.5f);
        setHealth(getMaxHealth());
        this.getEntityAttribute(RegisterAttr.STR).setBaseValue(459);
        this.getEntityAttribute(RegisterAttr.DEX).setBaseValue(328);
        this.getEntityAttribute(RegisterAttr.END).setBaseValue(375);
        this.getEntityAttribute(RegisterAttr.MEN).setBaseValue(357);
        this.getEntityAttribute(RegisterAttr.INT).setBaseValue(346);
        this.getEntityAttribute(RegisterAttr.LUC).setBaseValue(328);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(393.5f);
        return super.onInitialSpawn(difficulty, livingdata);
    }
}
