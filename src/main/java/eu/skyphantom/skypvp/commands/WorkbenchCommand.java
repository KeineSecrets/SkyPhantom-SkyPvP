package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.SkyPvP;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class WorkbenchCommand extends Command {

    public WorkbenchCommand() {
        super("workbench", "", "", Arrays.asList("werkbank", "wb"));
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("system.fage")) {
                player.openWorkbench(player.getLocation(), true);
                player.sendMessage(SkyPvP.PREFIX + "§7Du hast eine Werkbank geöffnet§8.");
                return true;
            }
            player.sendMessage(SkyPvP.NOPERM);
            return true;
        }
        return false;
    }
}
