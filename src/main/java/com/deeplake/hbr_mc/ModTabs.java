package com.deeplake.hbr_mc;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModTabs {
    public static final CreativeTabs TAB1 = new CreativeTabs(CreativeTabs.getNextID(), "hbr_mc.tab1")
    {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(Items.BED);
        }
    };
}
