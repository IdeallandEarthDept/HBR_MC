package com.deeplake.hbr_mc.entities.npc.a31;

import com.deeplake.hbr_mc.entities.npc.EntityNpcMelee;
import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityTKunimi extends EntityNpcMelee {
    public EntityTKunimi(World worldIn) {
        super(worldIn);
        can_swim = false;
    }

    @Override
    public void initEquip() {
        super.initEquip();
        ItemStack stack = new ItemStack(RegisterItem.PHANTOM_WEAVER_S);
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND,stack);
    }
}
