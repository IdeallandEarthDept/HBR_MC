package com.deeplake.hbr_mc.items.seraph;

import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.init.util.CombatUtil;
import com.deeplake.hbr_mc.init.util.CommonDef;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

//Ruka Kayamori
public class ItemBraveBlue extends ItemSeraphBase{
    public ItemBraveBlue(String name) {
        super(name);
    }

    public float getAttrValue(ItemStack stack, IAttribute attr)
    {
        if (attr == RegisterAttr.STR)
        {
            return super.getAttrValue(stack, attr) + 1;
        }
        return super.getAttrValue(stack, attr);
    }

    @Override
    public void castSkillSneak(ItemStack stack, World worldIn, EntityPlayer caster) {
        if (!worldIn.isRemote)
        {
            float minHeal = 305;
            float cap = 200;

            CombatUtil.areaHeal(worldIn, caster, minHeal, cap);
            worldIn.playSound(null, caster.getPosition(), SoundEvents.ENTITY_GENERIC_DRINK,SoundCategory.PLAYERS, 1f,1.5f);
            setCoolDown(caster, CommonDef.TICK_PER_SECOND * 5);
        }
    }

    @Override
    public boolean castSkillFriend(ItemStack stack, World worldIn, EntityPlayer player, EntityLivingBase target) {
        castSkillSneak(stack, worldIn, player);
        return true;
    }

    @Override
    public boolean castSkillEnemy(ItemStack stack, World worldIn, EntityPlayer player, EntityLivingBase target) {
        //original: 12SP, 138 cap, 1602-8010 potency
        if (!worldIn.isRemote)
        {
            float minPotency = 160;
            float cap = 138;
            float bonusRate = 1.3f;

            boolean extra = target.getAbsorptionAmount() > 0;
            CombatUtil.attackAsHBR(player, target, CombatUtil.EnumAttrType.STR_FOCUS, CombatUtil.EnumDefType.END,
                    extra ? minPotency * 0.15f * bonusRate : minPotency * 0.15f, cap);

            for (int i = 0; i < 6; i++) {
                extra = target.getAbsorptionAmount() > 0;
                CombatUtil.attackAsHBR(player, target, CombatUtil.EnumAttrType.STR_FOCUS, CombatUtil.EnumDefType.END,
                        extra ? minPotency * 0.05f * bonusRate : minPotency * 0.05f, cap);
            }
            extra = target.getAbsorptionAmount() > 0;
            CombatUtil.attackAsHBR(player, target, CombatUtil.EnumAttrType.STR_FOCUS, CombatUtil.EnumDefType.END,
                    extra ? minPotency * 0.5f * bonusRate : minPotency * 0.5f, cap);
            setCoolDown(player, CommonDef.TICK_PER_SECOND * 12);
            worldIn.playSound(null, player.getPosition(), SoundEvents.ENTITY_GENERIC_EXPLODE,SoundCategory.PLAYERS, 1f,1.5f);
        }
        else {
            worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, player.posX,player.posY,player.posZ,0,0,0);
        }

        return true;
    }
}
