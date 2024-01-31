package com.deeplake.hbr_mc.events;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.RegisterItem;
import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.init.util.PlayerUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class EventsAdvancements {
    @SubscribeEvent
    public static void onAdv(AdvancementEvent event)
    {
        EntityPlayer player = event.getEntityPlayer();
        if (player.world.isRemote)
        {
            return;
        }

        //prevent errors
        if (event.getAdvancement().getDisplay() == null)
        {
            return;
        }

        int count;
        switch (event.getAdvancement().getDisplay().getFrame())
        {
            case TASK:
                count = ModConfig.CONFIG.CRYSTAL_PER_TASK;
                break;
            case GOAL:
                count = ModConfig.CONFIG.CRYSTAL_PER_GOAL;
                break;
            case CHALLENGE:
                count = ModConfig.CONFIG.CRYSTAL_PER_CHALL;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + event.getAdvancement().getDisplay().getFrame());
        }
        if (count > 0)
        {
            PlayerUtil.giveToPlayer(player, new ItemStack(RegisterItem.GEM_LOTTERY, count));
            CommonFunctions.SafeSendMsgToPlayer(player, "hbr_mc.msg.bonus_for_advancements", count);
        }
    }
}
