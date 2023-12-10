package com.deeplake.hbr_mc.init.util;

import com.deeplake.hbr_mc.init.RegisterAttr;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;

import java.util.UUID;

public class DShieldUtil {
    public static final UUID DP_DAMAGE_UUID = UUID.fromString("BF7582FA-244A-4B9E-B1ED-160F7FE96680");

    //called by serverside
    public static void syncDPStatus(EntityLivingBase entityLivingBase){
        if (entityLivingBase == null || entityLivingBase.getEntityWorld().isRemote)
        {
            return;
        }
        IAttributeInstance attributeInstance = entityLivingBase.getEntityAttribute(RegisterAttr.DP_SYNC);
        if (attributeInstance != null) {
            attributeInstance.setBaseValue(RegisterAttr.getAttrValue(entityLivingBase, RegisterAttr.DP_MAX)-entityLivingBase.getAbsorptionAmount());
        }
    }

    public static double getDPDamage(EntityLivingBase entityLivingBase, float damage) {
        IAttributeInstance attributeInstance = entityLivingBase.getEntityAttribute(RegisterAttr.DP_SYNC);
        if (attributeInstance != null) {
            double dp = attributeInstance.getAttributeValue();
            if (dp > 0) {
                return Math.min(dp, damage);
            }
        }
        return 0;
    }

    public static double getRemainDP(EntityLivingBase entityLivingBase) {
        return RegisterAttr.getAttrValue(entityLivingBase, RegisterAttr.DP_MAX)
                -RegisterAttr.getAttrValue(entityLivingBase, RegisterAttr.DP_SYNC);
    }

}
