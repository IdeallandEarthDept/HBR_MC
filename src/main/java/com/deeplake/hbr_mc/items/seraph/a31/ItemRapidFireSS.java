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
import net.minecraft.util.SoundEvent;
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
            volleyAttack(worldIn, caster, 3,
                    ModConfig.COMBAT.RAPID_FIRE_7SP_ST);
            castSkill(caster, worldIn, ModConfig.COMBAT.RAPID_FIRE_7SP_ST, SoundEvents.ENTITY_BLAZE_SHOOT);
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

            worldIn.playSound(null, caster.posX, caster.posY, caster.posZ, SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

            ModConfig.SkillConf skill = ModConfig.COMBAT.RAPID_FIRE_11SP_AOE;
            CombatUtil.areaAttack(worldIn,caster,
                    ModConfig.COMBAT.AOE_DISTANCE,
                    ModConfig.COMBAT.AOE_RADIUS,
                    CombatUtil.EnumAttrType.DEX_FOCUS,
                    skill.MIN_POTENCY,
                    skill.CAP,
                    ModConfig.COMBAT.BONUS_DAMAGE_RATE);

            castSkill(caster, worldIn, ModConfig.COMBAT.RAPID_FIRE_11SP_AOE, SoundEvents.ENTITY_GENERIC_EXPLODE);
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
