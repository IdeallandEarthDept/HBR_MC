package com.deeplake.hbr_mc.entities.effect;

import com.deeplake.hbr_mc.init.RegisterBlocks;
import com.deeplake.hbr_mc.init.util.CombatUtil;
import com.deeplake.hbr_mc.init.util.CommonDef;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityCastDelayIcePillar extends EntityCastingBase {

    EnumParticleTypes particleTypes = EnumParticleTypes.SNOW_SHOVEL;
    int triggerTick = 1;

    public EntityCastDelayIcePillar(World worldIn) {
        super(worldIn);
        setTickToLive(CommonDef.TICK_PER_SECOND);
    }

    public EntityCastDelayIcePillar(World worldIn, float life, EntityLivingBase caster) {
        super(worldIn);
        shootingEntity = caster;
        setTickToLive((int) (life * CommonDef.TICK_PER_SECOND));
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        upkeep();
        if (world.isRemote)
        {
            //create particles
            int particlePerTick = 3;
            float radius = 0.5f;
            float ySpeed = 1f;
            for (int i = 0; i < particlePerTick; i++)
            {
                float angle = (float) (Math.random() * 6.28f);
                float tempRadius =  (float) (radius * Math.random());
                world.spawnParticle(particleTypes,
                        posX + Math.sin(angle) * tempRadius,
                        posY ,
                        posZ + Math.cos(angle) * tempRadius,
                        0,
                        ySpeed,
                        0
                        );

                world.spawnParticle(particleTypes,
                        posX + Math.sin(angle) * radius,
                        posY ,
                        posZ + Math.cos(angle) * radius,
                        0,
                        0,
                        0
                );
            }
        }
        else {

        }
    }

    @Override
    public void onExpire() {
        super.onExpire();
        trigger();
    }

    void trigger()
    {
        if (!world.isRemote && shootingEntity != null && shootingEntity.isEntityAlive())
        {
            for (int i = 0; i < 4; i++) {
                BlockPos pos = getPosition().up(i);
                IBlockState state = world.getBlockState(pos);
                if (state.getBlock().isReplaceable(world, pos))
                {
                    world.setBlockState(pos, RegisterBlocks.DRY_ICE.getDefaultState());
                }
            }

            CombatUtil.areaAttack(
                    world, shootingEntity, getPositionVector(),
                    0.5f, CombatUtil.EnumAttrType.STANDARD,
                    300, 200, 0f
            );
        }
    }
}
