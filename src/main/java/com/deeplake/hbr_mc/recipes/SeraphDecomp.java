package com.deeplake.hbr_mc.recipes;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.NonNullList;

public class SeraphDecomp extends ShapedRecipes {
    public static final Ingredient SSS = Ingredient.fromItem(RegisterItem.SHARD_SS);
    public SeraphDecomp(Item seraphA, int count) {
        super(Main.MODID, 1, 1, NonNullList.create(), new ItemStack(RegisterItem.SHARD_SS,count));
        recipeItems.add(Ingredient.fromItem(seraphA));
    }
}
