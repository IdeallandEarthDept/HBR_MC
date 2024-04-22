package com.deeplake.hbr_mc.entities.npc;

import com.deeplake.hbr_mc.entities.IHasOwner;
import com.deeplake.hbr_mc.entities.ai.EntityAIFollowSomething;
import com.deeplake.hbr_mc.entities.ai.idl.EnumActionMode;
import com.deeplake.hbr_mc.entities.ai.idl.EnumAttackMode;
import com.deeplake.hbr_mc.entities.npc.idl.EntityFlyableMobV1;
import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.init.util.EntityUtil;
import com.deeplake.hbr_mc.init.util.IDLNBTDef;
import com.google.common.base.Predicate;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

import static com.deeplake.hbr_mc.init.util.CommonDef.TICK_PER_SECOND;
import static com.deeplake.hbr_mc.init.util.CommonDef.UUID_DEFAULT;

//parent of EntityAlterEgo
public class EntityCleverNPCForHBR extends EntityFlyableMobV1 implements IHasOwner {

    EnumActionMode currentMode = EnumActionMode.FOLLOW;

    final int FOLLOW_PRIORITY = 4;
    final int COMBAT_PRIORITY = 5;

    float loseHealthRate = 0.1f;//when the player is offline, drain health at 2/sec

    float recoverRate = 1f / TICK_PER_SECOND;
    float maxRecoverDist = 8f;

    public boolean autoFade = false;

    float maxDefendDistSqr = 64f;

    Predicate<EntityLiving> ATTACK_PREDICATE = p_apply_1_ -> p_apply_1_ != null && IMob.VISIBLE_MOB_SELECTOR.apply(p_apply_1_) && !(p_apply_1_ instanceof EntityCreeper);
    ;
    private final EntityAIFollowSomething AI_FOLLOW = new EntityAIFollowSomething(this, 1.5D, 4, 32);
    protected EntityAIBase TARGET_REVENGE = new EntityAIHurtByTarget(this, false);
    private final EntityAINearestAttackableTarget TARGET_SEEKING = new EntityAINearestAttackableTarget(this, EntityLiving.class, 10, false, true,
            ATTACK_PREDICATE);

    private final EntityAINearestAttackableTarget TARGET_PLAYERS = new EntityAINearestAttackableTarget(this, EntityPlayer.class, true);

    public EntityLivingBase owner;
    protected static final DataParameter<String> PLAYER_UUID = EntityDataManager.<String>createKey(EntityCleverNPCForHBR.class, DataSerializers.STRING);

    public EntityCleverNPCForHBR(World worldIn) {
        super(worldIn);
        experienceValue = 0;
        dontDespawn = true;
        melee_atk = false;
        setAlwaysRenderNameTag(true);
        attackPriority = COMBAT_PRIORITY;
    }

    public void imitatePlayer(EntityPlayer player) {
        imitateLiving(player);
        setOwner(player);
        this.setHealth(player.getHealth());
        clearEquips();
        setDropItemsWhenDead(false);
    }

    public void setOwner(EntityPlayer owner) {
        this.owner = owner;
        if (owner == null) {
            this.dataManager.set(PLAYER_UUID, UUID_DEFAULT.toString());
        } else {
            this.dataManager.set(PLAYER_UUID, owner.getUniqueID().toString());
        }
    }

    @Override
    public EntityLivingBase getOwner() {
        return owner;
    }

    public UUID getPlayerUUID() {
        try {
            return UUID.fromString(this.dataManager.get(PLAYER_UUID));
        } catch (Exception e) {
            return UUID_DEFAULT;
        }
    }

