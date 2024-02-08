package com.deeplake.hbr_mc.designs.chara.f31;

import com.deeplake.hbr_mc.entities.npc.f31.EntityINatsume;
import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.init.RegisterItem;
import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.items.seraph.EnumSeraphType;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphBase;
import com.deeplake.hbr_mc.items.seraph.SeraphUtil;
import com.deeplake.hbr_mc.items.seraph.f31.ItemRenBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class DesignINatsume {
    public static boolean NO_SPAM = false;
    //Judgement-Damage dealt to weak enemies is increased by 50%.
    @SubscribeEvent
    public static void onAttackEvent(LivingHurtEvent event)
    {
        EntityLivingBase attacker = (EntityLivingBase) event.getSource().getTrueSource();
        EntityLivingBase target = event.getEntityLiving();
        if (attacker != null && target != null)
        {
            ItemStack stack = attacker.getHeldItemOffhand();
            boolean correctItem = SeraphUtil.isIntactSeraph(stack) && stack.getItem() instanceof ItemRenBase;
            correctItem |= stack.getItem() == RegisterItem.REN;
            if (correctItem || attacker instanceof EntityINatsume)
            {
                Item mainHand = attacker.getHeldItemMainhand().getItem();
                if (mainHand instanceof ItemSword ||
                        (mainHand instanceof ItemSeraphBase &&
                                ((ItemSeraphBase) mainHand).type == EnumSeraphType.SWORD))
                {
                    if (checkExecute(attacker, target))
                    {
                        event.setAmount(event.getAmount() * 99F);
                        if (!NO_SPAM) {
                            NO_SPAM = true;
                            CommonFunctions.broadCastByKeyInRange(attacker.world, attacker.getPositionVector(),
                                    32, TextFormatting.BOLD,"hbr_mc.msg.natsume_judgement");
                        }
                    }
                }
            }
        }
    }

    public static boolean checkExecute(EntityLivingBase attacker, EntityLivingBase target)
    {
        if (target.getAbsorptionAmount() <= 0)
        {
            if (RegisterAttr.getAttrValue(target, RegisterAttr.DP_MAX) <= 0)
            {
                ItemStack stack = target.getHeldItemMainhand();
                if (!SeraphUtil.isIntactSeraph(stack) && target.isNonBoss())
                {
                    if (target.getHealth() < attacker.getMaxHealth() * 2F)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
