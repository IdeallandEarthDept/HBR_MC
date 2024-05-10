package com.deeplake.hbr_mc.blocks;

import com.deeplake.hbr_mc.ModTabs;
import com.deeplake.hbr_mc.init.RegisterUtil;
import net.minecraft.block.BlockIce;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockDryIce extends BlockIce {
    //todo: consider melting. see BlockFrostedIce.
    public BlockDryIce(String name) {
        super();
        setCreativeTab(ModTabs.TAB1);
        RegisterUtil.initBlock(this, name);
    }

    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
        player.addStat(StatList.getBlockStats(this));
        player.addExhaustion(0.005F);
    }

    @Override
    protected void turnIntoWater(World worldIn, BlockPos pos) {
        worldIn.setBlockToAir(pos);
    }
}
