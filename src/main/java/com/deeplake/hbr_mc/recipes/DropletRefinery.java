package com.deeplake.hbr_mc.recipes;

import com.deeplake.hbr_mc.init.util.IDLNBTUtil;
import com.deeplake.hbr_mc.items.ItemArmorCancer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;

public class DropletRefinery extends ShapelessRecipes {
    public DropletRefinery(String group, ItemStack output, NonNullList<Ingredient> ingredients) {
        super(group, output, ingredients);
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack result = super.getCraftingResult(inv);
        IDLNBTUtil.SetState(result, 0);
        for (ItemArmorCancer.EnumBonusAttr attr: ItemArmorCancer.EnumBonusAttr.values()){
            if (result.hasTagCompound() && result.getTagCompound().hasKey(attr.name))
            {
                result.getTagCompound().removeTag(attr.name);
            }
        }
        return result;
    }
}
