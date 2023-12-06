package com.deeplake.hbr_mc.blocks;

import com.deeplake.hbr_mc.ModTabs;
import com.deeplake.hbr_mc.init.RegisterUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block {
    public BlockBase(String name, Material materialIn) {
        super(materialIn);
        setCreativeTab(ModTabs.TAB1);
        RegisterUtil.initBlock(this, name);
    }
}
