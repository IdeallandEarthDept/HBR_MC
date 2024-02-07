package com.deeplake.hbr_mc.designs;

import com.deeplake.hbr_mc.items.seraph.EnumSeraphRarity;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphBase;
import net.minecraft.item.Item;

public class SeraphBoostConst {
    public static int getMaxBreakThrough(Item seraph)
    {
        if (seraph instanceof ItemSeraphBase)
        {
            EnumSeraphRarity seraphRarity = ((ItemSeraphBase) seraph).seraphRarity;
            return getMaxBreakThrough(seraphRarity);
        }
        return getMaxBreakThrough(EnumSeraphRarity.A);
    }

    public static int getMaxBreakThrough(EnumSeraphRarity seraphRarity)
    {
        switch (seraphRarity) {
            case SS:
                return 4;
            case S:
                return 10;
            case A:
                return 20;
            default:
                throw new IllegalStateException("Unexpected value: " + seraphRarity);
        }
    }

    public static int getMaxBoost(Item seraph)
    {
        if (seraph instanceof ItemSeraphBase)
        {
            EnumSeraphRarity seraphRarity = ((ItemSeraphBase) seraph).seraphRarity;
            return getMaxBoost(seraphRarity);
        }
        return getMaxBoost(EnumSeraphRarity.A);
    }

    public static int getMaxBoost(EnumSeraphRarity seraphRarity)
    {
        switch (seraphRarity) {
            case SS:
                return 18;
            case S:
                return 38;
            case A:
                return 57;
            default:
                throw new IllegalStateException("Unexpected value: " + seraphRarity);
        }
    }
}
