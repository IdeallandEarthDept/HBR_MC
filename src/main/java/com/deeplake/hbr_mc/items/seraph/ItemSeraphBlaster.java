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
    public ItemSeraphBlaster(String name, EnumSeraphType seraphType, EnumSeraphRarity rarity) {
        super(name, seraphType, rarity);
    }

    Set<String> toolSet = com.google.common.collect.ImmutableSet.of("pickaxe","axe","shovel");

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        return 80.0F;
    }

    @Override
    public int getHarvestLevel(ItemStack stack, String toolClass, @Nullable EntityPlayer player, @Nullable IBlockState blockState) {
        return -1;
    }

    @Override
    public Set<String> getToolClasses(ItemStack stack) {
        return super.getToolClasses(stack);
    }
}
