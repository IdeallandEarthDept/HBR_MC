package com.deeplake.hbr_mc.client;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphBase;
import com.deeplake.hbr_mc.items.seraph.SeraphUtil;
import net.minecraft.item.Item;
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
                //todo: type check
                ItemSeraphBase item = (ItemSeraphBase) stack.getItem();
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

                for (int i = item.getMaxSkillSlot(stack)-1; i >= 0; i--) {
                    strings.add(1, net.minecraft.client.resources.I18n.format(item.getUnlocalizedName(stack)+".skill."+i,
                            item.getSkillLeft(stack,i),
                            item.getSkillLimit(stack,i)));
                }
            }
        }
    }

    static final String[] SKILL_DESC =
            {
                    "hbr_mc.seraph.skill.desc.0",
                    "hbr_mc.seraph.skill.desc.1",
            };
}
