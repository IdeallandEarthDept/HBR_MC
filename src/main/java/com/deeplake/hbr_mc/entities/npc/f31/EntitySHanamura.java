package com.deeplake.hbr_mc.entities.npc.f31;

import com.deeplake.hbr_mc.entities.npc.EntityNpcMelee;
import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntitySHanamura extends EntityNpcMelee {
    public EntitySHanamura(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(6, new EntityAITempt(this, 1.0D, Items.AIR, false));
        //this.tasks.addTask(6, new EntityAIFollowSomething(this, 1f,1f,16f));
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

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
    }

    @Override
    public void onFirstTickInLife() {
        super.onFirstTickInLife();
        ItemStack stack = new ItemStack(Blocks.RED_FLOWER);
        setItemStackToSlot(EntityEquipmentSlot.OFFHAND,stack);

        stack = new ItemStack(RegisterItem.FURY_STRINGER);
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND,stack);
    }
}
