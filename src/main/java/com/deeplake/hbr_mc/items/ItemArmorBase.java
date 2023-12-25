package com.deeplake.hbr_mc.items;

import com.deeplake.hbr_mc.ModTabs;
import com.deeplake.hbr_mc.init.RegisterUtil;
import com.google.common.collect.Multimap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class ItemArmorBase extends ItemArmor {

    public ItemArmorBase(String name, ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, 5, equipmentSlotIn);
        RegisterUtil.initItem(this, name);
        setCreativeTab(ModTabs.TAB1);
    }
}
