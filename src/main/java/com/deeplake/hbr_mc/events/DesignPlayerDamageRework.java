package com.deeplake.hbr_mc.events;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.util.CombatUtil;
import com.deeplake.hbr_mc.init.util.EntityUtil;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphBase;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphCannonBase;
import com.deeplake.hbr_mc.items.seraph.SeraphUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class DesignPlayerDamageRework {

    @SubscribeEvent
    public static void tryReplacePlayerNormalAttack(LivingHurtEvent event)
    {
        if (CombatUtil.isIsHBRAttackProcess() || event.isCanceled())
        {
            return;
        }
        //first we check if it's player damage
        Entity trueSource = event.getSource().getTrueSource();
        if (trueSource instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) trueSource;
            if (SeraphUtil.canAttackWithMainHandSeraph(player))
            {
                EntityLivingBase target = event.getEntityLiving();
                event.setCanceled(true);

                Item item = player.getHeldItemMainhand().getItem();
                if (item instanceof ItemSeraphCannonBase)
                {
                    return;
                }

                float fullPower = ModConfig.COMBAT.NORMAL_ATK_POWER;
                float normal_atk_cap = ModConfig.COMBAT.NORMAL_ATK_CAP;
                int hits = ((ItemSeraphBase)item).type.hitCount;
                float powerPerHit = fullPower / hits;
                for (int i = 0; i < hits; i++) {
                    CombatUtil.attackAsHBR(player, target, normal_atk_cap, powerPerHit);
                }
            }
        }
    }
}
