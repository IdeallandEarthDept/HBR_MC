package com.deeplake.hbr_mc.blocks;

import com.deeplake.hbr_mc.ModTabs;
import com.deeplake.hbr_mc.init.RegisterUtil;
import net.minecraft.block.BlockIce;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockDryIce extends BlockIce {
    //todo: consider melting. see BlockFrostedIce.
    public BlockDryIce(String name) {
        super();
        setCreativeTab(ModTabs.TAB1);
        RegisterUtil.initBlock(this, name);
    }

    @Override
    protected void turnIntoWater(World worldIn, BlockPos pos) {
        worldIn.setBlockToAir(pos);
    }
}
