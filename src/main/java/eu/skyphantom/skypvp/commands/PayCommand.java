package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.provider.StatsProvider;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.Collections;

public class PayCommand extends Command {

    public PayCommand() {
        super("pay", "", "", Collections.singletonList("überweisen"));
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (args.length < 2) {
                player.sendMessage(SkyPvP.PREFIX + "§8/§apay §8<§2Spieler§8> <§2Amount§8>");
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                try {
                    double value = Double.parseDouble(args[1].replaceAll("e", "").replace(".", ""));
                    StatsProvider providerPlayer = new StatsProvider(player.getUniqueId());
                    StatsProvider providerTarget = new StatsProvider(target.getUniqueId());
                    if (providerPlayer.getCoins() >= value) {
                        providerPlayer.removeCoins(value);
                        providerTarget.addCoins(value);
                        player.sendMessage(SkyPvP.PREFIX + "§7Du hast §a" + new DecimalFormat("#,###.##").format(value).replace(",", ".").replace(".", "§8'§a") + "§7 Tokens an §2" + target.getName() + "§7 überwiesen§8.");
                        target.sendMessage(SkyPvP.PREFIX + "§7Dir wurden §a" + new DecimalFormat("#,###.##").format(value).replace(",", ".").replace(".", "§8'§a") + "§7 Tokens von §2" + player.getName() + "§7 überwiesen§8.");
                    } else {
                        player.sendMessage(SkyPvP.PREFIX + "§7Dazu hast du zu wenig Tokens§8.");
                    }
                    return true;
                } catch (NumberFormatException e) {
                    player.sendMessage(SkyPvP.PREFIX + "§7Gebe eine gülrige Zahl an§8.");
                    return true;
                }
            }
            player.sendMessage(SkyPvP.PREFIX + "§8/§apay §8<§2Spieler§8> <§2Amount§8>");
            return true;
        }
        return false;
    }
}