    protected void entityInit() {
        this.dataManager.register(PLAYER_UUID, UUID_DEFAULT.toString());
        super.entityInit();
    }

    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand) {
        if (!world.isRemote && this.owner == null && currentMode != EnumActionMode.BETRAY) {
            this.owner = player;
            setBehaviorMode(EnumActionMode.FOLLOW);
            this.dataManager.set(PLAYER_UUID, player.getUniqueID().toString());
            return true;
        }
        return super.processInteract(player, hand);
    }

    int PARTICLE_PER_TICK = 3;

    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();
        if (world.isRemote) {
            if (owner != null) {
                float factorBase = (float) (world.getTotalWorldTime() % TICK_PER_SECOND) / TICK_PER_SECOND;
                for (int i = 0; i < PARTICLE_PER_TICK; i++) {
                    //lerp
                    float factor = factorBase + getRNG().nextFloat() * 0.1f;
                    factor = CommonFunctions.clamp(factor, 0f, 1f);
                    Vec3d pos = (owner.getPositionEyes(0).scale(factor))
                            .add(getPositionEyes(0).scale(1 - factor));
                    world.spawnParticle(EnumParticleTypes.SPELL, pos.x, pos.y, pos.z, 0, 0.1, 0);
                }
            }
        } else {
            if (owner == null) {
                if (autoFade) {
                    owner = world.getPlayerEntityByUUID(UUID.fromString(this.dataManager.get(PLAYER_UUID)));
                    if (owner == null && !world.isRemote) {
                        setHealth(getHealth() - loseHealthRate);
                    }
                }
            } else {
                float distToOwner = getDistance(owner);
                //Idealland.Log("dist to ownwer:%s",distToOwner);
                if (distToOwner < maxRecoverDist) {
                    //recovers if owner is nearby
                    heal((1 - distToOwner / maxRecoverDist) * recoverRate);
                }

                if (currentMode == EnumActionMode.DEFEND) {
                    if (getAttackTarget() == null) {
                        List<EntityLiving> livings = EntityUtil.getEntitiesWithinAABB(world,
                                EntityLiving.class, CommonFunctions.ServerAABB(owner.getPositionVector(), 8f), ATTACK_PREDICATE);

                        //first, attack those who are attacking the player
                        for (EntityLiving target : livings) {
                            if (target.getAttackTarget() == owner) {
                                setAttackTarget(target);
                                break;
                            }
                        }

                        //second, attack any are too close around the player
                        if (getAttackTarget() == null) {
                            if (livings.size() > 0) {
                                setAttackTarget(livings.get(0));
                            }
                        }
                    } else {//if already has a target
                        EntityLivingBase target = getAttackTarget();
                        if (target.getDistanceSq(owner) > maxDefendDistSqr) {
                            if (target instanceof EntityLiving && ((EntityLiving) target).getAttackTarget() == null) {
                                //give up if it's too far and not attacking anything.
                                setAttackTarget(null);
                            }
                        }
                    }
                }
            }
        }
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        setAttr(16.0D, 0.33000000417232513D, 1.0D, 0, 20.0D);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        setBehaviorMode(EnumActionMode.fromInt(compound.getInteger(IDLNBTDef.STATE)));
        this.dataManager.set(PLAYER_UUID, compound.getString(IDLNBTDef.OWNER_UUID));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.setInteger(IDLNBTDef.STATE, currentMode.ordinal());
        //use random to prevent bad format exception
        compound.setString(IDLNBTDef.OWNER_UUID, owner == null ? UUID.randomUUID().toString() : owner.getUniqueID().toString());
        super.writeEntityToNBT(compound);
    }

    //todo: give off hand flags
    public void setBehaviorMode(EnumActionMode action) {
        currentMode = action;
        this.targetTasks.removeTask(TARGET_REVENGE);
        this.targetTasks.removeTask(TARGET_SEEKING);
        this.targetTasks.removeTask(TARGET_PLAYERS);
        setCombatTask();
        switch (action) {
            case NONE:
                this.tasks.addTask(FOLLOW_PRIORITY, AI_FOLLOW);
                break;
            case ATTACK:
                this.tasks.removeTask(AI_FOLLOW);
                this.targetTasks.addTask(1, TARGET_SEEKING);
                break;
            case DEFEND:
                this.tasks.addTask(FOLLOW_PRIORITY, AI_FOLLOW);
                this.targetTasks.addTask(1, TARGET_REVENGE);
                break;
            case BETRAY:
                this.tasks.removeTask(AI_FOLLOW);
                this.targetTasks.addTask(1, TARGET_PLAYERS);
                this.targetTasks.addTask(2, TARGET_REVENGE);
                break;
            case FOLLOW:
                this.tasks.addTask(FOLLOW_PRIORITY, AI_FOLLOW);
                attackMode = EnumAttackMode.NONE;
                setCombatTask();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + action);
        }
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        return super.onInitialSpawn(difficulty, livingdata);
    }
}
