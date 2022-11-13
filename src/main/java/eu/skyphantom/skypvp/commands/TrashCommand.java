package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.List;

public class TrashCommand extends Command {

    public TrashCommand() {
        super("trash", "", "", Arrays.asList("bin", "trashcan", "müll", "mülleimer"));
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("system.titan")) {
                Inventory inventory = Bukkit.createInventory(null, 36, Utils.capitalizeFirstLetter(s));
                player.openInventory(inventory);
                player.sendMessage(SkyPvP.PREFIX + "§7Du hast einen Mülleimer geöffnet§8.");
                return true;
            }
            player.sendMessage(SkyPvP.NOPERM);
            return true;
        }
        return false;
    }
}
