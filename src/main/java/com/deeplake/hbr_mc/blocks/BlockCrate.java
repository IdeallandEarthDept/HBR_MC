package com.deeplake.hbr_mc.blocks;

import com.deeplake.hbr_mc.init.util.ChancePicker;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockCrate extends BlockBase {
    public ChancePicker dropList = new ChancePicker();

    public BlockCrate(String name, Material material) {
        super(name, material);
        this.setHardness(0.1F);
        this.setSoundType(SoundType.WOOD);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote && playerIn.getDistanceSq(pos) < 25f) {
            dropBlockAsItem(worldIn, pos, state, (int) playerIn.getLuck());
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
            worldIn.playSound(null, pos, getSoundType().getBreakSound(), SoundCategory.BLOCKS, 1f, 0.5f);
            return true;
        }
        else {
            return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
        }
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        Random rand = world instanceof World ? ((World)world).rand : RANDOM;
        dropContent(drops, rand);

        if (fortune > 0 && rand.nextInt(10) < fortune)
        {
            //double drop
            dropContent(drops, rand);
        }
    }

    private void dropContent(NonNullList<ItemStack> drops, Random rand) {
        drops.add(dropList.getItem(rand));
    }
}
