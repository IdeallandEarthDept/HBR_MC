package com.deeplake.hbr_mc.entities.npc.f31;

import com.deeplake.hbr_mc.entities.npc.EntityNpcMelee;
import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityMKurosawa extends EntityNpcMelee {
    public EntityMKurosawa(World worldIn) {
        super(worldIn);
    }

    @Override
    public void onFirstTickInLife() {
        super.onFirstTickInLife();
        ItemStack stack = new ItemStack(RegisterItem.AMAZING_EXECUTER);
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND,stack);
    }
}
