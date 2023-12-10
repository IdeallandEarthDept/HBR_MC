package com.deeplake.hbr_mc.init;

import com.deeplake.hbr_mc.Main;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Main.MODID, category = "")
public class ModConfig {
    @Mod.EventBusSubscriber(modid = Main.MODID)
    private static class EventHandler {

        private EventHandler() {
        }

        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(Main.MODID)) {
                ConfigManager.sync(Main.MODID, Config.Type.INSTANCE);
            }
        }
    }

    public static final GeneralConf CONFIG = new GeneralConf();

    public static class GeneralConf {
        public boolean LOG_ON = false;

        public boolean BAN_HEAL = true;
    }

    public static final GUIConf GUI_CONF = new GUIConf();

    public static class GUIConf {
        @Config.Comment("The Y position of the GUI")
        public boolean RENDER_DP = true;

        @Config.Comment("The Y position of the GUI")
        public int GUI_Y = 0;

        public float MAX_RENDER_HUD_DISTANCE = 64.0f;
        public float RENDER_HUD_SIZE = 5f;
    }
}
