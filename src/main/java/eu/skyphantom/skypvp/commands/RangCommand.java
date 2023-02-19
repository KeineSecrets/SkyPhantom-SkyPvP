package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.SkyPvP;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RangCommand extends Command {

    public RangCommand() {
        super("Rang");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (!player.hasPermission("system.admin")) {
                player.sendMessage(SkyPvP.NOPERM);
                return true;
            }
            if (args.length < 2) {
                player.sendMessage(SkyPvP.PREFIX + "§8/§arang §8<§2Spieler§8> <§2Rang§8>");
                player.sendMessage(SkyPvP.PREFIX + "§7Verfügbare Ränge§8:");
                player.sendMessage(SkyPvP.PREFIX + "§8- ");
            }
        }
        return false;
    }
}
