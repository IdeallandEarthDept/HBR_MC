package com.deeplake.hbr_mc.items.seraph.a31;

import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.RegisterEffects;
import com.deeplake.hbr_mc.init.util.CombatUtil;
import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.init.util.EntityUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class ItemGlitterShadowSS extends ItemGlitterShadowBase {
    public ItemGlitterShadowSS(String name) {
        super(name);
    }
    @Override
    public int getMaxSkillSlot(ItemStack stack) {
        return 2;
    }

    @Override
    public boolean castSkillEnemy(ItemStack stack, World worldIn, EntityPlayer player, EntityLivingBase target) {
        //
        if (!canUseSkills(player))
        {
            return false;
        }

        if (!worldIn.isRemote)
        {
            if (player.isSneaking())
            {
                float minPotency = ModConfig.COMBAT.GLITTER_SHADOW_SS_ULTI.MIN_POTENCY;
                float cap = ModConfig.COMBAT.GLITTER_SHADOW_SS_ULTI.CAP;
                calcAtkBuffSkill(player);
                CombatUtil.attackGroup(player, target, minPotency,
                        new float[]{0.1f,0.1f,0.1f,0.1f,0.1f,
                                0.1f,0.1f,0.1f,0.1f,0.1f,}, cap);
                postCastSkill(player,worldIn,ModConfig.COMBAT.GLITTER_SHADOW_SS_ULTI, SoundEvents.BLOCK_GLASS_BREAK);
                EntityUtil.ApplyBuff(player, RegisterEffects.SELF_RECOIL, 0, 2);
                skillUseMark(stack, SLOT_ULTI);
            }
            else
            {
                float minPotency = ModConfig.COMBAT.GLITTER_SHADOW_SS.MIN_POTENCY;
                float cap = ModConfig.COMBAT.GLITTER_SHADOW_SS.CAP;
                calcAtkBuffSkill(player);
                CombatUtil.areaAttackGroup(new float[]{0.33f,0.33f,0.33f}, worldIn, player,
                        ModConfig.COMBAT.AOE_DISTANCE, ModConfig.COMBAT.AOE_RADIUS,
                        CombatUtil.EnumAttrType.STANDARD, minPotency,
                        cap, 0f);
                postCastSkill(player,worldIn,ModConfig.COMBAT.GLITTER_SHADOW_SS, SoundEvents.BLOCK_ANVIL_USE);
            }
        }
        else {
            worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, player.posX,player.posY,player.posZ,0,0,0);
        }

        return true;
    }

    @Override
    public int getSkillLimit(ItemStack stack, int slot) {
        switch (slot)
        {
            case SLOT_ULTI:
                ModConfig.SkillConf conf = ModConfig.COMBAT.GLITTER_SHADOW_SS_ULTI;
                return conf.uses + CommonFunctions.clamp( getSkillLevel(stack, slot) - 1,0,conf.usesGrowth);
            default:
                return -1;
        }
    }
}
