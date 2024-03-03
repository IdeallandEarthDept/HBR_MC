package com.deeplake.hbr_mc.client;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.init.util.IDLNBTUtil;
import com.deeplake.hbr_mc.items.IHasRandomAttr;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;


@Mod.EventBusSubscriber(modid = Main.MODID)
public class DescForAttrpoints {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onDesc(ItemTooltipEvent event)
    {
        ItemStack stack = event.getItemStack();
        List<String> strings = event.getToolTip();
        if (stack != null)
        {
            Item item = stack.getItem();
            if (item instanceof IHasRandomAttr)
            {
                int count = IDLNBTUtil.GetState(stack);
                if (count > 0)
                {
                    strings.add(1,I18n.translateToLocalFormatted("desc.hbr_mc.armor.random_points",
                            count));

                }
            }
        }
    }
}
