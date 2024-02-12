package com.deeplake.hbr_mc.blocks;

import com.deeplake.hbr_mc.ModTabs;
import com.deeplake.hbr_mc.init.RegisterUtil;
import com.deeplake.hbr_mc.init.util.PlayerUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBase extends Block {
    boolean canPickup = false;
    public BlockBase(String name, Material materialIn) {
        super(materialIn);
        setCreativeTab(ModTabs.TAB1);
        RegisterUtil.initBlock(this, name);
    }

    public BlockBase setPickable(boolean val)
    {
        this.canPickup = val;
        return this;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (canPickup)
        {
            if (!worldIn.isRemote && playerIn.getDistanceSq(pos) < 25f) {
                PlayerUtil.giveToPlayer(playerIn, new ItemStack(this, 1, getMetaFromState(state)));
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
            }

            return true;
        }
        else {
            return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
        }
    }
}
