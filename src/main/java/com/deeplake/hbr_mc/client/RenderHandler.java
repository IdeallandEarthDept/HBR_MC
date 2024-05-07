package com.deeplake.hbr_mc.client;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.client.renderer.*;
import com.deeplake.hbr_mc.client.renderer.cancer.*;
import com.deeplake.hbr_mc.entities.boss.EntityBossBase;
import com.deeplake.hbr_mc.entities.cancer.*;
import com.deeplake.hbr_mc.entities.effect.EntityCastDelayIcePillar;
import com.deeplake.hbr_mc.entities.npc.a31.*;
import com.deeplake.hbr_mc.entities.npc.b31.*;
import com.deeplake.hbr_mc.entities.npc.c31.*;
import com.deeplake.hbr_mc.entities.npc.e31.*;
import com.deeplake.hbr_mc.entities.npc.f31.*;
import com.deeplake.hbr_mc.entities.npc.g30.*;
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

        //31c
        RenderingRegistry.registerEntityRenderingHandler(EntityIYamawaki.class,
                renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/c31/ivar_bon_yamawaki/ivar_bon_yamawaki",1f));
        RenderingRegistry.registerEntityRenderingHandler(EntitySSakuraba.class,
                renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/c31/seira_sakuraba/seira_sakuraba",1.05f));
        RenderingRegistry.registerEntityRenderingHandler(EntityMTenne.class,
                renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/c31/miko_tenne/miko_tenne",0.8f));
        RenderingRegistry.registerEntityRenderingHandler(EntityYBungo.class,
                renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/c31/yayoi_bungo/yayoi_bungo",0.8f));
        RenderingRegistry.registerEntityRenderingHandler(EntityAKanzaki.class,
                renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/c31/adelheid_kanzaki/adelheid_kanzaki",1f));
        RenderingRegistry.registerEntityRenderingHandler(EntityMSatsuki.class,
                renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/c31/mari_satsuki/mari_satsuki",1f));


        //30G
        RenderingRegistry.registerEntityRenderingHandler(EntityYShirakawa.class,
                renderManager -> new RenderHumanoid(renderManager, "skin/g30/yuina_shirakawa/yuina",1.1f));
        RenderingRegistry.registerEntityRenderingHandler(EntityMTsukishiro.class,
                renderManager -> new RenderHumanoid(renderManager, "skin/g30/monaka_tsukishiro/monaka",0.82f));
        RenderingRegistry.registerEntityRenderingHandler(EntityMKiryu.class,
                renderManager -> new RenderHumanoid(renderManager, "skin/g30/miya_kiryu/miya",1f));
        RenderingRegistry.registerEntityRenderingHandler(EntityCSugawara.class,
                renderManager -> new RenderHumanoid(renderManager, "skin/g30/chie_sugawara/chie",0.9f));
        RenderingRegistry.registerEntityRenderingHandler(EntityHOgasahara.class,
                renderManager -> new RenderHumanoid(renderManager, "skin/g30/hisame_ogasahara/hisame",0.82f));
        RenderingRegistry.registerEntityRenderingHandler(EntitySKura.class,
                renderManager -> new RenderHumanoid(renderManager, "skin/g30/satomi_kura/kura",1f));

        //31E
        RenderingRegistry.registerEntityRenderingHandler(EntityOhshima1.class,
                renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/e31/ichiko_ohshima/ichiko",1.05f));
        RenderingRegistry.registerEntityRenderingHandler(EntityOhshima2.class,
                renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/e31/niina_ohshima/niina", 0.95f));
        RenderingRegistry.registerEntityRenderingHandler(EntityOhshima3.class,
                renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/e31/minori_ohshima/minori", 0.9f));
        RenderingRegistry.registerEntityRenderingHandler(EntityOhshima4.class,
                renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/e31/yotsuha_ohshima/yotsuha", 0.8f));
        RenderingRegistry.registerEntityRenderingHandler(EntityOhshima5.class,
                renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/e31/isuzu_ohshima/isuzu", 0.91f));
        RenderingRegistry.registerEntityRenderingHandler(EntityOhshima6.class,
                renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/e31/muya_ohshima/muya", 0.85f));

        //31F
        RenderingRegistry.registerEntityRenderingHandler(EntityMYanagi.class,
                renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/f31/mion_yanagi/yanagi",1.1f));
        RenderingRegistry.registerEntityRenderingHandler(EntityKMaruyama.class,
                renderManager -> new RenderHumanoid(renderManager, "skin/f31/kanata_maruyama/kanata_2",0.82f));
        RenderingRegistry.registerEntityRenderingHandler(EntityINatsume.class,
                renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/f31/inori_natsume/inori_natsume"));
        RenderingRegistry.registerEntityRenderingHandler(EntitySHanamura.class,
                renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/f31/shiki_hanamura/shiki",1.05f));
        RenderingRegistry.registerEntityRenderingHandler(EntityCMatsuoka.class,
                renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/f31/chiroru_matsuoka/chiroru",0.9f));
        RenderingRegistry.registerEntityRenderingHandler(EntityMKurosawa.class,
                renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/f31/maki_kurosawa/maki",0.9f));

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

        //boss
        RenderingRegistry.registerEntityRenderingHandler(EntityBossBase.class,
                renderManager -> new RenderHumanoidGlowShell(renderManager, "skin/c31/ivar_bon_yamawaki/ivar_bon_yamawaki",1f));
        RenderingRegistry.registerEntityRenderingHandler(EntityCastDelayIcePillar.class,
                RenderNone::new);


    }
}
