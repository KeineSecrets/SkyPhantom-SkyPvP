package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.provider.LuckPermsProvider;
import eu.skyphantom.skypvp.provider.StatsProvider;
import eu.skyphantom.skypvp.utils.Utils;
import eu.skyphantom.skypvp.utils.time.OnlineTime;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

public class StatsCommand extends Command {

    public StatsCommand() {
        super("stats", "", "", Collections.singletonList("statistics"));
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (args.length < 1) {
                StatsProvider statsProvider = new StatsProvider(player.getUniqueId());

                player.sendMessage(SkyPvP.PREFIX + "§7Deine Statistiken§8:");
                player.sendMessage("");
                player.sendMessage("§8▎ §7Name§8: §a" + player.getName() + "§8 ▪ §7Status§8: §aonline §8▪ §7Rang§8: §e" + Utils.capitalizeFirstLetter(LuckPermsProvider.getGroupName(player.getUniqueId())));
                player.sendMessage("§8▎ §7Zuletzt gesehen§8: §3" + new SimpleDateFormat("dd.MM.yy, HH:mm").format(new Date()).replace(", ", "§8, §3").replace(".", "§8.§3").replace(":", "§8:§3") + "§3 Uhr§8 ▪ §7Kopfgeld§8: §d0§5$");
                player.sendMessage("§8▎ §7Kills§8: §a" + new DecimalFormat("#,###.##").format(statsProvider.getKills()).replace(",", ".").replace(".", "§8'§a") + "§8 ▪ §7Tode§8: §c" + new DecimalFormat("#,###.##").format(statsProvider.getDeaths()).replace(",", ".").replace(".", "§8'§c") + "§8 ▪ §7KDr§8: §e" + statsProvider.getKDr("§e") + "§8 ▪ §7Liga§8: §r" + statsProvider.getLeague().getDisplay());
                player.sendMessage("§8▎ §7Tokens§8: §6" + new DecimalFormat("#,###.##").format(statsProvider.getCoins()).replace(",", ".").replace(".", "§8'§6") + "§8 ▪ §7Votes§8: §90 §8▪ §7Spielzeit§8: §6" + OnlineTime.get(player.getUniqueId(), true));
                player.sendMessage("");
                return true;
            }
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            if (target != null && target.hasPlayedBefore()) {
                StatsProvider statsProvider = new StatsProvider(target.getUniqueId());
                player.sendMessage(SkyPvP.PREFIX + "§7Statistik von §a" + target.getName() + "§8:");
                player.sendMessage("");
                player.sendMessage("§8▎ §7Name§8: §a" + target.getName() + "§8 ▪ §7Status§8: " + (target.isOnline() ? "§aonline" : "§coffline") + " §8▪ §7Rang§8: §e" + Utils.capitalizeFirstLetter(LuckPermsProvider.giveMeADamnUser(target.getUniqueId()).getPrimaryGroup()));
                player.sendMessage("§8▎ §7Zuletzt gesehen§8: §3" + new SimpleDateFormat("dd.MM.yy, HH:mm").format((target.isOnline() ? new Date() : new Date(target.getLastPlayed()))).replace(", ", "§8, §3").replace(".", "§8.§3").replace(":", "§8:§3") + "§3 Uhr§8 ▪ §7Kopfgeld§8: §d0§5$");
                player.sendMessage("§8▎ §7Kills§8: §a" + new DecimalFormat("#,###.##").format(statsProvider.getKills()).replace(",", ".").replace(".", "§8'§a") + "§8 ▪ §7Tode§8: §c" + new DecimalFormat("#,###.##").format(statsProvider.getDeaths()).replace(",", ".").replace(".", "§8'§c") + "§8 ▪ §7KDr§8: §e" + statsProvider.getKDr("§e") + "§8 ▪ §7Liga§8: §r" + statsProvider.getLeague().getDisplay());
                player.sendMessage("§8▎ §7Tokens§8: §6" + new DecimalFormat("#,###.##").format(statsProvider.getCoins()).replace(",", ".").replace(".", "§8'§6") + "§8 ▪ §7Votes§8: §90 §8▪ §7Spielzeit§8: §6" + OnlineTime.get(target.getUniqueId(), true));
                player.sendMessage("");
                return true;
            }
            player.sendMessage(SkyPvP.PREFIX + "§7Der Spieler hat noch nie auf dem Server gespielt§8.");
            return true;
        }
        return false;
    }
}
