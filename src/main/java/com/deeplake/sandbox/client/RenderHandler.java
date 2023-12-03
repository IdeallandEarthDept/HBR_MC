package com.deeplake.sandbox.client;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber
public class RenderHandler {

    public static void registerEntityRenders() {
        //RenderingRegistry.registerEntityRenderingHandler(EntityMoroonUnitBase.class, renderManager -> new RenderHumanoid(renderManager, "moroon_humanoid"));
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event)
    {
        RenderHandler.registerEntityRenders();
    }
}
