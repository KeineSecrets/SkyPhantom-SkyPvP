package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.Collections;

public class CClearCommand extends Command {

    public CClearCommand() {
        super("cclear", "", "", Collections.singletonList("occ"));
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("system.titan")) {
                for (int i = 0; i < 256; i++) player.sendMessage("§r");
                player.sendMessage(SkyPvP.PREFIX + "§7Du hast deinen Chat geleert§8.");
                return true;
            }
            player.sendMessage(SkyPvP.NOPERM);
            return true;
        }
        return false;
    }
}
