package com.deeplake.hbr_mc.entities.npc.a31;

import com.deeplake.hbr_mc.entities.npc.EntityNpcRanged;
import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityYIzumi extends EntityNpcRanged {
    public EntityYIzumi(World worldIn) {
        super(worldIn);
    }

    @Override
    public void onFirstTickInLife() {
        super.onFirstTickInLife();
        ItemStack stack = new ItemStack(RegisterItem.RAPID_FIRE_S);
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND,stack);
    }
}
