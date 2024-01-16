package com.deeplake.hbr_mc.init.util;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.items.seraph.SeraphUtil;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EntitySelectors;
import net.minecraft.world.World;

import java.util.List;

public class CombatUtil {
    public static final float DEFAULT_HEAL_CAP = 200;
    static boolean isHBRAttackProcess = false;

    public static boolean isIsHBRAttackProcess() {
        return isHBRAttackProcess;
    }

    public static void areaHeal(World worldIn, EntityPlayer caster, float minHeal, float cap) {
        List<EntityPlayer> players = EntityUtil.getEntitiesWithinAABB(
                worldIn,EntityPlayer.class, caster.getPositionVector(), 32, EntitySelectors.IS_ALIVE
        );
        float maxHeal = minHeal * 3;
        float intelligence = (float) RegisterAttr.getAttrValue(caster, RegisterAttr.INT);
        float healAmount = Math.min(intelligence, cap) / cap * (maxHeal - minHeal) + minHeal;
        for (EntityPlayer player :
                players) {
            SeraphUtil.cureSeraph(SeraphUtil.getFirstSeraphNonBrokenInHand(player), healAmount);
        }
    }

    public static void areaAttack(World worldIn, EntityPlayer caster, float dist, float radius,EnumAttrType atkType, EntityLivingBase player, float minPotency, float cap, float bonusRate) {
        List<EntityLiving> targets = EntityUtil.getEntitiesWithinAABB(
                worldIn,EntityLiving.class, caster.getPositionVector().add(caster.getLookVec()).scale(dist), radius, EntitySelectors.IS_ALIVE
        );

        for (EntityLiving target :
                targets) {
            generalAttack(atkType, player, minPotency, cap, bonusRate, target);
        }
    }

