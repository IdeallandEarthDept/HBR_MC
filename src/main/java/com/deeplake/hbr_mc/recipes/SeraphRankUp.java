package com.deeplake.hbr_mc.recipes;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.init.RegisterItem;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphBase;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;

public class SeraphRankUp extends ShapedRecipes {
    public static final Ingredient SSS = Ingredient.fromItem(RegisterItem.SHARD_SS);
    public SeraphRankUp(Item seraphA, Item seraphSS) {
        super(Main.MODID, 3, 3, NonNullList.create(), new ItemStack(seraphSS));
        recipeItems.add(SSS);
        recipeItems.add(SSS);
        recipeItems.add(SSS);
        recipeItems.add(SSS);
        recipeItems.add(Ingredient.fromItem(seraphA));
        recipeItems.add(SSS);
        recipeItems.add(SSS);
        recipeItems.add(SSS);
        recipeItems.add(SSS);
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack result = super.getCraftingResult(inv);
        ItemStack origin = ItemStack.EMPTY;
        for (int i = 0; i < inv.getSizeInventory(); i++)
        {
            ItemStack stack = inv.getStackInSlot(i);
            Item item = stack.getItem();
            if (item instanceof ItemSeraphBase)
            {
                origin = stack;
                break;
            }
        }

        if (origin != ItemStack.EMPTY)
        {
            NBTTagCompound compound = origin.getTagCompound();
            if (compound == null)
            {
                compound = new NBTTagCompound();
            }
            result.setTagCompound(compound.copy());
        }
        return result;
    }
}
