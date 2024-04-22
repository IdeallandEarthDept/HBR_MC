package com.deeplake.hbr_mc.entities.ai.idl;

import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.init.util.CommonDef;
import com.deeplake.hbr_mc.init.util.EntityUtil;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.util.EnumHand;

public class EntityAIMeleeIDL extends EntityAIAttackMelee {
    float reachDist = 1.5f;
    public EntityAIMeleeIDL(EntityCreature creature, double speedIn, boolean useLongMemory) {
        super(creature, speedIn, useLongMemory);
    }

    public EntityAIMeleeIDL(EntityCreature creature, double speedIn, boolean useLongMemory, float reach) {
        super(creature, speedIn, useLongMemory);
        reachDist = reach;
    }

    protected double getAttackReachSqr(EntityLivingBase attackTarget)
    {
        //super : (double)(this.attacker.width * 2.0F *
        // this.attacker.width * 2.0F + attackTarget.width)
        double reachAttr = reachDist;
        IAttributeInstance attribute = attacker.getEntityAttribute(RegisterAttr.AI_REACH);
        if (attribute != null)
        {
            reachAttr = attribute.getAttributeValue();
        }
        return reachAttr*reachAttr + attackTarget.width;
    }

    //applies attack speed to it.
    //assuming all will have ATTACK_SPEED as ModAttributes did.
    protected void checkAndPerformAttack(EntityLivingBase p_190102_1_, double p_190102_2_)
    {
        double d0 = this.getAttackReachSqr(p_190102_1_);

        if (p_190102_2_ <= d0 && this.attackTick <= 0)
        {
            attackTick = (int)
                    (CommonDef.TICK_PER_SECOND /
                            attacker.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).getAttributeValue()
                    * EntityUtil.getHasteModifierIDL(attacker));
            attacker.swingArm(EnumHand.MAIN_HAND);
            attacker.attackEntityAsMob(p_190102_1_);
        }
    }
}
