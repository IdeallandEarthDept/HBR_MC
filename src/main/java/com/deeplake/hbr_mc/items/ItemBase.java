package com.deeplake.hbr_mc.items;

import com.deeplake.hbr_mc.ModTabs;
import com.deeplake.hbr_mc.entities.projectiles.EntityIdlProjectile;
import com.deeplake.hbr_mc.entities.projectiles.ProjectileArgs;
import com.deeplake.hbr_mc.init.RegisterUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBase extends Item {

    public ItemBase(String name)
    {
        super();
        RegisterUtil.initItem(this, name);
        setCreativeTab(ModTabs.TAB1);
    }

    public EntityIdlProjectile getBullet(EntityLivingBase shooter, Entity target, ItemStack stack,
                                         World world, ProjectileArgs args, double dx, double dy, double dz, float bulletAccel)
    {
        return new EntityIdlProjectile(world, args, shooter,
                dx,
                dy,
                dz,
                bulletAccel);
    }
}
