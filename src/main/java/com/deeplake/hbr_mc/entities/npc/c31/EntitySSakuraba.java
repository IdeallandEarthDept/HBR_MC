package com.deeplake.hbr_mc.entities.npc.c31;

import com.deeplake.hbr_mc.entities.npc.EntityNpcMelee;
import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntitySSakuraba extends EntityNpcMelee {
    public EntitySSakuraba(World worldIn) {
        super(worldIn);
    }

    @Override
    public void onFirstTickInLife() {
        super.onFirstTickInLife();
        ItemStack stack = new ItemStack(RegisterItem.POP_N_GORE);
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND,stack);
    }
}
