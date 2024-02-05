package com.deeplake.hbr_mc.entities.npc.a31;

import com.deeplake.hbr_mc.entities.npc.EntityNpcMelee;
import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityKAsakura extends EntityNpcMelee {
    public EntityKAsakura(World worldIn) {
        super(worldIn);
    }

    @Override
    public void onFirstTickInLife() {
        super.onFirstTickInLife();
        ItemStack stack = new ItemStack(RegisterItem.GLITTER_SHADOW_S);
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND,stack);
    }
}
