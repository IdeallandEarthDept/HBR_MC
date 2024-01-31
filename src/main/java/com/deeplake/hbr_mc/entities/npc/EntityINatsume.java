package com.deeplake.hbr_mc.entities.npc;

import com.deeplake.hbr_mc.entities.cancer.EntityCancer;
import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.init.util.EntityUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntitySelectors;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class EntityINatsume extends EntityNPC{
    public EntityINatsume(World worldIn) {
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

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
        if (CommonFunctions.isSecondTick(world))
        {
            List<EntityPlayer> players = EntityUtil.getEntitiesWithinAABB(world, EntityPlayer.class, getPositionVector(), 16f, EntitySelectors.NOT_SPECTATING);
            for (EntityPlayer player :
                    players) {
                EntityUtil.ApplyBuff(player, MobEffects.MINING_FATIGUE, 0,3);
            }
        }
    }

    @Override
    public void onFirstTickInLife() {
        super.onFirstTickInLife();
        ItemStack stack = new ItemStack(Items.IRON_SWORD);
        stack.addEnchantment(Enchantments.SWEEPING,3);
        stack.addEnchantment(Enchantments.UNBREAKING,10);
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND,stack);
    }
}
