package com.deeplake.hbr_mc.items;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class ItemCirclet extends ItemBase {
    public ItemCirclet(String name) {
        super(name);
    }

    @Nullable
    @Override
    public EntityEquipmentSlot getEquipmentSlot(ItemStack stack) {
        return EntityEquipmentSlot.HEAD;
    }
}
