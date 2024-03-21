package com.deeplake.hbr_mc.entities.npc.x31;

import com.deeplake.hbr_mc.entities.npc.EntityNpcMelee;
import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityMdAngelis extends EntityNpcMelee {
    public EntityMdAngelis(World worldIn) {
        super(worldIn);
    }

    @Override
    public void onFirstTickInLife() {
        super.onFirstTickInLife();
        ItemStack stack = new ItemStack(RegisterItem.BLOODY_SAGE_S);
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND,stack);
    }
}
