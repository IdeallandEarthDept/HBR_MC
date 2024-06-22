package com.deeplake.hbr_mc.entities.npc.d31;

import com.deeplake.hbr_mc.entities.npc.EntityNpcMelee;
import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityAMizuhara extends EntityNpcMelee {
    public EntityAMizuhara(World worldIn) {
        super(worldIn);
    }

    @Override
    public void initEquip() {
        super.initEquip();
        ItemStack stack = new ItemStack(RegisterItem.LAUGHING_DIVER);
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND,stack);
    }
}
