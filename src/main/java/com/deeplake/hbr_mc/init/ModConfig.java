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
    @Config.LangKey("configgui.hbr_mc.category.Menu0.GeneralConf")
    public static final GeneralConf CONFIG = new GeneralConf();

    public static class GeneralConf {
        public boolean LOG_ON = false;

        public boolean BAN_HEAL = true;
        @Config.Comment("keep main hand seraph")
        public boolean SERAPH_RETRIEVE = true;
        @Config.RangeDouble(min=0f,max = 1f)
        @Config.LangKey("configgui.hbr_mc.category.Menu0.CombatConf.SSChance")
        public float SS_CHANCE = 0.1f;

        public int CRYSTAL_PER_TASK = 0;
        public int CRYSTAL_PER_GOAL = 0;
        public int CRYSTAL_PER_CHALL = 6;
        public boolean ALLOW_SPOILERS = false;
    }

    public static class SkillConf {
        public SkillConf(float MIN_POTENCY, float CAP, int SP, float destruction) {
            this.MIN_POTENCY = MIN_POTENCY;
            this.CAP = CAP;
            this.SP = SP;
            this.DR = destruction;
        }

        public SkillConf(float MIN_POTENCY, float CAP, int SP) {
            this.MIN_POTENCY = MIN_POTENCY;
            this.CAP = CAP;
            this.SP = SP;
        }

        public SkillConf setUses(int uses, int usesGrowth)
        {
            this.uses = uses;
            this.usesGrowth = usesGrowth;
            return this;
        }

        public float MIN_POTENCY = 10f;
        public float CAP = 150;

        public int SP = 0;

        //Destruction Rate
        public float DR = 1;

        public int uses = -1;
        public int usesGrowth = 0;
    }
    @Config.LangKey("configgui.hbr_mc.category.Menu0.CombatConf")
    public static CombatConf COMBAT = new CombatConf();

    public static class CombatConf {
        public float NORMAL_ATK_POWER = 10f;
        public float NORMAL_ATK_CAP = 150;

        @Config.RangeDouble(min = 0f, max = 1f)
        public float SKILL_UP_CHANCE = 0.01f;
        public float BONUS_DAMAGE_RATE = 1.3f;
        public float AOE_DISTANCE = 32f;
        public float AOE_RADIUS = 32f;
        public int STUN_TICK_PER_TURN = 50;
        public int BUFF_TIME = 20 * 60;

        public SkillConf BRAVE_BLUE_A = new SkillConf(58.2F, 123, 6,1.5f);
        public SkillConf BRAVE_BLUE_HEAL = new SkillConf(305F, CombatUtil.DEFAULT_HEAL_CAP, 5);
        public SkillConf BRAVE_BLUE_ULTI = new SkillConf(160.2f, 138, 12,2.75f);

        public SkillConf RAPID_FIRE_4SP_AOE = new SkillConf(29, 117, 4,0.8f);
        //ブレイクカノン,3hit DP+30
        public SkillConf RAPID_FIRE_7SP_ST = new SkillConf(73, 126, 7,1.75f);
        //流星, 1hit non-plus ver， 2uses
        public SkillConf RAPID_FIRE_11SP_AOE = new SkillConf(167f, 144, 11,2.6f).setUses(2,2);
        public SkillConf SUPREME_EDGE_6SP_BUFF = new SkillConf(50/65f, 208, 6);
        public SkillConf CLAVIS_A = new SkillConf(45f, 120, 5,1.25f);
        public SkillConf CLAVIS_SS_10SP_STUN = new SkillConf(105f, 135, 10,2f);
        public SkillConf CLAVIS_SS_14SP_ULTI = new SkillConf(250f, 147, 14,3.5f).setUses(2,2);
        public SkillConf GLITTER_SHADOW_A = new SkillConf(450, 120, 5,5);
        public SkillConf GLITTER_SHADOW_SS = new SkillConf(591, 126, 7,5.6f);//3hit
        public SkillConf GLITTER_SHADOW_SS_ULTI = new SkillConf(2178, 144, 11,13).setUses(2,2);//10hit,self recoil
        public SkillConf PHANTOM_WEAVER_HEAL_A = new SkillConf(305F, CombatUtil.DEFAULT_HEAL_CAP, 5).setUses(5,5);
        public SkillConf PHANTOM_WEAVER_7SP_AOE = new SkillConf(59.1f, 126, 7,1.4f);
        public SkillConf PHANTOM_WEAVER_13SP_REVIVAL = new SkillConf(1169, 248, 13).setUses(2,2);
        public SkillConf KAZABANA_5SP_AOE = new SkillConf(37.5f, 120, 5,1f);//potency means success rate
    }

    public static final GUIConf GUI_CONF = new GUIConf();

    public static class GUIConf {
        public boolean RENDER_DP = true;

        public float GUI_Y = 0.5f;

        public float MAX_RENDER_HUD_DISTANCE = 64.0f;
        public float RENDER_HUD_SIZE = 20f;
    }

    @Config.LangKey("configgui.hbr_mc.category.Menu0.SpawnConf")
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

        @Config.LangKey("entity.c_small_hopper.name")
        @Config.RequiresMcRestart
        public int SPAWN_SMALL_HOPPER = 30;

        @Config.LangKey("entity.c_slasher.name")
        @Config.RequiresMcRestart
        public int SPAWN_SLASHER = 30;

        public float SNOWY_EXTRA_DIFFICULTY = 180;
        public float DESERT_EXTRA_DIFFICULTY = 100;
    }

    @Config.LangKey("configgui.hbr_mc.category.Menu0.WorldConf")
    @Config.Comment("World Spawn")
    public static final WorldConf WORLD_CONF = new WorldConf();

    public static class WorldConf{
        @Config.RangeInt(min = 0, max = 99)
        public int CRYSTAL_PER_CHUNK = 6;
        @Config.RangeInt(min = 1, max = 255)
        public int CRYSTAL_MIN_Y = 12;

        @Config.LangKey("hbr_mc.conf.worldGen.spawn_clear_distance")
        @Config.Comment("Within in this range(in chunks) from world spawn, no structure will be spawned other than rail.")
        @Config.RangeInt(min = 0)
        public int SPAWN_CLEAR_DISTANCE = 4;

        @Config.LangKey("hbr_mc.conf.worldGen.clock_tower")
        @Config.Comment("Generate clock tower chance. 1 = 100% per chunk")
        @Config.RangeDouble(min = 0,max = 1)
        public float CLOCK_TOWER_CHANCE = 0.005f;
        @Config.LangKey("hbr_mc.conf.worldGen.drops")
        @Config.Comment("Generate drops per chunk")
        @Config.RangeDouble(min = 0)
        public float POT_DENSITY = 1f;
    }
}
