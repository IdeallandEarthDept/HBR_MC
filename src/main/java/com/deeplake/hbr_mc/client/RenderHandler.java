package com.deeplake.hbr_mc.client;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.client.renderer.RenderBullet;
import com.deeplake.hbr_mc.client.renderer.RenderHumanoid;
import com.deeplake.hbr_mc.client.renderer.cancer.RenderDoll;
import com.deeplake.hbr_mc.client.renderer.cancer.RenderMarionette;
import com.deeplake.hbr_mc.client.renderer.cancer.RenderSmallHopper;
import com.deeplake.hbr_mc.entities.cancer.EntityDoll;
import com.deeplake.hbr_mc.entities.cancer.EntityMarionette;
import com.deeplake.hbr_mc.entities.cancer.EntitySmallHopper;
import com.deeplake.hbr_mc.entities.npc.EntityINatsume;
import com.deeplake.hbr_mc.entities.projectiles.EntityIdlProjectile;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;


public class RenderHandler {
    public static void registerEntityRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntityINatsume.class,
                renderManager -> new RenderHumanoid(renderManager, "skin/inori_natsume/inori_natsume"));
        RenderingRegistry.registerEntityRenderingHandler(EntityMarionette.class, RenderMarionette::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDoll.class, RenderDoll::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySmallHopper.class, RenderSmallHopper::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityIdlProjectile.class, renderManager -> new RenderBullet<>(renderManager, new ResourceLocation(Main.MODID,
                "textures/entity/projectiles/bullet_norm.png")));

    }
}
