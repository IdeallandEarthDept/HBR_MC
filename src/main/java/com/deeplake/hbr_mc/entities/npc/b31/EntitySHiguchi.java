package com.deeplake.hbr_mc.entities.npc.b31;

import com.deeplake.hbr_mc.entities.npc.EntityNpcRanged;
import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntitySHiguchi extends EntityNpcRanged {
    public EntitySHiguchi(World worldIn) {
        super(worldIn);
    }

    @Override
    public void onFirstTickInLife() {
        super.onFirstTickInLife();
        ItemStack stack = new ItemStack(RegisterItem.FATAL_NULL);
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND,stack);
    }
}
