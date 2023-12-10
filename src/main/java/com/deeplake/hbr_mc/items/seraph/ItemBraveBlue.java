package com.deeplake.hbr_mc.items.seraph;

import com.deeplake.hbr_mc.init.RegisterAttr;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.item.ItemStack;

//Ruka Kayamori
public class ItemBraveBlue extends ItemSeraphBase{
    public ItemBraveBlue(String name) {
        super(name);
    }

    public float getAttrValue(ItemStack stack, IAttribute attr)
    {
        if (attr == RegisterAttr.STR)
        {
            return super.getAttrValue(stack, attr) + 1;
        }
        return super.getAttrValue(stack, attr);
    }
}
