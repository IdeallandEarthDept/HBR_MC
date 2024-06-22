package com.deeplake.hbr_mc.entities.npc.d31;

import com.deeplake.hbr_mc.entities.npc.EntityNpcMelee;
import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityADate extends EntityNpcMelee {
    public EntityADate(World worldIn) {
        super(worldIn);
    }

    @Override
    public void initEquip() {
        super.initEquip();
        ItemStack stack = new ItemStack(RegisterItem.FALLING_EXPECTER);
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND,stack);
    }
}
