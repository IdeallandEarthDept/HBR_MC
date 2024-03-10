package com.deeplake.hbr_mc.entities.npc.b31;

import com.deeplake.hbr_mc.entities.npc.EntityNpcRanged;
import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityKHiiragi extends EntityNpcRanged {
    public EntityKHiiragi(World worldIn) {
        super(worldIn);
    }

    @Override
    public void onFirstTickInLife() {
        super.onFirstTickInLife();
        ItemStack stack = new ItemStack(RegisterItem.GLITTER_SHADOW_S);
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND,stack);
    }
}
