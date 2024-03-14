package com.deeplake.hbr_mc.entities.npc.b31;

import com.deeplake.hbr_mc.entities.npc.EntityNpcMelee;
import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntitySMinase extends EntityNpcMelee {
    public EntitySMinase(World worldIn) {
        super(worldIn);
    }

    @Override
    public void onFirstTickInLife() {
        super.onFirstTickInLife();
        ItemStack stack = new ItemStack(RegisterItem.RAINNY_LULL);
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND,stack);
    }
}
