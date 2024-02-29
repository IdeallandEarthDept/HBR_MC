package com.deeplake.hbr_mc.init;

import com.deeplake.hbr_mc.entities.cancer.*;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RegisterSpawn {

    public static void registerSpawnList() {
        Map<BiomeDictionary.Type, Set<Biome>> biomeMap = buildBiomeListByType();

        addNormalSpawn(biomeMap);
    }

    private static void addNormalSpawn(Map<BiomeDictionary.Type, Set<Biome>> biomeMap) {
        for (Biome biome : Biome.REGISTRY) {
            add(biome, ModConfig.SPAWN_CONF.SPAWN_MARIONETTE, EntityMarionette.class, 1, 3);
            add(biome, ModConfig.SPAWN_CONF.SPAWN_DOLL, EntityDoll.class, 1, 3);
            add(biome, ModConfig.SPAWN_CONF.SPAWN_SMALL_HOPPER, EntitySmallHopper.class, 1, 3);
            add(biome, ModConfig.SPAWN_CONF.SPAWN_CREST_HOPPER, EntityCrestHopper.class, 1, 3);
            add(biome, ModConfig.SPAWN_CONF.SPAWN_SLASHER, EntitySlasher.class, 1, 3);
        }
    }

    private static Map<BiomeDictionary.Type, Set<Biome>> buildBiomeListByType() {
        Map<BiomeDictionary.Type, Set<Biome>> biomesAndTypes = new HashMap<>();

        for (Biome biome : Biome.REGISTRY) {
            Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biome);
            for (BiomeDictionary.Type type : types) {
                if (!biomesAndTypes.containsKey(type)) {
                    biomesAndTypes.put(type, new HashSet<>());
                }

                biomesAndTypes.get(type).add(biome);
            }
        }

        return biomesAndTypes;
    }

    public static void add(Biome biome, int weight, Class<? extends EntityLiving> entityclassIn, int groupCountMin, int groupCountMax) {
        if (weight > 0) {
            biome.getSpawnableList(EnumCreatureType.MONSTER).add(new Biome.SpawnListEntry(entityclassIn, weight, groupCountMin, groupCountMax));
        }
    }
}
