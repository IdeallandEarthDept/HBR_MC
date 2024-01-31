package com.deeplake.hbr_mc.designs;

import com.deeplake.hbr_mc.init.RegisterAttr;
import net.minecraft.entity.ai.attributes.IAttribute;

import java.util.HashMap;

public class SeraphAttrData {
    public static HashMap<IAttribute, Float> fighterA;
    public static void getData()
    {
        HashMap<IAttribute, Float> buffRatio = new HashMap<>();
        buffRatio.put(RegisterAttr.STR,0.4f);
        buffRatio.put(RegisterAttr.DEX,0.2f);
        buffRatio.put(RegisterAttr.END,0.4f);
        buffRatio.put(RegisterAttr.MEN,0.35f);
        buffRatio.put(RegisterAttr.INT,0.2f);
        buffRatio.put(RegisterAttr.LUC,0.35f);
    }
}
