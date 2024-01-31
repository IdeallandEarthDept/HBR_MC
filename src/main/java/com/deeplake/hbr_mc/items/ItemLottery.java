package com.deeplake.hbr_mc.items;

import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphBase;
import com.google.gson.JsonParseException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketTitle;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.*;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemLottery extends ItemBase{
//    public static class SeraphLottery extends WeightedRandom.Item
//    {
//        public final Item item;
//
//        public SeraphLottery(Item item, int weight)
//        {
//            super(weight);
//            this.item = item;
//        }
//    }
//
//    protected SeraphLottery getWeightedBiomeEntry(net.minecraftforge.common.BiomeManager.BiomeType type)
//    {
//        List<SeraphLottery> list = biomes[type.ordinal()];
//        return (SeraphLottery)net.minecraft.util.WeightedRandom.getRandomItem(biomeList, weight);
//    }

    static ArrayList<Item> itemSS = new ArrayList<>();
    static ArrayList<Item> itemA = new ArrayList<>();

    public static void addSS(Item item)
    {
        itemSS.add(item);
    }

    public static void addA(Item item)
    {
        itemA.add(item);
    }

    public ItemLottery(String name) {
        super(name);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
        player.setActiveHand(hand);
        ItemStack stack = player.getHeldItem(hand);

        if (!world.isRemote) {
            Item result;
            ItemStack stack1;
            Random random = player.getRNG();
            boolean isSS = player.getRNG().nextFloat() < ModConfig.CONFIG.SS_CHANCE;
            if (isSS)
            {
                result = itemSS.get(random.nextInt(itemSS.size()));
                stack1 = new ItemStack(result);
                world.playSound(null,player.getPosition(),SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, 1.5f, 1f);
                world.playSound(null,player.getPosition(),SoundEvents.UI_TOAST_CHALLENGE_COMPLETE, SoundCategory.PLAYERS, 1.5f, 1f);

                String toast = result.getUnlocalizedNameInefficiently(stack1) + ".toast";

                ITextComponent itextcomponent = new TextComponentTranslation(toast);
                itextcomponent.setStyle(new Style().setColor(TextFormatting.LIGHT_PURPLE));
                SPacketTitle spackettitle1 = new SPacketTitle(SPacketTitle.Type.TITLE, itextcomponent);
                ((EntityPlayerMP)player).connection.sendPacket(spackettitle1);
            }
            else {
                result = itemA.get(random.nextInt(itemA.size()));
                stack1 = new ItemStack(result);
                world.playSound(null,player.getPosition(),SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1f, 1f);
            }

            CommonFunctions.SafeSendMsgToPlayer(TextFormatting.ITALIC, player, stack1.getDisplayName());
            player.addItemStackToInventory(stack1);
           ;
            //player.addExperience(10);

            //	Must do shrink AFTER addItemStackToInventory,
            //or it would make the addItemStackToInventory fail if the new thing were to be in the new place.
            //	Try do this when helding one sealed weapon in slot 1, and something else in slot 2.
            stack.shrink(1);
        }

        return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
    }

    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        return EnumActionResult.PASS;
    }
}
