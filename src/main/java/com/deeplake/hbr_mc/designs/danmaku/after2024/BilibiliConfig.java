package com.deeplake.hbr_mc.designs.danmaku.after2024;

import com.deeplake.hbr_mc.init.ModConfig;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class BilibiliConfig {
    public static BilibiliConfig instance = new BilibiliConfig();

    public static void init() {
        BilibiliConfig config = new BilibiliConfig();
        config.getRoom().setId(ModConfig.LIVE_CONF.LIVE_BILI_ROOM_ID);
        config.getRoom().setEnable(false);
        //config.getRoom().setManualAuth(false);
        //config.getRoom().setAuth("{\"roomid\": ${roomId}}");
    }

    public Room getRoom() {
        room.setId(ModConfig.LIVE_CONF.LIVE_BILI_ROOM_ID);
        return room;
    }

    @SerializedName("room")
    private Room room = new Room();

//    @SerializedName("danmaku")
//    private Danmaku danmaku = new Danmaku();
//
//    @SerializedName("gift")
//    private Gift gift = new Gift();
//
//    @SerializedName("enter")
//    private Enter enter = new Enter();
//
//    @SerializedName("guard")
//    private Guard guard = new Guard();
//
//    @SerializedName("sc")
//    private SpecialChat sc = new SpecialChat();

    public static class Room {
        @SerializedName("id")
        private int id = -1;

        @SerializedName("enable")
        private boolean enable = false;

        /**
         * 是否手动设置验证信息
         */
        @SerializedName("manual_auth")
        private boolean manualAuth = false;

        /**
         * WebSocket 鉴权验证，即连接wss后所发送的第一条信息。${roomId} 为房间ID占位符
         */
        @SerializedName("auth")
        private String auth = "{\"roomid\": ${roomId}}";

        @SerializedName("cookie")
        private Map<String, String> cookie;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public boolean isManualAuth() {
            return !ModConfig.LIVE_CONF.MANUAL_AUTH.isEmpty();
        }

        public void setManualAuth(boolean manualAuth) {
            this.manualAuth = manualAuth;
        }

        public String getAuth() {
            return ModConfig.LIVE_CONF.MANUAL_AUTH;
        }

        public void setAuth(String auth) {
            this.auth = auth;
        }

        public void setCookie(Map<String, String> cookie) {
            this.cookie = cookie;
        }

        public Map<String, String> getCookie() {
            return cookie;
        }
    }
}
