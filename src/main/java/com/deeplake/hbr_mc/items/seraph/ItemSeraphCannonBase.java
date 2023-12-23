package com.deeplake.hbr_mc.items.seraph;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.entities.projectiles.EntityHBRProjectile;
import com.deeplake.hbr_mc.entities.projectiles.ProjectileArgs;
import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.util.CommonDef;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEgg;
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
    public ItemSeraphCannonBase(String name) {
        super(name);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
        if (timeLeft > 0)
        {
            EntityPlayer playerIn = (EntityPlayer) entityLiving;

            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

            if (!worldIn.isRemote)
            {
                int volley = 3;
                for (int i = 0; i < volley; i++) {
                    EntityHBRProjectile bullet =
                            new EntityHBRProjectile(worldIn);

                    Vec3d lookVec = playerIn.getLookVec();
                    bullet.shootingEntity = playerIn;
                    bullet.shoot(lookVec.x, lookVec.y, lookVec.z, 1.8f + i * 0.2f, 1f);
                    bullet.setPositionAndUpdate(
                            playerIn.posX+lookVec.x,
                            playerIn.posY+playerIn.getEyeHeight()+lookVec.y,
                            playerIn.posZ+lookVec.z);
                    worldIn.spawnEntity(bullet);
                    bullet.minPotency = ModConfig.COMBAT.NORMAL_ATK_POWER / volley;
                    bullet.cap = ModConfig.COMBAT.NORMAL_ATK_CAP;
                }
            }

            playerIn.getCooldownTracker().setCooldown(this,10);
            playerIn.swingArm(playerIn.getActiveHand());
            playerIn.addStat(StatList.getObjectUseStats(this));
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
}
