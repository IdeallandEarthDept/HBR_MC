package com.deeplake.hbr_mc.entities.npc.a31;

import com.deeplake.hbr_mc.entities.npc.EntityNpcRanged;
import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityTTsukasa extends EntityNpcRanged {
    public EntityTTsukasa(World worldIn) {
        super(worldIn);
    }

    @Override
    public void onFirstTickInLife() {
        super.onFirstTickInLife();
        ItemStack stack = new ItemStack(RegisterItem.SUPERME_EDGE_S);
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND,stack);
    }
}
