package com.deeplake.hbr_mc;

import com.deeplake.hbr_mc.init.*;
import com.deeplake.hbr_mc.recipes.FurnaceRecipes;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION)
public class Main
{
    public static final String MODID = "hbr_mc";
    public static final String NAME = "Heaven Burns Red";
    public static final String VERSION = "1.1";

    private static Logger logger;
    @Mod.Instance
    public static Main instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        RegisterEntities.registerEntities();
        RegisterWorldGen.registerWorldGen();
    }

    @EventHandler
    public static void serverInit(FMLServerStartingEvent event) {
        RegisterHandler.serverRegistries(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        FurnaceRecipes.registerFurnaceRecipes();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        // Moved Spawning registry to last since forge doesn't auto-generate sub
        // "M' biomes until late
        if (ModConfig.SPAWN_CONF.SPAWN) {
            RegisterSpawn.registerSpawnList();
        }


    }

    public static void LogWarning(String str, Object...args)
    {
        logger.warn(String.format(str, args));
    }

    public static void Log(String str, Object...args)
    {
        if (ModConfig.CONFIG.LOG_ON)
        {
            logger.info(String.format(str, args));
        }
    }
}
