package com.deeplake.hbr_mc.designs;

import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.items.seraph.EnumSeraphClass;
import com.deeplake.hbr_mc.items.seraph.EnumSeraphRarity;
import net.minecraft.entity.ai.attributes.IAttribute;

import java.util.*;

public class SeraphAttrData {
    public static HashMap<IAttribute, Float> fighterA;
    public static HashMap<IAttribute, Float> rangerA;
    public static HashMap<IAttribute, Float> wizardA;

    public static HashMap<IAttribute, Float> fighterSS;
    public static HashMap<IAttribute, Float> rangerSS;
    public static HashMap<IAttribute, Float> wizardSS;

    public static List<List<SeraphBoostUnit>> fighterBoostSS;
    public static List<List<SeraphBoostUnit>> rangerBoostSS;
    public static List<List<SeraphBoostUnit>> wizardBoostSS;

    public static HashMap<EnumSeraphClass, List<SeraphBoostUnit>> boostSS;

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
        initA(aBuff, buffRatio);
        initSS();

        boostSS = new HashMap<>();

        //STR
        List<List<SeraphBoostUnit>> totalList = new ArrayList<>();
        SSBoostSTR(totalList);
        fighterBoostSS = totalList;

        totalList = new ArrayList<>();
        SSBoostDEX(totalList);
        rangerBoostSS = totalList;

