package com.deeplake.sandbox.entities;

import com.deeplake.sandbox.items.seraph.ItemSeraphBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class EntityCancer extends EntityBase implements IMob {
    public EntityCancer(World worldIn) {
        super(worldIn);
    }

    public float getInitShield()
    {
        return 20;
    }

    @Override
    public void onFirstTickInLife() {
        super.onFirstTickInLife();
        setAbsorptionAmount(getInitShield());
    }

    protected void damageEntity(DamageSource damageSrc, float damageAmount)
    {
        if (!this.isEntityInvulnerable(damageSrc))
        {
            damageAmount = net.minecraftforge.common.ForgeHooks.onLivingHurt(this, damageSrc, damageAmount);
            if (damageAmount <= 0) return;
            damageAmount = this.applyArmorCalculations(damageSrc, damageAmount);
            damageAmount = this.applyPotionDamageCalculations(damageSrc, damageAmount);
            float f = damageAmount;
            damageAmount = Math.max(damageAmount - this.getAbsorptionAmount(), 0.0F);
            if (getAbsorptionAmount() > 0)
            {
                this.setAbsorptionAmount(this.getAbsorptionAmount() - (f - damageAmount));
            }
            else {
                damageAmount = net.minecraftforge.common.ForgeHooks.onLivingDamage(this, damageSrc, damageAmount);
                if (damageAmount != 0.0F)
                {
                    float f1 = this.getHealth();
                    this.getCombatTracker().trackDamage(damageSrc, f1, damageAmount);
                    this.setHealth(f1 - damageAmount); // Forge: moved to fix MC-121048
                    this.setAbsorptionAmount(this.getAbsorptionAmount() - damageAmount);
                }
            }
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (getAbsorptionAmount() > 0 && !canAttackCancer(source))
        {
            if (!world.isRemote)
            {
                world.playSound(null, posX, posY, posZ, SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.HOSTILE, 1.0f, 1.0f);
//                world.playSound(null, posX, posY, posZ, SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.HOSTILE, 1.0f, 1.0f);
            }
        }
        return super.attackEntityFrom(source, amount);
    }

    //Cannot be attacked when it still has shell
    @Override
    public boolean isEntityInvulnerable(DamageSource source) {
        if (getAbsorptionAmount() > 0 && !canAttackCancer(source))
        {
            return true;
        }
        return super.isEntityInvulnerable(source);
    }

    public static boolean canAttackCancer(DamageSource source)
    {
        if (source == DamageSource.OUT_OF_WORLD)
        {
            return true;
        }

        if (isUsingSeraph(source.getTrueSource()))
        {
            return true;
        }

        return false;
    }

    public static boolean isUsingSeraph(Entity livingBase)
    {
        if (livingBase instanceof EntityLivingBase)
        {
            Item item = ((EntityLivingBase) livingBase).getHeldItemMainhand().getItem();
            if (item instanceof ItemSeraphBase)
            {
                return true;
            }
        }

        return false;
    }
}
