package com.deeplake.hbr_mc.entities.npc;

import com.deeplake.hbr_mc.entities.EntityHumanoid;
import net.minecraft.entity.INpc;
import net.minecraft.world.World;

public class EntityNPC extends EntityHumanoid implements INpc {
    public EntityNPC(World worldIn) {
        super(worldIn);
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }
}