        totalList = new ArrayList<>();
        SSBoostWIS(totalList);
        wizardBoostSS = totalList;
    }

    private static void SSBoostWIS(List<List<SeraphBoostUnit>> totalList) {
        List<SeraphBoostUnit> unitList = new ArrayList<>();
        unitList.add(new SeraphBoostUnit(RegisterAttr.INT, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.MEN, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.STR, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.INT, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.DEX, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.LUC, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.END, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.MEN, 0.1f, EnumBoostType.PERCENT));
        totalList.add(unitList);
        List<SeraphBoostUnit> flatList = new ArrayList<>(unitList);

        //group 2
        unitList = new ArrayList<>();
        unitList.add(new SeraphBoostUnit(RegisterAttr.INT, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.MEN, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.STR, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.STR,2));
        unitList.add(new SeraphBoostUnit(null,1));
        unitList.add(new SeraphBoostUnit(RegisterAttr.DEX,2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.LUC,2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.DEX, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.END, 2));
        //hit combo +1%
        unitList.add(new SeraphBoostUnit(RegisterAttr.INT, 1));//global
        totalList.add(unitList);
        flatList.addAll(unitList);

        //group 3
        unitList = new ArrayList<>();
        unitList.add(new SeraphBoostUnit(RegisterAttr.INT, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.MEN, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.INT, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.STR,2));
        unitList.add(new SeraphBoostUnit(null,1));
        unitList.add(new SeraphBoostUnit(RegisterAttr.DEX,2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.LUC,2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.MEN, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.END, 2));
        unitList.add(new SeraphBoostUnit(null,1));
        unitList.add(new SeraphBoostUnit(RegisterAttr.INT, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.MEN, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.LUC, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.STR,2));
        unitList.add(new SeraphBoostUnit(null,1));
        unitList.add(new SeraphBoostUnit(RegisterAttr.DEX,2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.LUC,2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.END, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.END, 2));
        //hit combo +1%
        unitList.add(new SeraphBoostUnit(RegisterAttr.MEN, 2));//global
        totalList.add(unitList);
        flatList.addAll(unitList);

        //group 4
        unitList = new ArrayList<>();
        unitList.add(new SeraphBoostUnit(RegisterAttr.INT, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.MEN, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.DEX, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.STR, 2));
        unitList.add(new SeraphBoostUnit(null,1));
        unitList.add(new SeraphBoostUnit(RegisterAttr.DEX, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.INT, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.STR, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.MEN, 2));
        //hit combo +1%
        unitList.add(new SeraphBoostUnit(RegisterAttr.LUC, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.END, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.LUC, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.INT, 2));
        unitList.add(new SeraphBoostUnit(null,1));
        unitList.add(new SeraphBoostUnit(RegisterAttr.MEN, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.STR, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.INT, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.DEX, 2));
        //hit combo +1%
        unitList.add(new SeraphBoostUnit(RegisterAttr.INT, 2));//global
        totalList.add(unitList);
        flatList.addAll(unitList);
        boostSS.put(EnumSeraphClass.WIZARD, flatList);
    }

    private static void SSBoostDEX(List<List<SeraphBoostUnit>> totalList) {
        List<SeraphBoostUnit> unitList = new ArrayList<>();
        unitList.add(new SeraphBoostUnit(RegisterAttr.DEX, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.LUC, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.END, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.DEX, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.INT, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.MEN, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.STR, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.LUC, 0.1f, EnumBoostType.PERCENT));
        totalList.add(unitList);
        List<SeraphBoostUnit> flatList = new ArrayList<>(unitList);

        //group 2
        unitList = new ArrayList<>();
        unitList.add(new SeraphBoostUnit(RegisterAttr.DEX, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.LUC, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.END, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.END,2));
        unitList.add(new SeraphBoostUnit(null,1));
        unitList.add(new SeraphBoostUnit(RegisterAttr.INT,2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.MEN,2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.INT, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.STR, 2));
        //hit combo +1%
        unitList.add(new SeraphBoostUnit(RegisterAttr.DEX, 1));//global
        totalList.add(unitList);
        flatList.addAll(unitList);

        //group 3
        unitList = new ArrayList<>();
        unitList.add(new SeraphBoostUnit(RegisterAttr.DEX, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.LUC, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.DEX, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.END,2));
        unitList.add(new SeraphBoostUnit(null,1));
        unitList.add(new SeraphBoostUnit(RegisterAttr.INT,2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.MEN,2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.LUC, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.STR, 2));
        unitList.add(new SeraphBoostUnit(null,1));
        unitList.add(new SeraphBoostUnit(RegisterAttr.DEX, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.LUC, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.MEN, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.END,2));
        unitList.add(new SeraphBoostUnit(null,1));
        unitList.add(new SeraphBoostUnit(RegisterAttr.INT,2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.MEN,2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.STR, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.STR, 2));
        //hit combo +1%
        unitList.add(new SeraphBoostUnit(RegisterAttr.LUC, 2));//global
        totalList.add(unitList);
        flatList.addAll(unitList);

        //group 4
        unitList = new ArrayList<>();
        unitList.add(new SeraphBoostUnit(RegisterAttr.DEX, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.LUC, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.INT, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.END, 2));
        unitList.add(new SeraphBoostUnit(null,1));
        unitList.add(new SeraphBoostUnit(RegisterAttr.INT, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.DEX, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.END, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.LUC, 2));
        //hit combo +1%
        unitList.add(new SeraphBoostUnit(RegisterAttr.MEN, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.STR, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.MEN, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.DEX, 2));
        unitList.add(new SeraphBoostUnit(null,1));
        unitList.add(new SeraphBoostUnit(RegisterAttr.LUC, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.END, 2));
        unitList.add(new SeraphBoostUnit(RegisterAttr.DEX, 0.1f, EnumBoostType.PERCENT));
        unitList.add(new SeraphBoostUnit(RegisterAttr.INT, 2));
        //hit combo +1%
        unitList.add(new SeraphBoostUnit(RegisterAttr.DEX, 2));//global
        totalList.add(unitList);
        flatList.addAll(unitList);
        boostSS.put(EnumSeraphClass.RANGER, flatList);
    }

    private static void SSBoostSTR(List<List<SeraphBoostUnit>> totalList) {
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
        List<SeraphBoostUnit> flatList = new ArrayList<>(unitList);

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
        flatList.addAll(unitList);

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
        flatList.addAll(unitList);

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
        flatList.addAll(unitList);
        boostSS.put(EnumSeraphClass.RANGER, flatList);
    }

    private static void initA(float aBuff, HashMap<IAttribute, Float> buffRatio) {
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
    }

    private static void initSS() {
        HashMap<IAttribute, Float> buffRatio;
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
    }


}
