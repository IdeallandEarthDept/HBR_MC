package com.deeplake.hbr_mc.entities.npc.idl;

import com.deeplake.hbr_mc.entities.ai.EntityAIStrafeRangedAttack;
import com.deeplake.hbr_mc.entities.ai.idl.EntityStrafeMelee;
import com.deeplake.hbr_mc.entities.ai.idl.EnumAttackMode;
import com.deeplake.hbr_mc.entities.ai.idl.EnumMovementType;
import com.deeplake.hbr_mc.entities.ai.phantom.EntityAIPhantomAttackStrategy;
import com.deeplake.hbr_mc.entities.ai.phantom.EntityAIPhantomCircleAroundAnchor;
import com.deeplake.hbr_mc.entities.ai.phantom.EntityAIPhantomSweepAttack;
import com.deeplake.hbr_mc.entities.ai.phantom.PhantomMoveControl;
import com.deeplake.hbr_mc.init.util.CommonDef;
import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.init.util.WorldUtil;
import com.deeplake.hbr_mc.items.ItemWIPRanged;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.UUID;

public class EntityFlyableMobV1 extends EntityMobRanged {
    public EnumMovementType movementType = EnumMovementType.WALKER;
    public EnumAttackMode attackMode = EnumAttackMode.RANGED;

    EntityMoveHelper glideMover = new PhantomMoveControl(this, this);
    EntityMoveHelper generalMover = new EntityMoveHelper(this);

    public static final UUID FLYING_SIGHT_BONUS = UUID.fromString("bf7cb2d3-9de7-4440-8fea-fc7519b3c23a");

    public EntityFlyableMobV1(World p_33102_) {
        super(p_33102_);
//        setFlying(true);
        avengeful = true;

        melee_atk = false;
        can_swim = false;
        avoid_danger = false;
        go_home = false;
        across_village = false;
        wander = false;
        canLookAround = false;
        rally_on_hurt = true;
        spawn_without_darkness = true;

        experienceValue = 15;
    }

    HashSet<EntityAIBase> modularAI = new HashSet<>();

    EntityAIBase GLIDE_ATTACK_CIRCLE = registerModularAI(new EntityAIPhantomAttackStrategy(this));
    EntityAIBase GLIDE_ATTACK_SWOOP = registerModularAI(new EntityAIPhantomSweepAttack(this));
    EntityAIBase GLIDE_HOVER = registerModularAI(new EntityAIPhantomCircleAroundAnchor(this));
    EntityAIBase WALKER_ATTACK = registerModularAI(new EntityStrafeMelee(this, 1.0D, CommonDef.TICK_PER_SECOND,3));
//    EntityAIBase WALKER_ATTACK = registerModularAI(new EntityAIMeleeIDL(this, 1.0D, true));
    EntityAIBase WALKER_RANGED = registerModularAI(new EntityAIStrafeRangedAttack<>(this, 1.0D, 5, 16.0F)
        );
    EntityAIBase WALKER_WANDER = registerModularAI(new EntityAIWanderAvoidWater(this,1.0));
    public int attackPriority = 1;

    //careful! Huge sight causes huge performance impact.
    public double getFlyingSightBonus()
    {
        return 32;
    }


    public EntityAIBase registerModularAI(EntityAIBase aiBase)
    {
        modularAI.add(aiBase);
        return aiBase;
    }

    public void clearAll()
    {
        clearModular(tasks);
        clearModular(targetTasks);
    }

    public void clearModular(EntityAITasks aiTasks)
    {
        aiTasks.taskEntries.removeIf(taskEntry -> modularAI.contains(taskEntry.action));
    }

    @Override
    protected void applyEntityAI() {
        super.applyEntityAI();
        setCombatTask();
    }

    @Override
    public boolean isFlying() {
        return movementType == EnumMovementType.FLYING_GLIDE || movementType == EnumMovementType.FLYING_FLOAT;
    }

