package com.deeplake.hbr_mc.init.util;

import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.items.seraph.SeraphUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class DShieldUtil {
    public static final UUID DP_DAMAGE_UUID = UUID.fromString("BF7582FA-244A-4B9E-B1ED-160F7FE96680");

    //called by serverside
    public static void syncDPStatus(EntityLivingBase entityLivingBase){
        if (entityLivingBase == null || entityLivingBase.getEntityWorld().isRemote)
        {
            return;
        }
        IAttributeInstance attributeInstance = entityLivingBase.getEntityAttribute(RegisterAttr.DP_LOSS_SYNC);
        if (attributeInstance != null) {
            attributeInstance.setBaseValue(RegisterAttr.getAttrValue(entityLivingBase, RegisterAttr.DP_MAX)-entityLivingBase.getAbsorptionAmount());
        }
    }

    public static double getDPDamage(EntityLivingBase entityLivingBase, float damage) {
        IAttributeInstance attributeInstance = entityLivingBase.getEntityAttribute(RegisterAttr.DP_LOSS_SYNC);
        if (attributeInstance != null) {
            double dp = attributeInstance.getAttributeValue();
            if (dp > 0) {
                return Math.min(dp, damage);
            }
        }
        return 0;
    }

    public static boolean isDPDepleted(EntityLivingBase entityLivingBase) {
        double max = RegisterAttr.getAttrValue(entityLivingBase, RegisterAttr.DP_MAX);
        if (max != 0)
        {
            if (RegisterAttr.getAttrValue(entityLivingBase, RegisterAttr.DP_LOSS_SYNC) >= max)
            {
                return false;
            }
        }
        return true;
    }

    public static double getRemainDP(EntityLivingBase entityLivingBase) {
        if (entityLivingBase instanceof EntityPlayer)
        {
            ItemStack stack = entityLivingBase.getHeldItemMainhand();
            if (SeraphUtil.isSeraph(stack))
            {
                return stack.getMaxDamage() - stack.getItemDamage() + entityLivingBase.getAbsorptionAmount();
            }
            else
            {
                if (entityLivingBase.world.isRemote)
                {
                    return entityLivingBase.getEntityAttribute(RegisterAttr.DP_LOSS_SYNC).getAttributeValue();
                }
                else {
                    return entityLivingBase.getAbsorptionAmount();
                }

            }
        }

        return RegisterAttr.getAttrValue(entityLivingBase, RegisterAttr.DP_MAX)
                -RegisterAttr.getAttrValue(entityLivingBase, RegisterAttr.DP_LOSS_SYNC);
    }

}
