package com.deeplake.hbr_mc;

import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModTabs {
    public static final CreativeTabs TAB1 = new CreativeTabs(CreativeTabs.getNextID(), "hbr_mc.tab1")
    {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(RegisterItem.BRAVE_BLUE);
        }
    };

    public static final CreativeTabs TAB_EGG = new CreativeTabs(CreativeTabs.getNextID(), "hbr_mc.tab2")
    {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(RegisterItem.BETTER_SPAWNER);
        }
    };
}
