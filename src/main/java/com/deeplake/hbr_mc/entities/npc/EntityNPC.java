package com.deeplake.hbr_mc.entities.npc;

import com.deeplake.hbr_mc.entities.EntityHumanoid;
import com.deeplake.hbr_mc.init.RegisterEffects;
import com.deeplake.hbr_mc.init.util.DShieldUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.INpc;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityNPC extends EntityHumanoid implements INpc {
    float anger = 0;
    public EntityNPC(World worldIn) {
        super(worldIn);
    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setLeftHanded(false);
        setAttr(32,0.3, 1, 0, 20);
        return livingdata;
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        anger = compound.getFloat("anger");
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.setFloat("anger", anger);
        super.writeEntityToNBT(compound);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        Entity attacker = source.getTrueSource();
        if (attacker != null)
        {
            if (isFriendlyTo(attacker))
            {
                float remainDP = (float) DShieldUtil.getRemainDP(this) + 1;
                float bulletModifier = source.isProjectile() ? 0.33f : 1;
                anger += (1000 * amount / remainDP + 300) * bulletModifier;
            }
            else {
                anger -= 80;
            }
        }
        return super.attackEntityFrom(source, amount);
    }

    public boolean isFriendlyTo(Entity attacker) {
        return attacker instanceof EntityNPC || attacker instanceof EntityPlayer;
    }

    @Override
    protected void updateAITasks() {
        if (anger > 0)
        {
            if (anger > 800 && !isPotionActive(RegisterEffects.ANGRY))
            {
                addPotionEffect(new PotionEffect(RegisterEffects.ANGRY, 100, 0, false, false));
            }
            anger -= 1;
        }
        else if (anger < -200)
        {
            anger = 0;
        }
        else {
            anger += 1;
        }
        super.updateAITasks();
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (world.isRemote)
        {
            if (RegisterEffects.ANGRY.hasPotion(this))
            {
                if (rand.nextBoolean())
                {
                    double d0 = this.rand.nextGaussian() * 0.02D;
                    double d1 = this.rand.nextGaussian() * 0.02D;
                    double d2 = this.rand.nextGaussian() * 0.02D;
                    this.world.spawnParticle(EnumParticleTypes.VILLAGER_ANGRY, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 1.0D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
                }
            }
        }
    }

    public float getAnger() {
        return anger;
    }

    public void setAnger(float anger) {
        this.anger = anger;
    }
}
