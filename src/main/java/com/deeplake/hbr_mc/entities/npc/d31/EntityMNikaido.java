package com.deeplake.hbr_mc.entities.npc.d31;

import com.deeplake.hbr_mc.entities.npc.EntityNpcMelee;
import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityMNikaido extends EntityNpcMelee {
    public EntityMNikaido(World worldIn) {
        super(worldIn);
    }

    @Override
    public void initEquip() {
        super.initEquip();
        ItemStack stack = new ItemStack(RegisterItem.SHOOTING_STAR);
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND,stack);
    }
}
