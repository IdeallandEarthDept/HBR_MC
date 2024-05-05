package com.deeplake.hbr_mc.init;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.entities.EntityNabiSlime;
import com.deeplake.hbr_mc.entities.cancer.*;
import com.deeplake.hbr_mc.entities.npc.a31.*;
import com.deeplake.hbr_mc.entities.npc.b31.*;
import com.deeplake.hbr_mc.entities.npc.c31.*;
import com.deeplake.hbr_mc.entities.npc.e31.*;
import com.deeplake.hbr_mc.entities.npc.f31.*;
import com.deeplake.hbr_mc.entities.npc.g30.*;
import com.deeplake.hbr_mc.entities.npc.x31.*;
import com.deeplake.hbr_mc.entities.projectiles.EntityHBRProjectile;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class RegisterEntities {
    private static int ENTITY_NEXT_ID = 1;
    public static void registerEntities()
    {
        registerEntity("cancer_test", EntityDummyCancer.class);
        registerEntity("nabi", EntityNabiSlime.class);
//        registerEntity("cancer_test_50", EntityDummy50.class);
//        registerEntity("cancer_test_100", EntityDummy100.class);
        registerEntity("c_marionette", EntityMarionette.class, 64, 0x000000, 0xcccccc);
        registerEntity("c_doll", EntityDoll.class, 64, 0x000000, 0xcccccc);
        registerEntity("c_small_hopper", EntitySmallHopper.class, 64, 0x000000, 0xcccccc);
        registerEntity("c_crest_hopper", EntityCrestHopper.class, 64, 0x000000, 0xeecc66);
        registerEntity("c_slasher", EntitySlasher.class, 64, 0x000000, 0xeecc66);

        //31a
        registerEntity("l_kayamori", EntityLKayamori.class, 64, 0x4b7fdc, 0xe0c385);
        registerEntity("y_izumi", EntityYIzumi.class, 64, 0x4b7fdc, 0xe0c385);
        registerEntity("m_aikawa", EntityMAikawa.class, 64, 0x4b7fdc, 0xe0c385);
        registerEntity("t_tsukasa", EntityTTsukasa.class, 64, 0x4b7fdc, 0xe0c385);
        registerEntity("k_asakura", EntityKAsakura.class, 64, 0x4b7fdc, 0xe0c385);
        registerEntity("t_kunimi", EntityTKunimi.class, 64, 0x4b7fdc, 0xe0c385);

        //31b
        registerEntity("e_aoi", EntityEAoi.class, 64, 0xb1a08f, 0xc94644);
        registerEntity("i_minase", EntityIMinase.class, 64, 0xf1e7d4, 0xc94644);
        registerEntity("s_minase", EntitySMinase.class, 64, 0x8bc2c1, 0xc94644);
        registerEntity("s_higuchi", EntitySHiguchi.class, 64, 0xf1e7d4, 0xc94644);
        registerEntity("k_hiiragi", EntityKHiiragi.class, 64, 0xf1e7d4, 0xc94644);
        registerEntity("byakko", EntityByakko.class, 64, 0xe4e3ec, 0x61568b);

        //31c
        registerEntity("i_yamawaki", EntityIYamawaki.class, 64, 0xD5D1D7, 0x9F193B);
        registerEntity("s_sakuraba", EntitySSakuraba.class, 64, 0xF0E2F0, 0xF8BDC9);
        registerEntity("m_tenne", EntityMTenne.class, 64, 0x3C3245, 0xAA958B);
        registerEntity("y_bungo", EntityYBungo.class, 64, 0xC70909, 0xD5CCAB);
        registerEntity("a_kanzaki", EntityAKanzaki.class, 64, 0xE9CEA1, 0xB52221);
        registerEntity("m_satsuki", EntityMSatsuki.class, 64, 0xFAF5F7, 0x484477);

        //30g
        registerEntity("y_shirakawa", EntityYShirakawa.class, 64, 0xF2E9EF, 0x476EC0);
        registerEntity("m_tsukishiro", EntityMTsukishiro.class, 64, 0xF2E9EF, 0xDAB7CE);
        registerEntity("m_kiryu", EntityMKiryu.class, 64, 0xF7F7F6, 0xC84649);
        registerEntity("c_sugawara", EntityCSugawara.class, 64, 0xF7F7F6, 0x75AB8F);
        registerEntity("h_ogasahara", EntityHOgasahara.class, 64, 0x4e3c5a, 0x94312e);
        registerEntity("s_kura", EntitySKura.class, 64, 0xF2E9EF, 0xB77DC0);


        //31e
        registerEntity("ohshima_1", EntityOhshima1.class, 64, 0x404158, 0x37b2b1);
        registerEntity("ohshima_2", EntityOhshima2.class, 64, 0x404158, 0x37b2b1);
        registerEntity("ohshima_3", EntityOhshima3.class, 64, 0x404158, 0x37b2b1);
        registerEntity("ohshima_4", EntityOhshima4.class, 64, 0x404158, 0x37b2b1);
        registerEntity("ohshima_5", EntityOhshima5.class, 64, 0x404158, 0x37b2b1);
        registerEntity("ohshima_6", EntityOhshima6.class, 64, 0x404158, 0x37b2b1);

        //31f
        registerEntity("m_yanagi", EntityMYanagi.class, 64, 0xa9aaaf, 0xa03838);
        registerEntity("k_maruyama", EntityKMaruyama.class, 64, 0x757a96, 0x3845bb);
        registerEntity("i_natsume", EntityINatsume.class, 64, 0x2a2b2c, 0xc03d3d);
        registerEntity("s_hanamura", EntitySHanamura.class, 64, 0x798bd2, 0x50566b);
        registerEntity("c_matsuoka", EntityCMatsuoka.class, 64, 0xd284b8, 0x354542);
        registerEntity("m_kurosawa", EntityMKurosawa.class, 64, 0x8884bc, 0xf2f2b4);

        //31X
        registerEntity("c_reaper", EntityCReaper.class, 64, 0x143281, 0xc03d3d);
        registerEntity("l_shanhua", EntityLShanhua.class, 64, 0xe86e44, 0xc03d3d);
        registerEntity("i_redmayne", EntityIRedmayne.class, 64, 0x90887f, 0xfdf586);
        registerEntity("v_balakrishnan", EntityVBalakrishnan.class, 64, 0xf6ecea, 0xe8a9e2);
        registerEntity("md_angelis", EntityMdAngelis.class, 64, 0xbe3143, 0x212953);
        registerEntity("c_skopovskaya", EntityCSkopovskaya.class, 64, 0x57596e, 0xf9303c);

        registerEntityNoEgg("bullet", EntityHBRProjectile.class);

        //Assign Dungeons
        //DungeonHooks.addDungeonMob(EntityList.getKey(EntityMoroonStandardInfantrySpawner.class), STANDARD_DUNGEON_MOB_RARITY >> 1);
    }

    private static void registerEntity(String name, Class<? extends Entity> entity)
    {
        registerEntity(name, entity, ENTITY_NEXT_ID, 50, 0xff00ff, 0x000000);
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int range, int color1, int color2)
    {
        registerEntity(name, entity, ENTITY_NEXT_ID, range, color1, color2);
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int color1, int color2)
    {
        registerEntity(name, entity, ENTITY_NEXT_ID, 50, color1, color2);
    }

    private  static  void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2){
        EntityRegistry.registerModEntity(new ResourceLocation(Main.MODID + ":" + name),
                entity,
                name,
                id,
                Main.instance,
                range,
                1,
                true,
                color1, color2
                );
        ENTITY_NEXT_ID++;
    }

    private  static  void registerEntityNoEgg(String name, Class<? extends Entity> entity)
    {
        registerEntityNoEgg(name, entity, ENTITY_NEXT_ID, 50);
    }

    private  static  void registerEntityNoEgg(String name, Class<? extends Entity> entity, int id, int range){
        EntityRegistry.registerModEntity(new ResourceLocation(Main.MODID + ":" + name),
                entity,
                name,
                id,
                Main.instance,
                range,
                1,
                true
        );
        ENTITY_NEXT_ID++;
    }
}
