package com.deeplake.hbr_mc.items.seraph.b31;

import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.items.seraph.EnumSeraphType;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphCannonBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

//Seika Higuchi
//copied from ItemSupremeEdge
public class ItemFatalNull extends ItemSeraphCannonBase {
    public ItemFatalNull(String name) {
        super(name, EnumSeraphType.CANNON);
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
        ModConfig.SkillConf skillConf = ModConfig.COMBAT.FATAL_NULL_6SP_BUFF;
        cast6SPBoost(worldIn, caster, skillConf);
    }


}
