package com.deeplake.hbr_mc.events;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.entities.EntityNabiSlime;
import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.RegisterItem;
import net.minecraft.client.particle.ParticleEndRod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class EventsPlayerDeath {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onDeath(LivingDeathEvent evt) {
        if (ModConfig.CONFIG.SERAPH_RETRIEVE)
        {
            if (evt.getEntityLiving() instanceof EntityPlayer && !evt.getEntityLiving().world.isRemote && !evt.isCanceled()) {
                EntityPlayer player = (EntityPlayer) evt.getEntityLiving();
                boolean foundCirclet = false;
                ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND);
                if (stack.getItem() == RegisterItem.LEAF_CIRCLET)
                {
                    player.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, ItemStack.EMPTY);
                    foundCirclet = true;
                } else {
                    stack = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
                    if (stack.getItem() == RegisterItem.LEAF_CIRCLET)
                    {
                        player.setItemStackToSlot(EntityEquipmentSlot.HEAD, ItemStack.EMPTY);
                        foundCirclet = true;
                    }
                }

                if (foundCirclet)
                {
                    World world = player.getEntityWorld();
                    EntityNabiSlime slime = new EntityNabiSlime(world);
                    slime.setPositionAndRotation(player.posX,player.posY,player.posZ,player.rotationYaw, player.rotationPitch);
                    slime.setHealth(player.getMaxHealth());
                    if (stack.hasDisplayName())
                    {
                        slime.setCustomNameTag(stack.getDisplayName());
                    }
                    else
                    {
                        slime.setCustomNameTag(player.getDisplayNameString());
                    }
                    world.spawnEntity(slime);
                }
            }
        }
    }
}
