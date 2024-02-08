package com.deeplake.hbr_mc.designs.chara;

import com.deeplake.hbr_mc.designs.chara.f31.DesignINatsume;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber
public class EventsSpamProtect {
    @SubscribeEvent
    public static void onTick(TickEvent.ServerTickEvent event)
    {
        DesignINatsume.NO_SPAM = false;
    }
}
