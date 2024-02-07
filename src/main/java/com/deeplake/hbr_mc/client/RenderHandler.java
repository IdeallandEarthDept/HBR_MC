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
import com.deeplake.hbr_mc.entities.npc.b31.EntitySHiguchi;
import com.deeplake.hbr_mc.entities.npc.e31.EntityOhshima1;
import com.deeplake.hbr_mc.entities.npc.f31.EntityINatsume;
import com.deeplake.hbr_mc.entities.npc.a31.*;
import com.deeplake.hbr_mc.entities.npc.f31.EntityKMaruyama;
import com.deeplake.hbr_mc.entities.npc.g30.EntityHOgasahara;
import com.deeplake.hbr_mc.entities.projectiles.EntityIdlProjectile;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;


public class RenderHandler {
    public static void registerEntityRenders() {
        //31A
        RenderingRegistry.registerEntityRenderingHandler(EntityLKayamori.class,
                renderManager -> new RenderHumanoid(renderManager, "skin/luka_kayamori/luka"));
        RenderingRegistry.registerEntityRenderingHandler(EntityYIzumi.class,
                renderManager -> new RenderHumanoid(renderManager, "skin/yuki_izumi/yuki"));
        RenderingRegistry.registerEntityRenderingHandler(EntityMAikawa.class,
                renderManager -> new RenderHumanoid(renderManager, "skin/megumi_aikawa/megumi"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTTsukasa.class,
                renderManager -> new RenderHumanoid(renderManager, "skin/tojo_tsukasa/tojo"));
        RenderingRegistry.registerEntityRenderingHandler(EntityKAsakura.class,
                renderManager -> new RenderHumanoid(renderManager, "skin/karen_asakura/karen"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTKunimi.class,
                renderManager -> new RenderHumanoid(renderManager, "skin/tama_kunimi/tama"));

        //31b
        RenderingRegistry.registerEntityRenderingHandler(EntitySHiguchi.class,
                renderManager -> new RenderHumanoid(renderManager, "skin/b31/seika_higuchi/lancelot_ver"));

        //30G
        RenderingRegistry.registerEntityRenderingHandler(EntityHOgasahara.class,
                renderManager -> new RenderHumanoid(renderManager, "skin/g30/hisame_ogasahara/maid_hisame"));

        //31E
        RenderingRegistry.registerEntityRenderingHandler(EntityOhshima1.class,
                renderManager -> new RenderHumanoid(renderManager, "skin/e31/ichiko_ohshima/ichiko"));

        //31F
        RenderingRegistry.registerEntityRenderingHandler(EntityKMaruyama.class,
                renderManager -> new RenderHumanoid(renderManager, "skin/f31/kanata_maruyama/kanata"));
        RenderingRegistry.registerEntityRenderingHandler(EntityINatsume.class,
                renderManager -> new RenderHumanoid(renderManager, "skin/f31/inori_natsume/inori_natsume"));

        RenderingRegistry.registerEntityRenderingHandler(EntityMarionette.class, RenderMarionette::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDoll.class, RenderDoll::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySmallHopper.class, RenderSmallHopper::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityIdlProjectile.class, renderManager -> new RenderBullet<>(renderManager, new ResourceLocation(Main.MODID,
                "textures/entity/projectiles/bullet_norm.png")));

    }
}
