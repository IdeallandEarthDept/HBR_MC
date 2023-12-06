package com.deeplake.hbr_mc.events;

import com.deeplake.hbr_mc.init.ModConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class DesignBanHeal {

    static boolean HEAL_ALLOWED = false;

    public static void setHealAllowed(boolean allowed)
    {
        HEAL_ALLOWED = allowed;
    }

    //if a player is to heal, stop it.
    @SubscribeEvent
    public static void onPlayerHeal(net.minecraftforge.event.entity.living.LivingHealEvent event)
    {
        if (event.getEntityLiving().world.isRemote)
        {
            return;
        }

        if (ModConfig.CONFIG.BAN_HEAL && event.getEntityLiving() instanceof net.minecraft.entity.player.EntityPlayer)
        {
            if (!HEAL_ALLOWED)
            {
                event.setCanceled(true);
            }
        }
    }
}
