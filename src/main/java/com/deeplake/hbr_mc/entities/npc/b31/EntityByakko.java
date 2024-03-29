package com.deeplake.hbr_mc.entities.npc.b31;

import com.deeplake.hbr_mc.entities.npc.EntityNpcRanged;
import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityByakko extends EntityNpcRanged {
    public EntityByakko(World worldIn) {
        super(worldIn);
        this.setSize(0.8f, 2.0f);
    }

    @Override
    public void onFirstTickInLife() {
        super.onFirstTickInLife();
        ItemStack stack = new ItemStack(RegisterItem.FATAL_NULL);
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND,stack);
    }
}
