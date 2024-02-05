package com.deeplake.hbr_mc.designs;

import net.minecraft.entity.ai.attributes.IAttribute;

public class SeraphBoostUnit {
    public final EnumBoostType type;
    public final float value;
    public final IAttribute attr;

    public SeraphBoostUnit(IAttribute attr, float value, EnumBoostType type) {
        this.type = type;
        this.value = value;
        this.attr = attr;
    }

    public SeraphBoostUnit(IAttribute attr, float value) {
        this.type = EnumBoostType.FIXED;
        this.value = value;
        this.attr = attr;
    }
}
