package com.deeplake.hbr_mc.items.seraph;

import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.init.util.IDLNBTDef;
import com.deeplake.hbr_mc.init.util.IDLNBTUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import static com.deeplake.hbr_mc.items.seraph.ItemSeraphBase.USAGE;

public class SeraphUtil {

    public static boolean isSeraph(ItemStack stack)
    {
        return stack.getItem() instanceof ItemSeraphBase;
    }
    public static boolean isIntactSeraph(ItemStack stack)
    {
        return isSeraph(stack) && !isBroken(stack);
    }

    static int clampedLevel(int level, ItemStack stack)
    {
        return Math.max(1, Math.min(level, getMaxLevel(stack)));
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
        Item item = stack.getItem();
        if (item instanceof ItemSeraphBase) {
            ItemSeraphBase seraphBase = (ItemSeraphBase) item;
            switch (seraphBase.seraphRarity) {
                case SS:
                    return 120 + getBreakThrough(stack)*10;
                case S:
                    return 110 + getBreakThrough(stack)*2;
                case A:
                    return 100 + getBreakThrough(stack);
                default:
                    throw new IllegalStateException("Unexpected value: " + seraphBase.seraphRarity);
            }
        }
        return 100;
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

    public static void repairSeraphFully(ItemStack stack)
    {
        if (stack.isEmpty())
        {
            return;
        }
        stack.setItemDamage(0);
        IDLNBTUtil.SetBoolean(stack, IDLNBTDef.KEY_BROKEN, false);
    }

    public static void recoverSeraphSkill(ItemStack stack)
    {
        if (stack.isEmpty())
        {
            return;
        }
        stack.setItemDamage(0);
        IDLNBTUtil.SetBoolean(stack, IDLNBTDef.KEY_BROKEN, false);
        for (int i = 0; i < USAGE.length; i++) {
            IDLNBTUtil.setInt(stack, USAGE[i], 0);
        }
    }

    public static void cureSeraph(ItemStack stack, float amount)
    {
        if (!stack.isEmpty() && !isBroken(stack))
        {
            stack.setItemDamage((int) (stack.getItemDamage() - amount));
        }
    }

    public static boolean reviveSeraph(ItemStack stack, float amount)
    {
        boolean result = false;
        if (stack.isEmpty())
        {
            return false;
        }
        if (IDLNBTUtil.GetBoolean(stack,IDLNBTDef.KEY_BROKEN))
        {
            result = true;
        }
        IDLNBTUtil.SetBoolean(stack, IDLNBTDef.KEY_BROKEN, false);
        stack.setItemDamage((int) (stack.getItemDamage() - amount));
        return result;
    }

    public static ItemStack getFirstSeraphInHand(EntityPlayer player)
    {
        ItemStack stack = player.getHeldItemMainhand();
        if (!SeraphUtil.isSeraph(stack))
        {
            stack = player.getHeldItemOffhand();
            if (!SeraphUtil.isSeraph(stack))
            {
                return ItemStack.EMPTY;
            }
        }

        return stack;
    }

    public static ItemStack getFirstSeraphNonBrokenInHand(EntityPlayer player)
    {
        ItemStack stack = player.getHeldItemMainhand();
        if (!isFunctioningSeraph(stack))
        {
            stack = player.getHeldItemOffhand();
            if (!isFunctioningSeraph(stack))
            {
                return ItemStack.EMPTY;
            }
        }

        return stack;
    }

    public static boolean canAttackWithMainHandSeraph(EntityLivingBase livingBase)
    {
        ItemStack stack = livingBase.getHeldItemMainhand();
        return isFunctioningSeraph(stack);
    }

    private static boolean isFunctioningSeraph(ItemStack stack) {
        return SeraphUtil.isSeraph(stack) && !SeraphUtil.isBroken(stack);
    }

    public static void skillLevelUp(ItemSeraphBase itemSeraphBase, ItemStack stack, int slot)
    {
        int level = itemSeraphBase.getSkillLevel(stack, slot);
        if (level < 0)
        {
            IDLNBTUtil.setInt(stack, ItemSeraphBase.SKILL_LEVEL[slot], 1);
            level = 1;
        }

        if (level < itemSeraphBase.getSkillLevelMax(stack, slot))
        {
            IDLNBTUtil.addInt(stack, ItemSeraphBase.SKILL_LEVEL[slot], 1);
        }
    }

    public static boolean isUsingSeraph(Entity livingBase)
    {
        if (livingBase instanceof EntityLivingBase)
        {
            ItemStack stack = ((EntityLivingBase) livingBase).getHeldItemMainhand();
            return isSeraph(stack) && !isBroken(stack);
        }

        return false;
    }
}
