package com.deeplake.hbr_mc.events;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.entities.EntityNabiSlime;
import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class EventsDropHandler {
    @SubscribeEvent
    public static void onDrop(LivingDropsEvent event)
    {
        EntityLivingBase living = event.getEntityLiving();
        if (!living.isNonBoss())
        {
            return;
        }

        if (ModConfig.CONFIG.ALLOW_SPOILERS)
        {
            if (living instanceof EntitySlime && !(living instanceof EntityNabiSlime))
            {
                if (living.getRNG().nextFloat() < 0.1f)
                {
                    Item item = RegisterItem.LEAF_CIRCLET;
                    EntityItem copy = dropItem(living, item);
                    event.getDrops().add(copy);
                }
            }
        }
    }

    private static EntityItem dropItem(EntityLivingBase living, Item item) {
        return dropItem(living, new ItemStack(item));
    }

    private static EntityItem dropItem(EntityLivingBase living, ItemStack item) {
        EntityItem copy = new EntityItem(living.world, living.posX, living.posY, living.posZ, item);
        living.world.spawnEntity(copy);
        return copy;
    }
}
