package com.deeplake.hbr_mc.commands;


import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.init.util.CommonFunctions;
import com.deeplake.hbr_mc.init.util.IDLNBTUtil;
import com.google.common.collect.Lists;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

import static com.deeplake.hbr_mc.designs.SeraphTeleportControl.SERAPH_TELEPORT;

public class CommandSeraphTP extends CommandBase {
    private final List<String> aliases = Lists.newArrayList( "srtp", "seraphtp", "toggletp");
    @Override
    public String getName() {
        return "seraphtp";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "seraphtp";
    }

    @Override
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (sender instanceof EntityPlayer)
        {
            boolean isOn = IDLNBTUtil.getPlayerIdeallandBoolSafe((EntityPlayer) sender, SERAPH_TELEPORT);
            isOn = !isOn;
            IDLNBTUtil.setPlayerIdeallandTagSafe((EntityPlayer) sender, SERAPH_TELEPORT,
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
