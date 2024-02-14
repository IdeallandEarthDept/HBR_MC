package com.deeplake.hbr_mc.init;

import com.deeplake.hbr_mc.enchantments.ModEnchantmentBase;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

import java.util.ArrayList;
import java.util.List;

public class RegisterEnchant {
    public static final EntityEquipmentSlot[] armorSlots = new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET};
    public static final EntityEquipmentSlot[] allSlots = EntityEquipmentSlot.values();
    public static final EntityEquipmentSlot[] handSlots = new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND};
    public static final EntityEquipmentSlot[] mainHand = new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND};
    public static final EntityEquipmentSlot[] feet = new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND};
    public static final EntityEquipmentSlot[] chest = new EntityEquipmentSlot[] {EntityEquipmentSlot.CHEST};
    public static final EntityEquipmentSlot[] legs = new EntityEquipmentSlot[] {EntityEquipmentSlot.LEGS};
    public static final List<Enchantment> ENCHANTMENT_LIST = new ArrayList<Enchantment>();

    public static final ModEnchantmentBase HISTORY_4ky = new ModEnchantmentBase("history_4ky", Enchantment.Rarity.RARE,
            EnumEnchantmentType.ALL, EntityEquipmentSlot.values());
}
