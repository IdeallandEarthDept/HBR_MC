package com.deeplake.hbr_mc.entities.npc;

import com.deeplake.hbr_mc.entities.ai.EntityAIStrafeRangedAttack;
import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.items.ItemWIPRanged;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphCannonBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityNpcRanged extends EntityNPC implements IRangedAttackMob {
    boolean useFixedArrow = true;//damage unrelated to speed.
    public EntityNpcRanged(World worldIn) {
        super(worldIn);
    }

    protected EntityAIStrafeRangedAttack getStrafeRangedAttack() {
        return new EntityAIStrafeRangedAttack(this, 1.0D, 18, 12f).setVolley(3,3,1);
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
        setCombatTask();
        return super.onInitialSpawn(difficulty, livingdata);
    }

    public void attackEntityWithRangedAttackSeraph(EntityLivingBase target, float distanceFactor) {
        int volley = 1;
        ItemSeraphCannonBase.volleyAttack(world, this, volley,
                ModConfig.COMBAT.NORMAL_ATK_POWER, ModConfig.COMBAT.NORMAL_ATK_CAP);

        this.playSound(SoundEvents.ENTITY_BLAZE_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        Item item = getHeldItemMainhand().getItem();
        if (item instanceof ItemSeraphCannonBase || item instanceof ItemWIPRanged) {
            attackEntityWithRangedAttackSeraph(target, distanceFactor);
        } else {
            attackEntityWithAutoArrow(target, distanceFactor);
        }
    }
}
