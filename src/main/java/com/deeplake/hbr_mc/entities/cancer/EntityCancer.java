package com.deeplake.hbr_mc.entities.cancer;

import com.deeplake.hbr_mc.entities.EntityBase;
import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.init.RegisterEffects;
import com.deeplake.hbr_mc.init.RegisterItem;
import com.deeplake.hbr_mc.init.util.DShieldUtil;
import com.deeplake.hbr_mc.init.util.EntityUtil;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphBase;
import com.deeplake.hbr_mc.items.seraph.SeraphUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class EntityCancer extends EntityBase implements IMob, ICancer {
//    b9d86352-2564-4848-9e90-04e8c3ea3519
//    cf1d7390-9122-4b1d-82f0-73459592687a
//1a2e2e5f-a2f7-431c-adab-b0d5fc3bbeb9
//    ca9c4dff-3942-48fe-90b9-0780d2d2938e
//            8e92cda3-ebca-42bc-b34b-53877d661407
//            6444f1f6-36a5-4e4e-a6dc-98de10d4b895
//55294859-291b-433d-8eb2-f5b97a0ba25f
//    ce9d3c6e-26ce-49e3-8518-dc37c70807ba
//6b589caa-7069-4af1-be1e-1c3987d56262
//    cdb2ec2f-febe-4ca9-a5be-df87bdc25997
    UUID biomeDiff = UUID.fromString("b9d86352-2564-4848-9e90-04e8c3ea3519");
    public EntityCancer(World worldIn) {
        super(worldIn);
    }

    public float getInitShield()
    {
        return 20;
    }

    public void boost6Attr(float value)
    {
        EntityUtil.boostAttr(this, RegisterAttr.STR, value, biomeDiff);
        EntityUtil.boostAttr(this, RegisterAttr.DEX, value, biomeDiff);
        EntityUtil.boostAttr(this, RegisterAttr.END, value, biomeDiff);
        EntityUtil.boostAttr(this, RegisterAttr.MEN, value, biomeDiff);
        EntityUtil.boostAttr(this, RegisterAttr.INT, value, biomeDiff);
        EntityUtil.boostAttr(this, RegisterAttr.LUC, value, biomeDiff);
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        if (!(this instanceof EntityDummyCancer)) {
            Biome biome = world.getBiome(getPosition());
            if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.SNOWY)) {
                boost6Attr(ModConfig.SPAWN_CONF.SNOWY_EXTRA_DIFFICULTY);
            } else if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.SANDY)) {
                boost6Attr(ModConfig.SPAWN_CONF.DESERT_EXTRA_DIFFICULTY);
            }
        }
        return super.onInitialSpawn(difficulty, livingdata);
    }

    @Override
    public void onFirstTickInLife() {
        super.onFirstTickInLife();
        //setAbsorptionAmount(getInitShield());
    }

    @Override
    public boolean isInvulnerableToAttacks() {
        return !DShieldUtil.isDPDepleted(this);
    }

    //when there is still shield, takes no damage to HP even if damage is overkill for shield
    protected void damageEntity(DamageSource damageSrc, float damageAmount)
    {
        float lastDP = getAbsorptionAmount();
        if (!this.isEntityInvulnerable(damageSrc))
        {
            damageAmount = net.minecraftforge.common.ForgeHooks.onLivingHurt(this, damageSrc, damageAmount);
            if (damageAmount <= 0) return;
            damageAmount = this.applyArmorCalculations(damageSrc, damageAmount);
            damageAmount = this.applyPotionDamageCalculations(damageSrc, damageAmount);
            float f = damageAmount;
            damageAmount = Math.max(damageAmount - this.getAbsorptionAmount(), 0.0F);
            if (lastDP > 0)
            {
                this.setAbsorptionAmount(this.getAbsorptionAmount() - (f - damageAmount));
                //no damage to HP
                if (getAbsorptionAmount() <= 0)
                {
                    //stun
                    addPotionEffect(new PotionEffect(RegisterEffects.SELF_RECOIL, 20, 0));
                    world.playSound(null, posX, posY, posZ, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.HOSTILE, 1.0f, 1.0f);
                    world.spawnParticle(net.minecraft.util.EnumParticleTypes.BLOCK_CRACK, posX, posY, posZ, 0.0D, 1.0D, 0.0D, Block.getStateId(Blocks.BLUE_GLAZED_TERRACOTTA.getDefaultState()));
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
            DShieldUtil.syncDPStatus(this);
        }
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    public void handleCancerDrop(boolean wasRecentlyHit, int lootingModifier, DamageSource source)
    {
        dropByAttribute(RegisterItem.CANCER_SHELL, 1);
    }

    @Override
    protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
        super.dropLoot(wasRecentlyHit, lootingModifier, source);
        if (source.getTrueSource() instanceof EntityPlayer)
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

                SeraphUtil.addXP(stack, (int) (getXPWorth() + 5 * EntityUtil.getAttr(this, RegisterAttr.STR)), player);
                if (rand.nextFloat() < ModConfig.COMBAT.SKILL_UP_CHANCE)
                {
                    Item item = stack.getItem();
                    if (item instanceof ItemSeraphBase)
                    {
                        int skillCount = ((ItemSeraphBase) item).getMaxSkillSlot(stack);
                        if (skillCount > 0)
                        {
                            int skillIndex = rand.nextInt(skillCount);
                            SeraphUtil.skillLevelUp((ItemSeraphBase) item, stack, skillIndex);
                        }
                    }
                }
            }
        }
        handleCancerDrop(wasRecentlyHit, lootingModifier, source);
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
        if (fallDistance > 20)
        {
            setFire(10);
        }
        if (this instanceof EntitySmallHopper && onGround && getAttackTarget() != null)
        {
            jump();
        }
    }

    public void onUpdate()
    {
        super.onUpdate();

        if (!this.world.isRemote && this.world.getDifficulty() == EnumDifficulty.PEACEFUL)
        {
            this.setDead();
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

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        return super.attackEntityAsMob(entityIn);
    }

    @Override
    public boolean getCanSpawnHere() {
        return super.getCanSpawnHere()
                && this.world.getDifficulty() != EnumDifficulty.PEACEFUL
                && this.world.canSeeSky(new BlockPos(this).up());
    }

    protected void dropByAttribute(Item dropItem, int sizePerDrop)
    {
        //drop bones per 100 HPMax, and chance corresponse to the remainder
        float dropChance = (float) (RegisterAttr.getAttrValue(this, RegisterAttr.STR) / 100);
        advancedDrop(dropChance, dropItem, sizePerDrop);
    }

    protected void advancedDrop(float dropChance, Item dropItem, int sizePerDrop) {
        int maxStack = dropItem.getItemStackLimit();
        int sizeTotal = 0;
        while (dropChance > 1) {
            sizeTotal += sizePerDrop;
            while (sizeTotal > maxStack) {
                dropItem(dropItem, maxStack);
                sizeTotal -= maxStack;
            }
            dropChance -= 1;
        }
        if (rand.nextFloat() < dropChance) {
            sizeTotal += sizePerDrop;
            while (sizeTotal > maxStack) {
                dropItem(dropItem, maxStack);
                sizeTotal -= maxStack;
            }
            dropItem(dropItem, sizeTotal);
        }
    }
}
