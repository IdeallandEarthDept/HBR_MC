package com.deeplake.hbr_mc.recipes;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.init.RegisterRecipes;
import com.deeplake.hbr_mc.items.ItemXpBook;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphBase;
import com.deeplake.hbr_mc.items.seraph.SeraphUtil;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreIngredient;

public class RecipeXpBookUp extends ShapelessRecipes {

    public RecipeXpBookUp(Item seraph) {
        super(Main.MODID, new ItemStack(seraph), NonNullList.create());
        recipeItems.add(Ingredient.fromItem(seraph));
        recipeItems.add(new OreIngredient(RegisterRecipes.OREDICT_XPBOOK));
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack result = super.getCraftingResult(inv);
        ItemStack origin = ItemStack.EMPTY;
        int xp = 0;
        for (int i = 0; i < inv.getSizeInventory(); i++)
        {
            ItemStack stack = inv.getStackInSlot(i);
            Item item = stack.getItem();
            if (item instanceof ItemXpBook)
            {
                xp += ((ItemXpBook) item).xp;
            } else if (item instanceof ItemSeraphBase)
            {
                origin = stack;
            }
        }

        if (origin != ItemStack.EMPTY)
        {
            result = origin.copy();
            NBTTagCompound compound = origin.getTagCompound();
            if (compound == null)
            {
                compound = new NBTTagCompound();
            }
            result.setTagCompound(compound.copy());
            SeraphUtil.addXP(result, xp, null);
        }
        return result;
    }
}
