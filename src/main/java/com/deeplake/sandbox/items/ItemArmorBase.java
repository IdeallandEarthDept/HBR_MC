package com.deeplake.sandbox.items;

import com.deeplake.sandbox.ModTabs;
import com.deeplake.sandbox.init.RegisterUtil;
import com.google.common.collect.Multimap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemArmorBase extends ItemArmor {

    public ItemArmorBase(String name, ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, 5, equipmentSlotIn);
        RegisterUtil.initItem(this, name);
        setCreativeTab(ModTabs.TAB1);
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        Multimap<String, AttributeModifier> hashmap = super.getAttributeModifiers(slot, stack);
        if (slot == EntityEquipmentSlot.MAINHAND)
        {
        }
        return hashmap;
    }
}
