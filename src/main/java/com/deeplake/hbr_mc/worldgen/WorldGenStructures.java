package com.deeplake.hbr_mc.worldgen;

import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.util.WorldGenUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenStructures implements IWorldGenerator {
    public static final IDFGenStructure CLOCK_TOWER = new IDFGenStructure("clock_tower").setyOffset(-10);

    boolean generateStructure(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, float chance, Block topBlock)
    {
        return generateStructure(generator, world, random, chunkX, chunkZ, chance, topBlock, false);
    }

    boolean generateStructure(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, float chance, Block topBlock, boolean atCenter)
    {
        return generateStructure(generator, world, random, chunkX, chunkZ, calculateGenerationHeight(world, chunkX << 4, chunkZ << 4, topBlock), chance, atCenter);
    }

    boolean generateStructure(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int y, float chance, boolean atCenter) {
        int x = (chunkX << 4);
        int z = (chunkZ << 4);

        if (y < 0) {
            //Idealland.LogWarning("Failed to find y for a structurebig %s", generator);
            return false;
        }

        BlockPos pos = atCenter ? new BlockPos(x + 7, y, z + 7) : new BlockPos(x, y, z);
        if (random.nextFloat() < chance) {
            generator.generate(world, random, pos);
            return true;
        }
        return false;
    }

    static int calculateGenerationHeight(World world, int x, int z, Block topBlock)
    {
        int y = world.getHeight();
        boolean foundGround = false;
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(x, y, z);

        if (topBlock == Blocks.AIR) {
            IBlockState topState = world.getBiome(pos).topBlock;
            while (y-- >= 0) {
                pos.setY(y);
                foundGround = world.getBlockState(pos) == topState;
                if (foundGround)
                {
                    return y;
                }
            }
        } else {
            while (y-- >= 0) {
                pos.setY(y);
                Block block = world.getBlockState(pos).getBlock();
                foundGround = block == topBlock;
                if (foundGround)
                {
                    return y;
                }
            }
        }

        return -1;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

        if (world.provider.getDimensionType() == DimensionType.OVERWORLD)
        {
            BlockPos pos = new BlockPos(chunkX << 4, 128, chunkZ << 4);
            Biome biome = world.getBiome(pos);

            int seaLevel = world.getSeaLevel();

            if (WorldGenUtil.withinWorldSpawnForbidZone(chunkX, chunkZ, world)) return;

            if (generateStructure(CLOCK_TOWER, world,random,chunkX,chunkZ, ModConfig.WORLD_CONF.CLOCK_TOWER_CHANCE, Blocks.AIR)) return;
        }
    }

    public static boolean isWatery(Biome biome) {
        return BiomeManager.oceanBiomes.contains(biome)
                || BiomeDictionary.hasType(biome, BiomeDictionary.Type.RIVER)
                || BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN)
                || BiomeDictionary.hasType(biome, BiomeDictionary.Type.WATER);
    }
}
