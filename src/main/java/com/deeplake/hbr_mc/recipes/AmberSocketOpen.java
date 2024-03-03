package com.deeplake.hbr_mc.recipes;

import com.deeplake.hbr_mc.init.util.IDLNBTUtil;
import com.deeplake.hbr_mc.items.ItemArmorCancer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;

public class AmberSocketOpen extends ShapelessRecipes {
    public AmberSocketOpen(String group, ItemStack output, NonNullList<Ingredient> ingredients) {
        super(group, output, ingredients);
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack result = super.getCraftingResult(inv);

        int currentSockets = IDLNBTUtil.GetState2(result);
        if (currentSockets < 3)
        {
            //give 1 slot
            IDLNBTUtil.SetState(result, 1);
            ItemArmorCancer.distributeAttr(result);
        }

        return result;
    }
}
