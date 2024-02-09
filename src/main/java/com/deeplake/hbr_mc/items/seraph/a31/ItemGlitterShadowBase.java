package com.deeplake.hbr_mc.items.seraph.a31;

import com.deeplake.hbr_mc.items.seraph.EnumSeraphRarity;
import com.deeplake.hbr_mc.items.seraph.EnumSeraphType;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphBlaster;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemGlitterShadowBase extends ItemSeraphBlaster {
    public ItemGlitterShadowBase(String name) {
        super(name, EnumSeraphType.SCYTHE);
    }
    public ItemGlitterShadowBase(String name, EnumSeraphRarity rarity) {
        super(name, EnumSeraphType.SCYTHE, rarity);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
        EntityPlayer player = (EntityPlayer) entityIn;
        if (!isSelected)
        {
            player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 10, 1, true, false));
            player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 10, 1, true, false));
        }else {
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 10, 1, true, false));
            player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 10, 1, true, false));
        }
    }
}
