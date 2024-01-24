package com.deeplake.hbr_mc.init;

import com.deeplake.hbr_mc.worldgen.SpawnCrystal;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RegisterWorldGen {
    static int nextWeight = 100;
    public static void registerWorldGen()
    {
        register(new SpawnCrystal());
    }

    static void register(IWorldGenerator generator)
    {
        register(generator, nextWeight);
    }

    static void register(IWorldGenerator generator, int modGenerationWeight)
    {
        GameRegistry.registerWorldGenerator(generator, modGenerationWeight);
        nextWeight++;
    }
}
