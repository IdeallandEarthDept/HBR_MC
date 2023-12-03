package com.deeplake.sandbox.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockHealStash extends BlockBase{
    float healAmount = 20;
    public BlockHealStash(String name, Material materialIn, float healAmount) {
        super(name, materialIn);
        this.healAmount = healAmount;
    }

    //when interacted, remove the block, and heal the player
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote)
        {
            return true;
        }
        else {
            worldIn.playSound(null, pos, net.minecraft.init.SoundEvents.ENTITY_PLAYER_LEVELUP, net.minecraft.util.SoundCategory.BLOCKS, 1.0F, 1.0F);
            playerIn.heal(healAmount);
            worldIn.setBlockToAir(pos);
            return true;
        }
    }
}
