package com.deeplake.hbr_mc.items.seraph.a31;

import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.init.RegisterEffects;
import com.deeplake.hbr_mc.init.util.CombatUtil;
import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.init.util.EntityUtil;
import com.deeplake.hbr_mc.items.seraph.EnumSeraphRarity;
import com.deeplake.hbr_mc.items.seraph.EnumSeraphType;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

import java.util.List;

//Ikki Burst Strike
public class ItemClavisSS extends ItemSeraphBase {
    public ItemClavisSS(String name) {
        super(name, EnumSeraphType.LARGE_SWORD,EnumSeraphRarity.SS);
    }

    @Override
    public int getMaxSkillSlot(ItemStack stack) {
        return 2;
    }

    @Override
    public void castSkillNonSneak(ItemStack stack, World worldIn, EntityPlayer player) {
        if (!canUseSkills(player))
        {
            return;
        }

        if (!worldIn.isRemote)
        {
            float minPotency = ModConfig.COMBAT.CLAVIS_SS_10SP_STUN.MIN_POTENCY;
            float cap = ModConfig.COMBAT.CLAVIS_SS_10SP_STUN.CAP;
            calcAtkBuffSkill(player);
            List<EntityLiving> livings = CombatUtil.areaAttack(worldIn, player, ModConfig.COMBAT.AOE_DISTANCE,ModConfig.COMBAT.AOE_RADIUS, CombatUtil.EnumAttrType.STANDARD, minPotency, cap, 0);

            for (EntityLiving living :
                    livings) {
                //apply debuff
                float chance = getDebuffChance(player, cap, 0.3f, 0.55f);
                if (player.getRNG().nextFloat() < chance)
                {
                    living.addPotionEffect(new PotionEffect(RegisterEffects.STUNNED,ModConfig.COMBAT.STUN_TICK_PER_TURN,0));
                }
            }

            postCastSkill(player, worldIn, ModConfig.COMBAT.CLAVIS_SS_10SP_STUN, SoundEvents.BLOCK_ANVIL_FALL);
        }
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
                if (!canUseSkill(stack, SLOT_ULTI))
                {
                    return false;
                }

                float minPotency = ModConfig.COMBAT.CLAVIS_SS_14SP_ULTI.MIN_POTENCY;
                float cap = ModConfig.COMBAT.CLAVIS_SS_14SP_ULTI.CAP;
                calcAtkBuffSkill(player);
                CombatUtil.generalAttack(CombatUtil.EnumAttrType.STANDARD, player, minPotency, cap, 0, target);

                //apply debuff
                float chance = getDebuffChance(player, cap, 0.5f, 0.5f);
                if (player.getRNG().nextFloat() < chance)
                {
                    target.addPotionEffect(new PotionEffect(RegisterEffects.STUNNED,ModConfig.COMBAT.STUN_TICK_PER_TURN,0));
                }
                skillUseMark(stack,SLOT_ULTI);
                postCastSkill(player, worldIn, ModConfig.COMBAT.CLAVIS_SS_14SP_ULTI, SoundEvents.BLOCK_ANVIL_FALL);
            }
            else {
                float minPotency = ModConfig.COMBAT.CLAVIS_SS_10SP_STUN.MIN_POTENCY;
                float cap = ModConfig.COMBAT.CLAVIS_SS_10SP_STUN.CAP;

                List<EntityLiving> livings = CombatUtil.areaAttack(worldIn, player, ModConfig.COMBAT.AOE_DISTANCE,ModConfig.COMBAT.AOE_RADIUS, CombatUtil.EnumAttrType.STANDARD, minPotency, cap, 0);

                for (EntityLiving living :
                        livings) {
                    //apply debuff
                    float chance = getDebuffChance(player, cap, 0.3f, 0.55f);
                    if (player.getRNG().nextFloat() < chance)
                    {
                        living.addPotionEffect(new PotionEffect(RegisterEffects.STUNNED,ModConfig.COMBAT.STUN_TICK_PER_TURN,0));
                    }
                }

                postCastSkill(player, worldIn, ModConfig.COMBAT.CLAVIS_SS_10SP_STUN, SoundEvents.BLOCK_ANVIL_FALL);
            }
        }
        else {
            worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, player.posX,player.posY,player.posZ,0,0,0);
        }

        return true;
    }

    private float getDebuffChance(EntityPlayer player, float cap, float x, float x1) {
        float pwr = (float) ((EntityUtil.getAttr(player, RegisterAttr.INT) + 2 * EntityUtil.getAttr(player, RegisterAttr.LUC))/3f);
        float enemyBorder = (float) EntityUtil.getAttr(player, RegisterAttr.MEN);

        float ratio = (pwr - enemyBorder)/ cap;
        ratio = CommonFunctions.clamp(ratio,0,1);

        float chance = x + x1 * ratio;
        return chance;
    }

    @Override
    public int getSkillLimit(ItemStack stack, int slot) {
        switch (slot)
        {
            case SLOT_ULTI:
                ModConfig.SkillConf conf = ModConfig.COMBAT.CLAVIS_SS_14SP_ULTI;
                return conf.uses + CommonFunctions.clamp( getSkillLevel(stack, slot) - 1,0,conf.usesGrowth);
            default:
                return -1;
        }
    }
}
