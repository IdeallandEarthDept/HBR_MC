package com.deeplake.hbr_mc.worldgen;

import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.RegisterBlocks;
import com.deeplake.hbr_mc.init.util.CommonDef;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class ModGenDropsOverworld implements IWorldGenerator {

    public ModGenDropsOverworld() {

    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        BlockPos pos = new BlockPos((chunkX << 4)+8, 0, (chunkZ << 4)+8);
        if (world.provider.getDimension() != 0)
        {
            //only in overworld
            return;
        }

        for (int i = 0; i < ModConfig.WORLD_CONF.POT_DENSITY; i++)
        {
            BlockPos.MutableBlockPos pointer = new BlockPos.MutableBlockPos(pos)
                    .setPos(pos.getX() + random.nextInt(CommonDef.CHUNK_SIZE),
                            0,
                            pos.getZ() + random.nextInt(CommonDef.CHUNK_SIZE));

            for (int y = 0; y <= CommonDef.MAX_BUILD_HEIGHT; y++)
            {
                pointer.setY(y);
                IBlockState state = world.getBlockState(pointer);
                IBlockState state1 = world.getBlockState(pointer.up());
                if (state.isSideSolid(world, pointer, EnumFacing.UP)
                    && state1.getBlock().isAir(state1, world, pointer.up())
                    //&& world.getLight(pointer.up()) < 3)
                )
                {
                    if (random.nextFloat() < 0.5f)
                    {
                        world.setBlockState(pointer.up(), RegisterBlocks.RANDOM_DROP_BASIC.getDefaultState());
                        y+=2;
                    }
                }
            }
        }
    }
}
