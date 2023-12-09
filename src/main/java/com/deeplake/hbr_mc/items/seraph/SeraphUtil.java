package com.deeplake.hbr_mc.items.seraph;

import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.init.util.IDLNBTDef;
import com.deeplake.hbr_mc.init.util.IDLNBTUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

public class SeraphUtil {

    public static boolean isSeraph(ItemStack stack)
    {
        return stack.getItem() instanceof ItemSeraphBase;
    }
    static int clampedLevel(int level, ItemStack stack)
    {
        return Math.max(0, Math.min(level, getMaxLevel(stack)));
    }

    public static int getLevel(ItemStack stack) {
        return clampedLevel(IDLNBTUtil.GetInt(stack, IDLNBTDef.LEVEL), stack);
    }

    public static void setLevel(ItemStack stack, int level) {
        IDLNBTUtil.setInt(stack, IDLNBTDef.LEVEL, clampedLevel(level, stack));
    }

    public static int getXP(ItemStack stack) {
        return IDLNBTUtil.GetInt(stack, IDLNBTDef.LV_EXP);
    }

    public static void setXP(ItemStack stack, int xp) {
        IDLNBTUtil.setInt(stack, IDLNBTDef.LV_EXP, xp);
    }

    public static int getXPForLevel(int level) {
        return level * 100;
    }

    public static int getBreakThrough(ItemStack stack)
    {
        return IDLNBTUtil.GetInt(stack, IDLNBTDef.BREAKTHROUGH);
    }

    public static int getMaxLevel(ItemStack stack)
    {
        return 100 + getBreakThrough(stack);
    }

    public static void addXP(ItemStack stack, int xp, EntityPlayer holder) {
        int currentXP = getXP(stack);
        int nextXP = currentXP + xp;

        int level  = getLevel(stack);
        int newLevel = level;
        int maxLevel = getMaxLevel(stack);
        while (newLevel < maxLevel && nextXP >= getXPForLevel(level))
        {
            nextXP -= getXPForLevel(level);
            newLevel++;
        }

        if (level != newLevel) {
            setLevel(stack, newLevel);
            if (holder != null)
            {
                CommonFunctions.SafeSendMsgToPlayer(TextFormatting.BOLD, holder, "msg.hbr_mc.level_up", level, newLevel);
            }
        }
        setXP(stack, nextXP);
    }

    public static boolean isBroken(ItemStack stack) {
        return IDLNBTUtil.GetBoolean(stack, IDLNBTDef.KEY_BROKEN);
    }

    public static void repairSeraph(ItemStack stack)
    {
        stack.setItemDamage(0);
        IDLNBTUtil.SetBoolean(stack, IDLNBTDef.KEY_BROKEN, false);
    }

    public static ItemStack getFirstSeraphInHand(EntityPlayer player)
    {
        ItemStack stack = player.getHeldItemMainhand();
        if (!SeraphUtil.isSeraph(stack))
        {
            stack = player.getHeldItemOffhand();
            if (!SeraphUtil.isSeraph(stack))
            {
                return null;
            }
        }

        return stack;
    }
}
