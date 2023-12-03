package com.deeplake.sandbox.items;

import com.deeplake.sandbox.ModTabs;
import com.deeplake.sandbox.init.RegisterUtil;
import net.minecraft.item.ItemBow;

public class ItemBowBase extends ItemBow {
    public ItemBowBase(String name) {
        super();
        RegisterUtil.initItem(this, name);
        setCreativeTab(ModTabs.TAB1);
    }
}
