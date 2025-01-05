package com.deeplake.hbr_mc.entities.npc;

import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.util.CombatUtil;
import com.deeplake.hbr_mc.items.ItemSeraphForNPC;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityNpcMelee extends EntityNPC{
    public EntityNpcMelee(World worldIn) {
        super(worldIn);
    }


    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        float f = (float)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
        int i = 0;

        boolean targetIsCreature = entityIn instanceof EntityLivingBase;
        if (targetIsCreature)
        {
            f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), ((EntityLivingBase)entityIn).getCreatureAttribute());
            i += EnchantmentHelper.getKnockbackModifier(this);
        }

        Item item = getHeldItemMainhand().getItem();

        boolean flag = true;

        if (item instanceof ItemSeraphForNPC && targetIsCreature)
        {
            //special damage calculation
            float fullPower = ModConfig.COMBAT.NORMAL_ATK_POWER;
            float normal_atk_cap = ModConfig.COMBAT.NORMAL_ATK_CAP;
            int hits = ((ItemSeraphForNPC)item).type.hitCount;
            float powerPerHit = fullPower / hits;
            for (int hit = 0; hit < hits; hit++) {
                CombatUtil.attackAsHBR(this, (EntityLivingBase) entityIn, normal_atk_cap, powerPerHit);
            }
        }
        else {
            //normal weapons
            flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);
        }

        if (flag)
        {
            if (i > 0 && targetIsCreature)
            {
                ((EntityLivingBase)entityIn).knockBack(this, (float)i * 0.5F, (double) MathHelper.sin(this.rotationYaw * 0.017453292F), (double)(-MathHelper.cos(this.rotationYaw * 0.017453292F)));
                this.motionX *= 0.6D;
                this.motionZ *= 0.6D;
            }

            int j = EnchantmentHelper.getFireAspectModifier(this);

            if (j > 0)
            {
                entityIn.setFire(j * 4);
            }

            if (entityIn instanceof EntityPlayer)
            {
                EntityPlayer entityplayer = (EntityPlayer)entityIn;
                ItemStack itemstack = this.getHeldItemMainhand();
                ItemStack itemstack1 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack() : ItemStack.EMPTY;

                if (!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.getItem().canDisableShield(itemstack, itemstack1, entityplayer, this) && itemstack1.getItem().isShield(itemstack1, entityplayer))
                {
                    float f1 = 0.25F + (float)EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;

                    if (this.rand.nextFloat() < f1)
                    {
                        entityplayer.getCooldownTracker().setCooldown(itemstack1.getItem(), 100);
                        this.world.setEntityState(entityplayer, (byte)30);
                    }
                }
            }

            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }
}
