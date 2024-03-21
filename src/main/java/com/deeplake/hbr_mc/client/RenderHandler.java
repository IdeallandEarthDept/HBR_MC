package com.deeplake.hbr_mc.client;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.client.renderer.RenderBullet;
import com.deeplake.hbr_mc.client.renderer.RenderHumanoid;
import com.deeplake.hbr_mc.client.renderer.RenderHumanoidGlassShell;
import com.deeplake.hbr_mc.client.renderer.RenderHumanoidGlowShell;
import com.deeplake.hbr_mc.client.renderer.cancer.*;
import com.deeplake.hbr_mc.entities.cancer.*;
import com.deeplake.hbr_mc.entities.npc.a31.*;
import com.deeplake.hbr_mc.entities.npc.b31.*;
import com.deeplake.hbr_mc.entities.npc.e31.EntityOhshima1;
import com.deeplake.hbr_mc.entities.npc.f31.EntityINatsume;
import com.deeplake.hbr_mc.entities.npc.f31.EntityKMaruyama;
import com.deeplake.hbr_mc.entities.npc.g30.EntityHOgasahara;
import com.deeplake.hbr_mc.entities.npc.x31.*;
import com.deeplake.hbr_mc.entities.projectiles.EntityIdlProjectile;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;


public class RenderHandler {
    public static void registerEntityRenders() {
        //31A
        RenderingRegistry.registerEntityRenderingHandler(EntityLKayamori.class,
                renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/a31/luka_kayamori/luka_hy"));
        RenderingRegistry.registerEntityRenderingHandler(EntityYIzumi.class,
                renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/a31/yuki_izumi/yuki_hy"));
        RenderingRegistry.registerEntityRenderingHandler(EntityMAikawa.class,
                renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/a31/megumi_aikawa/megumi_hy"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTTsukasa.class,
                renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/a31/tojo_tsukasa/tojo_hy"));
        RenderingRegistry.registerEntityRenderingHandler(EntityKAsakura.class,
                renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/a31/karen_asakura/karen_hy",0.95f));
        RenderingRegistry.registerEntityRenderingHandler(EntityTKunimi.class,
                renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/a31/tama_kunimi/tama_hy",0.82f));

        //31b
        RenderingRegistry.registerEntityRenderingHandler(EntityEAoi.class,
                renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/b31/erika_aoi/aoi_ht",1f));
        RenderingRegistry.registerEntityRenderingHandler(EntityIMinase.class,
                renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/b31/ichigo_minase/ichigo_ht",1f));
        RenderingRegistry.registerEntityRenderingHandler(EntitySMinase.class,
                renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/b31/sumomo_minase/sumomo_ht",0.9f));
        RenderingRegistry.registerEntityRenderingHandler(EntitySHiguchi.class,
                renderManager -> new RenderHumanoidGlassShell(renderManager, "skin/b31/seika_higuchi/seika_ht",0.9f));
        RenderingRegistry.registerEntityRenderingHandler(EntityKHiiragi.class,
                renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/b31/kozue_hiiragi/hiiragi_ht",0.9f));
        RenderingRegistry.registerEntityRenderingHandler(EntityByakko.class,
                renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/b31/byakko/byakko_ht",1.1f));

        //30G
        RenderingRegistry.registerEntityRenderingHandler(EntityHOgasahara.class,
                renderManager -> new RenderHumanoid(renderManager, "skin/g30/hisame_ogasahara/maid_hisame",0.82f));

        //31E
        RenderingRegistry.registerEntityRenderingHandler(EntityOhshima1.class,
                renderManager -> new RenderHumanoid(renderManager, "skin/e31/ichiko_ohshima/ichiko"));

        //31F
        RenderingRegistry.registerEntityRenderingHandler(EntityKMaruyama.class,
                renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/f31/kanata_maruyama/kanata_2",0.82f));
        RenderingRegistry.registerEntityRenderingHandler(EntityINatsume.class,
                renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/f31/inori_natsume/inori_natsume"));

        //31X
        RenderingRegistry.registerEntityRenderingHandler(EntityCReaper.class, renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/x31/c_reaper/reaper_ht",1f));
        RenderingRegistry.registerEntityRenderingHandler(EntityCSkopovskaya.class, renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/x31/c_skopovskaya/skopovskaya_ht",0.9f));
        RenderingRegistry.registerEntityRenderingHandler(EntityIRedmayne.class, renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/x31/i_redmayne/redmayne_ht",0.85f));
        RenderingRegistry.registerEntityRenderingHandler(EntityLShanhua.class, renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/x31/l_shanhua/shanhua_ht",1f));
        RenderingRegistry.registerEntityRenderingHandler(EntityMdAngelis.class, renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/x31/md_angelis/angelis_ht",1f));
        RenderingRegistry.registerEntityRenderingHandler(EntityVBalakrishnan.class, renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/x31/v_balakrishnan/balakrishnan_ht",0.9f));

        //Cancer
        RenderingRegistry.registerEntityRenderingHandler(EntityMarionette.class, RenderMarionette::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDoll.class, RenderDoll::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySmallHopper.class, RenderSmallHopper::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityCrestHopper.class, RenderCrestHopper::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySlasher.class, RenderSlasher::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityIdlProjectile.class, renderManager -> new RenderBullet<>(renderManager, new ResourceLocation(Main.MODID,
                "textures/entity/projectiles/bullet_norm.png")));

    }
}
