package com.deeplake.hbr_mc.init.util;


import com.deeplake.hbr_mc.Main;
import net.minecraft.item.ItemStack;

public class MessageDef {
    //GENERAL:
    public static final String OUT_OF_RANGE = "idealland.msg.out_of_range";
    public static final String IN_COOLDOWN = "idealland.skill.msg.cool_down";
    public static final String NOT_CASTABLE_MAINHAND = "idealland.skill.msg.not_castable_mainhand";
    public static final String NOT_CASTABLE_OFFHAND = "idealland.skill.msg.not_castable_offhand";

    public static final String TP_BADLUCK = "idealland.msg.tp_badluck";

    public static final String MAGICAL_EYE_GAZED = "idealland.msg.gazed_by";
    public static final String MAGICAL_EYE_READY = "idealland.msg.gazed_ready";
    public static final String MAGICAL_EYE_CD= "idealland.msg.gaze_cd";
    public static final String MAGICAL_EYE_CAST_BROAD= "idealland.msg.gaze_broad";
    public static final String MAGICAL_EYE_CAST_NARROW= "idealland.msg.gaze_narrow";

    //VEX
    public static final String VEX_ATTACK_YOU = "idealland.msg.vex_attack_you";
    public static final String VEX_DEFEND_YOU = "idealland.msg.vex_defend_you";

    public static final String AMK_NECKLACE = "msg.amk_necklace.basic";

    public static final String MSG_SOVEREIGN_FAIL = "msg.sovereign_seal.fail";

    //dungeon
    public static final String MSG_CLEANSE_FIREPROOF = "idealland.msg.dunegon.cleanse_fireproof";
    public static final String MSG_NEED_KILL_BOSS = "idealland.msg.dunegon.need_kill_boss";

    //Grand Protection
    public static final String MSG_LOGIN_ABSOLUTE = "msg.grand_def.login.absolute";
    public static final String MSG_LOGIN_RELATIVE = "msg.grand_def.login.relative";

    public static final String MSG_TRIGGER_ABSOLUTE_OPPONENT = "msg.grand_def.trigger.absolute.opponent";
    public static final String MSG_TRIGGER_RELATIVE_OPPONENT = "msg.grand_def.trigger.relative.opponent";
    public static final String MSG_LOST_ABSOLUTE = "msg.grand_def.lost.absolute";

    public static final String MSG_TRIGGER_RELATIVE = "msg.grand_def.trigger.relative";

    public static final String MSG_BEGIN_EVIL = "idealland.msg.evil.begin";
    public static final String MSG_FADE_EVIL = "idealland.msg.evil.fade";
    public static final String MSG_FADE_EVIL_FAIL = "idealland.msg.evil.fade.fail";
    public static final String MSG_END_EVIL = "idealland.msg.evil.end";

    public static final String MSG_NO_VALID_PLAYER = "idealland.msg.no_valid_player";

    public static final String MSG_TELEPORT_ACCEPTED = "idealland.msg.tp_accepted";

    public static final String MSG_WRONG_CLASS = "idealland.msg.skill.wrong_class";

    public static final String MSG_ALREADY_SUMMONED = "idealland.msg.skill.ego_twin.already_summoned";

    public static final String MSG_INTIMIDATE = "idealland.msg.intimidate";

    //dungeon
    public static final String MSG_TRAP_INFO = Main.MODID + ".msg.trap.info";

    public static final String MSG_DOOR_FAIL = Main.MODID + ".msg.door.fail";
    public static final String MSG_PLACE_STOPPED_BY_DAMPER = Main.MODID + ".msg.place_stopped_by_damper";
    public static final String MSG_DIG_STOPPED_BY_DAMPER = Main.MODID + ".msg.dig_stopped_by_damper";
    public static final String MSG_NEED_CHIPS_CITADEL_CALL = Main.MODID + ".msg.msg_need_chips_citadel_call";

    //danmaku
    public static final String MSG_LIVE_CONFIG_DISBALED = Main.MODID + ".danmaku.config_disabled";
    public static final String MSG_LIVE_CONNECT_ALREADY = Main.MODID + ".danmaku.connect_already";
    public static final String MSG_LIVE_CONNECTING = Main.MODID + ".danmaku.connect";
    public static final String MSG_LIVE_CONNECT_FAILED = Main.MODID + ".danmaku.connect_failed";
    public static final String MSG_LIVE_CONNECT_SUCCESS = Main.MODID + ".danmaku.connect_success";
    public static final String MSG_LIVE_CLOSING = Main.MODID + ".danmaku.closing";
    public static final String MSG_LIVE_CLOSED = Main.MODID + ".danmaku.closed";
    public static final String MSG_LIVE_TEXT = Main.MODID + ".danmaku.text";

    public static final String MSG_LIVE_LOGIN = Main.MODID + ".danmaku.login";
    public static final String MSG_LIVE_GIFT_AUTO_THANK = Main.MODID + ".danmaku.gift_auto_thank";
    public static final String MSG_LIVE_GUARD_1 = Main.MODID + ".danmaku.guard1";
    public static final String MSG_LIVE_GUARD_2 = Main.MODID + ".danmaku.guard2";
    public static final String MSG_LIVE_GUARD_3 = Main.MODID + ".danmaku.guard3";

    public static final String MSG_DISABLED_IN_CONFIG = Main.MODID + ".general.config_disabled";

    public static String getSkillCastKey(ItemStack stack, int index)
    {
        //remove"item."
        return String.format("msg.%s.cast.%d", stack.getUnlocalizedName().substring(5), index);
    }
}
