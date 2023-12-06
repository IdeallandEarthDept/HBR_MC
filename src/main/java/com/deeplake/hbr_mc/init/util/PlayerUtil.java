package com.deeplake.hbr_mc.init.util;

import com.deeplake.hbr_mc.Main;
import net.minecraft.advancements.Advancement;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.FoodStats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PlayerUtil {

    public static int FindItemInIvtrGeneralized(EntityPlayer player, Class<? extends Item> itemClass)
    {
        for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
            ItemStack itemstack = player.inventory.getStackInSlot(i);
            {
                //itemClass.getClass();
                if (itemClass.isAssignableFrom(itemstack.getItem().getClass()))
                {
                    return i;
                }
            }
        }
        return -1;
    }

    public static ItemStack FindStackInIvtrGeneralized(EntityPlayer player, Class<? extends Item> itemClass)
    {
        for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
            ItemStack itemstack = player.inventory.getStackInSlot(i);
            {
                //itemClass.getClass();
                if (itemClass.isAssignableFrom(itemstack.getItem().getClass()))
                {
                    return itemstack;
                }
            }
        }
        return ItemStack.EMPTY;
    }

    public static int FindItemInIvtr(EntityPlayer player, Item item)
    {
        for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
            ItemStack itemstack = player.inventory.getStackInSlot(i);
            {
                if (itemstack.getItem() == item)
                {
                    return i;
                }
            }
        }
        return -1;
    }

    public static ItemStack FindStackInIvtr(EntityPlayer player, Item item)
    {
        for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
            ItemStack itemstack = player.inventory.getStackInSlot(i);
            {
                if (itemstack.getItem() == item)
                {
                    return itemstack;
                }
            }
        }
        return ItemStack.EMPTY;
    }

    public static boolean isCreative(Entity player)
    {
        if (player instanceof EntityPlayer) {
            return isCreative((EntityPlayer) player);
        }
        else {
            return false;
        }
    }

    public static boolean isCreative(EntityPlayer player)
    {
        return player.capabilities.isCreativeMode;
    }

    public static void giveToPlayer(EntityPlayer player, Item item, int count) {
        giveToPlayer(player, item, 0, count, null);
    }

    public static void giveToPlayer(EntityPlayer player, Item item, int meta, int count, NBTTagCompound nbtTagCompound) {
        int max = item.getItemStackLimit();
        while (count > max) {
            ItemStack stack = new ItemStack(item, max, meta);
            stack.setTagCompound(nbtTagCompound);
            if (!player.addItemStackToInventory(stack)) {
                player.dropItem(stack, false);
            }
            count -= max;
        }

        ItemStack stack = new ItemStack(item, count, meta);
        stack.setTagCompound(nbtTagCompound);
        if (!player.addItemStackToInventory(stack)) {
            player.dropItem(stack, false);
        }
    }

    public static boolean giveToPlayer(EntityPlayer player, ItemStack stack)
    {
        boolean result = player.addItemStackToInventory(stack);
        if (!result)
        {
            player.dropItem(stack, false);
        }
        return result;
    }

    public static void TryGrantAchv(EntityPlayer player, String key)
    {
        if (player instanceof EntityPlayerMP)
        {
            EntityPlayerMP playerMP = ((EntityPlayerMP) player);
            Advancement advancement = playerMP.getServerWorld().getAdvancementManager().getAdvancement(new ResourceLocation(Main.MODID, key));

            //String achvName = GetAchvName(key);
            //playerMP.getStatFile().unlockAchievement(this.gameController.player, statbase, k);
        }

        //todo
    }

    public static void setCoolDown(EntityPlayer player, EnumHand hand)
    {
        player.getCooldownTracker().setCooldown(player.getHeldItem(hand).getItem(), 5);
    }

    //not intended to decrease
    public static boolean addFoodLevel(EntityPlayer player, int value)
    {
        FoodStats stats = player.getFoodStats();
        if (stats.needFood())
        {
            stats.setFoodLevel(stats.getFoodLevel() + value);
            return true;
        }
        else {
            return false;
        }
    }

    public static void playHintSound(World world, BlockPos pos, boolean success) {
        if (!success)
        {
            world.playSound(null, pos, SoundEvents.BLOCK_DISPENSER_FAIL, SoundCategory.PLAYERS, 1f, 1f);
        }
        else
        {
            world.playSound(null, pos, SoundEvents.BLOCK_DISPENSER_LAUNCH, SoundCategory.PLAYERS, 0.8f, 1f);
        }
    }
}
