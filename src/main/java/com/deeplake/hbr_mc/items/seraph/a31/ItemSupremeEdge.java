package com.deeplake.hbr_mc.items.seraph.a31;

import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.init.RegisterEffects;
import com.deeplake.hbr_mc.init.util.CombatUtil;
import com.deeplake.hbr_mc.init.util.CommonDef;
import com.deeplake.hbr_mc.init.util.EntityUtil;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphCannonBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.stats.StatList;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;


import java.util.ArrayList;
import java.util.List;

//Tsukasa Tojo
//Serious or Stupid
public class ItemSupremeEdge extends ItemSeraphCannonBase {
    public ItemSupremeEdge(String name) {
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
                float range = 16f;
                List<EntityPlayer> playerList = EntityUtil.getEntitiesWithinAABB(worldIn, EntityPlayer.class, playerIn.getPositionVector(), range, EntitySelectors.NOT_SPECTATING);

                float chance = ModConfig.COMBAT.SUPREME_EDGE_6SP_BUFF.MIN_POTENCY;
                float cap = ModConfig.COMBAT.SUPREME_EDGE_6SP_BUFF.CAP;
                chance += (1-chance) * Math.min(0, (EntityUtil.getAttr(playerIn, RegisterAttr.INT)/cap));

                for (EntityPlayer player :
                        playerList) {
                    if (player.getRNG().nextFloat() <= chance)
                    {
                        Potion buff = RegisterEffects.SKILL_ATK_UP;
                        int level = EntityUtil.getBuffLevelIDL(player, buff);
                        EntityUtil.ApplyBuff(player, buff, level, 3600);
                    }
                }
            }
            playerIn.swingArm(playerIn.getActiveHand());
            playerIn.addStat(StatList.getObjectUseStats(this));
            worldIn.playSound(null, caster.getPosition(), SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, 1f, 1.5f);
            setCoolDown(caster, CommonDef.TICK_PER_SECOND * ModConfig.COMBAT.SUPREME_EDGE_6SP_BUFF.SP);
        }
    }
}
