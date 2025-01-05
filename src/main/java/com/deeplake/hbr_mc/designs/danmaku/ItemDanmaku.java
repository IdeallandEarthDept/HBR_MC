package com.deeplake.hbr_mc.designs.danmaku;

import com.deeplake.hbr_mc.designs.danmaku.basic.InitDanmaku;
import com.deeplake.hbr_mc.designs.danmaku.basic.UtilDanmaku;
import com.deeplake.hbr_mc.designs.danmaku.basic.WebSocketClient;
import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.util.CommonDef;
import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.init.util.MessageDef;
import com.deeplake.hbr_mc.items.ItemBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemDanmaku extends ItemBase {
    boolean open = true;

    public ItemDanmaku(String name, boolean open) {
        super(name);
        this.open = open;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
        if (world.isRemote) {

        } else {
            player.getCooldownTracker().setCooldown(player.getHeldItem(hand).getItem(), 5 * CommonDef.TICK_PER_SECOND);
//            if (ModConfig.LIVE_CONF.LIVE_ENABLED) {
                if (!player.isSneaking()) {
                    openDanmaku(player);
                    world.playSound(null, player.getPosition(), SoundEvents.BLOCK_NOTE_CHIME, SoundCategory.PLAYERS, 1f, 1f);
                } else {
                    closeDanmaku(player);
                    world.playSound(null, player.getPosition(), SoundEvents.BLOCK_NOTE_CHIME, SoundCategory.PLAYERS, 1f, 1f);
                }
//            } else {
//                CommonFunctions.SafeSendMsgToPlayer(TextFormatting.YELLOW, player, MessageDef.MSG_LIVE_CONFIG_DISBALED);
//            }
        }

        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    public static void openDanmaku(EntityPlayer player) {
        UtilDanmaku site = new UtilDanmaku();
        if (InitDanmaku.WEBSOCKET_CLIENT != null) {
            CommonFunctions.SafeSendMsgToPlayer(player, MessageDef.MSG_LIVE_CONNECT_ALREADY);
            return;
        }

        InitDanmaku.WEBSOCKET_CLIENT = new WebSocketClient(site);
        try {
            CommonFunctions.SafeSendMsgToPlayer(player, MessageDef.MSG_LIVE_CONNECTING, ModConfig.LIVE_CONF.LIVE_BILI_ROOM_ID);
            InitDanmaku.WEBSOCKET_CLIENT.open();
        } catch (Exception e) {
            InitDanmaku.WEBSOCKET_CLIENT = null;
            e.printStackTrace();
        }
    }

    public static void closeDanmaku(EntityPlayer player) {
        if (InitDanmaku.WEBSOCKET_CLIENT == null) {
            return;
        }
        try {
            CommonFunctions.SafeSendMsgToPlayer(player, MessageDef.MSG_LIVE_CLOSING);
            InitDanmaku.WEBSOCKET_CLIENT.close();
            CommonFunctions.SafeSendMsgToPlayer(player, MessageDef.MSG_LIVE_CLOSED);
        } catch (Exception ignore) {
        } finally {
            InitDanmaku.WEBSOCKET_CLIENT = null;
        }
    }
}
