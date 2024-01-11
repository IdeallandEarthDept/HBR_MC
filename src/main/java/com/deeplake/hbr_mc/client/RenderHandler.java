package com.deeplake.hbr_mc.client;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.client.renderer.RenderBullet;
import com.deeplake.hbr_mc.client.renderer.cancer.RenderDoll;
import com.deeplake.hbr_mc.client.renderer.cancer.RenderMarionette;
import com.deeplake.hbr_mc.client.renderer.cancer.RenderSmallHopper;
import com.deeplake.hbr_mc.entities.cancer.EntityDoll;
import com.deeplake.hbr_mc.entities.cancer.EntityMarionette;
import com.deeplake.hbr_mc.entities.cancer.EntitySmallHopper;
import com.deeplake.hbr_mc.entities.projectiles.EntityIdlProjectile;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber
public class RenderHandler {

    public static void registerEntityRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntityMarionette.class, RenderMarionette::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDoll.class, RenderDoll::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySmallHopper.class, RenderSmallHopper::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityIdlProjectile.class, renderManager -> new RenderBullet<>(renderManager, new ResourceLocation(Main.MODID,
                "textures/entity/projectiles/bullet_norm.png")));

    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event)
    {
        RenderHandler.registerEntityRenders();
    }
}
