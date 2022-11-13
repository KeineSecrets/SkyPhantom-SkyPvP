package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.punish.BanManager;
import eu.skyphantom.skypvp.punish.Bans;
import eu.skyphantom.skypvp.punish.MuteManager;
import eu.skyphantom.skypvp.punish.Mutes;
import eu.skyphantom.skypvp.utils.UUIDFetcher;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MuteCommand extends Command {

    public MuteCommand() {
        super("mute");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (!player.hasPermission("system.mod")) {
                player.sendMessage(SkyPvP.NOPERM);
                return true;
            }
            if (args.length < 2) {
                player.sendMessage(SkyPvP.PUNISH + "§8/§amute §8<§2Spieler§8> <§2ID§8>");
                player.sendMessage("§r");
                for (Mutes value : Mutes.values()) {
                    player.sendMessage(SkyPvP.PUNISH + "§7ID §2" + (value.ordinal()+1) + "§8 × §a" + value.getName() + "§8 × §a" + value.getTimeString());
                }
                return true;
            }
            Player target = Bukkit.getPlayer(UUIDFetcher.getUUID(args[0]));
            if (target != null) {
                if (target.getName().equals(player.getName())) {
                    player.sendMessage(SkyPvP.PREFIX + "§7Du kannst dich nicht selbst muten§8.");
                    return true;
                }
                if (target.hasPermission("system.team")) {
                    player.sendMessage(SkyPvP.PREFIX + "§7Du kannst diese Person nicht muten§8.");
                    return true;
                }
                try {
                    MuteManager banManager = new MuteManager(target.getUniqueId());
                    int id = Integer.parseInt(args[1]);
                    Mutes ban = null;
                    for (Mutes bans : Mutes.values()) {
                        int banId = bans.ordinal()+1;
                        if (id == banId) ban = bans;
                    }
                    if (ban != null) {
                        if (ban == Mutes.ADMINBAN) {
                            if (player.hasPermission("system.admin")) {
                                banManager.mute(ban.getName(), (ban.getTime()));
                                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                                    if (!onlinePlayer.hasPermission("system.team")) continue;
                                    onlinePlayer.sendMessage(SkyPvP.PUNISH + "§7Der Spieler §c" + target.getName() + "§7 wurde gemuted§8.");
                                    onlinePlayer.sendMessage(SkyPvP.PUNISH + "§7Grund§8: §c" + ban.getName() + "§8 × §c" + ban.getTimeString());
                                    onlinePlayer.sendMessage(SkyPvP.PUNISH + "§7Von§8: §c" + player.getDisplayName());
                                }
                                return true;
                            }
                            player.sendMessage(SkyPvP.NOPERM);
                            return true;
                        }
                        banManager.mute(ban.getName(), (ban.getTime()));
                        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                            if (!onlinePlayer.hasPermission("system.team")) continue;
                            onlinePlayer.sendMessage(SkyPvP.PUNISH + "§7Der Spieler §c" + target.getName() + "§7 wurde gemuted§8.");
                            onlinePlayer.sendMessage(SkyPvP.PUNISH + "§7Grund§8: §c" + ban.getName() + "§8 × §c" + ban.getTimeString());
                            onlinePlayer.sendMessage(SkyPvP.PUNISH + "§7Von§8: §c" + player.getDisplayName());
                        }
                        return true;
                    }
                    player.sendMessage(SkyPvP.PREFIX + "§7Ein Mutegrund mit dieser ID existiert nicht§8.");
                    return true;
                } catch (NumberFormatException e) {
                    player.sendMessage(SkyPvP.PREFIX + "§7Gebe eine gültige ID an§8.");
                    return true;
                }
            }
            player.sendMessage(SkyPvP.PREFIX + "§7Der Spieler ist nicht online§8.");
            return true;
        }
        return false;
    }
}
