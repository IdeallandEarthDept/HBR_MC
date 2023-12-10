package com.deeplake.hbr_mc.client;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.items.seraph.SeraphUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = Main.MODID)
public class DescForSeraph {


    @SubscribeEvent
    public static void onDesc(ItemTooltipEvent event)
    {
        ItemStack stack = event.getItemStack();
        List<String> strings = event.getToolTip();
        if (stack != null)
        {
            if (SeraphUtil.isSeraph(stack))
            {
                int level = SeraphUtil.getLevel(stack);
                int lvMax = SeraphUtil.getMaxLevel(stack);
                if (level == lvMax)
                {
                    strings.add(1,I18n.translateToLocalFormatted("desc.hbr_mc.seraph.level_format_max", level));
                }
                else {
                    strings.add(1,I18n.translateToLocalFormatted("desc.hbr_mc.seraph.level_format", level, SeraphUtil.getXP(stack), SeraphUtil.getXPForLevel(level)));
                }

                if (SeraphUtil.isBroken(stack))
                {
                    strings.add(1,I18n.translateToLocalFormatted("desc.hbr_mc.seraph.broken"));
                }
                else {
                    strings.add(1,I18n.translateToLocalFormatted("desc.hbr_mc.seraph.dura_format", stack.getMaxDamage() - stack.getItemDamage(), stack.getMaxDamage()));
                }
            }
        }
    }

}
