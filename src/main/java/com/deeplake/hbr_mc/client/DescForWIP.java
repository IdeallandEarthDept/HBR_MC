package com.deeplake.hbr_mc.client;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.items.ItemWIP;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.deeplake.hbr_mc.init.util.IDLNBTDef.WIP_DESC;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class DescForWIP {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onToolTipColor(RenderTooltipEvent.Color event) {
        if (event.getStack().getItem() instanceof ItemWIP) {
            event.setBackground(0xf0330000);
            event.setBorderStart(0xf0cc0000);
            event.setBorderEnd(0xf0cc0000);
        }
    }


    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    static void onToolTip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        Item item = stack.getItem();
        if (item instanceof ItemWIP) {
            event.getToolTip().add(1, TextFormatting.RED + I18n.format(WIP_DESC));
        }
    }
}
