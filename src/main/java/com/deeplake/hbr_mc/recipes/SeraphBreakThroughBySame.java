package com.deeplake.hbr_mc.recipes;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.init.util.IDLNBTDef;
import com.deeplake.hbr_mc.init.util.IDLNBTUtil;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphBase;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class SeraphBreakThroughBySame extends ShapedRecipes {
    final int maxBreakThrough;
    public SeraphBreakThroughBySame(Item seraphSS, int maxBreakThrough) {
        super(Main.MODID, 2, 1, NonNullList.create(), new ItemStack(seraphSS));
        recipeItems.add(Ingredient.fromItem(seraphSS));
        recipeItems.add(Ingredient.fromItem(seraphSS));
        this.maxBreakThrough = maxBreakThrough;
    }

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        ItemStack origin = ItemStack.EMPTY;
        for (int i = 0; i < inv.getSizeInventory(); i++)
        {
            ItemStack stack = inv.getStackInSlot(i);
            Item item = stack.getItem();
            if (item instanceof ItemSeraphBase)
            {
                if (origin.isEmpty())
                {
                    origin = stack;
                }
                else {
                    //Those already have breakthrough can't be used to breakthrough again
                    if (IDLNBTUtil.GetInt(stack, IDLNBTDef.BREAKTHROUGH) > 0)
                    {
                        return false;
                    }
                }
            }
        }

        if (origin != ItemStack.EMPTY)
        {
            int cur = IDLNBTUtil.GetInt(origin, IDLNBTDef.BREAKTHROUGH, 0);
            if (cur >= maxBreakThrough)
            {
                return false;
            }
        }
        return super.matches(inv, worldIn);
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
            result = origin.copy();
            NBTTagCompound compound = origin.getTagCompound();
            if (compound == null)
            {
                compound = new NBTTagCompound();
            }
            result.setTagCompound(compound.copy());
            IDLNBTUtil.addInt(result, IDLNBTDef.BREAKTHROUGH, 1);
        }
        return result;
    }
}
