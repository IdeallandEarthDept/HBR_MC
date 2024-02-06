package com.deeplake.hbr_mc.items.seraph;

import com.deeplake.hbr_mc.entities.projectiles.EntityHBRProjectile;
import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.util.CombatUtil;
import com.deeplake.hbr_mc.init.util.CommonDef;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemSeraphCannonBase extends ItemSeraphBase{
    public ItemSeraphCannonBase(String name, EnumSeraphType type) {
        super(name, type);
    }

    public ItemSeraphCannonBase(String name, EnumSeraphType type, EnumSeraphRarity rarity) {
        super(name, type, rarity);
    }

    public static void spreadAttack(World worldIn, EntityPlayer playerIn, int volley, int spread, float perAngle, float potency, float cap, CombatUtil.EnumAttrType type) {
        for (int i = 0; i < volley; i++) {
            Vec3d lookVec = playerIn.getLookVec();
            lookVec = rotate(lookVec, -perAngle * (spread / 2f -0.5f));

            for (int r = 0; r < spread; r++) {
                EntityHBRProjectile bullet =
                        new EntityHBRProjectile(worldIn);
                bullet.shootingEntity = playerIn;
                bullet.shoot(lookVec.x, lookVec.y*(1+0.4*i), lookVec.z, 1.8f + i * 0.2f, 1f);

                bullet.setPositionAndUpdate(
                        playerIn.posX + lookVec.x,
                        playerIn.posY + playerIn.getEyeHeight() + lookVec.y,
                        playerIn.posZ + lookVec.z);
                worldIn.spawnEntity(bullet);
                bullet.minPotency = potency;
                bullet.cap = cap;
                bullet.attackType = type;
                lookVec = rotate(lookVec, perAngle);
            }
        }
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
        if (timeLeft > 0 && canUseSkills(entityLiving))
        {
            EntityPlayer playerIn = (EntityPlayer) entityLiving;

            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

            if (!worldIn.isRemote)
            {
                int volley = 3;
                volleyAttack(worldIn, playerIn, volley,
                        ModConfig.COMBAT.NORMAL_ATK_POWER, ModConfig.COMBAT.NORMAL_ATK_CAP);
            }

            playerIn.getCooldownTracker().setCooldown(this,10);
            playerIn.swingArm(playerIn.getActiveHand());
            playerIn.addStat(StatList.getObjectUseStats(this));
        }
    }

    public static void volleyAttack(World worldIn, EntityLivingBase playerIn, int volley, ModConfig.SkillConf skillConf) {
        volleyAttack(worldIn, playerIn, volley, skillConf.MIN_POTENCY, skillConf.CAP, CombatUtil.EnumAttrType.STANDARD);
    }
    public static void volleyAttack(World worldIn, EntityLivingBase playerIn, int volley, float minPotency, float cap) {
        volleyAttack(worldIn, playerIn, volley, minPotency, cap, CombatUtil.EnumAttrType.STANDARD);
    }
    public static void volleyAttack(World worldIn, EntityLivingBase playerIn, int volley, float minPotency, float cap, CombatUtil.EnumAttrType atkType) {
        for (int i = 0; i < volley; i++) {
            EntityHBRProjectile bullet =
                    new EntityHBRProjectile(worldIn);

            Vec3d lookVec = playerIn.getLookVec();
            bullet.shootingEntity = playerIn;
            bullet.shoot(lookVec.x, lookVec.y, lookVec.z, 1.8f + i * 0.2f, 1f);
            bullet.setPositionAndUpdate(
                    playerIn.posX+lookVec.x,
                    playerIn.posY+ playerIn.getEyeHeight()+lookVec.y,
                    playerIn.posZ+lookVec.z);
            worldIn.spawnEntity(bullet);
            bullet.minPotency = minPotency/volley;
            bullet.cap = cap;
            bullet.attackType = atkType;
        }
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        playerIn.setActiveHand(handIn);
        worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!worldIn.isRemote)
        {
//            EntityHBRProjectile bullet =
//                    new EntityHBRProjectile(worldIn);
//
//            Vec3d lookVec = playerIn.getLookVec();
//            bullet.shootingEntity = playerIn;
//            bullet.shoot(lookVec.x, lookVec.y, lookVec.z, 1f, 0.00f);
//            bullet.setPositionAndUpdate(
//                    playerIn.posX+lookVec.x,
//                    playerIn.posY+playerIn.getEyeHeight()+lookVec.y,
//                    playerIn.posZ+lookVec.z);
//            worldIn.spawnEntity(bullet);
//            bullet.minPotency = ModConfig.COMBAT.NORMAL_ATK_POWER;
//            bullet.cap = ModConfig.COMBAT.NORMAL_ATK_CAP;
        }

//        playerIn.swingArm(handIn);
//        playerIn.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        return false;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 3 * CommonDef.TICK_PER_SECOND;
    }

    public static Vec3d rotate(Vec3d vec3d, float deg)
    {
        float rad = deg * CommonDef.DEG_TO_RAD;
        return new Vec3d(vec3d.x * Math.cos(rad) - vec3d.z * Math.sin(rad), vec3d.y, vec3d.x * Math.sin(rad) + vec3d.z * Math.cos(rad));
    }
}
