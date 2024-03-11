package com.deeplake.hbr_mc.potion_effects;

import com.deeplake.hbr_mc.init.util.CombatUtil;

public class ModPotionField extends ModPotionBase{
    final CombatUtil.EnumElement element;
    public ModPotionField(CombatUtil.EnumElement element, int liquidColorIn, String name, int icon) {
        super(false, liquidColorIn, name, icon);
        this.element = element;
    }
}
