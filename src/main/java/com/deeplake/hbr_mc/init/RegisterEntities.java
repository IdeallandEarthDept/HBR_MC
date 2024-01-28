package com.deeplake.hbr_mc.init;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.entities.EntityNabiSlime;
import com.deeplake.hbr_mc.entities.cancer.*;
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
        registerEntity("cancer_test_50", EntityDummy50.class);
        registerEntity("cancer_test_100", EntityDummy100.class);
        registerEntity("c_marionette", EntityMarionette.class, 64, 0x000000, 0xcccccc);
        registerEntity("c_doll", EntityDoll.class, 64, 0x000000, 0xcccccc);
        registerEntity("c_small_hopper", EntitySmallHopper.class, 64, 0x000000, 0xcccccc);
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
