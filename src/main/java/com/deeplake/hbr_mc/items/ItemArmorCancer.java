package com.deeplake.hbr_mc.items;

import com.deeplake.hbr_mc.ModTabs;
import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.init.RegisterUtil;
import com.deeplake.hbr_mc.init.util.IDLNBTUtil;
import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.UUID;

public class ItemArmorCancer extends ItemArmorBase {

    static final UUID[] ARMOR_MODIFIERS_VANILLA = new UUID[] {UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};
    static final UUID ARMOR_BASE_MODIFY = UUID.fromString("5B0E382C-0AC1-4CFA-9748-5EBA6FAF4653");

    public ItemArmorCancer(String name, ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn) {
        super(name, materialIn, equipmentSlotIn);
    }

    public float armorBaseBonus = 14;//best should give 28

    private void addToMap(Multimap<String, AttributeModifier> multimap, UUID uuid, IAttribute str, float amount) {
        multimap.put(str.getName(), new AttributeModifier(uuid, "armor_modifier", amount, 0));
    }

    //todo: random bonus of total 3 points, marked by NBT
    public static final String NBT_MEN = "men_up";
    public static final String NBT_END = "end_up";
    public static final String NBT_STR = "str_up";
    public static final String NBT_DEX = "dex_up";
    public static final String NBT_LUC = "luc_up";
    public static final String NBT_INT = "int_up";
    enum EnumBonusAttr{
        MEN(NBT_MEN, RegisterAttr.MEN),
        END(NBT_END, RegisterAttr.END),
        STR(NBT_STR, RegisterAttr.STR),
        DEX(NBT_DEX, RegisterAttr.DEX),
        LUC(NBT_LUC, RegisterAttr.LUC),
        INT(NBT_INT, RegisterAttr.INT);

        final String name;
        final IAttribute attribute;

        EnumBonusAttr(String name, IAttribute attribute) {
            this.name = name;
            this.attribute = attribute;
        }
    }

    void distributeAttr(ItemStack stack)
    {
        int total = 3;
        int[] attr = new int[EnumBonusAttr.values().length];

        while (total > 0)
        {
            int index = (int)(Math.random() * EnumBonusAttr.values().length);
            attr[index] += 1;
            total -= 1;
        }

        for (int i = 0; i < attr.length; ++i)
        {
            if (attr[i] > 0)
            {
                addBonusAttr(stack, EnumBonusAttr.values()[i], attr[i]);
            }
        }
    }

    void addBonusAttr(ItemStack stack, EnumBonusAttr attr, int amount)
    {
        IDLNBTUtil.addInt(stack, attr.name, amount);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
        if (IDLNBTUtil.GetState(stack) == 0)
        {
            distributeAttr(stack);
            IDLNBTUtil.SetState(stack, 3);
        }
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        Multimap<String, AttributeModifier> hashmap = super.getAttributeModifiers(slot, stack);
        if (slot == this.armorType) {
            switch (slot)
            {
                case MAINHAND:
                    break;
                case OFFHAND:
                    break;

                case FEET:
                    addToMap(hashmap, ARMOR_BASE_MODIFY, RegisterAttr.LUC, armorBaseBonus);
                    break;
                case LEGS:
                    addToMap(hashmap, ARMOR_BASE_MODIFY, RegisterAttr.END, armorBaseBonus);
                    addToMap(hashmap, ARMOR_BASE_MODIFY, RegisterAttr.MEN, armorBaseBonus);
                    break;
                case CHEST:
                    addToMap(hashmap, ARMOR_BASE_MODIFY, RegisterAttr.STR, armorBaseBonus);
                    addToMap(hashmap, ARMOR_BASE_MODIFY, RegisterAttr.DEX, armorBaseBonus);
                    break;
                case HEAD:
                    addToMap(hashmap, ARMOR_BASE_MODIFY, RegisterAttr.MEN, armorBaseBonus);
                    break;
                default:
                    break;
            }

            for (EnumBonusAttr attr: EnumBonusAttr.values()){
                if (stack.hasTagCompound() && stack.getTagCompound().hasKey(attr.name))
                {
                    int value = IDLNBTUtil.GetInt(stack, attr.name);
                    if (value > 0)
                    {
                        addToMap(hashmap, ARMOR_MODIFIERS_VANILLA[slot.getIndex()], attr.attribute, value);
                    }
                }
            }
        }

        return hashmap;
    }
}
