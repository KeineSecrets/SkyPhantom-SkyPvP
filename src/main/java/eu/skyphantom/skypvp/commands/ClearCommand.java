package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.SkyPvP;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;

public class ClearCommand extends Command {

    public ClearCommand() {
        super("clear", "", "", Collections.singletonList("ci"));
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("system.titan")) {
                if (args.length == 0) {
                    player.getInventory().clear();
                    player.sendMessage(SkyPvP.PREFIX + "§7Du hast dein Inventar geleert§8.");
                    return true;
                }
                if (player.hasPermission("system.team")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        player.sendMessage(SkyPvP.PREFIX + "§8/§aclear §8<§2Spieler§8>");
                        return true;
                    }
                    target.getInventory().clear();
                    player.sendMessage(SkyPvP.PREFIX + "§7Du hast das Inventar von §a" + target.getName() + "§7 geleert§8.");
                    target.sendMessage(SkyPvP.PREFIX + "§7Dein Inventar wurde von §a" + player.getName() + "§7 geleert§8.");
                    return true;
                }
                player.getInventory().clear();
                player.sendMessage(SkyPvP.PREFIX + "§7Du hast dein Inventar geleert§8.");
                return true;
            }
            player.sendMessage(SkyPvP.NOPERM);
            return true;
        }
        return false;
    }
}
