package com.deeplake.hbr_mc.init;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.init.util.CombatUtil;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Main.MODID, category = "")
public class ModConfig {
    @Mod.EventBusSubscriber(modid = Main.MODID)
    private static class EventHandler {

        private EventHandler() {
        }

        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(Main.MODID)) {
                ConfigManager.sync(Main.MODID, Config.Type.INSTANCE);
            }
        }
    }

    public static final GeneralConf CONFIG = new GeneralConf();

    public static class GeneralConf {
        public boolean LOG_ON = false;

        public boolean BAN_HEAL = true;
    }

    public static class SkillConf {
        public SkillConf(float MIN_POTENCY, float CAP, int SP) {
            this.MIN_POTENCY = MIN_POTENCY;
            this.CAP = CAP;
            this.SP = SP;
        }

        public float MIN_POTENCY = 10f;
        public float CAP = 150;

        public float SP = 0;
    }

    public static CombatConf COMBAT = new CombatConf();

    public static class CombatConf {
        public float NORMAL_ATK_POWER = 10f;
        public float NORMAL_ATK_CAP = 150;

        @Config.RangeDouble(min = 0f, max = 1f)
        public float SKILL_UP_CHANCE = 0.01f;

        public SkillConf BRAVE_BLUE_HEAL = new SkillConf(305F, CombatUtil.DEFAULT_HEAL_CAP, 5);
        public SkillConf BRAVE_BLUE_ULTI = new SkillConf(160, 138, 12);

        public SkillConf RAPID_FIRE_4SP_AOE = new SkillConf(29, 117, 4);
        public SkillConf KAZABANA_5SP_AOE = new SkillConf(37.5f, 120, 5);
    }

    public static final GUIConf GUI_CONF = new GUIConf();

    public static class GUIConf {
        @Config.Comment("The Y position of the GUI")
        public boolean RENDER_DP = true;

        @Config.Comment("The Y position of the GUI")
        public float GUI_Y = 0.5f;

        public float MAX_RENDER_HUD_DISTANCE = 64.0f;
        public float RENDER_HUD_SIZE = 5f;
    }

    @Config.LangKey("configgui.idealland.category.Menu0.SpawnConf")
    @Config.Comment("Spawning")
    public static final SpawnConf SPAWN_CONF = new SpawnConf();

    public static class SpawnConf {
        @Config.LangKey("conf.spawn.enabled")
        @Config.Comment("Spawn mod creatures")
        @Config.RequiresMcRestart
        public boolean SPAWN = true;

        @Config.LangKey("entity.c_marionette.name")
        @Config.RequiresMcRestart
        public int SPAWN_MARIONETTE = 30;

        @Config.LangKey("entity.c_doll.name")
        @Config.RequiresMcRestart
        public int SPAWN_DOLL = 30;
    }
}
