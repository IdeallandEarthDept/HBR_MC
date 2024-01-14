package com.deeplake.hbr_mc.items.seraph.a31;

import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.init.util.CombatUtil;
import com.deeplake.hbr_mc.init.util.CommonDef;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

//Ruka Kayamori
//[A] Attack or Music
public class ItemBraveBlue extends ItemSeraphBase {
    public ItemBraveBlue(String name) {
        super(name);
    }

    @Override
    public int getMaxSkillSlot(ItemStack stack) {
        return 1;
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
    public boolean castSkillEnemy(ItemStack stack, World worldIn, EntityPlayer player, EntityLivingBase target) {
        //
        if (!canUseSkill(stack, 0))
        {
            return false;
        }

        if (!worldIn.isRemote)
        {
            float minPotency = ModConfig.COMBAT.BRAVE_BLUE_A.MIN_POTENCY;
            float cap = ModConfig.COMBAT.BRAVE_BLUE_A.CAP;
            float bonusRate = ModConfig.COMBAT.BONUS_DAMAGE_RATE;;

            CombatUtil.HPAttackGroup(player, target, minPotency,new float[]{0.15f,0.05f,0.05f,0.05f,0.05f,0.05f,0.05f,0.5f}, cap, bonusRate);
            player.swingArm(EnumHand.OFF_HAND);
            player.swingArm(EnumHand.MAIN_HAND);
            setCoolDown(player, CommonDef.TICK_PER_SECOND * ModConfig.COMBAT.BRAVE_BLUE_ULTI.SP);
            worldIn.playSound(null, player.getPosition(), SoundEvents.BLOCK_ANVIL_USE,SoundCategory.PLAYERS, 1f,1.5f);
        }
        else {
            worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, player.posX,player.posY,player.posZ,0,0,0);
        }

        return true;
    }
}
