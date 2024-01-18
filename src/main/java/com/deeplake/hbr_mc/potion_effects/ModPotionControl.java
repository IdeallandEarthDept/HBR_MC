package com.deeplake.hbr_mc.potion_effects;

import com.deeplake.hbr_mc.entities.ai.EntityAIStunned;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;

public class ModPotionControl extends ModPotionBase{
    public ModPotionControl(boolean isBadEffectIn, int liquidColorIn, String name, int icon) {
        super(isBadEffectIn, liquidColorIn, name, icon);
    }

    @Override
    public void applyAttributesModifiersToEntity(EntityLivingBase entityLivingBaseIn, AbstractAttributeMap attributeMapIn, int amplifier) {
        super.applyAttributesModifiersToEntity(entityLivingBaseIn, attributeMapIn, amplifier);
        if (entityLivingBaseIn instanceof EntityLiving)
        {
            EntityLiving living = (EntityLiving) entityLivingBaseIn;
            for (EntityAITasks.EntityAITaskEntry ai:
                    living.tasks.taskEntries) {
                if (ai.action instanceof EntityAIStunned)
                {
                    return;
                }
            }

            living.tasks.addTask(0, new EntityAIStunned(living));
        }
    }
}
