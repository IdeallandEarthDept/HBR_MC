package com.deeplake.hbr_mc.designs.danmaku.basic;

import com.deeplake.hbr_mc.designs.danmaku.DanmakuEventUtil;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;

@Mod.EventBusSubscriber
public class TextDanmakuEvent extends Event {
    private final String message;
    private final String senderName;
    private final int guardLevel;
    private final int liveLevel;
    private final int wealthLevel;

    public TextDanmakuEvent(String message) {
        this.message = message;
        senderName = "";
        guardLevel = 0;
        liveLevel = 0;
        wealthLevel = 0;
    }

    public TextDanmakuEvent(String message, String senderName, int guardLevel, int liveLevel, int wealthLevel) {
        this.message = message;
        this.senderName = senderName;
        this.guardLevel = guardLevel;
        this.liveLevel = liveLevel;
        this.wealthLevel = wealthLevel;
    }

    public String getMessage() {
        return message;
    }

    public String getSenderName() {
        return senderName;
    }

    public int getGuardLevel() {
        return guardLevel;
    }

    public int getLiveLevel() {
        return liveLevel;
    }

    public DanmakuEventUtil.DanmakuUserData getDanmakuUserData(){
        return new DanmakuEventUtil.DanmakuUserData(senderName, guardLevel, liveLevel, wealthLevel);
    }
}

