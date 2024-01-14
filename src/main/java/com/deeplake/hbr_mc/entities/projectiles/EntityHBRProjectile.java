package com.deeplake.hbr_mc.entities.projectiles;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.util.CombatUtil;
import com.deeplake.hbr_mc.init.util.CommonDef;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.Random;

public class EntityHBRProjectile extends EntityIdlProjectile{
    public CombatUtil.EnumAttrType attackType = CombatUtil.EnumAttrType.STANDARD;
    public CombatUtil.EnumDefType defType = CombatUtil.EnumDefType.END;
    public float minPotency = 100f;
    public float cap = 150;

    long debugTimeCreated = System.currentTimeMillis();

    public EntityHBRProjectile(World worldIn) {
        super(worldIn);
        setSize(0.1f,0.1f);
        Main.Log("Created bullet"+this.toString());
    }

    public EntityHBRProjectile(World worldIn, ProjectileArgs args, EntityLivingBase shooter, double accelX, double accelY, double accelZ, float acceleration) {
        super(worldIn, args, shooter, accelX, accelY, accelZ, acceleration);
    }

    protected void onImpact(RayTraceResult result)
    {
        Entity target = result.entityHit;
        if (!this.world.isRemote)
        {
            if (target != null)
            {
                if (shootingEntity != null && target instanceof EntityLivingBase && target != shootingEntity)
                {
                    float bonusRate = ModConfig.COMBAT.BONUS_DAMAGE_RATE;
                    switch (attackType)
                    {
                        case STR_FOCUS:
                            CombatUtil.HPAttack(shootingEntity, (EntityLivingBase) target,minPotency,cap,bonusRate);
                            break;
                        case DEX_FOCUS:
                            CombatUtil.DPAttack(shootingEntity, (EntityLivingBase) target,minPotency,cap,bonusRate);
                            break;
                        default:
                            CombatUtil.attackAsHBR(shootingEntity, (EntityLivingBase) target, attackType, defType, minPotency,cap);
                            break;
                    }
                }
            }
            this.setDead();
        }
        else {
            world.spawnParticle(EnumParticleTypes.CLOUD, posX, posY, posZ, motionX, motionY, motionZ);
            if (target == null)
            {
                Random random = new Random();
                float vx = (float) (motionX * 0.3f);
                float vy = (float) (motionY * 0.3f);
                float vz = (float) (motionZ * 0.3f);

                world.spawnParticle(EnumParticleTypes.FLAME, posX, posY, posZ, vx * random.nextFloat(), vy * random.nextFloat(), vz * random.nextFloat());
                world.spawnParticle(EnumParticleTypes.FLAME, posX, posY, posZ, -vx * random.nextFloat(), -vy * random.nextFloat(), -vz * random.nextFloat());
                world.spawnParticle(EnumParticleTypes.FLAME, posX, posY, posZ, -vx * random.nextFloat(), -vy * random.nextFloat(), -vz * random.nextFloat());
            }
        }
    }
}
