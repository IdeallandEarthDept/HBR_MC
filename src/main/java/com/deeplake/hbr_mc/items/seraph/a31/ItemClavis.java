package com.deeplake.hbr_mc.items.seraph.a31;

import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.init.RegisterEffects;
import com.deeplake.hbr_mc.init.util.CombatUtil;
import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.init.util.EntityUtil;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class ItemClavis extends ItemSeraphBase {
    public ItemClavis(String name) {
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
            float minPotency = ModConfig.COMBAT.CLAVIS_A.MIN_POTENCY;
            float cap = ModConfig.COMBAT.CLAVIS_A.CAP;
            calcAtkBuffSkill(player);
            CombatUtil.generalAttack(CombatUtil.EnumAttrType.STANDARD, player, minPotency*0.5f, cap, 0, target);
            CombatUtil.generalAttack(CombatUtil.EnumAttrType.STANDARD, player, minPotency*0.5f, cap, 0, target);

            //apply debuff
            float pwr = (float) ((EntityUtil.getAttr(player, RegisterAttr.INT) + 2 * EntityUtil.getAttr(player, RegisterAttr.LUC))/3f);
            float enemyBorder = (float) EntityUtil.getAttr(player, RegisterAttr.MEN);

            float ratio = (pwr - enemyBorder)/cap;
            ratio = CommonFunctions.clamp(ratio,0,1);

            float chance = (float) (0.3 + 0.55 * ratio);
            if (player.getRNG().nextFloat() < chance)
            {
                target.addPotionEffect(new PotionEffect(RegisterEffects.STUNNED,ModConfig.COMBAT.STUN_TICK_PER_TURN,0));
            }

            postCastSkill(player, worldIn, ModConfig.COMBAT.CLAVIS_A, SoundEvents.BLOCK_ANVIL_FALL);
        }
        else {
            worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, player.posX,player.posY,player.posZ,0,0,0);
        }

        return true;
    }
}
