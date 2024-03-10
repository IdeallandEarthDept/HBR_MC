package com.deeplake.hbr_mc;

import com.deeplake.hbr_mc.init.*;
import com.deeplake.hbr_mc.init.util.CommonDef;
import com.deeplake.hbr_mc.proxy.ProxyBase;
import com.deeplake.hbr_mc.recipes.FurnaceRecipes;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
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
    public static final String VERSION = "1.1.5";

    private static Logger logger;
    @Mod.Instance
    public static Main instance;

    @SidedProxy(clientSide = CommonDef.PROXY_CLIENT, serverSide = CommonDef.PROXY_SERVER)
    public static ProxyBase proxy;

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
        if (ModConfig.SPAWN_CONF.SPAWN) {
            RegisterSpawn.registerSpawnList();
        }
        RegisterDrop.initCrateList();

        proxy.registerLayers();
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
