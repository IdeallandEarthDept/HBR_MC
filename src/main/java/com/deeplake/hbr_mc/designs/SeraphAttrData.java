package com.deeplake.hbr_mc.designs;

import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.items.seraph.EnumSeraphClass;
import com.deeplake.hbr_mc.items.seraph.EnumSeraphRarity;
import net.minecraft.entity.ai.attributes.IAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SeraphAttrData {
    public static HashMap<IAttribute, Float> fighterA;
    public static HashMap<IAttribute, Float> rangerA;
    public static HashMap<IAttribute, Float> wizardA;

    public static HashMap<IAttribute, Float> fighterSS;
    public static HashMap<IAttribute, Float> rangerSS;
    public static HashMap<IAttribute, Float> wizardSS;

    public static List<List<SeraphBoostUnit>> fighterBoostSS;

    public static HashMap<IAttribute, Float> getData(EnumSeraphRarity rarity, EnumSeraphClass enumSeraphClass)
    {
        switch (rarity) {
            case SS:
                switch (enumSeraphClass) {
                    case FIGHTER:
                        return fighterSS;
                    case RANGER:
                        return rangerSS;
                    case WIZARD:
                        return wizardSS;
                    default:
                        throw new IllegalStateException("Unexpected value: " + enumSeraphClass);
                }
            case S:
                return fighterA;
            case A:
                switch (enumSeraphClass) {
                    case FIGHTER:
                        return fighterA;
                    case RANGER:
                        return rangerA;
                    case WIZARD:
                        return wizardA;
                    default:
                        throw new IllegalStateException("Unexpected value: " + enumSeraphClass);
                }
            default:
                throw new IllegalStateException("Unexpected value: " + rarity);
        }
    }

    static
    {
        float aBuff = 0.2f;
        HashMap<IAttribute, Float> buffRatio = new HashMap<>();
        //DP +30%+0
        buffRatio.put(RegisterAttr.STR,0.4f  - aBuff);
        buffRatio.put(RegisterAttr.DEX,0.2f  - aBuff);
        buffRatio.put(RegisterAttr.END,0.4f  - aBuff);
        buffRatio.put(RegisterAttr.MEN,0.35f - aBuff);
        buffRatio.put(RegisterAttr.INT,0.2f  - aBuff);
        buffRatio.put(RegisterAttr.LUC,0.35f - aBuff);
        fighterA = buffRatio;

        buffRatio = new HashMap<>();
        buffRatio.put(RegisterAttr.STR,0.20f  - aBuff);
        buffRatio.put(RegisterAttr.DEX,0.40f  - aBuff);
        buffRatio.put(RegisterAttr.END,0.35f - aBuff);
        buffRatio.put(RegisterAttr.MEN,0.20f - aBuff);
        buffRatio.put(RegisterAttr.INT,0.35f  - aBuff);
        buffRatio.put(RegisterAttr.LUC,0.40f - aBuff);
        rangerA = buffRatio;

        buffRatio = new HashMap<>();
        buffRatio.put(RegisterAttr.STR,0.35f  - aBuff);
        buffRatio.put(RegisterAttr.DEX,0.35f  - aBuff);
        buffRatio.put(RegisterAttr.END,0.20f - aBuff);
        buffRatio.put(RegisterAttr.MEN,0.40f - aBuff);
        buffRatio.put(RegisterAttr.INT,0.40f  - aBuff);
        buffRatio.put(RegisterAttr.LUC,0.20f - aBuff);
        wizardA = buffRatio;

        //DP+70%+200
        //All+15
        buffRatio = new HashMap<>();
        buffRatio.put(RegisterAttr.STR,0.35f);
        buffRatio.put(RegisterAttr.DEX,0.35f);
        buffRatio.put(RegisterAttr.END,0.30f);//A+0.1
        buffRatio.put(RegisterAttr.MEN,0.40f);
        buffRatio.put(RegisterAttr.INT,0.40f);
        buffRatio.put(RegisterAttr.LUC,0.30f);//A+0.1
        wizardSS = buffRatio;

        //DP+70%+200
        //All+15
        buffRatio = new HashMap<>();
        buffRatio.put(RegisterAttr.STR,0.40f);
        buffRatio.put(RegisterAttr.DEX,0.30f);
        buffRatio.put(RegisterAttr.END,0.40f);
        buffRatio.put(RegisterAttr.MEN,0.35f);
        buffRatio.put(RegisterAttr.INT,0.30f);
        buffRatio.put(RegisterAttr.LUC,0.35f);
        fighterSS = buffRatio;

        buffRatio = new HashMap<>();
        buffRatio.put(RegisterAttr.STR,0.30f);
        buffRatio.put(RegisterAttr.DEX,0.40f);
        buffRatio.put(RegisterAttr.END,0.35f);
        buffRatio.put(RegisterAttr.MEN,0.30f);
        buffRatio.put(RegisterAttr.INT,0.35f);
        buffRatio.put(RegisterAttr.LUC,0.40f);
        rangerSS = buffRatio;

        //STR
        List<List<SeraphBoostUnit>> totalList = new ArrayList<>();
        List<SeraphBoostUnit> unitList = new ArrayList<>();
        unitList.add(new SeraphBoostUnit(RegisterAttr.STR, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.END, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.MEN, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.STR, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.LUC, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.INT, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.DEX, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.END, 0.1f, EnumBoostType.PERCENT));
        totalList.add(unitList);

        unitList = new ArrayList<>();
        unitList.add(new SeraphBoostUnit(RegisterAttr.STR, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.END, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.MEN, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.MEN,2));
        unitList.add(new SeraphBoostUnit(null,1));
        unitList.add(new SeraphBoostUnit(RegisterAttr.LUC,2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.INT,2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.LUC, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.DEX, 2));
        //hit combo +1%
        unitList.add(new SeraphBoostUnit(RegisterAttr.STR, 1));//global
        totalList.add(unitList);

        unitList = new ArrayList<>();
        unitList.add(new SeraphBoostUnit(RegisterAttr.STR, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.END, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.MEN, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.MEN,2));
        unitList.add(new SeraphBoostUnit(null,1));
        unitList.add(new SeraphBoostUnit(RegisterAttr.LUC,2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.INT,2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.END, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.DEX, 2));
        unitList.add(new SeraphBoostUnit(null,1));
        unitList.add(new SeraphBoostUnit(RegisterAttr.STR, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.END, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.INT, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.MEN,2));
        unitList.add(new SeraphBoostUnit(null,1));
        unitList.add(new SeraphBoostUnit(RegisterAttr.LUC,2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.INT,2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.DEX, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.DEX, 2));
        //hit combo +1%
        unitList.add(new SeraphBoostUnit(RegisterAttr.END, 2));//global
        totalList.add(unitList);

        unitList = new ArrayList<>();
        unitList.add(new SeraphBoostUnit(RegisterAttr.STR, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.END, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.LUC, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.MEN, 2));
        unitList.add(new SeraphBoostUnit(null,1));
        unitList.add(new SeraphBoostUnit(RegisterAttr.LUC, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.STR, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.MEN, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.END, 2));
        //hit combo +1%
        unitList.add(new SeraphBoostUnit(RegisterAttr.INT, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.DEX, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.INT, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.STR, 2));
        unitList.add(new SeraphBoostUnit(null,1));
        unitList.add(new SeraphBoostUnit(RegisterAttr.END, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.MEN, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.STR, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.LUC, 2));
        //hit combo +1%
        unitList.add(new SeraphBoostUnit(RegisterAttr.STR, 2));//global
        totalList.add(unitList);
        fighterBoostSS = totalList;

        unitList = new ArrayList<>();
    }


}
