package com.deeplake.hbr_mc.designs.danmaku.basic;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;

@Mod.EventBusSubscriber
public class GiftDanmakuEvent extends Event {
    private final String message;
    private final String senderName;
    private final int count;

    public GiftDanmakuEvent(String message, String senderName, int count) {
        this.message = message;
        this.senderName = senderName;
        this.count = count;
    }

    public String getMessage() {
        return message;
    }

    public String getSenderName() {
        return senderName;
    }

    public int getCount() {
        return count;
    }
}

