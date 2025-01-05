package com.deeplake.hbr_mc.designs.danmaku;

public class LiveKeywords {
    //Text
    static String[] stringsInvisible = {"隐形", "隐身"};

    public static boolean checkInvisible(String msg) {
        return contains(msg, stringsInvisible);
    }

    //gifts
    public static String getRotflesh() {
        return "辣条";
    }

    public static boolean contains(String message, String sub) {
        return message != null && message.contains(sub);
    }

    public static boolean contains(String message, String[] sub) {
        for (String str : sub) {
            if (message.contains(str)) {
                return true;
            }
        }
        return false;
    }
}
