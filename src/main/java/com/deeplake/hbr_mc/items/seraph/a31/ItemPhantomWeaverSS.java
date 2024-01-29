package com.deeplake.hbr_mc.items.seraph.a31;

import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.init.RegisterEffects;
import com.deeplake.hbr_mc.init.util.CombatUtil;
import com.deeplake.hbr_mc.init.util.CommonDef;
import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.init.util.EntityUtil;
import com.deeplake.hbr_mc.items.seraph.EnumSeraphRarity;
import com.deeplake.hbr_mc.items.seraph.EnumSeraphType;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphBase;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphCannonBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.List;

//Kunimi Tama
//気合一閃エンジェルセイラー
public class ItemPhantomWeaverSS extends ItemSeraphBase {

    public ItemPhantomWeaverSS(String name) {
        super(name, EnumSeraphType.SWORD,EnumSeraphRarity.SS);
    }

    @Override
    public boolean castSkillEnemy(ItemStack stack, World worldIn, EntityPlayer player, EntityLivingBase target) {
        castSkillNonSneak(stack, worldIn, player);
        return true;
    }

    @Override
    public void castSkillNonSneak(ItemStack stack, World worldIn, EntityPlayer caster) {
        if (!worldIn.isRemote) {
            calcAtkBuffSkill(caster);
            ModConfig.SkillConf skill = ModConfig.COMBAT.PHANTOM_WEAVER_7SP_AOE;
            int hitCount = 3;
            for (int i = 0; i < hitCount; i++) {
                List<EntityLiving> targets = CombatUtil.areaAttack(worldIn,caster,
                        ModConfig.COMBAT.AOE_DISTANCE,
                        ModConfig.COMBAT.AOE_RADIUS,
                        CombatUtil.EnumAttrType.STANDARD,
                        skill.MIN_POTENCY/hitCount,
                        skill.CAP,
                        1f);
            }

            postCastSkill(caster, worldIn, skill, SoundEvents.BLOCK_ANVIL_USE);

            float pwr = (float) EntityUtil.getAttr(caster, RegisterAttr.LUC);

            float ratio = pwr/skill.CAP;
            ratio = CommonFunctions.clamp(ratio,0,1);
            int sp = skill.SP;
            float chance = (float) (0.3 + 0.3 * ratio);
            if (caster.getRNG().nextFloat() < chance)
            {
                sp-=3;
            }
            setCoolDown(caster, CommonDef.TICK_PER_SECOND * sp);
        }
    }

    @Override
    public void castSkillSneak(ItemStack stack, World worldIn, EntityPlayer caster) {
        //revive
        if (!worldIn.isRemote) {
            if (!canUseSkill(stack, SLOT_ULTI))
            {
                return;
            }
            float minHeal = ModConfig.COMBAT.PHANTOM_WEAVER_13SP_REVIVAL.MIN_POTENCY;
            float cap = ModConfig.COMBAT.PHANTOM_WEAVER_13SP_REVIVAL.CAP;

            CombatUtil.areaRevive(worldIn, caster);
            CombatUtil.areaHeal(worldIn, caster, minHeal, cap);
            worldIn.playSound(null, caster.getPosition(), SoundEvents.ENTITY_GENERIC_DRINK,SoundCategory.PLAYERS, 1f,1.5f);
            setCoolDown(caster, CommonDef.TICK_PER_SECOND * ModConfig.COMBAT.PHANTOM_WEAVER_13SP_REVIVAL.SP);

            skillUseMark(stack, SLOT_ULTI);
        }
    }
    
    @Override
    public int getSkillLimit(ItemStack stack, int slot) {
        switch (slot)
        {
            case SLOT_ULTI:
                ModConfig.SkillConf conf = ModConfig.COMBAT.RAPID_FIRE_11SP_AOE;
                return conf.uses + CommonFunctions.clamp( getSkillLevel(stack, slot) - 1,0,conf.usesGrowth);
            default:
                return -1;
        }
    }
}
