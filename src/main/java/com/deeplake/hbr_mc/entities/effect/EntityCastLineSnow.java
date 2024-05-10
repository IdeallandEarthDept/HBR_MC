package com.deeplake.hbr_mc.entities.effect;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityCastLineSnow extends EntityCastLineBase{
    public EntityCastLineSnow(World worldIn) {
        super(worldIn);
    }

    public EntityCastLineSnow(World worldIn, float lifeSeconds) {
        super(worldIn, lifeSeconds);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        //spawn particle between start and end per 0.3 block
        for (double i = 0; i < getStart().distanceTo(getEnd()); i += 0.3)
        {
            double ratio = i / getStart().distanceTo(getEnd());
            double x = getStart().x + (getEnd().x - getStart().x) * ratio;
            double y = getStart().y + (getEnd().y - getStart().y) * ratio;
            double z = getStart().z + (getEnd().z - getStart().z) * ratio;
            world.spawnParticle(EnumParticleTypes.SNOW_SHOVEL, x, y, z, 0, 0, 0);
        }
    }
}
