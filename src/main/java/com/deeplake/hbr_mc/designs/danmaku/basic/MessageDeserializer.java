package com.deeplake.hbr_mc.designs.danmaku.basic;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.designs.danmaku.NameManager;
import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.init.util.MessageDef;
import com.google.gson.*;
import net.minecraftforge.common.MinecraftForge;

import java.lang.reflect.Type;
import java.util.Random;

public class MessageDeserializer implements JsonDeserializer<String> {

    public MessageDeserializer() {
    }

    @Override
    public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonObject()) {
            JsonObject data = json.getAsJsonObject();
            String type = data.get("cmd").getAsString();
            switch (type) {
                case "DANMU_MSG":
//                        if (config.getDanmaku().isShow()) {
                    return handDanmaku(data);
//                        }

                case "SEND_GIFT":
//                        if (config.getGift().isShow()) {
                    return handGift(data);
//                        }

                case "COMBO_SEND":
//                        if (config.getGift().isShow()) {
                    return handComboGift(data);
//                        }

                case "INTERACT_WORD":
                case "WELCOME":
                    return handNormalEnter(data);

                case "WELCOME_GUARD":
                    return handGuardWelcome(data);

                case "GUARD_BUY":
                    return handBuyGuard(data);

                case "SUPER_CHAT_MESSAGE":
                    return handSuperChat(data);

                default:
            }
        }
        return null;
    }

    int getGuardLevel(String str) {
        switch (str) {
            case "总督":
                return 1;

            case "提督":
                return 2;

            case "舰长":
                return 3;

            default:
                return 0;
        }
    }

    Random random = new Random();
    private String handDanmaku(JsonObject dataIn) {
        //wrap it with try catch to prevent connection shutdown
        try
        {
            JsonArray info = dataIn.getAsJsonArray("info");
            JsonArray user = info.get(2).getAsJsonArray();

            JsonArray userPlus = info.get(3).getAsJsonArray();
            int uid = user.get(0).getAsInt();

            String face = String.valueOf(random.nextInt());
            try{
                JsonObject baseObj = info.get(0).getAsJsonArray().get(16).getAsJsonObject();
                JsonObject info_16_user = baseObj.getAsJsonObject("user");
                JsonObject info_16_user_base = info_16_user.getAsJsonObject("base");
                face = info_16_user_base.get("face").getAsString();
            }catch (Exception e) {
                Main.LogWarning("Cannot fetch face: " + e.getMessage());
            }

            String userName = NameManager.filterName(face, user.get(1).getAsString());

            String danmaku = info.get(1).getAsString();
            int userLevel = 1;
            int wealthLevel = 0;
            if (userPlus.size()>0)
            {
                userLevel = userPlus.get(0).getAsInt();//[11,"湖中景","道家深湖",893240,9272486,"",0,9272486,9272486,9272486,0,1,24124162]
//                if (userPlus.size() > 2)
//                {
//                    userName = userPlus.get(2).getAsString();
//                }
            }

            JsonArray wealth = info.get(info.size() - 2).getAsJsonArray();
            if (wealth != null) {
                wealthLevel = wealth.get(0).getAsInt();
            }

            boolean isAdmin = user.get(2).getAsInt() == 1;
            String guardName = user.get(7).getAsString();

            if (ModConfig.CONFIG.LOG_ON) {
                Main.Log("userPlus: " + userPlus.toString());
                Main.Log("guardName: " + guardName);
            }

            MinecraftForge.EVENT_BUS.post(new TextDanmakuEvent(danmaku, userName, getGuardLevel(guardName), userLevel, wealthLevel));

            return null;
        }
        catch (Exception e)
        {
            Main.LogWarning("Error in handDanmaku: " + e.getMessage());
            return null;
        }
    }

    private String handGift(JsonObject dataIn) {
        JsonObject data = dataIn.getAsJsonObject("data");
        int uid = data.get("uid").getAsInt();
//        String userName = NameManager.filterName(uid, data.get("uname").getAsString());
        String userName = data.get("uname").getAsString();
        String action = data.get("action").getAsString();
        String giftName = data.get("giftName").getAsString();
        int num = data.get("num").getAsInt();

//            for (String block : config.getGift().getBlockGift()) {
//                if (giftName.equals(block)) {
//                    return null;
//                }
//            }
        MinecraftForge.EVENT_BUS.post(new GiftDanmakuEvent(giftName, userName, num));

        return String.format("%s:%s %sx%s", userName, action, giftName, num);
    }

    private String handComboGift(JsonObject dataIn) {
        JsonObject data = dataIn.getAsJsonObject("data");
        int uid = data.get("uid").getAsInt();
//        String userName = NameManager.filterName(uid, data.get("uname").getAsString());
        String userName = data.get("uname").getAsString();
        String action = data.get("action").getAsString();
        String giftName = data.get("gift_name").getAsString();
        int num = data.get("total_num").getAsInt();

//            for (String block : config.getGift().getBlockGift()) {
//                if (giftName.equals(block)) {
//                    return null;
//                }
//            }
        MinecraftForge.EVENT_BUS.post(new GiftDanmakuEvent(giftName, userName, num));

        return String.format("%s:%s %sx%s", userName, action, giftName, num);
    }

    private String handNormalEnter(JsonObject dataIn) {
        JsonObject data = dataIn.getAsJsonObject("data");
        int uid = data.get("uid").getAsInt();

        String face = String.valueOf(random.nextInt());
        try{
            JsonObject uinfo = data.getAsJsonObject("uinfo");
            JsonObject uinfo_base = uinfo.getAsJsonObject("base");
            face = uinfo_base.get("face").getAsString();
        }catch (Exception e) {
            Main.LogWarning("Cannot fetch face: " + e.getMessage());
        }


        //        String userName = NameManager.filterName(uid, data.get("uname").getAsString());
        String userName = data.get("uname").getAsString();

        int liveLevel = 0;
        int wealthLevel = 0;

        JsonObject medal = data.getAsJsonObject("medal");
        if (medal != null) {

            liveLevel = medal.get("medal_level").getAsInt();
        }

        JsonObject wealth = data.getAsJsonObject("wealth");
        if (wealth != null) {
            wealthLevel = wealth.get("level").getAsInt();
        }

        MinecraftForge.EVENT_BUS.post(new LiveLoginEvent(userName, 0, liveLevel, wealthLevel));

        return String.format("%s IN", userName);
    }
