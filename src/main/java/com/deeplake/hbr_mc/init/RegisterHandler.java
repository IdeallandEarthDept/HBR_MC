package com.deeplake.hbr_mc.init;

import com.deeplake.hbr_mc.client.RenderHandler;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class RegisterHandler {
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event)
    {
        RenderHandler.registerEntityRenders();
    }
}