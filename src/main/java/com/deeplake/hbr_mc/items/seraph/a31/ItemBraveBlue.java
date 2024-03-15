package com.deeplake.hbr_mc.items.seraph.a31;

import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.util.CombatUtil;
import com.deeplake.hbr_mc.items.seraph.EnumSeraphType;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
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

    @Override
    public boolean castSkillEnemy(ItemStack stack, World worldIn, EntityPlayer player, EntityLivingBase target) {
        //
        if (!canUseSkills(player))
        {
            return false;
        }

        if (!worldIn.isRemote)
        {
            float minPotency = ModConfig.COMBAT.BRAVE_BLUE_A.MIN_POTENCY;
            float cap = ModConfig.COMBAT.BRAVE_BLUE_A.CAP;
            float bonusRate = ModConfig.COMBAT.BONUS_DAMAGE_RATE;
            calcAtkBuffSkill(player);
            CombatUtil.hpattackgroup(player, target, minPotency,new float[]{0.5f,0.5f}, cap, bonusRate);
            postCastSkill(player,worldIn,ModConfig.COMBAT.BRAVE_BLUE_A,SoundEvents.BLOCK_ANVIL_USE);
        }
        else {
            worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, player.posX,player.posY,player.posZ,0,0,0);
        }

        return true;
    }
}