    public void setCombatTask(){
        clearAll();
        decideBehaviorMode();
        IAttributeInstance attrFollow = getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
        switch (movementType) {
            case FLYING_GLIDE:
            case FLYING_FLOAT:
                moveHelper = glideMover;
                switch (attackMode)
                {
                    case NONE:
                        tasks.addTask(attackPriority, GLIDE_ATTACK_CIRCLE);
                        break;
                    case MELEE:
                        tasks.addTask(attackPriority, GLIDE_ATTACK_CIRCLE);
                        tasks.addTask(attackPriority+1, GLIDE_ATTACK_SWOOP);
                        break;
                    case RANGED:
                        tasks.addTask(attackPriority, GLIDE_ATTACK_CIRCLE);
                        tasks.addTask(attackPriority+1,WALKER_RANGED);
                        break;

                }
                tasks.addTask(attackPriority+2,GLIDE_HOVER);

                //note this will wear it out. and sometimes wrong pose
                // The flunctation is pretty large. even lower it by 1/7 is still visible
                //and it seems that it only happens at turns, as it almost never turn smoothly
                setFlag(7, true);
                setFlying(true);
                setNoGravity(true);
                if (attrFollow.getModifier(FLYING_SIGHT_BONUS) == null)
                {
                    attrFollow.applyModifier(new AttributeModifier(FLYING_SIGHT_BONUS, "flying sight", getFlyingSightBonus(), 0));
                }
                break;
            case WALKER:
                moveHelper = generalMover;
                switch (attackMode)
                {
                    case MELEE:
                        tasks.addTask(attackPriority,WALKER_ATTACK);
                        break;
                    case RANGED:
                        tasks.addTask(attackPriority,WALKER_RANGED);
                        if (wander)
                        {
                            tasks.addTask(attackPriority+1,WALKER_WANDER);
                        }
                        break;
                }
                setNoGravity(false);
                setFlag(7, false);
                setFlying(false);
                AttributeModifier modifier = attrFollow.getModifier(FLYING_SIGHT_BONUS);
                if (modifier != null)
                {
                    attrFollow.removeModifier(FLYING_SIGHT_BONUS);
                }
                break;
        }
    }

    private void decideBehaviorMode() {
        if (this.world != null && !this.world.isRemote)
        {
            ItemStack itemstack = this.getHeldItemMainhand();

            if (CommonFunctions.isItemRangedWeapon(itemstack))
            {
                int i = this.world.getDifficulty() == EnumDifficulty.HARD ? 20 : 40;
                this.aiArrowAttack.setAttackCooldown(i);
                attackMode = EnumAttackMode.RANGED;
                //auto choose projectile type
                Item item = itemstack.getItem();
                if (item instanceof ItemBow) {
                    useBulletForRanged = false;
                } else if (item instanceof ItemWIPRanged) {
                    useBulletForRanged = true;
                }
            }
            else
            {
                attackMode = EnumAttackMode.MELEE;
            }

            decideMovementType();
        }
    }

    public void decideMovementType()
    {
        ItemStack itemstack = getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        if (forcedFlying || itemstack.getItem() == Items.ELYTRA)
        {
            movementType = EnumMovementType.FLYING_GLIDE;
        }
        else {
            movementType = EnumMovementType.WALKER;
        }
    }

    @Override
    protected void applyTargetAI() {
        super.applyTargetAI();
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        setAttr(64, 0.3, 5,0,15);
    }

    @Override
    public void setAttackTarget(@Nullable EntityLivingBase entitylivingbaseIn) {
        super.setAttackTarget(entitylivingbaseIn);
    }

    public int getUniqueFlapTickOffset() {
        return this.getEntityId() * 3;
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        switch (movementType) {
            case FLYING_GLIDE:
            case FLYING_FLOAT:
                //if you only set it once, it will get erased upon touching ground.
                this.setFlag(7, true);
                break;
            case WALKER:
                break;
        }
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        this.anchorPoint = this.getPosition().add(0,5,0);
        return super.onInitialSpawn(difficulty, livingdata);
    }

    protected float getSoundVolume() {
        return 1.0F;
    }

    @Override
    protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
        super.dropLoot(wasRecentlyHit, lootingModifier, source);
        dropItem(Items.GOLD_INGOT, 1+lootingModifier);
        if (rand.nextInt(10) < lootingModifier)
        {
            dropItem(Items.ELYTRA, 1);
        }
    }

    @Override
    public boolean getCanSpawnHere() {
        if (world.canBlockSeeSky(getPosition()) && WorldUtil.isNight(getEntityWorld()))
        {
            return super.getCanSpawnHere();
        }
        return false;
    }
}