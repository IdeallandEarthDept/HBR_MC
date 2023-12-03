package com.deeplake.sandbox.items;

import com.deeplake.sandbox.ModTabs;
import com.deeplake.sandbox.init.RegisterUtil;
import net.minecraft.item.Item;

public class ItemBase extends Item {

    public ItemBase(String name)
    {
        super();
        RegisterUtil.initItem(this, name);
        setCreativeTab(ModTabs.TAB1);
    }

}
