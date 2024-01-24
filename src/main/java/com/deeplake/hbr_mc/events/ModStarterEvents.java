package com.deeplake.hbr_mc.events;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.init.RegisterItem;
import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.init.util.IDLNBTUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.deeplake.hbr_mc.init.util.IDLNBTDef.CUR_STARTER_KIT_VERSION;
import static com.deeplake.hbr_mc.init.util.IDLNBTDef.STARTER_KIT_VERSION_TAG;
import static com.deeplake.hbr_mc.init.util.IDLNBTUtil.getPlayerIdeallandIntSafe;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ModStarterEvents {

	public static SimpleDateFormat formatLoginTime = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss");

	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		EntityPlayer player = event.player;
		Calendar calendar = player.world.getCurrentDate();
		Main.Log("[HBRMC][LOGIN]%s(%s) logged in @%s", player.getDisplayNameString(), player.getUniqueID(), formatLoginTime.format(calendar.getTime()));
		if (!player.world.isRemote)
		{
			int lastStarterVer = getPlayerIdeallandIntSafe(player, STARTER_KIT_VERSION_TAG);
			if(lastStarterVer < CUR_STARTER_KIT_VERSION) {
				IDLNBTUtil.setPlayerIdeallandTagSafe(player, STARTER_KIT_VERSION_TAG, CUR_STARTER_KIT_VERSION);

				ItemStack starter = new ItemStack(RegisterItem.LOTTERY);
				player.addItemStackToInventory(starter);

				if (player instanceof EntityPlayerMP) {
				  CommonFunctions.SendMsgToPlayerStyled((EntityPlayerMP)player, "hbr_mc.msg.starter_kit_given", TextFormatting.AQUA);
				}
				Main.Log(String.format("Given starter items to player %s, ver %d", player.getDisplayNameString(), CUR_STARTER_KIT_VERSION));
			}
		}
	}
	
}