    public static void generalAttack(EnumAttrType atkType, EntityLivingBase player, float minPotency, float cap, float bonusRate, EntityLiving target) {
        switch (atkType){
            case STANDARD:
            case INT_FOCUS:
                attackAsHBR(player, target, atkType,EnumDefType.MEN, minPotency, cap);
                break;
            case STR_FOCUS:
                HPAttack(player, target, minPotency, cap, bonusRate);
                break;
            case DEX_FOCUS:
                DPAttack(player, target, minPotency, cap, bonusRate);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + atkType);
        }
    }

    public static void areaAttackGroup(float[] group, World worldIn, EntityPlayer caster, float dist, float radius,EnumAttrType atkType, EntityLivingBase player, float minPotency, float cap, float bonusRate) {
        for (float ratio: group
             ) {
            areaAttack(worldIn, caster, dist, radius, atkType, player, minPotency * ratio, cap, bonusRate);
        }
    }

    public static void HPAttackGroup(EntityLivingBase player, EntityLivingBase target, float minPotency, float[] group, float cap, float bonusRate)
    {
        for (float ratio: group
             ) {
            HPAttack(player, target, minPotency * ratio, cap, bonusRate);
        }
    }

    public static void HPAttack(EntityLivingBase player, EntityLivingBase target, float minPotency, float cap, float bonusRate) {
        boolean extra = target.getAbsorptionAmount() > 0;
        attackAsHBR(player, target, EnumAttrType.STR_FOCUS, EnumDefType.END,
                extra ? minPotency * bonusRate : minPotency, cap);
    }

    public static void DPAttack(EntityLivingBase player, EntityLivingBase target, float minPotency, float cap, float bonusRate) {
        boolean extra = target.getAbsorptionAmount() <= 0;
        attackAsHBR(player, target, EnumAttrType.DEX_FOCUS, EnumDefType.END,
                extra ? minPotency * bonusRate : minPotency, cap);
    }

    public enum EnumAttrType {
        STANDARD,
        STR_FOCUS,
        DEX_FOCUS,
        INT_FOCUS,
    }

    public enum EnumDefType {
        MEN,
        END,//stamina?
    }

    //min = 1x, max = 5x
    static final float MAX_EXTRA = 4f;

    public static void attackAsHBR(EntityLivingBase attacker, EntityLivingBase target, float cap, float lowDamage) {
        attackAsHBR(attacker, target, EnumAttrType.STANDARD, EnumDefType.END, lowDamage, cap);
    }

    public static void attackAsHBR(EntityLivingBase attacker, EntityLivingBase target, EnumAttrType atkType, EnumDefType defType, float lowDamage, float cap) {
        if (target == null || target.isDead || target.getHealth() <= 0) {
            return;
        }
        isHBRAttackProcess = true;
        target.hurtResistantTime = 0;

        double refAttr = 0;
        switch (atkType)
        {
            case STANDARD:
                refAttr = (RegisterAttr.getAttrValue(attacker, RegisterAttr.STR)+RegisterAttr.getAttrValue(attacker, RegisterAttr.DEX))/2;
                break;
            case STR_FOCUS:
                refAttr = (2*RegisterAttr.getAttrValue(attacker, RegisterAttr.STR)+RegisterAttr.getAttrValue(attacker, RegisterAttr.DEX))/3;
                break;
            case DEX_FOCUS:
                refAttr = (RegisterAttr.getAttrValue(attacker, RegisterAttr.STR)+ 2*RegisterAttr.getAttrValue(attacker, RegisterAttr.DEX))/3;
                break;
            case INT_FOCUS:
                refAttr = attacker.getEntityAttribute(RegisterAttr.INT).getAttributeValue();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + atkType);
        }

        double enemyAttr = 0;
        double men = RegisterAttr.getAttrValue(attacker, RegisterAttr.MEN);
        double end = RegisterAttr.getAttrValue(attacker, RegisterAttr.END);
        switch (defType)
        {
            case MEN:
                enemyAttr= (men * 2 + end) / 3;
                break;
            case END:
                enemyAttr= (end * 2 + men) / 3;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + defType);
        }

        boolean isCritical = checkCritical(attacker);
        if (isCritical)
        {
            enemyAttr -= 50;
            if (enemyAttr < 0)
            {
                enemyAttr = 0;
            }
        }

        float damage;

        float halfEnemyAttr = (float) (enemyAttr/2);
        if (refAttr < halfEnemyAttr)
        {
            damage = 0.01f;
        }
        else {
            //Normal Situation
            if (refAttr < enemyAttr)
            {
                //0~LOW
                float ratio = (float) ((refAttr - halfEnemyAttr) / halfEnemyAttr);
                damage = lowDamage * ratio;
            }
            else if (refAttr < cap + enemyAttr)
            {
                //LOW~MAX
                //MAX = 1 + MAX_EXTRA
                double deltaValue = refAttr - enemyAttr;
                float ratio = (float) (deltaValue/cap) * MAX_EXTRA + 1;
                damage = lowDamage * ratio;
            }
            else
            {
                damage = lowDamage * (MAX_EXTRA + 1);
            }
            damage *= (target.getRNG().nextFloat() * 0.2f + 0.9f);
        }

        if(isCritical)
        {
            Main.Log("CrtDMG=%2f:[%s]->[%s]",damage,attacker==null ? "NULL" :attacker.getName(),target.getName());
        }
        else {
            Main.Log("Damage=%2f:[%s]->[%s]",damage,attacker==null ? "NULL" :attacker.getName(),target.getName());
        }

        Main.Log("(%2f,%2f)=DP,HP",target.getAbsorptionAmount(),target.getHealth());
        target.attackEntityFrom(EntityUtil.attack(attacker), damage);

        isHBRAttackProcess = false;
    }

    public static boolean checkCritical(EntityLivingBase attacker)
    {
        float luck = (float) RegisterAttr.getAttrValue(attacker, RegisterAttr.LUC);
        //todo: check buff
        float chance = (1 - 1 / (luck + 1)) * 0.1f;
        return attacker.getRNG().nextFloat() < chance;
    }
}
