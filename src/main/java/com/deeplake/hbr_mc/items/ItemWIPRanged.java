package com.deeplake.hbr_mc.items;

import com.deeplake.hbr_mc.init.util.CommonDef;
import com.deeplake.hbr_mc.items.seraph.EnumSeraphType;
import net.minecraft.item.ItemStack;

public class ItemWIPRanged extends ItemSeraphForNPC{
    public ItemWIPRanged(String name, EnumSeraphType type) {
        super(name, type);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 3 * CommonDef.TICK_PER_SECOND;
    }
}
