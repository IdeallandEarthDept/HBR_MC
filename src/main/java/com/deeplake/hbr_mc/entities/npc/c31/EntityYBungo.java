package com.deeplake.hbr_mc.entities.npc.c31;

import com.deeplake.hbr_mc.entities.npc.EntityNpcRanged;
import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityYBungo extends EntityNpcRanged {
    public EntityYBungo(World worldIn) {
        super(worldIn);
    }

    @Override
    public void initEquip() {
        super.initEquip();
        ItemStack stack = new ItemStack(RegisterItem.SCARLET_VALET_S);
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND,stack);
    }
}
