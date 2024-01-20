package com.deeplake.hbr_mc.items.seraph;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;
import java.util.Set;

public class ItemSeraphBlaster extends ItemSeraphBase{
    public ItemSeraphBlaster(String name, EnumSeraphType seraphType) {
        super(name, seraphType);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        return 3.0F;
    }

    @Override
    public int getHarvestLevel(ItemStack stack, String toolClass, @Nullable EntityPlayer player, @Nullable IBlockState blockState) {
        return 0;
    }

    @Override
    public Set<String> getToolClasses(ItemStack stack) {
        return super.getToolClasses(stack);
    }
}
