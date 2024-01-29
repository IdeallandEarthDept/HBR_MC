package com.deeplake.hbr_mc.items.seraph.a31;

import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.init.util.CombatUtil;
import com.deeplake.hbr_mc.init.util.CommonDef;
import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.items.seraph.EnumSeraphRarity;
import com.deeplake.hbr_mc.items.seraph.EnumSeraphType;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

//Kunimi Tama-Truth or Lies
public class ItemPhantomWeaver extends ItemSeraphBase {
    public ItemPhantomWeaver(String name) {
        super(name, EnumSeraphType.SWORD, EnumSeraphRarity.A);
    }
    final int HEAL_INDEX = 0;

    @Override
    public int getMaxSkillSlot(ItemStack stack) {
        return super.getMaxSkillSlot(stack);
    }

    @Override
    public void castSkillNonSneak(ItemStack stack, World worldIn, EntityPlayer caster) {
        if (!worldIn.isRemote)
        {
            if (!canUseSkill(stack, HEAL_INDEX) || !canUseSkills(caster))
            {
                return;
            }

            float minHeal = ModConfig.COMBAT.PHANTOM_WEAVER_HEAL_A.MIN_POTENCY;
            float cap = ModConfig.COMBAT.PHANTOM_WEAVER_HEAL_A.CAP;

            CombatUtil.areaHeal(worldIn, caster, minHeal, cap);
            worldIn.playSound(null, caster.getPosition(), SoundEvents.ENTITY_GENERIC_DRINK,SoundCategory.PLAYERS, 1f,1.5f);
            setCoolDown(caster, CommonDef.TICK_PER_SECOND * ModConfig.COMBAT.PHANTOM_WEAVER_HEAL_A.SP);

            skillUseMark(stack, HEAL_INDEX);
        }
    }

    @Override
    public boolean castSkillFriend(ItemStack stack, World worldIn, EntityPlayer player, EntityLivingBase target) {
        castSkillNonSneak(stack, worldIn, player);
        return true;
    }

    @Override
    public int getSkillLimit(ItemStack stack, int slot) {
        switch (slot)
        {
            case 0:
                ModConfig.SkillConf conf = ModConfig.COMBAT.PHANTOM_WEAVER_HEAL_A;
                return conf.uses + CommonFunctions.clamp( getSkillLevel(stack, slot) - 1,0,conf.usesGrowth);
            default:
                throw new IllegalStateException("Unexpected value: " + slot);
        }
    }
}
