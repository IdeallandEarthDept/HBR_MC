package com.deeplake.hbr_mc.events;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.init.ModConfig;
import com.deeplake.hbr_mc.init.util.IDLNBTUtil;
import com.deeplake.hbr_mc.items.seraph.ItemSeraphBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import static com.deeplake.hbr_mc.init.util.IDLNBTDef.*;

@Mod.EventBusSubscriber
public class SeraphRetrieve {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onDeath(LivingDeathEvent evt) {
        if (ModConfig.CONFIG.SERAPH_RETRIEVE)
        {
            if (evt.getEntityLiving() instanceof EntityPlayer && !evt.getEntityLiving().world.isRemote && !evt.isCanceled()) {
                EntityPlayer player = (EntityPlayer) evt.getEntityLiving();
                ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
                if (stack.getItem() instanceof ItemSeraphBase)
                {
                    IDLNBTUtil.setPlayerIdeallandTagSafe(player, KEY_SERAPH_RETRIEVE, stack.getItem().getRegistryName().toString());
                    IDLNBTUtil.setPlayerIdeallandTagSafe(player, KEY_SERAPH_RETRIEVE_NBT, stack.getTagCompound().toString());
                    IDLNBTUtil.setPlayerIdeallandTagSafe(player, KEY_SERAPH_RETRIEVE_DAMAGE, stack.getItemDamage());

                    player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStack.EMPTY);
                }
                else {
                    IDLNBTUtil.setPlayerIdeallandTagSafe(player, KEY_SERAPH_RETRIEVE, "");
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onRevive(PlayerEvent.PlayerRespawnEvent evt) {
        if (ModConfig.CONFIG.SERAPH_RETRIEVE)
        {
            if (!evt.player.world.isRemote && !evt.isCanceled()) {
                EntityPlayer player = evt.player;
                String retrieveType = IDLNBTUtil.getPlayerIdeallandStrSafe(player, KEY_SERAPH_RETRIEVE);
                if (!retrieveType.isEmpty())
                {
                    Item item = Item.getByNameOrId(retrieveType);
                    if (item != null)
                    {
                        ItemStack stack = new ItemStack(item);
                        stack.setTagCompound(IDLNBTUtil.getPlayerIdeallandTagGroupSafe(player,KEY_SERAPH_RETRIEVE_NBT));
                        stack.setItemDamage(IDLNBTUtil.getPlayerIdeallandIntSafe(player,KEY_SERAPH_RETRIEVE_DAMAGE));
                        player.addItemStackToInventory(stack);
                    }
                    else {
                        Main.LogWarning("Failed to give %s a %s", player.getName(), retrieveType);
                    }
                    IDLNBTUtil.setPlayerIdeallandTagSafe(player, KEY_SERAPH_RETRIEVE, "");
                }
            }
        }
    }
}
