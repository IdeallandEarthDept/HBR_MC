package com.deeplake.hbr_mc.entities.npc;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.entities.IHasOwner;
import com.deeplake.hbr_mc.entities.ai.EntityAIFollowSomething;
import com.deeplake.hbr_mc.entities.ai.idl.EntityAINearestAttackableTargetExcludeSelf;
import com.deeplake.hbr_mc.entities.ai.idl.EntityKeepDistance;
import com.deeplake.hbr_mc.entities.ai.idl.EnumActionMode;
import com.deeplake.hbr_mc.entities.ai.idl.EnumAttackMode;
import com.deeplake.hbr_mc.entities.npc.idl.EntityFlyableMobV1;
import com.deeplake.hbr_mc.init.RegisterAttr;
import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.init.util.EntityUtil;
import com.deeplake.hbr_mc.init.util.IDLNBTDef;
import com.google.common.base.Predicate;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

import static com.deeplake.hbr_mc.init.util.CommonDef.TICK_PER_SECOND;
import static com.deeplake.hbr_mc.init.util.CommonDef.UUID_DEFAULT;
import static net.minecraft.entity.monster.IMob.MOB_SELECTOR;

//parent of EntityAlterEgo
@Mod.EventBusSubscriber(modid = Main.MODID)
public class EntityCleverNPCForHBR extends EntityFlyableMobV1 implements IHasOwner {

    EnumActionMode currentMode = EnumActionMode.FOLLOW;

    final int FOLLOW_PRIORITY = 4;
    final int COMBAT_PRIORITY = 5;

    float loseHealthRate = 0.1f;//when the player is offline, drain health at 2/sec

    float recoverRate = 1f / TICK_PER_SECOND;
    float maxRecoverDist = 8f;

    public boolean autoFade = false;

    float maxDefendDistSqr = 64f;

    public boolean makeLoudFallSound = false;

    Predicate<EntityLiving> ATTACK_PREDICATE = p_apply_1_ -> p_apply_1_ != null && IMob.VISIBLE_MOB_SELECTOR.apply(p_apply_1_) && !(p_apply_1_ instanceof EntityCreeper);
    ;
    private final EntityAIFollowSomething AI_FOLLOW = new EntityAIFollowSomething(this, 1.5D, 4, 32);
    protected EntityAIBase TARGET_REVENGE = new EntityAIHurtByTarget(this, false);
    private final EntityAINearestAttackableTargetExcludeSelf TARGET_SEEKING = new EntityAINearestAttackableTargetExcludeSelf(this, EntityLiving.class, 10, false, true,
            ATTACK_PREDICATE);

    private final EntityAINearestAttackableTargetExcludeSelf TARGET_PLAYERS = new EntityAINearestAttackableTargetExcludeSelf(this, EntityPlayer.class, true);
    private final EntityKeepDistance<EntityCleverNPCForHBR> KEEP_DISTANCE = new EntityKeepDistance(this, EntityCleverNPCForHBR.class, 0.8f, 1.0D, 1.2D);
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

    public void setAsLevel(int level)
    {
        setDPMax(10+level*2);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100+level*2);
        setHealth(getMaxHealth());
        this.getEntityAttribute(RegisterAttr.STR).setBaseValue(level + 10);
        this.getEntityAttribute(RegisterAttr.DEX).setBaseValue(level + 10);
        this.getEntityAttribute(RegisterAttr.END).setBaseValue(level + 10);
        this.getEntityAttribute(RegisterAttr.MEN).setBaseValue(level + 10);
        this.getEntityAttribute(RegisterAttr.INT).setBaseValue(level + 10);
        this.getEntityAttribute(RegisterAttr.LUC).setBaseValue(level + 10);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3+level);
    }

    public void setAsStrongData(){
        //Level 133
        setDPMax(4000);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(134.5f);
        setHealth(getMaxHealth());
        this.getEntityAttribute(RegisterAttr.STR).setBaseValue(459);
        this.getEntityAttribute(RegisterAttr.DEX).setBaseValue(328);
        this.getEntityAttribute(RegisterAttr.END).setBaseValue(375);
        this.getEntityAttribute(RegisterAttr.MEN).setBaseValue(357);
        this.getEntityAttribute(RegisterAttr.INT).setBaseValue(346);
        this.getEntityAttribute(RegisterAttr.LUC).setBaseValue(328);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(393.5f);
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

                        livings = EntityUtil.getEntitiesWithinAABB(world,
                                EntityLiving.class, CommonFunctions.ServerAABB(owner.getPositionVector(), 8f), MOB_SELECTOR);
                        //second, attack any [Mob] are too close around the player
                        //excluded other similar things, including possible allies
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

    @Override
    protected void applyEntityAI() {
        super.applyEntityAI();
        this.tasks.addTask(FOLLOW_PRIORITY-1,KEEP_DISTANCE);
    }

    //todo: give off hand flags
    public void setBehaviorMode(EnumActionMode action) {
        currentMode = action;
        this.targetTasks.removeTask(TARGET_REVENGE);
        this.targetTasks.removeTask(TARGET_SEEKING);
        this.targetTasks.removeTask(TARGET_PLAYERS);
        decideBehaviorMode();
        setCombatTask();
        switch (action) {
            case NONE:
                this.setAttackTarget(null);
                this.tasks.addTask(FOLLOW_PRIORITY, AI_FOLLOW);
                break;
            case ATTACK:
                this.setAttackTarget(null);
                this.tasks.removeTask(AI_FOLLOW);
                this.targetTasks.addTask(1, TARGET_SEEKING);
                break;
            case DEFEND:
                this.setAttackTarget(null);
                this.tasks.addTask(FOLLOW_PRIORITY, AI_FOLLOW);
                this.targetTasks.addTask(1, TARGET_REVENGE);
                break;
            case BETRAY:
                this.setAttackTarget(null);
                this.tasks.removeTask(AI_FOLLOW);
                this.targetTasks.addTask(1, TARGET_PLAYERS);
                this.targetTasks.addTask(2, TARGET_REVENGE);
                break;
            case FOLLOW:
                this.setAttackTarget(null);
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

    @Override
    public void fall(float distance, float damageMultiplier) {
        if (makeLoudFallSound) {
            world.playSound(null, getPosition(), SoundEvents.ENTITY_GENERIC_EXPLODE, getSoundCategory(), 1.0F, 1.33F);
            makeLoudFallSound = false;
        }
        super.fall(distance, damageMultiplier);
    }

    public ITextComponent getDisplayName()
    {
        TextComponentString textcomponentstring = new TextComponentString(ScorePlayerTeam.formatPlayerName(this.getTeam(), this.getName()));
        textcomponentstring.getStyle().setHoverEvent(this.getHoverEvent());
        textcomponentstring.getStyle().setInsertion(this.getCachedUniqueIdString());
        return textcomponentstring;
    }

    @Override
    public boolean getAlwaysRenderNameTag() {
        return super.getAlwaysRenderNameTag() && !Main.isNeatInstalled;
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onNPCDeath(LivingDeathEvent event) {
        if (event.isCanceled()) {
            return;
        }

        if (!event.getEntity().world.isRemote) {
            return;
        }
        if (event.getEntity() instanceof EntityCleverNPCForHBR) {
            EntityLivingBase entity = event.getEntityLiving();
            boolean flag = entity.world.getGameRules().getBoolean("showDeathMessages");

            if (flag)
            {
                CommonFunctions.broadcast(entity.getCombatTracker().getDeathMessage());
            }
        }
    }
}
