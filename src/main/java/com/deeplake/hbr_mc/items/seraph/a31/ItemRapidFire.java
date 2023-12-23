package com.deeplake.hbr_mc.items.seraph.a31;

import com.deeplake.hbr_mc.items.seraph.ItemSeraphCannonBase;
import net.minecraft.item.ItemStack;

//Yuki Izumi
//Attack or March
public class ItemRapidFire extends ItemSeraphCannonBase {
    public ItemRapidFire(String name) {
        super(name);
    }

    @Override
    public int getMaxSkillSlot(ItemStack stack) {
        return 0;
    }
}
