package com.deeplake.hbr_mc.events.advancements;

import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.util.AdvancementUtil;
import com.deeplake.hbr_mc.init.util.RegisterAdvancement;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@Mod.EventBusSubscriber(modid = "hbr_mc")
public class EventsAdvancement {
    @SubscribeEvent
    public static void onHurt(LivingHurtEvent event)
    {
        //if the attacker is a player holding a ItemSeraphBase
        //give the attacked player the advancement
        Entity trueSource = event.getSource().getTrueSource();
        Entity target = event.getEntity();
        if (trueSource instanceof EntityPlayer)
        {
            Item item = ((EntityPlayer)trueSource).getHeldItemMainhand().getItem();
            if (item instanceof ItemSeraphBase && target instanceof EntityPlayer)
            {
                AdvancementUtil.giveAdvancement((EntityPlayer)target, RegisterAdvancement.KULU_BAKA);
            }
        }
    }

    @SubscribeEvent
    public static void onDie(LivingDeathEvent event)
    {
        Entity trueSource = event.getSource().getTrueSource();
        Entity target = event.getEntity();
        if (trueSource instanceof EntityPlayer)
        {
            Item item = ((EntityPlayer)trueSource).getHeldItemMainhand().getItem();
            if (item instanceof ItemSeraphBase && target instanceof EntityPlayer)
            {
                if (ModConfig.CONFIG.ALLOW_SPOILERS)
                {
                    AdvancementUtil.giveAdvancement((EntityPlayer)target, RegisterAdvancement.KULA);
                }
            }
        }
    }

    @SubscribeEvent
    static void onLogin(PlayerEvent.PlayerLoggedInEvent event)
    {
        AdvancementUtil.giveAdvancement(event.player, RegisterAdvancement.ROOT_M);
    }
}
