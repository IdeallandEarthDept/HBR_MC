package com.deeplake.hbr_mc.items;

import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.init.util.IDLNBTUtil;
import com.google.common.collect.Multimap;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;
import java.util.UUID;

import static com.deeplake.hbr_mc.items.ItemArmorCancer.ARMOR_MODIFIERS_VANILLA;

public class ItemCirclet extends ItemBase {
    public ItemCirclet(String name) {
        super(name);
    }

    @Nullable
    @Override
    public EntityEquipmentSlot getEquipmentSlot(ItemStack stack) {
        return EntityEquipmentSlot.HEAD;
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        Multimap<String, AttributeModifier> hashmap = super.getAttributeModifiers(slot, stack);

        switch (slot)
        {
            case OFFHAND:
            case HEAD:
                addToMap(hashmap, ARMOR_MODIFIERS_VANILLA[slot.getIndex()], SharedMonsterAttributes.LUCK, 1f);
                break;
            default:
                break;
        }

        return hashmap;
    }

    private void addToMap(Multimap<String, AttributeModifier> multimap, UUID uuid, IAttribute str, float amount) {
        multimap.put(str.getName(), new AttributeModifier(uuid, "armor_modifier", amount, 0));
    }
}
