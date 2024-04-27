package com.deeplake.hbr_mc.entities.npc.e31;

import com.deeplake.hbr_mc.entities.npc.EntityNpcMelee;
import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityOhshima3 extends EntityNpcMelee {
    public EntityOhshima3(World worldIn) {
        super(worldIn);
    }

    @Override
    public void initEquip() {
        super.initEquip();
        ItemStack stack = new ItemStack(RegisterItem.LIGHT_BEAT);
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND,stack);
    }
}
