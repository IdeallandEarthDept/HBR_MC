package com.deeplake.hbr_mc.designs;

import com.deeplake.hbr_mc.init.util.IDLNBTUtil;
import net.minecraft.entity.player.EntityPlayer;

public class SeraphTeleportControl {
    public static final String SERAPH_TELEPORT = "srph_tp";
    public static final String SERAPH_TELEPORT_ROUGH = "srph_tp_r";

    public static boolean canTeleport(EntityPlayer player)
    {
        return IDLNBTUtil.getPlayerIdeallandBoolSafe(player, SERAPH_TELEPORT);
    }

    public static void setSeraphTeleport(EntityPlayer player, boolean value)
    {
        IDLNBTUtil.setPlayerIdeallandTagSafe(player, SERAPH_TELEPORT, value);
    }
}
