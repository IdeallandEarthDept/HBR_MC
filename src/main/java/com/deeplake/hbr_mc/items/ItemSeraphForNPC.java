package com.deeplake.hbr_mc.items;

import com.deeplake.hbr_mc.items.seraph.EnumSeraphType;

public class ItemSeraphForNPC extends ItemWIP{
    public final EnumSeraphType type;
    public ItemSeraphForNPC(String name, EnumSeraphType type) {
        super(name);
        this.type = type;
    }
}
