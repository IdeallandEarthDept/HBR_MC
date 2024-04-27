package com.deeplake.hbr_mc.entities.npc.e31;

import com.deeplake.hbr_mc.entities.npc.EntityNpcMelee;
import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityOhshima6 extends EntityNpcMelee {
    public EntityOhshima6(World worldIn) {
        super(worldIn);
    }

    @Override
    public void initEquip() {
        super.initEquip();
        ItemStack stack = new ItemStack(RegisterItem.FLAVOR_RAIN);
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND,stack);
    }
}
