package com.deeplake.hbr_mc.entities.npc.c31;

import com.deeplake.hbr_mc.entities.npc.EntityNpcMelee;
import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityIYamawaki extends EntityNpcMelee {
    public EntityIYamawaki(World worldIn) {
        super(worldIn);
    }

    @Override
    public void initEquip() {
        super.initEquip();
        ItemStack stack = new ItemStack(RegisterItem.LOVE_REAPER_S);
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND,stack);
    }
}
