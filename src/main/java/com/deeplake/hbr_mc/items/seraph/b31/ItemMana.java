package com.deeplake.hbr_mc.items.seraph.b31;

import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.init.RegisterEffects;
import com.deeplake.hbr_mc.init.util.CombatUtil;
import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.init.util.EntityUtil;
import com.deeplake.hbr_mc.items.seraph.EnumSeraphType;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class ItemMana extends ItemSeraphBase {
    public ItemMana(String name) {
        super(name, EnumSeraphType.SCYTHE);
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
            CombatUtil.attackGroup(player, target, minPotency, new float[]{0.25f,0.25f,0.25f,0.25f}, cap);

            //apply debuff
            float pwr = (float) ((EntityUtil.getAttr(player, RegisterAttr.INT) + 2 * EntityUtil.getAttr(player, RegisterAttr.LUC))/3f);
            float enemyBorder = (float) EntityUtil.getAttr(player, RegisterAttr.MEN);

            float ratio = (pwr - enemyBorder)/cap;
            ratio = CommonFunctions.clamp(ratio,0,1);

            float chance = (float) (0.3 + 0.55 * ratio);
            if (player.getRNG().nextFloat() < chance)
            {
                target.addPotionEffect(new PotionEffect(RegisterEffects.SEAL,ModConfig.COMBAT.SEAL_TICK_PER_TURN,0));
            }

            postCastSkill(player, worldIn, ModConfig.COMBAT.MANA_5SP_SEAL, SoundEvents.BLOCK_GLASS_BREAK);
        }
        else {
            worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, player.posX,player.posY,player.posZ,0,0,0);
        }

        return true;
    }
}
