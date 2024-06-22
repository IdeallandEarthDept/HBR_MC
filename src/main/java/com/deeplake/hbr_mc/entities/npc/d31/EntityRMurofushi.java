package com.deeplake.hbr_mc.entities.npc.d31;

import com.deeplake.hbr_mc.entities.npc.EntityNpcRanged;
import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityRMurofushi extends EntityNpcRanged {
    public EntityRMurofushi(World worldIn) {
        super(worldIn);
    }

    @Override
    public void initEquip() {
        super.initEquip();
        ItemStack stack = new ItemStack(RegisterItem.CRY_FLOWER);
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND,stack);
    }
}
