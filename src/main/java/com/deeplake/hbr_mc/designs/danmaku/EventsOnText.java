package com.deeplake.hbr_mc.designs.danmaku;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.designs.danmaku.basic.TextDanmakuEvent;
import com.deeplake.hbr_mc.entities.ai.idl.EnumActionMode;
import com.deeplake.hbr_mc.entities.npc.EntityCleverNPCForHBR;
import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.init.util.EntityUtil;
import com.deeplake.hbr_mc.init.util.MessageDef;
import com.deeplake.hbr_mc.init.util.PlayerUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class EventsOnText {

    public static int lastCmd = -1;
    public static final List<Integer> queue = new ArrayList<>();//keycode
    public static final Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            Main.LogWarning(e.toString());
            throw new RuntimeException(e);
        }
    }

    @SubscribeEvent
    public static void onTick(TickEvent.PlayerTickEvent event)
    {
        if (event.player.world.isRemote)
        {
            if (lastCmd != -1)
            {
                robot.keyRelease(lastCmd);
            }

            if (robot != null && !queue.isEmpty())
            {
                robot.keyPress(queue.get(0));
                lastCmd = queue.get(0);
                queue.remove(0);
            }
            else {
                lastCmd = -1;
            }

        }
    }

    @SubscribeEvent
    public static void onSendDanmaku(TextDanmakuEvent event) {
        PlayerList list = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList();
        for (EntityPlayer player : list.getPlayers()) {
            World world = player.world;
            Random random = player.getRNG();

            if (!world.isRemote && DanmakuEventUtil.checkEnchantmentReq(player)) {
                String message = event.getMessage();
                String senderName = event.getSenderName();

                boolean foundAvatar = false;

                List<EntityCleverNPCForHBR> boxManList = world.getEntities(EntityCleverNPCForHBR.class, EntityUtil.ALL_ALIVE);
                for (EntityCleverNPCForHBR man : boxManList) {
                    if (man.getDisplayName().getUnformattedText().contains(senderName.toLowerCase())) {
                        if (message.contains("跟随")) {
                            man.setBehaviorMode(EnumActionMode.FOLLOW);
                            if (man.getOwner() == null) {
                                man.setOwner(player);
                            }
                        }
                        if (message.contains("战斗")) {
                            man.setBehaviorMode(EnumActionMode.ATTACK);
                        }
                        if (message.contains("保护")) {
                            man.setBehaviorMode(EnumActionMode.DEFEND);
                        }
                        if (message.contains("背叛") || message.contains("打架")) {
                            man.setBehaviorMode(EnumActionMode.BETRAY);
                            man.setAttackTarget(player);
                            man.setOwner(null);
                        }
                        executeCommands(man, world, random, message, senderName);
                        foundAvatar = true;
                    }
                }

//                if (!(foundAvatar && ModConfig.LIVE_CONF.AUTO_SELECT_BOXMAN)) {
//                    executeCommands(player, world, random, message, senderName);
//                }

                DanmakuEventUtil.DanmakuUserData data = event.getDanmakuUserData();
                if (message.contains("来了") || message.contains("来啦") || message.contains("来纳") || message.contains("复活")) {
                    DanmakuEventUtil.refreshOrSummon(player, data);
                }

                CommonFunctions.SafeSendMsgToPlayer(player, MessageDef.MSG_LIVE_TEXT,
                        data.wealthLevel, data.personLevel, senderName, message);
            }

            //Remote
            if (!world.isRemote && ModConfig.LIVE_CONF.MOVES_PLAYER)
            {
                parseCommands(event.getMessage());
            }
        }
    }

    protected static void executeCommands(EntityLivingBase target, World world, Random random, String message, String senderName) {
        if (message.contains("tnt") || message.contains("TNT")) {
            EntityTNTPrimed tnt = new EntityTNTPrimed(world);
            tnt.setPosition(target.posX, target.posY, target.posZ);
            world.spawnEntity(tnt);
        }

        if (message.contains("。")) {
            EntityUtil.ApplyBuff(target, MobEffects.WATER_BREATHING, 0, 5f);
        }

        if (message.contains("草")) {
            world.setBlockState(target.getPosition().add(random.nextInt(5) - 2, 0, random.nextInt(5) - 2),
                    Blocks.TALLGRASS.getDefaultState());
        }

        if (message.contains("蕨")) {
            world.setBlockState(target.getPosition().add(random.nextInt(5) - 2, 0, random.nextInt(5) - 2),
                    Blocks.TALLGRASS.getDefaultState());
        }

        if (message.contains("鸡")) {
            EntityLivingBase tnt = new EntityChicken(world);
            tnt.setPosition(target.posX, target.posY + 2, target.posZ);
            tnt.setCustomNameTag(senderName);
            world.spawnEntity(tnt);
            world.playSound(null, target.getPosition(), SoundEvents.ENTITY_CHICKEN_HURT, SoundCategory.PLAYERS, 1f, 1f);
        }

        if (message.contains("牛")) {
            EntityLivingBase tnt = new EntityCow(world);
            tnt.setPosition(target.posX, target.posY + 2, target.posZ);
            tnt.setCustomNameTag(senderName);
            world.spawnEntity(tnt);
            world.playSound(null, target.getPosition(), SoundEvents.ENTITY_COW_AMBIENT, SoundCategory.PLAYERS, 1f, 1f);
        }

        if (message.contains("万箭齐发")) {
            double _x = target.posX;
            double _y = target.posY + 20;
            double _z = target.posZ;
            for (int x = -3; x <= 3; x++) {
                for (int z = -3; z <= 3; z++) {
                    EntityArrow tnt = new EntityTippedArrow(world);
                    tnt.setPosition(_x + x, _y, _z + z);
                    world.spawnEntity(tnt);
                }
            }

            world.playSound(null, target.getPosition(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1f, 1f);
        }

        if (message.contains("苦力怕")) {
            EntityLiving tnt = new EntityCreeper(world);
            double omega = target.getRNG().nextFloat() * 2 * Math.PI;
            tnt.setPosition(target.posX - 8 * target.getLook(0).x, target.posY + 2, target.posZ - 8 * target.getLook(0).z);
//                    tnt.getLookHelper().setLookPosition();
            tnt.setAttackTarget(target);
            tnt.setCustomNameTag(senderName);
            world.spawnEntity(tnt);
//                    world.playSound(null, player.getPosition(), SoundEvents.ENTITY_COW_AMBIENT, SoundCategory.PLAYERS, 1f, 1f);
            CommonFunctions.SafeSendMsgToPlayer(target, "OHHH——MAN");
        }

        if (message.contains("很有精神")) {
            EntityUtil.ApplyBuff(target, MobEffects.HASTE, 0, 5);
            EntityUtil.ApplyBuff(target, MobEffects.STRENGTH, 0, 5);
            world.playSound(null, target.getPosition(), SoundEvents.ENTITY_COW_AMBIENT, SoundCategory.PLAYERS, 1f, 1f);
        }

        if (message.contains("起飞") || message.contains("芜湖")) {
            EntityUtil.ApplyBuff(target, MobEffects.LEVITATION, 0, 5);
            world.playSound(null, target.getPosition(), SoundEvents.ENTITY_COW_AMBIENT, SoundCategory.PLAYERS, 1f, 1f);
        }

        if (message.contains("裂开")) {

        }

        if (message.contains("爬")) {
            target.setSneaking(!target.isSneaking());
        }

        if (message.contains("夜视")) {
            EntityUtil.ApplyBuff(target, MobEffects.NIGHT_VISION, 0, 60);
        }

//        if (message.contains("害怕")) {
//            EntityUtil.ApplyBuff(target, ModPotions.FEAR, 0, 5);
//            world.playSound(null, target.getPosition(), SoundEvents.ENTITY_COW_AMBIENT, SoundCategory.PLAYERS, 1f, 1f);
//        }

        if (message.contains("妙啊") || message.contains("奶一口") || message.contains("药")) {
            target.heal(2);
            world.playSound(null, target.getPosition(), SoundEvents.ENTITY_COW_AMBIENT, SoundCategory.PLAYERS, 1f, 1f);
        }

        if (message.contains("鸡汤来啦")) {
            EntityUtil.ApplyBuff(target, MobEffects.POISON, 0, 5);
            world.playSound(null, target.getPosition(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 1f, 1f);
        }
        float base_range = 16f;

        if (message.contains("来打我呀")) {
            Vec3d basePos = target.getPositionVector();
            List<EntityLiving> entities = world.getEntitiesWithinAABB(EntityLiving.class,
                    CommonFunctions.ServerAABB(basePos.addVector(-base_range, -base_range, -base_range),
                            basePos.addVector(base_range, base_range, base_range)));
            for (EntityLiving living : entities) {
                living.setAttackTarget(target);
            }
            world.playSound(null, target.getPosition(), SoundEvents.ENTITY_WITCH_AMBIENT, SoundCategory.PLAYERS, 2f, 1f);
        }

        if (message.contains("牛奶")) {
            target.clearActivePotions();
            world.playSound(null, target.getPosition(), SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS, 2f, 1f);
        }

        if (message.contains("退")) {
            float level = base_range;
            Vec3d basePos = target.getPositionVector();
            List<EntityLiving> entities = world.getEntitiesWithinAABB(EntityLiving.class, CommonFunctions.ServerAABB(basePos.addVector(-level, -level, -level), basePos.addVector(level, level, level)));
            for (EntityLiving living : entities
            ) {
                EntityUtil.simpleKnockBack(0.8f, living, target);
            }

            world.playSound(null, target.getPosition(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 2f, 1f);
        }

        if (message.contains("固若金汤") || message.contains("安如磐石") || message.contains("俱收并蓄") || message.contains("盾")) {
            target.setAbsorptionAmount(target.getMaxHealth() / 2f);
            world.playSound(null, target.getPosition(), SoundEvents.BLOCK_METAL_PLACE, SoundCategory.PLAYERS, 2f, 1f);
        }

        if (target instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) target;

            if (message.contains("鱼")) {
                ItemStack stack = new ItemStack(Items.FISH);
                stack.setStackDisplayName(senderName);
                PlayerUtil.giveToPlayer(player, stack);
                world.playSound(null, player.getPosition(), SoundEvents.ENTITY_PLAYER_SPLASH, SoundCategory.PLAYERS, 1f, 1f);
            }

            if (message.contains("火把")) {
                PlayerUtil.giveToPlayer(player, new ItemStack(Blocks.TORCH));
                world.playSound(null, player.getPosition(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 1f, 1f);
            }

            if (message.contains("保熟吗")) {
                ItemStack stack = new ItemStack(Items.MELON);
                stack.setStackDisplayName(senderName);
                PlayerUtil.giveToPlayer(player, stack);
                world.playSound(null, player.getPosition(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 1f, 1f);
            }
        }
    }

    public static void parseCommands(String input)
    {
        if (checkqueueOverflow()) return;

        if (input.contains("切")) {
            Main.Log("cmd:F5");
            queue.add(KeyEvent.VK_F5);
        }

        if (input.contains("左转")) {
            Main.Log("cmd:Trun Left");
            Minecraft.getMinecraft().player.rotationYaw += 90;
        }

        if (input.contains("右转")) {
            Main.Log("cmd:Turn right");
            Minecraft.getMinecraft().player.rotationYaw -= 90;
        }

        if (input.contains("抬头")) {
            Main.Log("cmd:Raise");
            Minecraft.getMinecraft().player.rotationPitch += 30;
        }

        if (input.contains("低头")) {
            Main.Log("cmd:Lower");
            Minecraft.getMinecraft().player.rotationPitch -= 30;
        }

        for (int i = 0; i < input.length(); i++) {
            char cmd = input.charAt(i);

            Main.Log("cmd:%s", cmd);
            switch (cmd)
            {
                case 'w':case 'W':
                case 'a':case 'A':
                case 's':case 'S':
                case 'd':case 'D':
                    for (int j = 0; j < 5; j++) {
                        queue.add(cmd - 'a' + KeyEvent.VK_A);
                    }
                    break;
                case 'q':
                    queue.add(cmd - 'a' + KeyEvent.VK_A);
                    break;
                //sp
                case 'l':case 'L':
                    queue.add(cmd - 'a' + KeyEvent.VK_A);
                    break;
                case 'r':case 'R':
                    queue.add(cmd - 'a' + KeyEvent.VK_A);
                    break;

                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    queue.add(cmd - '1' + KeyEvent.VK_1);
                    break;

                case ' ':
                    queue.add(KeyEvent.VK_SPACE);
                    break;
                default:
                    //nothing;
            }


        }

    }

    private static boolean checkqueueOverflow() {
        if (queue.size() >= 999999)
        {
            CommonFunctions.broadcastbykey("Too many commands, please wait a while");
            return true;
        }
        return false;
    }
}
