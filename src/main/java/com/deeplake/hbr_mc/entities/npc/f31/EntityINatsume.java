package com.deeplake.hbr_mc.entities.npc.f31;

import com.deeplake.hbr_mc.entities.npc.EntityNpcMelee;
import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.init.RegisterItem;
import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.init.util.EntityUtil;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
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

public class EntityINatsume extends EntityNpcMelee {
    public EntityINatsume(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(6, new EntityAIAvoidEntity(this, EntityPlayer.class, 12.0F, 0.5D, 2D));
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
        setItemStackToSlot(EntityEquipmentSlot.OFFHAND,stack);

        stack = new ItemStack(RegisterItem.REN);
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND,stack);
    }
}
