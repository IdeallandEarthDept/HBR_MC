package com.deeplake.hbr_mc.items;

import com.deeplake.hbr_mc.ModTabs;
import com.deeplake.hbr_mc.init.RegisterUtil;
import net.minecraft.item.Item;

public class ItemBase extends Item {

    public ItemBase(String name)
    {
        super();
        RegisterUtil.initItem(this, name);
        setCreativeTab(ModTabs.TAB1);
    }

}
