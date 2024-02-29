package com.deeplake.hbr_mc.entities.npc.a31;

import com.deeplake.hbr_mc.entities.npc.EntityNpcMelee;
import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityLKayamori extends EntityNpcMelee {
    public EntityLKayamori(World worldIn) {
        super(worldIn);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (source == DamageSource.DROWN)
        {
            return super.attackEntityFrom(source, amount * 3f);
        }
        return super.attackEntityFrom(source, amount);
    }

    @Override
    public void onFirstTickInLife() {
        super.onFirstTickInLife();
        ItemStack stack = new ItemStack(RegisterItem.BRAVE_BLUE_S);
        setItemStackToSlot(EntityEquipmentSlot.MAINHAND,stack);
        setItemStackToSlot(EntityEquipmentSlot.OFFHAND,stack.copy());
    }
}
