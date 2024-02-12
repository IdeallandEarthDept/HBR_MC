package com.deeplake.hbr_mc.init;

import com.deeplake.hbr_mc.init.util.ChancePicker;

public class RegisterDrop {
    public static void initCrateList()
    {
        ChancePicker picker = RegisterBlocks.RANDOM_DROP_BASIC.dropList;

        picker.addTuple(10, RegisterItem.XP_ITEM_1, 1,5);
        picker.addTuple(10, RegisterItem.XP_ITEM_2, 1,2);
        picker.addTuple(10, RegisterItem.GEM_DROPLET, 1,5);
        picker.addTuple(5, RegisterItem.GEM_AMBER, 1,1);
    }
}
