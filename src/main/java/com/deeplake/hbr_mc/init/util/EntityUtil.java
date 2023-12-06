package com.deeplake.hbr_mc.init.util;

import com.deeplake.hbr_mc.Main;
import com.google.common.base.Predicate;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import javax.annotation.Nullable;
import java.util.*;

import static com.deeplake.hbr_mc.init.util.CommonDef.*;
import static net.minecraft.entity.SharedMonsterAttributes.*;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class EntityUtil {

    public static final String TRYING_TO_APPLY_ILLEGAL_POTION = "Trying to apply illegal potion";
    static float ATK_BUFF = 0.07f;

    public static DamageSource attack(EntityLivingBase source) {
        if (source instanceof EntityPlayer) {
            return DamageSource.causePlayerDamage((EntityPlayer) source);
        } else {
            return DamageSource.causeMobDamage(source);
        }
    }

    static final float cos45 = 1.414f / 2;

    public static boolean canSee(EntityLivingBase seer, Entity target) {
        //copied from enderman, modified
        Vec3d vec3d = seer.getLook(1.0F).normalize();
        Vec3d vec3d1 = new Vec3d(target.posX - seer.posX, target.getEntityBoundingBox().minY + (double) target.getEyeHeight() - (seer.posY + (double) seer.getEyeHeight()), target.posZ - seer.posZ);
        //double d0 = vec3d1.lengthVector();
        vec3d1 = vec3d1.normalize();
        double d1 = vec3d.dotProduct(vec3d1);
        return d1 > cos45 && seer.canEntityBeSeen(target);
    }

    public static boolean isSeeing(EntityLivingBase seer, Entity target) {
        //copied from enderman
        Vec3d vec3d = seer.getLook(1.0F).normalize();
        Vec3d vec3d1 = new Vec3d(target.posX - seer.posX, target.getEntityBoundingBox().minY + (double) target.getEyeHeight() - (seer.posY + (double) seer.getEyeHeight()), target.posZ - seer.posZ);
        double d0 = vec3d1.lengthVector();
        vec3d1 = vec3d1.normalize();
        double d1 = vec3d.dotProduct(vec3d1);
        return d1 > 1.0D - 0.025D / d0 && seer.canEntityBeSeen(target);
    }

    static Map<Class, Boolean> notHumanlike = new HashMap<>();

    public static void init() {
        notHumanlike.put(EntityCaveSpider.class, true);
        notHumanlike.put(EntitySpider.class, true);
        notHumanlike.put(EntitySilverfish.class, true);
        notHumanlike.put(EntityEndermite.class, true);
        notHumanlike.put(EntityCreeper.class, true);
        notHumanlike.put(EntityEnderman.class, true);
        notHumanlike.put(EntitySnowman.class, true);
        notHumanlike.put(EntityWitch.class, true);
        notHumanlike.put(EntityBlaze.class, true);
        notHumanlike.put(EntitySlime.class, true);
        notHumanlike.put(EntityGhast.class, true);
        notHumanlike.put(EntityMagmaCube.class, true);
        notHumanlike.put(EntitySquid.class, true);
        notHumanlike.put(EntityVillager.class, true);
        notHumanlike.put(EntityIronGolem.class, true);
        notHumanlike.put(EntityBat.class, true);
        notHumanlike.put(EntityGuardian.class, true);
        notHumanlike.put(EntityElderGuardian.class, true);
        notHumanlike.put(EntityShulker.class, true);
        notHumanlike.put(EntityEvoker.class, true);
        notHumanlike.put(EntityIllusionIllager.class, true);
        notHumanlike.put(EntityVindicator.class, true);
        notHumanlike.put(EntityDragon.class, true);
        notHumanlike.put(EntityWither.class, true);
    }

    public static final Predicate<EntityLivingBase> NOT_WEAR_HELM = new Predicate<EntityLivingBase>() {
        public boolean apply(@Nullable EntityLivingBase entity) {
            if (entity == null) {
                return false;
            }

            return ((entity instanceof EntityLiving) || (entity instanceof EntityPlayer))
                    && entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty();
        }
    };

    public static final Predicate<EntityLivingBase> IS_HUMANOID = new Predicate<EntityLivingBase>() {
        public boolean apply(@Nullable EntityLivingBase entity) {
            if (entity == null) {
                return false;
            }

            return (entity instanceof EntityPlayer) ||
                    ((entity instanceof EntityLiving) && !(entity instanceof EntityAnimal) && !notHumanlike.containsKey(entity.getClass()));
        }
    };

    public static String getRegName(EntityLivingBase livingBase) {
        String s = EntityList.getEntityString(livingBase);

        if (s == null) {
            s = "generic";
        }

        return I18n.translateToLocal("entity." + s + ".name");
    }

    public static void simpleKnockBack(float power, Entity source, EntityLivingBase target) {
        if ((source.posX - target.posX) == 0 && (source.posZ - target.posZ) == 0) {
            //prevent div 0
            target.knockBack(source, power, 1, 0);
        } else {
            target.knockBack(source, power, (source.posX - target.posX), (source.posZ - target.posZ));
        }
    }

    public static boolean giveIfEmpty(EntityLivingBase livingBase, ItemStack stack, EntityEquipmentSlot slot) {
        return giveIfEmpty(livingBase, slot, stack);
    }

    public static boolean giveIfEmpty(EntityLivingBase livingBase, EntityEquipmentSlot slot, ItemStack stack) {
        if (livingBase.getItemStackFromSlot(slot).isEmpty()) {
            livingBase.setItemStackToSlot(slot, stack);
            return true;
        }
        return false;
    }

    public static void TryRemoveDebuff(EntityLivingBase livingBase) {
        //washes away debuff
        Collection<PotionEffect> activePotionEffects = livingBase.getActivePotionEffects();
        Iterator it = activePotionEffects.iterator();
        while (it.hasNext()) {
            PotionEffect buff = (PotionEffect) it.next();
            if (buff.getPotion().isBadEffect()) {
                livingBase.removePotionEffect(buff.getPotion());
            }
        }
    }

    public static boolean TryRemoveGivenBuff(EntityLivingBase livingBase, Potion potion) {
        //washes away debuff
        Collection<PotionEffect> activePotionEffects = livingBase.getActivePotionEffects();
        for (int i = 0; i < activePotionEffects.size(); i++) {
            PotionEffect buff = (PotionEffect) activePotionEffects.toArray()[i];
            if (buff.getPotion() == potion) {
                livingBase.removePotionEffect(buff.getPotion());
                return true;
            }
        }

        return false;
    }

    //0 = buff I, 1 = buff II
    public static boolean ApplyBuff(Entity entity, Potion potion, int level, float seconds) {
        if (entity instanceof EntityLivingBase)
        {
            EntityLivingBase livingBase = (EntityLivingBase) entity;
            if (potion == null) {
                Main.LogWarning(TRYING_TO_APPLY_ILLEGAL_POTION);
                return false;
            }
            livingBase.addPotionEffect(new PotionEffect(potion, (int) (seconds * TICK_PER_SECOND), level));
            return true;
        }
        return false;
    }

    //Note: this returns 0 if no buff.
    public static int getBuffLevelIDL(EntityLivingBase livingBase, Potion potion) {
        if (livingBase == null || potion == null) {
            Main.LogWarning(TRYING_TO_APPLY_ILLEGAL_POTION);
            return 0;
        }
        PotionEffect effect = livingBase.getActivePotionEffect(potion);
        if (effect == null || effect.getDuration() <= 0) {
            return 0;
        } else {
            return effect.getAmplifier() + 1;
        }
    }

    public static String getModName(EntityLivingBase creature) {
        if (creature instanceof EntityPlayer || creature == null) {
            return "minecraft";
        }
        EntityRegistry.EntityRegistration er = EntityRegistry.instance().lookupModSpawn(creature.getClass(), true);
        if (er == null) {
            //Vanilla creatures don't have ER
            return "minecraft";
        }
        return er.getContainer().getModId();
    }

    public static <T extends Entity> List<T> getEntitiesWithinAABB(World world, Class<? extends T> clazz, AxisAlignedBB aabb, @Nullable Predicate<? super T> filter) {
        return world.getEntitiesWithinAABB(clazz, aabb, filter);
    }

    public static <T extends Entity> List<T> getEntitiesWithinAABB(World world, Class<? extends T> clazz, Vec3d center, float range, @Nullable Predicate<? super T> filter) {
        return world.getEntitiesWithinAABB(clazz, CommonFunctions.ServerAABB(center.addVector(-range, -range, -range), center.addVector(range, range, range)), filter);
    }

    public static <T extends Entity> List<T> getEntitiesWithinAABB(World world, Class<? extends T> clazz, BlockPos center, float range, @Nullable Predicate<? super T> filter) {
        Vec3d vec3d = CommonFunctions.getVecFromBlockPos(center);
        return world.getEntitiesWithinAABB(clazz, CommonFunctions.ServerAABB(vec3d.addVector(-range, -range, -range), vec3d.addVector(range, range, range)), filter);
    }

    public static Vec3d GetRandomAroundUnderfoot(EntityLivingBase entity, float radius) {
        float angle = entity.getRNG().nextFloat() * 6.282f;
        return new Vec3d(entity.posX + Math.sin(angle), entity.posY, entity.posZ + Math.cos(angle));
    }

    public static Vec3d GetRandomAroundPos(Vec3d pos, float radius, Random rng) {
        float angle = rng.nextFloat() * 6.282f;
        return new Vec3d(pos.x + radius * Math.sin(angle), pos.y, pos.z + radius * Math.cos(angle));
    }

    public static void SpawnParticleAround(EntityLivingBase entity, EnumParticleTypes particleTypes) {
        Vec3d pos = GetRandomAroundUnderfoot(entity, 1f);
        entity.world.spawnParticle(particleTypes, pos.x, pos.y, pos.z, 0, 0, 0);
    }

    public static void SpawnParticleAround(EntityLivingBase entity, EnumParticleTypes particleTypes, int count) {
        for (int i = 0; i < count; i++) {
            SpawnParticleAround(entity, particleTypes);
        }
    }

    public static void createTeleportEffect(EntityLivingBase livingBase) {
        if (livingBase == null) {
            return;
        }

        World worldIn = livingBase.world;
        if (worldIn.isRemote) {
            Vec3d oriPos = livingBase.getPositionEyes(0);
            Random random = livingBase.getRNG();
            AxisAlignedBB bb = livingBase.getRenderBoundingBox();
            double radiusX = bb.maxX - bb.minX;
            double radiusY = bb.maxY - bb.minY;
            double radiusZ = bb.maxZ - bb.minZ;

            for (int i = 0; i <= 10; i++) {
                worldIn.spawnParticle(EnumParticleTypes.PORTAL,
                        CommonFunctions.flunctate(oriPos.x, radiusX, random),
                        CommonFunctions.flunctate(oriPos.y, radiusY, random),
                        CommonFunctions.flunctate(oriPos.z, radiusZ, random),
                        random.nextFloat(),
                        random.nextFloat(),
                        random.nextFloat()
                );
            }

            worldIn.playSound(oriPos.x, oriPos.y, oriPos.z, SoundEvents.ENTITY_ENDERMEN_TELEPORT, null, 1f, 1.3f, false);
        }
    }

    static float angle = 0f;

    public static void spawnCubeParticleAround(EntityLivingBase entity, EnumParticleTypes particleTypes, float radius) {
        for (int i = 0; i < 10; i++) {
            Vec3d pos = entity.getPositionVector();
            Random random = entity.getRNG();
            float flunc = (float) CommonFunctions.flunctate(0, radius, random);
            entity.world.spawnParticle(particleTypes, pos.x + radius, pos.y, pos.z + flunc, 0, 0, 0);
            entity.world.spawnParticle(particleTypes, pos.x - radius, pos.y, pos.z + flunc, 0, 0, 0);
            entity.world.spawnParticle(particleTypes, pos.x + flunc, pos.y, pos.z + radius, 0, 0, 0);
            entity.world.spawnParticle(particleTypes, pos.x + flunc, pos.y, pos.z - radius, 0, 0, 0);
        }
    }

    public static Vec3d GetRandomAround(EntityLivingBase entity, float radius) {
        float angle = entity.getRNG().nextFloat() * 6.282f;
        return new Vec3d(entity.posX + Math.sin(angle), entity.getEyeHeight() + entity.posY, entity.posZ + Math.cos(angle));
    }

    public static final Predicate<EntityLivingBase> InWater = new Predicate<EntityLivingBase>() {
        public boolean apply(@Nullable EntityLivingBase p_apply_1_) {
            return p_apply_1_ != null && p_apply_1_.isInWater();
        }
    };

    public static double getHPMax(EntityLivingBase creature) {
        if (creature == null) {
            return 0;
        }

        IAttributeInstance attribute = creature.getEntityAttribute(MAX_HEALTH);
        if (attribute == null) {
            return 0;
        }
        return attribute.getAttributeValue();
    }

    public static double getAttack(EntityLivingBase creature) {
        if (creature == null) {
            return 0;
        }

        IAttributeInstance attribute = creature.getEntityAttribute(ATTACK_DAMAGE);
        if (attribute == null) {
            return 0;
        }
        return attribute.getAttributeValue();
    }

    public static double getSight(EntityLivingBase creature) {
        if (creature == null) {
            return 0;
        }

        IAttributeInstance attribute = creature.getEntityAttribute(FOLLOW_RANGE);
        return attribute.getAttributeValue();
    }

    public static double getAtkSpeed(EntityLivingBase creature) {
        if (creature == null) {
            return 0;
        }

        IAttributeInstance attribute = creature.getEntityAttribute(ATTACK_SPEED);
        return attribute.getAttributeValue();
    }

    public static double getAttr(EntityLivingBase creature, IAttribute attr) {
        if (creature == null) {
            return 0;
        }

        IAttributeInstance attribute = creature.getEntityAttribute(attr);
        if (attribute == null) {
            return 0;
        }
        return attribute.getAttributeValue();
    }

    public static double getAttrBase(EntityLivingBase creature, IAttribute attr) {
        if (creature == null) {
            return 0;
        }

        IAttributeInstance attribute = creature.getEntityAttribute(attr);
        if (attribute == null) {
            return 0;
        }
        return attribute.getBaseValue();
    }

    public static void setAttrModifier(IAttributeInstance iattributeinstance, AttributeModifier modifier) {
        if (iattributeinstance.hasModifier(modifier)) {
            //prevent crash
            iattributeinstance.removeModifier(modifier.getID());
        }
        iattributeinstance.applyModifier(modifier);
    }

    public static boolean boostAttr(EntityLivingBase creature, IAttribute attrType, float amountFixed, UUID uuid) {
        float val = amountFixed;
        IAttributeInstance attribute = creature.getEntityAttribute(attrType);

        if (attribute == null) {
            //this happens on creatures with no attack.
            //will surely happen.
            creature.playSound(SoundEvents.BLOCK_DISPENSER_FAIL, 1f, 1f);
            return false;
        }

        double valueBefore = attribute.getAttributeValue();

        AttributeModifier modifier = attribute.getModifier(uuid);
        if (modifier != null) {
            //stack up
            val += modifier.getAmount();
            attribute.removeModifier(modifier);
        }
        attribute.applyModifier(new AttributeModifier(uuid, "pwr up", val, 0).setSaved(true));
        double valueAfter = attribute.getAttributeValue();

        if (modifier == null) {
            modifier = attribute.getModifier(uuid);
        }

        //ExampleMod.Log("Value:%s: %.2f->%.2f", modifier.getName(), valueBefore, valueAfter);
        return true;
    }

    public static boolean boostAttrRatio(EntityLivingBase creature, IAttribute attrType, float amountRatio, UUID uuid) {
        float val = amountRatio;
        IAttributeInstance attribute = creature.getEntityAttribute(attrType);

        if (attribute == null) {
            //this happens on creatures with no attack.
            //will surely happen.
            //creature.playSound(SoundEvents.BLOCK_DISPENSER_FAIL, 1f, 1f);
            return false;
        }

        //double valueBefore = attribute.getAttributeValue();

        AttributeModifier modifier = attribute.getModifier(uuid);
        if (modifier != null) {
            //stack up
            val += modifier.getAmount();
            attribute.removeModifier(modifier);
        }
        attribute.applyModifier(new AttributeModifier(uuid, "pwr up percent", val, 1).setSaved(true));
        //double valueAfter = attribute.getAttributeValue();

        if (modifier == null) {
            modifier = attribute.getModifier(uuid);
        }

        //ExampleMod.Log("Value:%s: %.2f->%.2f", modifier.getName(), valueBefore, valueAfter);
        return true;
    }

    public static boolean tryApplyToIfCreative(EntityLivingBase entityDungeonSentry, EntityPlayer player, EnumHand hand) {
        if (!player.world.isRemote && PlayerUtil.isCreative(player))
        {
            ItemStack stack = player.getHeldItem(hand);
            Item item = stack.getItem();
            if (item instanceof ItemArmor)
            {
                entityDungeonSentry.setItemStackToSlot(EntityLiving.getSlotForItemStack(stack), stack.copy());
                return true;
            } else if (item instanceof ItemElytra) {
                entityDungeonSentry.setItemStackToSlot(EntityLiving.getSlotForItemStack(stack), stack.copy());
                return true;
            } else if (item instanceof ItemTool || item instanceof ItemSword || CommonFunctions.isRangedWeaponItem(item))
            {
                entityDungeonSentry.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, stack.copy());
                if (entityDungeonSentry instanceof EntityLiving)
                {
                    ((EntityLiving) entityDungeonSentry).setDropChance(EntityEquipmentSlot.MAINHAND, 0.5f);
                }
                return true;
            }
            else
            {
                entityDungeonSentry.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, stack.copy());
                return true;
            }
        }
        return false;
    }

    public enum EnumFaction {
        PLAYER((byte) 0, 0.7f, 0.8f, 1.0f),
        IDEALLAND((byte) 1, 1.0f, 1.0f, 0.7f),
        MOB_VANILLA((byte) 2, 1.0f, 0.5f, 0.5f),
        MOB_VAN_ZOMBIE((byte) 3, 0.4f, 1f, 0.4f),
        MOROON((byte) 4, 0.9f, 0.3f, 0.8f),
        CRITTER((byte) 5);

        public final byte index;
        float r = 1.0f, g = 1.0f, b = 1.0f;

        EnumFaction(byte index, float r, float g, float b) {
            this.index = index;
            this.r = r;
            this.g = g;
            this.b = b;
        }

        EnumFaction(byte index) {
            this.index = index;
        }

        public static EnumFaction fromIndex(byte index) {
            for (EnumFaction faction :
                    EnumFaction.values()) {
                if (faction.index == index) {
                    return faction;
                }
            }

            Main.LogWarning("Trying to parse non-existing faction : %s", index);
            return CRITTER;
        }

        public void applyColor() {
            GlStateManager.color(r, g, b);
        }
    }

    public enum EnumAttitude {
        HATE,
        IGNORE,
        FRIEND
    }

    public static Biome getBiomeForEntity(Entity entity) {
        World world = entity.getEntityWorld();
        return world.getBiomeForCoordsBody(entity.getPosition());
    }

    public static final Predicate<Entity> ALL = new Predicate<Entity>() {
        public boolean apply(@Nullable Entity entity) {
            return entity != null;
        }
    };

    public static final Predicate<Entity> ALL_ALIVE = new Predicate<Entity>() {
        public boolean apply(@Nullable Entity entity) {
            return entity != null && !entity.isDead;
        }
    };

    public static final Predicate<Entity> UNDER_SKY = new Predicate<Entity>() {
        public boolean apply(@Nullable Entity entity) {
            return entity != null && entity.world.canSeeSky(new BlockPos(entity.posX, entity.posY + (double) entity.getEyeHeight(), entity.posZ));
        }
    };

    public static boolean isSunlit(Entity entity) {
        float f = entity.getBrightness();
        return f > 0.5F && UNDER_SKY.apply(entity);
    }

    public static boolean isMoonlit(Entity entity) {
        if (entity == null) {
            return false;
        }

        if (WorldUtil.isNight(entity.getEntityWorld())) return false;
        return UNDER_SKY.apply(entity);
    }

    public static final Predicate<EntityLivingBase> LIVING = new Predicate<EntityLivingBase>() {
        public boolean apply(@Nullable EntityLivingBase entity) {
            return entity != null && !entity.isEntityUndead();
        }
    };

    public static final Predicate<EntityLivingBase> UNDEAD = new Predicate<EntityLivingBase>() {
        public boolean apply(@Nullable EntityLivingBase entity) {
            return entity != null && entity.isEntityUndead();
        }
    };


    public static final Predicate<EntityLivingBase> LIVING_HIGHER = new Predicate<EntityLivingBase>() {
        public boolean apply(@Nullable EntityLivingBase entity) {
            return entity != null && !entity.isEntityUndead() && !(entity instanceof EntityAnimal);
        }
    };

    public static final Predicate<EntityLivingBase> USING_MODDED = new Predicate<EntityLivingBase>() {
        public boolean apply(@Nullable EntityLivingBase entity) {
            if (entity == null) {
                return false;
            }

            for (EntityEquipmentSlot slot :
                    EntityEquipmentSlot.values()) {
                ItemStack stack = entity.getItemStackFromSlot(slot);
                if (stack.isEmpty()) {
                    continue;
                }

                ResourceLocation regName = stack.getItem().getRegistryName();
                if (!regName.getResourceDomain().equals(CommonDef.MINECRAFT)) {
                    return true;
                }
            }

            return false;
        }
    };

    public static final Predicate<EntityLivingBase> NON_HALF_CREATURE = new Predicate<EntityLivingBase>() {
        public boolean apply(@Nullable EntityLivingBase entity) {
            return entity instanceof EntityLiving || entity instanceof EntityPlayer;
        }
    };

    public static final Predicate<EntityLivingBase> WET_CREATURE = new Predicate<EntityLivingBase>() {
        public boolean apply(@Nullable EntityLivingBase entity) {
            return entity instanceof EntityLivingBase && entity.isWet();
        }
    };

    public static float getTemperature(Entity entity) {
        World world = entity.world;
        Biome biome = world.getBiome(entity.getPosition());

        return biome.getTemperature(entity.getPosition());
    }

    public static void setPosition(Entity entity, BlockPos pos) {
        entity.setPosition(pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f);
    }

    public static double getHasteModifier(EntityLivingBase livingBase) {
//        int level = getBuffLevelIDL(livingBase, MobEffects.HASTE) - getBuffLevelIDL(livingBase, MobEffects.MINING_FATIGUE);
        float f = 1f;
        if (livingBase.isPotionActive(MobEffects.HASTE)) {
            f *= 1.0F + (float) (livingBase.getActivePotionEffect(MobEffects.HASTE).getAmplifier() + 1) * 0.2F;
        }

        if (livingBase.isPotionActive(MobEffects.MINING_FATIGUE)) {
            float f1;

            switch (livingBase.getActivePotionEffect(MobEffects.MINING_FATIGUE).getAmplifier()) {
                case 0:
                    f1 = 0.3F;
                    break;
                case 1:
                    f1 = 0.09F;
                    break;
                case 2:
                    f1 = 0.0027F;
                    break;
                case 3:
                default:
                    f1 = 8.1E-4F;
            }

            f *= f1;
        }

        return f;
    }
}
