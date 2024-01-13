package com.deeplake.hbr_mc.items.seraph.a31;

import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.util.CombatUtil;
import com.deeplake.hbr_mc.init.util.CommonDef;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphCannonBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

//Yuki Izumi
//Attack or March
public class ItemRapidFire extends ItemSeraphCannonBase {
    public ItemRapidFire(String name) {
        super(name);
    }

    @Override
    public int getMaxSkillSlot(ItemStack stack) {
        return 1;
    }

    @Override
    public void castSkillNonSneak(ItemStack stack, World worldIn, EntityPlayer player) {
        castSkillSneak(stack, worldIn, player);
    }

    @Override
    public void castSkillSneak(ItemStack stack, World worldIn, EntityPlayer caster) {
        if (!worldIn.isRemote) {
//            if (!canUseSkill(stack, 0))
//            {
//                return;
//            }

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
}
