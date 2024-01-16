package com.deeplake.hbr_mc.items.seraph.a31;

import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.init.util.CombatUtil;
import com.deeplake.hbr_mc.init.util.CommonDef;
import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.items.seraph.EnumSeraphRarity;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

//Ruka Kayamori
public class ItemBraveBlueSS extends ItemSeraphBase {
    public ItemBraveBlueSS(String name) {
        super(name);
        setSeraphRarity(EnumSeraphRarity.SS);
    }

    public float getAttrValue(ItemStack stack, IAttribute attr)
    {
        if (attr == RegisterAttr.STR)
        {
            return super.getAttrValue(stack, attr) + 1;
        }
        return super.getAttrValue(stack, attr);
    }

    @Override
    public void castSkillSneak(ItemStack stack, World worldIn, EntityPlayer caster) {
        if (!worldIn.isRemote)
        {
            if (!canUseSkill(stack, HEAL_INDEX) || !canUseSkills(caster))
            {
                return;
            }

            float minHeal = ModConfig.COMBAT.BRAVE_BLUE_HEAL.MIN_POTENCY;
            float cap = ModConfig.COMBAT.BRAVE_BLUE_HEAL.CAP;

            CombatUtil.areaHeal(worldIn, caster, minHeal, cap);
            worldIn.playSound(null, caster.getPosition(), SoundEvents.ENTITY_GENERIC_DRINK,SoundCategory.PLAYERS, 1f,1.5f);
            setCoolDown(caster, CommonDef.TICK_PER_SECOND * ModConfig.COMBAT.BRAVE_BLUE_HEAL.SP);

            skillUseMark(stack, HEAL_INDEX);
        }
    }

    @Override
    public boolean castSkillFriend(ItemStack stack, World worldIn, EntityPlayer player, EntityLivingBase target) {
        castSkillSneak(stack, worldIn, player);
        return true;
    }

    final int ULTIMATE_INDEX = 1;
    final int HEAL_INDEX = 0;

    @Override
    public boolean castSkillEnemy(ItemStack stack, World worldIn, EntityPlayer player, EntityLivingBase target) {
        //original: 12SP, 138 cap, 1602-8010 potency
        if (!canUseSkill(stack, ULTIMATE_INDEX) || !canUseSkills(player))
        {
            return false;
        }

        if (!worldIn.isRemote)
        {
            float minPotency = ModConfig.COMBAT.BRAVE_BLUE_ULTI.MIN_POTENCY;
            float cap = ModConfig.COMBAT.BRAVE_BLUE_ULTI.CAP;
            float bonusRate = ModConfig.COMBAT.BONUS_DAMAGE_RATE;

            CombatUtil.HPAttackGroup(player, target, minPotency,new float[]{0.15f,0.05f,0.05f,0.05f,0.05f,0.05f,0.05f,0.5f}, cap, bonusRate);

            setCoolDown(player, CommonDef.TICK_PER_SECOND * ModConfig.COMBAT.BRAVE_BLUE_ULTI.SP);
            worldIn.playSound(null, player.getPosition(), SoundEvents.ENTITY_GENERIC_EXPLODE,SoundCategory.PLAYERS, 1f,1.5f);
            skillUseMark(stack, ULTIMATE_INDEX);
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
            case 0:
                return 5 + CommonFunctions.clamp( getSkillLevel(stack, slot) - 1,0,5);
            case  1:
                return 2 + CommonFunctions.clamp( getSkillLevel(stack, slot) - 1,0,2);
            default:
                throw new IllegalStateException("Unexpected value: " + slot);
        }
    }
}
