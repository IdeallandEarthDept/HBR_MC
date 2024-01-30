package com.deeplake.hbr_mc.commands;


import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.init.util.IDLNBTUtil;
import com.google.common.collect.Lists;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

import java.util.List;

import static com.deeplake.hbr_mc.designs.SeraphTeleportControl.SERAPH_TELEPORT;
import static com.deeplake.hbr_mc.designs.SeraphTeleportControl.SERAPH_TELEPORT_ROUGH;

public class CommandSeraphTPRough extends CommandBase {
    private final List<String> aliases = Lists.newArrayList( "srtpr", "seraphtpr", "toggletpr");
    @Override
    public String getName() {
        return "seraphtprough";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "seraphtprough";
    }

    @Override
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (sender instanceof EntityPlayer)
        {
            boolean isOn = IDLNBTUtil.getPlayerIdeallandBoolSafe((EntityPlayer) sender, SERAPH_TELEPORT_ROUGH);
            isOn = !isOn;
            IDLNBTUtil.setPlayerIdeallandTagSafe((EntityPlayer) sender, SERAPH_TELEPORT_ROUGH,
                    isOn);

            CommonFunctions.SafeSendMsgToPlayer((Entity) sender,
                    isOn?
                    "hbr_mc.msg.teleport_allow":"hbr_mc.msg.teleport_deny"
                    );
        }
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }
}
