package com.deeplake.hbr_mc.items.seraph.b31;

import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.init.RegisterBlocks;
import com.deeplake.hbr_mc.init.RegisterEffects;
import com.deeplake.hbr_mc.init.util.CombatUtil;
import com.deeplake.hbr_mc.init.util.CommonDef;
import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.init.util.EntityUtil;
import com.deeplake.hbr_mc.items.seraph.EnumSeraphRarity;
import com.deeplake.hbr_mc.items.seraph.EnumSeraphType;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphBase;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphCannonBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.stats.StatList;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class ItemFatalNullSS extends ItemSeraphCannonBase {
    final int ULTIMATE_INDEX = 1;

    public ItemFatalNullSS(String name) {
        super(name, EnumSeraphType.CANNON,EnumSeraphRarity.SS);
    }

    @Override
    public int getMaxSkillSlot(ItemStack stack) {
        return 2;
    }

    @Override
    public void castSkillNonSneak(ItemStack stack, World worldIn, EntityPlayer caster) {
        if (!worldIn.isRemote) {
            cast7SPBoost(worldIn, caster, ModConfig.COMBAT.FATAL_NULL_SS_7SP_BUFF);
        }
    }

    @Override
    public boolean castSkillEnemy(ItemStack stack, World worldIn, EntityPlayer player, EntityLivingBase target) {
        if (!canUseSkill(stack, ULTIMATE_INDEX) || !canUseSkills(player))
        {
            return false;
        }

        if (!worldIn.isRemote)
        {
            float minPotency = ModConfig.COMBAT.FATAL_NULL_SS_13SP_ULTI.MIN_POTENCY;
            float cap = ModConfig.COMBAT.FATAL_NULL_SS_13SP_ULTI.CAP;
            calcAtkBuffSkill(player);
            CombatUtil.attackAsHBR(null, player, target,
                    CombatUtil.EnumElement.THUNDER,
                    CombatUtil.EnumAttrType.STANDARD, CombatUtil.EnumDefType.END,
                    minPotency, cap);

            //expand thunder field
            int range = 7;

            for (int x = -range; x < range; x++) {
                for (int z = -range; z < range; z++) {
                    BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(player.getPosition().add(x, 0, z));
                    if (isReplaceable(worldIn, pos)) {
                        //find a place downwards to put the field
                        for (int i = 0; i < range; i++) {
                            if (!isReplaceable(worldIn, pos.move(EnumFacing.DOWN, 1))) {
                                worldIn.setBlockState(pos.move(EnumFacing.UP, 1),
                                        RegisterBlocks.FIELDS.get(CombatUtil.EnumElement.THUNDER).getDefaultState());
                                break;
                            }
                        }
                    }
                    else {
                        //find a place upwards to put the field
                        for (int i = 0; i < range; i++) {
                            if (isReplaceable(worldIn, pos.move(EnumFacing.UP, 1))) {
                                worldIn.setBlockState(pos, RegisterBlocks.FIELDS.get(CombatUtil.EnumElement.THUNDER).getDefaultState());
                                break;
                            }
                        }
                    }
                }
            }

            //debuff enemy
            if (itemRand.nextBoolean())
            {
                ItemSeraphBase.levelPlusOne(player, RegisterEffects.DEF_DN);
            }
            else {
                ItemSeraphBase.levelPlusOne(player, RegisterEffects.ATK_DN_SEIKA);
            }

            postCastSkill(player,worldIn,ModConfig.COMBAT.FATAL_NULL_SS_13SP_ULTI,SoundEvents.ENTITY_GENERIC_EXPLODE);
            skillUseMark(stack, ULTIMATE_INDEX);
        }
        else {
            worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, player.posX,player.posY,player.posZ,0,0,0);
        }

        return true;
    }

    private boolean isReplaceable(World worldIn, BlockPos pos) {
        return worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos);
    }

    @Override
    public int getSkillLimit(ItemStack stack, int slot) {
        switch (slot)
        {
            case SLOT_ULTI:
                ModConfig.SkillConf conf = ModConfig.COMBAT.RAPID_FIRE_11SP_AOE;
                return conf.uses + CommonFunctions.clamp( getSkillLevel(stack, slot) - 1,0,conf.usesGrowth);
            default:
                return -1;
        }
    }

    public void cast7SPBoost(World worldIn, EntityPlayer caster, ModConfig.SkillConf skillConf) {
        if (!worldIn.isRemote) {
            EntityPlayer playerIn = caster;
            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

            float range = 16f;
            List<EntityPlayer> playerList = EntityUtil.getEntitiesWithinAABB(worldIn, EntityPlayer.class, playerIn.getPositionVector(), range, EntitySelectors.NOT_SPECTATING);

            float chance = skillConf.MIN_POTENCY;
            float cap = skillConf.CAP;
            chance += (1-chance) * Math.min(0, (EntityUtil.getAttr(playerIn, RegisterAttr.INT)/cap));

            for (EntityPlayer player :
                    playerList) {
                Potion buff = RegisterEffects.SKILL_ATK_UP_GREATER;
                int level = EntityUtil.getBuffLevelIDL(player, buff);
                if (player.getRNG().nextFloat() <= chance)
                {
                    //+1 level
                    EntityUtil.ApplyBuff(player, buff, level, ModConfig.COMBAT.BUFF_TIME);
                }

                //def down 100%
                buff = RegisterEffects.DEF_DN_100;
                level = EntityUtil.getBuffLevelIDL(player, buff);
                //+1 level
                EntityUtil.ApplyBuff(player, buff, level, ModConfig.COMBAT.BUFF_TIME);
            }
            playerIn.swingArm(playerIn.getActiveHand());
            playerIn.addStat(StatList.getObjectUseStats(this));
            worldIn.playSound(null, caster.getPosition(), SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, 1f, 1.5f);
            setCoolDown(caster, CommonDef.TICK_PER_SECOND * skillConf.SP);
        }
    }
}
