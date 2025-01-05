package com.deeplake.hbr_mc.designs.danmaku.basic;

import com.deeplake.hbr_mc.designs.danmaku.DanmakuEventUtil;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;

@Mod.EventBusSubscriber
public class LiveLoginEvent extends Event {

    private final String senderName;
    private final int guardLevel;
    private final int liveLevel;
    private final int wealthLevel;

    public LiveLoginEvent(String senderName) {
        this.senderName = senderName;
        guardLevel = 0;
        liveLevel = 0;
        wealthLevel = 0;
    }

    public LiveLoginEvent(String senderName, int guardLevel, int liveLevel, int wealthLevel) {
        this.senderName = senderName;
        this.guardLevel = guardLevel;
        this.liveLevel = liveLevel;
        this.wealthLevel = wealthLevel;
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

    public int getWealthLevel() {
        return wealthLevel;
    }

    public DanmakuEventUtil.DanmakuUserData getDanmakuUserData(){
        return new DanmakuEventUtil.DanmakuUserData(senderName, guardLevel, liveLevel, wealthLevel);
    }
}

