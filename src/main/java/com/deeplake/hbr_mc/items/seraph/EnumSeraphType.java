package com.deeplake.hbr_mc.items.seraph;

public enum EnumSeraphType {

    DOUBLE_SWORD(EnumSeraphAtkMode.SLASH,2),
    CANNON(EnumSeraphAtkMode.SHOOT,3),
    LARGE_SWORD(EnumSeraphAtkMode.SMASH,1),
    GUN(EnumSeraphAtkMode.SHOOT,3),
    SWORD(EnumSeraphAtkMode.SLASH,3),
    ;

    EnumSeraphType(EnumSeraphAtkMode mode, int hitCount) {
        this.mode = mode;
        this.hitCount = hitCount;
    }

    public final EnumSeraphAtkMode mode;
    public final int hitCount;

}
