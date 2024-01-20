package com.deeplake.hbr_mc.items.seraph.a31;

import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.init.util.CombatUtil;
import com.deeplake.hbr_mc.init.util.CommonDef;
import com.deeplake.hbr_mc.items.seraph.EnumSeraphType;
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
        super(name, EnumSeraphType.DOUBLE_SWORD);
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
        if (!canUseSkill(stack, 0) || !canUseSkills(player))
        {
            return false;
        }

        if (!worldIn.isRemote)
        {
            float minPotency = ModConfig.COMBAT.BRAVE_BLUE_A.MIN_POTENCY;
            float cap = ModConfig.COMBAT.BRAVE_BLUE_A.CAP;
            float bonusRate = ModConfig.COMBAT.BONUS_DAMAGE_RATE;;
            calcAtkBuffSkill(player);
            CombatUtil.HPAttackGroup(player, target, minPotency,new float[]{0.5f,0.5f}, cap, bonusRate);
            postCastSkill(player,worldIn,ModConfig.COMBAT.BRAVE_BLUE_A,SoundEvents.BLOCK_ANVIL_USE);
        }
        else {
            worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, player.posX,player.posY,player.posZ,0,0,0);
        }

        return true;
    }
}
