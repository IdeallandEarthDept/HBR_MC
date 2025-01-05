package com.deeplake.hbr_mc.designs.danmaku;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.designs.danmaku.basic.GiftDanmakuEvent;
import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.init.util.MessageDef;
import com.deeplake.hbr_mc.init.util.PlayerUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class EventsOnGift {

    @SubscribeEvent
    public static void onGift(GiftDanmakuEvent event) {
        PlayerList list = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList();
        for (EntityPlayer player : list.getPlayers()) {
            World world = player.world;
            Random random = player.getRNG();
            //&& ModEnchantmentInit.INTERNET_BILI.getLevelOnCreature(player) > 0
            if (!world.isRemote && DanmakuEventUtil.checkEnchantmentReq(player)) {
                String message = event.getMessage();
                String senderName = event.getSenderName();
                int count = event.getCount();

                if (message.contains(LiveKeywords.getRotflesh())) {
                    ItemStack stack = new ItemStack(Items.ROTTEN_FLESH);
                    stack.setStackDisplayName(senderName);
                    PlayerUtil.giveToPlayer(player, stack.getItem(), count, 0, stack.getTagCompound());
                    world.playSound(null, player.getPosition(), SoundEvents.ENTITY_PLAYER_SPLASH, SoundCategory.PLAYERS, 1f, 1f);

                }
                CommonFunctions.SafeSendMsgToPlayer(player, MessageDef.MSG_LIVE_GIFT_AUTO_THANK, senderName, count, message);
//                else {

//                }
            }
        }
    }
}
