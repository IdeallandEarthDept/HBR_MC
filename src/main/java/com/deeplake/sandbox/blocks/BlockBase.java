package com.deeplake.sandbox.blocks;

import com.deeplake.sandbox.ModTabs;
import com.deeplake.sandbox.init.RegisterUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block {
    public BlockBase(String name, Material materialIn) {
        super(materialIn);
        setCreativeTab(ModTabs.TAB1);
        RegisterUtil.initBlock(this, name);
    }
}
