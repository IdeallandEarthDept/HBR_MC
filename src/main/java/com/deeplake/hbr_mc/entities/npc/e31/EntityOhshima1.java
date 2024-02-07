package com.deeplake.hbr_mc.entities.npc.e31;

import com.deeplake.hbr_mc.entities.npc.EntityNpcMelee;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityOhshima1 extends EntityNpcMelee {
    public EntityOhshima1(World worldIn) {
        super(worldIn);
    }

    @Override
    public void onFirstTickInLife() {
        super.onFirstTickInLife();
        ItemStack stack = new ItemStack(Items.SHIELD);
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND,stack);
    }
}
