package com.deeplake.hbr_mc.recipes;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.init.RegisterItem;
import com.deeplake.hbr_mc.init.util.IDLNBTDef;
import com.deeplake.hbr_mc.init.util.IDLNBTUtil;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphBase;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;

public class RecipeSeraphBoost extends ShapelessRecipes {

    final int maxBoost;
    public RecipeSeraphBoost(Item seraph, int maxBoost) {
        super(Main.MODID, new ItemStack(seraph), NonNullList.create());
        recipeItems.add(Ingredient.fromItem(seraph));
        recipeItems.add(Ingredient.fromItem(RegisterItem.BOOST_1_A));
        this.maxBoost = maxBoost;
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
            }
        }

        if (origin != ItemStack.EMPTY)
        {
            result = origin.copy();
            int cur = IDLNBTUtil.GetInt(result, IDLNBTDef.KEY_BOOST, 0);
            if (cur < maxBoost)
            {
                IDLNBTUtil.addInt(result, IDLNBTDef.KEY_BOOST, 1);
            }
        }
        return result;
    }
}
