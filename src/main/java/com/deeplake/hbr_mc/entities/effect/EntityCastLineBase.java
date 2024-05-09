package com.deeplake.hbr_mc.entities.effect;

import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityCastLineBase extends EntityCastingBase{
    private static final DataParameter<Float> START_X = EntityDataManager.<Float>createKey(EntityCastingBase.class, DataSerializers.FLOAT);
    private static final DataParameter<Float> START_Y = EntityDataManager.<Float>createKey(EntityCastingBase.class, DataSerializers.FLOAT);
    private static final DataParameter<Float> START_Z = EntityDataManager.<Float>createKey(EntityCastingBase.class, DataSerializers.FLOAT);

    private static final DataParameter<Float> END_X = EntityDataManager.<Float>createKey(EntityCastingBase.class, DataSerializers.FLOAT);
    private static final DataParameter<Float> END_Y = EntityDataManager.<Float>createKey(EntityCastingBase.class, DataSerializers.FLOAT);
    private static final DataParameter<Float> END_Z = EntityDataManager.<Float>createKey(EntityCastingBase.class, DataSerializers.FLOAT);

    public EntityCastLineBase(World worldIn) {
        super(worldIn);
    }

    public EntityCastLineBase(World worldIn, float lifeSeconds) {
        super(worldIn, lifeSeconds);
    }

    private Vec3d start = Vec3d.ZERO;
    private Vec3d end = Vec3d.ZERO;
    
    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(START_X, (float) 0);
        this.dataManager.register(START_Y, (float) 0);
        this.dataManager.register(START_Z, (float) 0);

        this.dataManager.register(END_X, (float) 0);
        this.dataManager.register(END_Y, (float) 0);
        this.dataManager.register(END_Z, (float) 0);
    }


    public Vec3d getStart() {
        if (world.isRemote)
        {
            return new Vec3d(
                    this.dataManager.get(START_X),
                    this.dataManager.get(START_Y),
                    this.dataManager.get(START_Z)
            );
        }
        else {
            return start;
        }
    }

    public void setStart(Vec3d start) {
        this.start = start;
        if (!world.isRemote)
        {
            this.dataManager.set(START_X, (float)start.x);
            this.dataManager.set(START_Y, (float)start.y);
            this.dataManager.set(START_Z, (float)start.z);
        }
    }

    public Vec3d getEnd() {
        if (world.isRemote)
        {
            return new Vec3d(
                    this.dataManager.get(END_X),
                    this.dataManager.get(END_Y),
                    this.dataManager.get(END_Z)
            );
        }
        else {
            return end;
        }
    }

    public void setEnd(Vec3d end) {
        this.end = end;
        if (!world.isRemote)
        {
            this.dataManager.set(END_X, (float)end.x);
            this.dataManager.set(END_Y, (float)end.y);
            this.dataManager.set(END_Z, (float)end.z);
        }
    }

}
