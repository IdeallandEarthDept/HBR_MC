package com.deeplake.hbr_mc.designs.danmaku;


import com.deeplake.hbr_mc.entities.ai.idl.EnumActionMode;
import com.deeplake.hbr_mc.entities.npc.EntityCleverNPCForHBR;
import com.deeplake.hbr_mc.entities.npc.RandomNPCUtil;
import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.util.CommonDef;
import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.init.util.EntityUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntitySelectors;
import net.minecraft.world.World;

import java.util.List;

import static com.deeplake.hbr_mc.init.util.MessageDef.MSG_LIVE_LOGIN;

public class DanmakuEventUtil {
    static float range = 5f;

    public static boolean refreshOrSummon(EntityPlayer player, DanmakuUserData data) {
        if (tryRefreshNPC(player.world, data)) return true;

        summonDropbox(player, data);
        return false;
    }

    private static boolean tryRefreshNPC(World world, DanmakuUserData data) {
        String npcName = data.name;
        npcName = getGuardName(data, npcName);
        //check if already exist
        if (EntityUtil.HasEntityWithName(world, npcName)) {
            //find the entity and make it glow
            Entity entity = EntityUtil.GetEntityWithName(world, npcName);
            if (entity instanceof EntityCleverNPCForHBR) {
                EntityCleverNPCForHBR box = (EntityCleverNPCForHBR) entity;
                EntityUtil.ApplyBuff(box, MobEffects.GLOWING, 0, 1f);
                box.setBehaviorMode(EnumActionMode.DEFEND);
            }
            return true;
        }
        return false;
    }

    public static class DanmakuUserData{
        public String name;
        public int level;
        public int personLevel;
        public int wealthLevel;

        public DanmakuUserData() {
        }

        public DanmakuUserData(String name, int level, int personLevel, int wealthLevel) {
            this.name = name;
            this.level = level;
            this.personLevel = personLevel;
            this.wealthLevel = wealthLevel;
        }
    }

    public static boolean checkEnchantmentReq(EntityLivingBase target) {
        return true;
//        return !ModConfig.LIVE_CONF.ONLY_AFFECT_ENCHANTED ||
//                EnchantmentHelper.getMaxEnchantmentLevel(ModEnchantmentInit.INTERNET_BILI, target) > 0;
    }

    //tries to summon a dropbox
    public static void summonDropbox(EntityPlayer focus, DanmakuUserData data){
        World world = focus.world;
        String npcName = data.name;

        npcName = getGuardName(data, npcName);

        //handle summon protection, guards are unaffected
        if (data.level > 0)
        {
            if (ModConfig.LIVE_CONF.AUTO_SUMMON_LV > data.personLevel){
                return;
            }
            List entities = world.getEntities(EntityCleverNPCForHBR.class, EntitySelectors.IS_ALIVE);
            if (entities.size() > ModConfig.LIVE_CONF.AUTO_SUMMON_MAX){
                return;
            }
        }

        EntityCleverNPCForHBR box = RandomNPCUtil.getRandomNPC(world);

        double angle = focus.rotationYaw * CommonDef.DEG_TO_RAD + Math.PI * 0.5 - focus.getRNG().nextFloat() * Math.PI;
        box.setPosition(focus.posX + range * Math.cos(angle), focus.posY + 64, focus.posZ + range * Math.sin(angle));
        box.setCustomNameTag(npcName);
        world.spawnEntity(box);
        EntityUtil.ApplyBuff(box, MobEffects.RESISTANCE, 4, 5f);
        EntityUtil.ApplyBuff(box, MobEffects.WEAKNESS, 4, 5f);
        EntityUtil.ApplyBuff(box, MobEffects.GLOWING, 4, 5f);
        box.makeLoudFallSound = true;
        box.setOwner(focus);
        box.setBehaviorMode(EnumActionMode.DEFEND);
        box.setAsLevel(data.personLevel+data.wealthLevel);

        switch (data.level) {
            case 3:
                EntityUtil.ApplyBuff(box, MobEffects.GLOWING, 0, 100f);
                box.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.ELYTRA));
                break;
            case 2:
                box.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.ELYTRA));
                break;
        }

        LoginMessage(focus, data.name, data.level, data.personLevel, data.wealthLevel);
    }

    public static String getGuardName(DanmakuUserData data, String npcName) {
        switch (data.level) {
            case 3:
                npcName = "§e" + npcName;
                break;
            case 2:
                npcName = "§b" + npcName;
                break;
            case 1:
                npcName = "§a" + npcName;
                break;
        }
        return npcName;
    }

    public static void LoginMessage(EntityPlayer focus, String name, int guardLevel, int personLevel, int wealthLevel)
    {
        switch (guardLevel) {
            case 3:
                name = "§e" + name + "§r§l";
                break;
            case 2:
                name = "§b" + name + "§r§l";
                break;
            case 1:
                name = "§c" + name + "§r§l";
                break;
        }
        CommonFunctions.SafeSendMsgToPlayer(focus, MSG_LIVE_LOGIN, wealthLevel, personLevel, name);
    }
}
