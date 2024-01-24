package com.deeplake.hbr_mc.worldgen;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.init.util.WorldGenUtil;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.Random;

public class IDFGenStructure extends WorldGenerator{

    WorldServer worldServer = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(0);
    int yOffset = 0;
    int xOffset = 7;
    int zOffset = 7;

    ResourceLocation location;
    public IDFGenStructure(String name) {
        structureName = name;
        location = new ResourceLocation(Main.MODID, structureName);
    }

    public IDFGenStructure setyOffset(int yOffset) {
        this.yOffset = yOffset;
        return this;
    }

    public String structureName;

    public void generateStructure(World worldIn, BlockPos pos, Mirror mirror, Rotation rotation) {
        WorldGenUtil.generate(location, worldIn, pos.add(xOffset, yOffset, zOffset), new PlacementSettings().setMirror(mirror).setRotation(rotation));
    }

    public void generateStructure(World worldIn, BlockPos pos) {
        WorldGenUtil.generate(location, worldIn, pos.add(xOffset, yOffset, zOffset), null);
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        this.generateStructure(worldIn, position);
        return true;
    }
}
