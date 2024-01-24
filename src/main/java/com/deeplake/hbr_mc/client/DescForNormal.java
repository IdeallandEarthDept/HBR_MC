package com.deeplake.hbr_mc.client;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.blocks.BlockBase;
import com.deeplake.hbr_mc.items.ItemBase;
import com.deeplake.hbr_mc.items.ItemWIP;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.deeplake.hbr_mc.init.util.IDLNBTDef.WIP_DESC;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class DescForNormal {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    static void onToolTip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        Item item = stack.getItem();
        if (item instanceof ItemBase || (item instanceof ItemBlock && ((ItemBlock) item).getBlock() instanceof BlockBase)) {
            String key = item.getUnlocalizedNameInefficiently(stack)+".desc";
            if (I18n.hasKey(key))
            {
                event.getToolTip().add(1, I18n.format(key));
            }
        }
    }
}
