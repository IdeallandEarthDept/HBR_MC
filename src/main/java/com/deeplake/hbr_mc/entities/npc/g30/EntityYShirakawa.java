package com.deeplake.hbr_mc.entities.npc.g30;

import com.deeplake.hbr_mc.entities.npc.EntityNpcMelee;
import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityYShirakawa extends EntityNpcMelee {
    public EntityYShirakawa(World worldIn) {
        super(worldIn);
    }

    @Override
    public void initEquip() {
        super.initEquip();
        ItemStack stack = new ItemStack(RegisterItem.IDOL_PLANET);
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND,stack);
    }
}
