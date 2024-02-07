package com.deeplake.hbr_mc.client;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.designs.SeraphBoostConst;
import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.init.util.IDLNBTDef;
import com.deeplake.hbr_mc.init.util.IDLNBTUtil;
import com.deeplake.hbr_mc.items.seraph.EnumSeraphRarity;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphBase;
import com.deeplake.hbr_mc.items.seraph.SeraphUtil;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;


@Mod.EventBusSubscriber(modid = Main.MODID)
public class DescForSeraph {
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onDesc(ItemTooltipEvent event)
    {
        ItemStack stack = event.getItemStack();
        List<String> strings = event.getToolTip();
        if (stack != null)
        {
            if (SeraphUtil.isSeraph(stack))
            {
                ItemSeraphBase item = (ItemSeraphBase) stack.getItem();
                EnumSeraphRarity rarity = item.seraphRarity;
                int offset = 0;
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
                if (item.fasterCooldown(stack))
                {
                    strings.add(1,I18n.translateToLocal("desc.hbr_mc.seraph.faster_cooldown"));
                }

                strings.add(1,I18n.translateToLocalFormatted("desc.hbr_mc.seraph.breakthrough",
                        SeraphUtil.getBreakThrough(stack), SeraphBoostConst.getMaxBreakThrough(item)));

                strings.add(1,I18n.translateToLocalFormatted("desc.hbr_mc.seraph.boost",
                        IDLNBTUtil.GetInt(stack, IDLNBTDef.KEY_BOOST), SeraphBoostConst.getMaxBoost(item)));


                for (int i = item.getMaxSkillSlot(stack)-1; i >= 0; i--) {
                    StringBuilder s = new StringBuilder(net.minecraft.client.resources.I18n.format(item.getUnlocalizedName(stack) + ".skill." + i,
                            item.getSkillLeft(stack, i),
                            item.getSkillLimit(stack, i)));
                    int lv = item.getSkillLevel(stack, i);
                    while (lv >= 5)
                    {
                        s.insert(0,"*");
                        lv -= 5;
                    }
                    while (lv >= 2)
                    {
                        s.insert(0,"+");
                        lv -= 1;
                    }
                    strings.add(1, s.toString());
                }

                if (event.getFlags().isAdvanced())
                {
                    if (stack.isItemDamaged())
                    {
                        offset += 1;
                    }

                    offset += 1;

                    if (stack.hasTagCompound())
                    {
                        offset += 1;
                    }
                }

                addDesc(strings, RegisterAttr.STR, item, stack, offset);
                addDesc(strings, RegisterAttr.DEX, item, stack, offset);
                addDesc(strings, RegisterAttr.END, item, stack, offset);
                addDesc(strings, RegisterAttr.MEN, item, stack, offset);
                addDesc(strings, RegisterAttr.INT, item, stack, offset);
                addDesc(strings, RegisterAttr.LUC, item, stack, offset);

            }
        }
    }

    private static void addDesc(List<String> strings, IAttribute attr, ItemSeraphBase seraphBase, ItemStack stack, int offset) {
        //
        strings.add(strings.size() - offset,
                I18n.translateToLocalFormatted(
                        "desc.hbr_mc.seraph.attr_desc",
                        I18n.translateToLocal("attribute.name." +attr.getName()),
                        String.format("%.0f",seraphBase.getAttrValue(stack, attr)),
                        String.format("%.0f",seraphBase.getAttrValuePercent(stack, attr)*100)));
    }

    static final String[] SKILL_DESC =
            {
                    "hbr_mc.seraph.skill.desc.0",
                    "hbr_mc.seraph.skill.desc.1",
            };
}
