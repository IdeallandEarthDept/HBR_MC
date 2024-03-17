package com.deeplake.hbr_mc.items.seraph.x31;

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

public class ItemIronMaidenA extends ItemSeraphBase {
    public ItemIronMaidenA(String name) {
        super(name, EnumSeraphType.SCYTHE);
    }
    @Override
    public int getMaxSkillSlot(ItemStack stack) {
        return 1;
    }

    @Override
    public void castSkillNonSneak(ItemStack stack, World worldIn, EntityPlayer player) {
        castSkillEnemy(stack, worldIn, player, null);
    }

    @Override
    public void castSkillSneak(ItemStack stack, World worldIn, EntityPlayer player) {
        castSkillNonSneak(stack, worldIn, player);
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
            ModConfig.SkillConf skillConf = ModConfig.COMBAT.SKILL_31X.IRON_MAIDEN_A;
            float minPotency = skillConf.MIN_POTENCY;
            float cap = skillConf.CAP;
            calcAtkBuffSkill(player);
            CombatUtil.areaHPAttackGroup(new float[]{0.33f,0.33f,0.33f}, worldIn, player,
                    ModConfig.COMBAT.AOE_DISTANCE, ModConfig.COMBAT.AOE_RADIUS,
                    CombatUtil.EnumAttrType.STR_FOCUS, minPotency,
                    cap, 0.3f);
            postCastSkill(player,worldIn, skillConf, SoundEvents.BLOCK_ANVIL_USE);
        }
        else {
            worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, player.posX,player.posY,player.posZ,0,0,0);
        }

        return true;
    }
}
