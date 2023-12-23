package com.deeplake.hbr_mc;

import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.RegisterEntities;
import com.deeplake.hbr_mc.init.RegisterSpawn;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION)
public class Main
{
    public static final String MODID = "hbr_mc";
    public static final String NAME = "Heaven Burns Red";
    public static final String VERSION = "1.0";

    private static Logger logger;
    @Mod.Instance
    public static Main instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        RegisterEntities.registerEntities();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {

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
