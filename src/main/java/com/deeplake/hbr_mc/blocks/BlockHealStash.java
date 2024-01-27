package com.deeplake.hbr_mc.blocks;

import com.deeplake.hbr_mc.events.DesignBanHeal;
import com.deeplake.hbr_mc.items.seraph.SeraphUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockHealStash extends BlockBase{
    float healAmount = 20;
    boolean oneUse = true;
    public BlockHealStash(String name, Material materialIn, float healAmount) {
        super(name, materialIn);
        this.healAmount = healAmount;
        setHardness(10f);
    }

    public Block setOneUse(boolean oneUse) {
        this.oneUse = oneUse;
        if (!oneUse)
        {
            setBlockUnbreakable();
        }
        return this;
    }

    @Override
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        return false;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Items.AIR;
    }

    //when interacted, remove the block, and heal the player
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote)
        {
            return true;
        }
        else {
            DesignBanHeal.setHealAllowed(true);
            worldIn.playSound(null, pos, net.minecraft.init.SoundEvents.ENTITY_PLAYER_LEVELUP, net.minecraft.util.SoundCategory.BLOCKS, 1.0F, 1.0F);
            playerIn.heal(healAmount);
            DesignBanHeal.setHealAllowed(false);
            ItemStack stack = SeraphUtil.getFirstSeraphInHand(playerIn);
            if (!stack.isEmpty())
            {
                SeraphUtil.repairSeraphFully(stack);
                playerIn.getCooldownTracker().setCooldown(stack.getItem(), 200);
            }
            if (oneUse)
            {
                worldIn.setBlockToAir(pos);
            }
            return true;
        }
    }
}
