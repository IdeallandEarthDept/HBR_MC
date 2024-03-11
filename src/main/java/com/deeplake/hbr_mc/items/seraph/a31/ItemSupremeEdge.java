package com.deeplake.hbr_mc.items.seraph.a31;

import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.items.seraph.EnumSeraphType;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphCannonBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

//Tsukasa Tojo
//Serious or Stupid
public class ItemSupremeEdge extends ItemSeraphCannonBase {
    public ItemSupremeEdge(String name) {
        super(name, EnumSeraphType.GUN);
    }

    @Override
    public int getMaxSkillSlot(ItemStack stack) {
        return 1;
    }

    @Override
    public void castSkillNonSneak(ItemStack stack, World worldIn, EntityPlayer player) {
        castSkillSneak(stack, worldIn, player);
    }

    @Override
    public void castSkillSneak(ItemStack stack, World worldIn, EntityPlayer caster) {
        cast6SPBoost(worldIn, caster, ModConfig.COMBAT.SUPREME_EDGE_6SP_BUFF);
    }
}
