package com.deeplake.hbr_mc.designs.danmaku;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.designs.danmaku.basic.LiveLoginEvent;
import com.deeplake.hbr_mc.entities.npc.EntityCleverNPCForHBR;
import com.deeplake.hbr_mc.init.ModConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.EntitySelectors;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class EventsOnLogin {

    @SubscribeEvent
    public static void onLogin(LiveLoginEvent event) {
        PlayerList list = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList();
        for (EntityPlayer player : list.getPlayers()) {
            World world = player.world;
            if (!world.isRemote && DanmakuEventUtil.checkEnchantmentReq(player)) {
                DanmakuEventUtil.DanmakuUserData data = event.getDanmakuUserData();
                
                //handle summon protection, guards are unaffected
                if (data.level == 0)
                {
                    if (ModConfig.LIVE_CONF.AUTO_SUMMON_LV > data.personLevel){
                        Main.LogRaw("Auto Summon Level Protection: " + data.personLevel + " < " + ModConfig.LIVE_CONF.AUTO_SUMMON_LV);
                        return;
                    }
                    List entities = world.getEntities(EntityCleverNPCForHBR.class, EntitySelectors.IS_ALIVE);
                    if (entities.size() >= ModConfig.LIVE_CONF.AUTO_SUMMON_MAX){
                        Main.LogRaw("Auto Summon Level Protection: " + entities.size() + " >= " + ModConfig.LIVE_CONF.AUTO_SUMMON_MAX);
                        return;
                    }
                }

                DanmakuEventUtil.refreshOrSummon(player, data);
            }
        }
    }

}
