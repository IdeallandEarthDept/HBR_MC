package com.deeplake.hbr_mc.items.seraph.a31;

import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.util.CombatUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class ItemGlitterShadowA extends ItemGlitterShadowBase {
    public ItemGlitterShadowA(String name) {
        super(name);
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
            float minPotency = ModConfig.COMBAT.GLITTER_SHADOW_A.MIN_POTENCY;
            float cap = ModConfig.COMBAT.GLITTER_SHADOW_A.CAP;
            calcAtkBuffSkill(player);
            CombatUtil.AttackGroup(player, target, minPotency,new float[]{0.25f,0.25f,0.25f,0.25f}, cap);
            postCastSkill(player,worldIn,ModConfig.COMBAT.GLITTER_SHADOW_A, SoundEvents.BLOCK_ANVIL_USE);
        }
        else {
            worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, player.posX,player.posY,player.posZ,0,0,0);
        }

        return true;
    }
}
