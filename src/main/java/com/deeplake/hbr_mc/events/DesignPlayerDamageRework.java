package com.deeplake.hbr_mc.events;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.util.CombatUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class DesignPlayerDamageRework {

    @SubscribeEvent
    public static void tryReplacePlayerNormalAttack(LivingHurtEvent event)
    {
        if (CombatUtil.isIsHBRAttackProcess() || event.isCanceled())
        {
            return;
        }
        //first we check if it's player damage
        Entity trueSource = event.getSource().getTrueSource();
        if (trueSource instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) trueSource;
            event.setCanceled(true);

            CombatUtil.attackAsHBR(player, event.getEntityLiving(), ModConfig.COMBAT.NORMAL_ATK_CAP, ModConfig.COMBAT.NORMAL_ATK_POWER);

        }
    }
}
