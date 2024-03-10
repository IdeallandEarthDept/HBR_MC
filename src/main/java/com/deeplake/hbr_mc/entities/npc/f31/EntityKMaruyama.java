package com.deeplake.hbr_mc.entities.npc.f31;

import com.deeplake.hbr_mc.entities.npc.EntityNpcRanged;
import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityKMaruyama extends EntityNpcRanged {
    public EntityKMaruyama(World worldIn) {
        super(worldIn);
    }

    @Override
    public void onFirstTickInLife() {
        super.onFirstTickInLife();
        ItemStack stack = new ItemStack(RegisterItem.CRY_FLOWER);
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND,stack);
        set6Attr(1);
        setDPMax(10);
    }
}
