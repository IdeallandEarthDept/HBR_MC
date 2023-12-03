package com.deeplake.sandbox.init;

import com.deeplake.sandbox.ExampleMod;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

@Config(modid = ExampleMod.MODID, category = "")
public class ModConfig {
    @Mod.EventBusSubscriber(modid = ExampleMod.MODID)
    private static class EventHandler {

        private EventHandler() {
        }

        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(ExampleMod.MODID)) {
                ConfigManager.sync(ExampleMod.MODID, Config.Type.INSTANCE);
            }
        }
    }

    public static final GeneralConf CONFIG = new GeneralConf();

    public static class GeneralConf {
        public boolean LOG_ON = false;

        public boolean BAN_HEAL = true;
    }
}
