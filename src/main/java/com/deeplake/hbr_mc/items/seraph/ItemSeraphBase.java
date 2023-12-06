package com.deeplake.hbr_mc.items.seraph;

import com.deeplake.hbr_mc.ModTabs;
import com.deeplake.hbr_mc.init.RegisterUtil;
import net.minecraft.item.ItemSword;

public class ItemSeraphBase extends ItemSword {
    public ItemSeraphBase(String name) {
        super(ToolMaterial.DIAMOND);
        RegisterUtil.initItem(this, name);
        setCreativeTab(ModTabs.TAB1);
    }
}
