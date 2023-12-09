package com.deeplake.hbr_mc.entities.cancer;

import com.deeplake.hbr_mc.entities.EntityBase;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphBase;
import com.deeplake.hbr_mc.items.seraph.SeraphUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.List;

public class EntityCancer extends EntityBase implements IMob, ICancer {
    public EntityCancer(World worldIn) {
        super(worldIn);
    }

    public float getInitShield()
    {
        return 20;
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    }

    @Override
    public void onFirstTickInLife() {
        super.onFirstTickInLife();
        setAbsorptionAmount(getInitShield());
    }

    //when there is still shield, takes no damage to HP even if damage is overkill for shield
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
                //no damage to HP
                if (getAbsorptionAmount() <= 0)
                {
                    //stun
                    addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20, 5));
                    world.playSound(null, posX, posY, posZ, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.HOSTILE, 1.0f, 1.0f);
                    //todo: special AI task
                }
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
            ItemStack stack = ((EntityLivingBase) livingBase).getHeldItemMainhand();
            return SeraphUtil.isSeraph(stack) && !SeraphUtil.isBroken(stack);
        }

        return false;
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        if (net.minecraftforge.common.ForgeHooks.onLivingDeath(this, cause)) return;
        if (!this.dead)
        {
            //add xp to seraph if killed by player, and seraph is in hand
            if (cause.getTrueSource() instanceof EntityPlayer)
            {
                List<EntityPlayer> players = world.getEntitiesWithinAABB(EntityPlayer.class, getEntityBoundingBox().grow(32));
                for (EntityPlayer player: players
                     ){
                    ItemStack stack = player.getHeldItemMainhand();
                    if (!SeraphUtil.isSeraph(stack))
                    {
                        stack = player.getHeldItemOffhand();
                        if (!SeraphUtil.isSeraph(stack))
                        {
                            continue;
                        }
                    }

                    SeraphUtil.addXP(stack, getXPWorth(), player);
                }
            }
        }
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
        if (fallDistance > 20)
        {
            setFire(10);
        }
    }

    @Override
    public void fall(float distance, float damageMultiplier) {
        super.fall(distance, damageMultiplier);
        if (distance > 20)
        {
            world.playSound(null, posX, posY, posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.HOSTILE, 1.0f, 1.0f);
        }
    }
}
