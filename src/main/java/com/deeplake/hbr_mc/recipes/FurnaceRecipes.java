package com.deeplake.hbr_mc.recipes;

import com.deeplake.hbr_mc.init.RegisterBlocks;
import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FurnaceRecipes {

    public static void registerFurnaceRecipes()
    {
        ItemStack stackResult = new ItemStack(RegisterItem.GEM_LOTTERY);
        stackResult.setCount(1);
        GameRegistry.addSmelting(RegisterBlocks.CRYSTAL_ORE,
                stackResult,
                1f);
    }
}