//
//    private String handWelcome(JsonObject dataIn) {
//        JsonObject data = dataIn.getAsJsonObject("data");
//        int uid = data.get("uid").getAsInt();
//        String userName = NameManager.filterName(uid, data.get("uname").getAsString());
//
//        return String.format("%s welcome", userName);
//    }

    private String handGuardWelcome(JsonObject dataIn) {
        JsonObject data = dataIn.getAsJsonObject("data");
        int uid = data.get("uid").getAsInt();
//        String userName = NameManager.filterName(uid, data.get("uname").getAsString());
        String userName = data.get("uname").getAsString();
        int level = data.get("guard_level").getAsInt();

        int liveLevel = 0;
        int wealthLevel = 0;

        JsonObject medal = data.getAsJsonObject("medal");
        if (medal != null) {

            liveLevel = medal.get("medal_level").getAsInt();
        }

        JsonObject wealth = data.getAsJsonObject("wealth");
        if (wealth != null) {
            wealthLevel = wealth.get("level").getAsInt();
        }

        MinecraftForge.EVENT_BUS.post(new LiveLoginEvent(userName, level, liveLevel, wealthLevel));

        switch (level) {
            case 1:
                return String.format("%s[^] welcome", userName);
            case 2:
                return String.format("%s[+] welcome", userName);
            case 3:
                return String.format("%s[++] welcome", userName);
            default:
                return null;
        }

    }

    private String handBuyGuard(JsonObject dataIn) {
        JsonObject data = dataIn.getAsJsonObject("data");
        String userName = data.get("username").getAsString();
        int level = data.get("guard_level").getAsInt();
        switch (level) {
                case 1:
                    CommonFunctions.broadcastTitle(MessageDef.MSG_LIVE_GUARD_1, userName);
                    //return String.format(config.getGuard().getGuardStyle1Formatted(), userName);
                    break;
                case 2:
                    CommonFunctions.broadcastbykey(MessageDef.MSG_LIVE_GUARD_2, userName);
                    //return String.format(config.getGuard().getGuardStyle2Formatted(), userName);
                    break;
                case 3:
                    CommonFunctions.broadcastbykey(MessageDef.MSG_LIVE_GUARD_3, userName);
                    //return String.format(config.getGuard().getGuardStyle3Formatted(), userName);
                    break;
            default:
                return null;
        }
        return String.format("%s buy guard", userName);
    }

    private String handSuperChat(JsonObject dataIn) {
        JsonObject data = dataIn.getAsJsonObject("data");
        String userName = data.getAsJsonObject("user_info").get("uname").getAsString();
        String message = data.get("message").getAsString();
        int price = data.get("price").getAsInt();
        CommonFunctions.broadcastTitle(message);
        return String.format("%s(%s):%s", userName, message, price);
    }
}

