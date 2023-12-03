package com.deeplake.sandbox.items.seraph;

import com.deeplake.sandbox.ModTabs;
import com.deeplake.sandbox.init.RegisterUtil;
import com.deeplake.sandbox.items.ItemBase;
import javafx.scene.paint.Material;
import net.minecraft.item.ItemSword;

public class ItemSeraphBase extends ItemSword {
    public ItemSeraphBase(String name) {
        super(ToolMaterial.DIAMOND);
        RegisterUtil.initItem(this, name);
        setCreativeTab(ModTabs.TAB1);
    }
}
