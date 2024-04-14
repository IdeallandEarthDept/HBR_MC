package com.deeplake.hbr_mc.entities.npc.e31;

import com.deeplake.hbr_mc.entities.npc.EntityNpcMelee;
import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityOhshima2 extends EntityNpcMelee {
    public EntityOhshima2(World worldIn) {
        super(worldIn);
    }

    @Override
    public void onFirstTickInLife() {
        super.onFirstTickInLife();
        ItemStack stack = new ItemStack(RegisterItem.IDOL_PLANET);
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND,stack);
    }
}
