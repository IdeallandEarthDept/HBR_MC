package com.deeplake.hbr_mc.init;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.entities.EntityNabiSlime;
import com.deeplake.hbr_mc.entities.cancer.*;
import com.deeplake.hbr_mc.entities.npc.a31.*;
import com.deeplake.hbr_mc.entities.npc.b31.*;
import com.deeplake.hbr_mc.entities.npc.e31.EntityOhshima1;
import com.deeplake.hbr_mc.entities.npc.f31.EntityINatsume;
import com.deeplake.hbr_mc.entities.npc.f31.EntityKMaruyama;
import com.deeplake.hbr_mc.entities.npc.g30.EntityHOgasahara;
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


        //30g
        registerEntity("h_ogasahara", EntityHOgasahara.class, 64, 0x4e3c5a, 0x94312e);

        //30g
        registerEntity("ohshima_1", EntityOhshima1.class, 64, 0x404158, 0x37b2b1);

        //31f
        registerEntity("k_maruyama", EntityKMaruyama.class, 64, 0x757a96, 0x3845bb);
        registerEntity("i_natsume", EntityINatsume.class, 64, 0x2a2b2c, 0xc03d3d);

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
