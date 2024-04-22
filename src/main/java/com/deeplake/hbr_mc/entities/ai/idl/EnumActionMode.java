package com.deeplake.hbr_mc.entities.ai.idl;

import com.deeplake.hbr_mc.init.util.CommonFunctions;

public enum EnumActionMode {
    NONE,
    ATTACK,
    DEFEND,
    BETRAY,
    FOLLOW;

    public static EnumActionMode fromInt(int val)
    {
        val = CommonFunctions.clamp(val, 0, EnumActionMode.values().length-1);
        return EnumActionMode.values()[val];
    }
}
