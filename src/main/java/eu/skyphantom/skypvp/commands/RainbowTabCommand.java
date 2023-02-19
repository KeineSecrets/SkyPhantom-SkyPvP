package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.api.RainbowTab;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * JavaDoc this file!
 * Created: 29/12/2022
 *
 * @author WeLoveSpigotPlugins (welovespigotplugins@gmail.com)
 */
public class RainbowTabCommand extends Command {

    boolean added = false;

    public RainbowTabCommand() {
        super("rainbowtab");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("system.owner")) {
                if (strings.length < 1) {
                    player.sendMessage(SkyPvP.PREFIX + "§8/§a" + s + " §8<§2all§8/§2Spieler§8>");
                    return true;
                }
                if (strings[0].equalsIgnoreCase("all")) {
                    if (!added) {
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            RainbowTab.set(all, true);
                        }
                        added = true;
                        player.sendMessage(SkyPvP.PREFIX + "§7Jeder Spieler hat nun eine Rainbow§8-§7Tab§8.");
                        return true;
                    }
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        RainbowTab.set(all, false);
                    }
                    added = false;
                    player.sendMessage(SkyPvP.PREFIX + "§7Keiner hat nun eine Rainbow§8-§7Tab mehr§8.");
                    return true;
                }
                Player target = Bukkit.getPlayer(strings[0]);
                if (target != null) {
                    if (RainbowTab.activePlayers.getOrDefault(player, false)) {
                        RainbowTab.set(target, false);
                        player.sendMessage(SkyPvP.PREFIX + target.getDisplayName() + "§7 hat nun keine Rainbow§8-§7Tab mehr§8.");
                        return true;
                    }
                    RainbowTab.set(target, true);
                    player.sendMessage(SkyPvP.PREFIX + target.getDisplayName() + "§7 hat nun eine Rainbow§8-§7Tab§8.");
                    return true;
                }
                player.sendMessage(SkyPvP.PREFIX + "§8/§a" + s + " §8<§2all§8/§2Spieler§8>");
                return true;
            }
            player.sendMessage(SkyPvP.NOPERM);
            return true;
        }
        return true;
    }
}
