package com.deeplake.hbr_mc.items.seraph.a31;

import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.RegisterEffects;
import com.deeplake.hbr_mc.init.util.CombatUtil;
import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.init.util.EntityUtil;
import com.deeplake.hbr_mc.items.seraph.EnumSeraphRarity;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphCannonBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.List;

//Yuki Izumi
//終いのSpitfire
public class ItemRapidFireSS extends ItemSeraphCannonBase {

    public static final int SLOT_ULTI = 1;

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
            afterCastSkill(caster, worldIn, ModConfig.COMBAT.RAPID_FIRE_7SP_ST, SoundEvents.ENTITY_BLAZE_SHOOT);
        }
    }

    @Override
    public void castSkillSneak(ItemStack stack, World worldIn, EntityPlayer caster) {
        //AoE: Ryusei
        if (!worldIn.isRemote) {
            if (!canUseSkill(stack, SLOT_ULTI))
            {
                return;
            }

            worldIn.playSound(null, caster.posX, caster.posY, caster.posZ, SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

            ModConfig.SkillConf skill = ModConfig.COMBAT.RAPID_FIRE_11SP_AOE;
            List<EntityLiving> targets = CombatUtil.areaAttack(worldIn,caster,
                    ModConfig.COMBAT.AOE_DISTANCE,
                    ModConfig.COMBAT.AOE_RADIUS,
                    CombatUtil.EnumAttrType.DEX_FOCUS,
                    skill.MIN_POTENCY,
                    skill.CAP,
                    ModConfig.COMBAT.BONUS_DAMAGE_RATE);

            for (EntityLiving target: targets
                 ) {
                worldIn.addWeatherEffect(new EntityLightningBolt(worldIn, target.posX, target.posY, target.posZ, true));
            }
            EntityUtil.ApplyBuff(caster, RegisterEffects.SELF_RECOIL, 0, 2);
            skillUseMark(stack, SLOT_ULTI);
            afterCastSkill(caster, worldIn, ModConfig.COMBAT.RAPID_FIRE_11SP_AOE, SoundEvents.ENTITY_GENERIC_EXPLODE);
        }
    }



    @Override
    public int getSkillLimit(ItemStack stack, int slot) {
        switch (slot)
        {
            case SLOT_ULTI:
                ModConfig.SkillConf conf = ModConfig.COMBAT.RAPID_FIRE_11SP_AOE;
                return conf.uses + CommonFunctions.clamp( getSkillLevel(stack, slot) - SLOT_ULTI,0,conf.usesGrowth);
            default:
                return -SLOT_ULTI;
        }
    }
}
