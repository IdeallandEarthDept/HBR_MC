package com.deeplake.hbr_mc.entities.npc.command;

import com.deeplake.hbr_mc.entities.npc.EntityNpcMelee;
import net.minecraft.world.World;

public class EntitySTezuka extends EntityNpcMelee {
    public EntitySTezuka(World worldIn) {
        super(worldIn);
    }

    @Override
    public void initEquip() {
        super.initEquip();
//        ItemStack stack = new ItemStack(RegisterItem.KAZABANA);
//        setItemStackToSlot(EntityEquipmentSlot.MAINHAND,stack);
    }
}
