package com.deeplake.hbr_mc.items.seraph.a31;

import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.util.CombatUtil;
import com.deeplake.hbr_mc.init.util.CommonDef;
import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.items.seraph.EnumSeraphRarity;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphCannonBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

//Yuki Izumi
//終いのSpitfire
public class ItemRapidFireSS extends ItemSeraphCannonBase {
    public ItemRapidFireSS(String name) {
        super(name);
        setSeraphRarity(EnumSeraphRarity.SS);
    }

    @Override
    public int getMaxSkillSlot(ItemStack stack) {
        return 2;
    }

    @Override
    public void castSkillNonSneak(ItemStack stack, World worldIn, EntityPlayer caster) {
        if (!worldIn.isRemote) {
            if (!canUseSkill(stack, 0))
            {
                return;
            }

            worldIn.playSound(null, caster.posX, caster.posY, caster.posZ, SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

            if (!worldIn.isRemote) {
                int volley = 3;
                int spread = 10;
                float perAngle = 5f;
                float minPotency = ModConfig.COMBAT.RAPID_FIRE_4SP_AOE.MIN_POTENCY / volley;
                float cap = ModConfig.COMBAT.RAPID_FIRE_4SP_AOE.CAP;
                CombatUtil.EnumAttrType attackType = CombatUtil.EnumAttrType.DEX_FOCUS;
                spreadAttack(worldIn, caster, volley, spread, perAngle, minPotency, cap, attackType);
            }

            caster.swingArm(caster.getActiveHand());
            caster.addStat(StatList.getObjectUseStats(this));
            worldIn.playSound(null, caster.getPosition(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 1f, 1.5f);
            setCoolDown(caster, CommonDef.TICK_PER_SECOND * ModConfig.COMBAT.RAPID_FIRE_4SP_AOE.SP);
        }
    }

    @Override
    public void castSkillSneak(ItemStack stack, World worldIn, EntityPlayer caster) {
        //AoE: Ryusei
        if (!worldIn.isRemote) {
            if (!canUseSkill(stack, 1))
            {
                return;
            }

            EntityPlayer playerIn = caster;

            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

            if (!worldIn.isRemote) {
                int volley = 3;
                int spread = 10;
                float perAngle = 5f;
                float minPotency = ModConfig.COMBAT.RAPID_FIRE_4SP_AOE.MIN_POTENCY / volley;
                float cap = ModConfig.COMBAT.RAPID_FIRE_4SP_AOE.CAP;
                CombatUtil.EnumAttrType attackType = CombatUtil.EnumAttrType.DEX_FOCUS;
                spreadAttack(worldIn, playerIn, volley, spread, perAngle, minPotency, cap, attackType);
            }

            playerIn.swingArm(playerIn.getActiveHand());
            playerIn.addStat(StatList.getObjectUseStats(this));
            worldIn.playSound(null, caster.getPosition(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 1f, 1.5f);
            setCoolDown(caster, CommonDef.TICK_PER_SECOND * ModConfig.COMBAT.RAPID_FIRE_4SP_AOE.SP);
        }
    }

    @Override
    public int getSkillLimit(ItemStack stack, int slot) {
        switch (slot)
        {
            case 1:
                ModConfig.SkillConf conf = ModConfig.COMBAT.RAPID_FIRE_11SP_AOE;
                return conf.uses + CommonFunctions.clamp( getSkillLevel(stack, slot) - 1,0,conf.usesGrowth);
            default:
                return -1;
        }
    }
}
