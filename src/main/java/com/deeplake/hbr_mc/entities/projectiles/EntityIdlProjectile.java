package com.deeplake.hbr_mc.entities.projectiles;

import com.deeplake.hbr_mc.init.util.IDLNBTUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

import static net.minecraft.util.EntitySelectors.IS_ALIVE;

//modified from fireballs
public class EntityIdlProjectile extends Entity implements IProjectile {
    public static final String BIRTH_TIME = "birthTime";
    public EntityLivingBase shootingEntity;
    private int ticksAlive;
    protected int ticksInAir;
    public double accelerationX;
    public double accelerationY;
    public double accelerationZ;
    public ProjectileArgs args;
    boolean hitToDeflect = false;

    public float acceleration = 0.1f;

    boolean bypassArmor = false;

    protected EntityIdlProjectile(World worldIn)
    {
        super(worldIn);
        args = new ProjectileArgs(1f);
        this.setSize(0.3125F, 0.3125F);
//        Main.Log("bullet created %s, args = %s", getUniqueID(), this.args);
    }

    @Override
    protected void entityInit() {

    }

    protected EntityIdlProjectile(World worldIn, ProjectileArgs args) {
        this(worldIn);
        this.args = args;
    }

    /**
     * Checks if the entity is in range to render.
     */
    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double distance)
    {
        //copied from fireballs
        double d0 = this.getEntityBoundingBox().getAverageEdgeLength() * 4.0D;

        if (Double.isNaN(d0))
        {
            d0 = 4.0D;
        }

        d0 = d0 * 64.0D;
        return distance < d0 * d0;
    }

    //no shooting entity
    public EntityIdlProjectile(World worldIn, ProjectileArgs args, BlockPos shooter, double accelX, double accelY, double accelZ, float acceleration)
    {
        this(worldIn, args);
        Vec3d shooterFacing = new Vec3d(accelX, accelY, accelZ).normalize();

        this.setLocationAndAngles(shooter.getX()+ 0.5f + shooterFacing.x,
                shooter.getY() + 0.5f + shooterFacing.y,
                shooter.getZ() + 0.5f + shooterFacing.z,
                0, 0);
        this.setPosition(this.posX ,
                this.posY,
                this.posZ);

        double accelLength = MathHelper.sqrt(accelX * accelX + accelY * accelY + accelZ * accelZ);
        this.accelerationX = accelX / accelLength * acceleration;
        this.accelerationY = accelY / accelLength * acceleration;
        this.accelerationZ = accelZ / accelLength * acceleration;
    }

    public EntityIdlProjectile(World worldIn, ProjectileArgs args, EntityLivingBase shooter, double accelX, double accelY, double accelZ, float acceleration)
    {
        this(worldIn, args);
        this.shootingEntity = shooter;
        Vec3d shooterFacing = shooter.getLook(0f);

        this.setLocationAndAngles(shooter.posX + shooterFacing.x,
                shooter.posY + shooter.getEyeHeight() + shooterFacing.y,
                shooter.posZ + shooterFacing.z,
                shooter.rotationYaw, shooter.rotationPitch);

        this.motionX = shooter.motionX;
        this.motionY = shooter.onGround ? 0 : shooter.motionY;
        this.motionZ = shooter.motionZ;
        double accelLength = (double)MathHelper.sqrt(accelX * accelX + accelY * accelY + accelZ * accelZ);
        this.accelerationX = accelX / accelLength * acceleration;
        this.accelerationY = accelY / accelLength * acceleration;
        this.accelerationZ = accelZ / accelLength * acceleration;
    }

    public EntityIdlProjectile(World worldIn, ProjectileArgs args, EntityLivingBase shooter, double accelX, double accelY, double accelZ)
    {
        this(worldIn, args, shooter, accelX, accelY, accelZ, 0.1f);
    }


    void markTimeStamp()
    {
        IDLNBTUtil.setLong(this, BIRTH_TIME, System.currentTimeMillis());
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        //Idealland.Log("Bullet pos update:%s", getPositionEyes(0));

        if (this.world.isRemote || (this.shootingEntity == null || !this.shootingEntity.isDead) && this.world.isBlockLoaded(new BlockPos(this)))
        {
            super.onUpdate();
            int count = world.getEntities(EntityIdlProjectile.class, IS_ALIVE).size();
//            if (this.world.isRemote &&
//                    (Minecraft.getDebugFPS() <= rand.nextInt(30)
//                            || (20 + rand.nextInt(count) < count)))
//            {
//                //client performance helper
//                return;
//            }

            //todo
            if (!world.isRemote && count > 999)
            {
                //performance failsafe
                if (rand.nextBoolean())
                {
                    setDead();
                }
            }

            if (this.isFireballFiery())
            {
                this.setFire(1);
            }

            ++this.ticksInAir;
            RayTraceResult raytraceresult = ProjectileHelper.forwardsRaycast(this, true, this.ticksInAir >= 25, this.shootingEntity);

            if (raytraceresult != null && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult))
            {
                this.onImpact(raytraceresult);
            }

            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            ProjectileHelper.rotateTowardsMovement(this, 0.2F);
            float f = 0.99f;

            if (this.isInWater())
            {
                for (int i = 0; i < 4; ++i)
                {
                    float f1 = 0.25F;
                    this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * 0.25D, this.posY - this.motionY * 0.25D, this.posZ - this.motionZ * 0.25D, this.motionX, this.motionY, this.motionZ);
                }

                f = 0.8F;
            }

            this.motionX += this.accelerationX;
            this.motionY += this.accelerationY;
            this.motionZ += this.accelerationZ;
            this.motionX *= f;
            this.motionY *= f;
            this.motionZ *= f;
            //this.world.spawnParticle(this.getParticleType(), this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
            this.setPosition(this.posX, this.posY, this.posZ);

            if (this.args.ttl < ticksInAir)
            {
                this.setDead();
            }
        }
        else
        {
            this.setDead();
        }
    }

    protected boolean isFireballFiery()
    {
        if (args == null)
        {
            //Idealland.LogWarning("Args not found for bullet");
            return false;
        }
        return args.burning;
    }

    public boolean shouldCauseDamage()
    {
        return true;
    }

    /**
     * Called when this EntityIdlProjectile hits a block or entity.
     */
    protected void onImpact(RayTraceResult result)
    {
        if (!this.world.isRemote)
        {
            if (result.entityHit != null)
            {
                if (shouldCauseDamage())
                {
                    DamageSource source = DamageSource.causeIndirectDamage(this, this.shootingEntity).setProjectile();
                    if (bypassArmor)
                    {
                        source.setDamageBypassesArmor();
                    }
                    boolean hit = result.entityHit.attackEntityFrom(source, args.damage);
                    if (!hit)
                    {
                        return;
                    }
                }

                result.entityHit.hurtResistantTime = 0;

                if (isBurning() || args.burning)
                {
                    result.entityHit.setFire(3);
                }
                this.applyEnchantments(this.shootingEntity, result.entityHit);
            }

            boolean flag = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this.shootingEntity);
            if (args.explosion_power > 0) {
                this.world.newExplosion(null, posX, posY, posZ, args.explosion_power, flag, flag);
            }

            world.playSound(posX, posY, posZ, SoundEvents.BLOCK_LAVA_EXTINGUISH,
                    SoundCategory.NEUTRAL, 1f, 1f, false);

            this.setDead();
        }
        else {
            world.spawnParticle(EnumParticleTypes.CLOUD, posX, posY, posZ, motionX, motionY, motionZ);
            if (result.entityHit == null)
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

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        compound.setTag("direction", this.newDoubleNBTList(this.motionX, this.motionY, this.motionZ));
        compound.setTag("power", this.newDoubleNBTList(this.accelerationX, this.accelerationY, this.accelerationZ));
        compound.setInteger("life", this.ticksAlive);

        this.args.writeEntityToNBT(compound);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        if (compound.hasKey("power", 9))
        {
            NBTTagList nbttaglist = compound.getTagList("power", 6);

            if (nbttaglist.tagCount() == 3)
            {
                this.accelerationX = nbttaglist.getDoubleAt(0);
                this.accelerationY = nbttaglist.getDoubleAt(1);
                this.accelerationZ = nbttaglist.getDoubleAt(2);
            }
        }

        this.ticksAlive = compound.getInteger("life");

        if (compound.hasKey("direction", 9) && compound.getTagList("direction", 6).tagCount() == 3)
        {
            NBTTagList nbttaglist1 = compound.getTagList("direction", 6);
            this.motionX = nbttaglist1.getDoubleAt(0);
            this.motionY = nbttaglist1.getDoubleAt(1);
            this.motionZ = nbttaglist1.getDoubleAt(2);
        }
        else
        {
            this.setDead();
        }

        if (this.args == null)
        {
            this.args = new ProjectileArgs(1f);
        }
        this.args.readEntityFromNBT(compound);
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return false;
    }

    public float getCollisionBorderSize()
    {
        return 1.0F;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source) || !hitToDeflect)
        {
            return false;
        }
        else
        {
            this.markVelocityChanged();

            if (source.getTrueSource() != null)
            {
                Vec3d vec3d = source.getTrueSource().getLookVec();

                this.motionX = vec3d.x;
                this.motionY = vec3d.y;
                this.motionZ = vec3d.z;
                this.accelerationX = this.motionX * 0.1D;
                this.accelerationY = this.motionY * 0.1D;
                this.accelerationZ = this.motionZ * 0.1D;

                if (source.getTrueSource() instanceof EntityLivingBase)
                {
                    this.shootingEntity = (EntityLivingBase)source.getTrueSource();
                }

                return true;
            }
            else
            {
                return false;
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender()
    {
        return 15728880;
    }

    /**
     * Similar to setArrowHeading, it's point the throwable entity to a x, y, z direction.
     */
    public void shoot(double x, double y, double z, float velocity, float inaccuracy)
    {
        float f = MathHelper.sqrt(x * x + y * y + z * z);
        x = x / (double)f;
        y = y / (double)f;
        z = z / (double)f;
        x = x + this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        y = y + this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        z = z + this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        x = x * (double)velocity;
        y = y * (double)velocity;
        z = z * (double)velocity;
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        float f1 = MathHelper.sqrt(x * x + z * z);
        this.rotationYaw = (float)(MathHelper.atan2(x, z) * (180D / Math.PI));
        this.rotationPitch = (float)(MathHelper.atan2(y, (double)f1) * (180D / Math.PI));
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
        markTimeStamp();
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (IDLNBTUtil.GetLong(this, BIRTH_TIME, 0) - System.currentTimeMillis() > 1000 * 60)
        {
            //too old
            this.setDead();
        }
    }

    public boolean isBypassArmor() {
        return bypassArmor;
    }

    public void setBypassArmor(boolean bypassArmor) {
        this.bypassArmor = bypassArmor;
    }
}
