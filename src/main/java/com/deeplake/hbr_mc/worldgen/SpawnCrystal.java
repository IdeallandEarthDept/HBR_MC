package com.deeplake.hbr_mc.worldgen;

import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.RegisterBlocks;
import com.deeplake.hbr_mc.init.util.CommonDef;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class SpawnCrystal implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimensionType() == DimensionType.OVERWORLD) {
            for (int i = 0; i < ModConfig.WORLD_CONF.CRYSTAL_PER_CHUNK; i++) {
                int x = chunkX<<4;
                int z = chunkZ<<4;
                x+=random.nextInt(CommonDef.CHUNK_SIZE)+CommonDef.CHUNK_CENTER_INT;
                z+=random.nextInt(CommonDef.CHUNK_SIZE)+CommonDef.CHUNK_CENTER_INT;
                int yREF = world.getHeight(x,z);
                int yMin = ModConfig.WORLD_CONF.CRYSTAL_MIN_Y;
                int yRange = yREF - yMin;
                if (yRange > 0)
                {
                    BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(x,0,z);
                    for(int trial = 9; trial>0; trial--)
                    {
                        int y = yMin + random.nextInt(yRange);
                        pos.setY(y);
                        if (world.getBlockState(pos).getBlock()== Blocks.STONE)
                        {
                            world.setBlockState(new BlockPos(x,y,z), RegisterBlocks.CRYSTAL_ORE.getDefaultState());
                            break;
                        }
                    }
                }
            }
        }
    }
}
