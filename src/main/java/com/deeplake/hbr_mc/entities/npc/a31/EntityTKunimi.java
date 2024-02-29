package com.deeplake.hbr_mc.entities.npc.a31;

import com.deeplake.hbr_mc.entities.npc.EntityNpcMelee;
import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityTKunimi extends EntityNpcMelee {
    public EntityTKunimi(World worldIn) {
        super(worldIn);
        canSwim = false;
    }

    @Override
    public void onFirstTickInLife() {
        super.onFirstTickInLife();
        ItemStack stack = new ItemStack(RegisterItem.PHANTOM_WEAVER_S);
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND,stack);
    }
}
