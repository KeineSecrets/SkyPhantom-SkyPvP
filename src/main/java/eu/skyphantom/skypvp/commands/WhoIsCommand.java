package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.provider.LuckPermsProvider;
import eu.skyphantom.skypvp.utils.GeoUtils;
import eu.skyphantom.skypvp.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import xyz.haoshoku.nick.api.NickAPI;

public class WhoIsCommand extends Command {

    public WhoIsCommand() {
        super("whois");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("system.owner")) {
                if (args.length < 1) {
                    player.sendMessage(SkyPvP.PREFIX + "§8/§awhois §8<§2Spieler§8>");
                    return true;
                }
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    GeoUtils geoUtils = new GeoUtils(target.getAddress().getAddress().toString());
                    player.sendMessage(SkyPvP.PREFIX + "§7WhoIs von §a" + target.getName());
                    player.sendMessage("§r");
                    player.sendMessage("§8▎ §a§lNICK§8 ▪ §7" + (NickAPI.isNicked(target) ? NickAPI.getName(target) : target.getName()));
                    player.sendMessage("§8▎ §a§lRANG§8 ▪ §7" + Utils.capitalizeFirstLetter(LuckPermsProvider.getGroupName(target.getUniqueId())));
                    player.sendMessage("§8▎ §a§lUUID§8 ▪ §7" + target.getUniqueId().toString());
                    player.sendMessage("§8▎ §a§lLEBEN§8 ▪ §7" + target.getHealth() + "§8 / §7" + target.getHealthScale());
                    player.sendMessage("§8▎ §a§lESSEN§8 ▪ §7" + target.getFoodLevel() + "§8 / §720.0 §8( +§7" + target.getSaturation() + "§7 Sättigung§8 )");
                    player.sendMessage("§8▎ §a§lEXP§8 ▪ §7" + Utils.getTotalExperience(target) + "§8 (§7 Level " + target.getLevel() + "§8 )");
                    player.sendMessage("§8▎ §a§lLOCATION§8 ▪ §7" + target.getLocation().getWorld().getName() + "§8, §7" + target.getLocation().getBlockX() + "§8, §7" + target.getLocation().getBlockY() + "§8, §7" + target.getLocation().getBlockZ());
                    player.sendMessage("§8▎ §a§lIP§8 ▪ §7" + target.getAddress().getAddress().toString().replaceAll("/", ""));
                    player.sendMessage("§8▎ §a§lSTADT§8 ▪ §7" + geoUtils.getCITY());
                    player.sendMessage("§8▎ §a§lVPN§8 ▪ §r" + (geoUtils.getPROXY().equalsIgnoreCase("true") ? "§a§lTRUE" : "§c§lFALSE"));
                    player.sendMessage("§8▎ §a§lGAMEMODE§8 ▪ §7" + target.getGameMode().toString().toUpperCase());
                    player.sendMessage("§8▎ §a§lFLY§8 ▪ §7" + (target.getAllowFlight() ? "§a§lTRUE" : "§c§lFALSE"));
                    player.sendMessage("§r");
                    player.sendMessage(SkyPvP.PREFIX + "§7WhoIs von §a" + target.getName());
                    return true;
                }
                player.sendMessage(SkyPvP.PREFIX + "§7Der Spieler ist nicht online§8.");
                return true;
            }
            player.sendMessage(SkyPvP.NOPERM);
            return true;
        } else {
            ConsoleCommandSender player = (ConsoleCommandSender) commandSender;
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                GeoUtils geoUtils = new GeoUtils(target.getAddress().getAddress().toString());
                player.sendMessage(SkyPvP.PREFIX + "§7WhoIs von §a" + target.getName());
                player.sendMessage("§r");
                player.sendMessage("§8▎ §a§lNICK§8 ▪ §7" + (NickAPI.isNicked(target) ? NickAPI.getName(target) : target.getName()));
                player.sendMessage("§8▎ §a§lRANG§8 ▪ §7" + Utils.capitalizeFirstLetter(LuckPermsProvider.getGroupName(target.getUniqueId())));
                player.sendMessage("§8▎ §a§lUUID§8 ▪ §7" + target.getUniqueId().toString());
                player.sendMessage("§8▎ §a§lLEBEN§8 ▪ §7" + target.getHealth() + "§8 / §7" + target.getHealthScale());
                player.sendMessage("§8▎ §a§lESSEN§8 ▪ §7" + target.getFoodLevel() + "§8 / §720.0 §8( +§7" + target.getSaturation() + "§7 Sättigung§8 )");
                player.sendMessage("§8▎ §a§lEXP§8 ▪ §7" + Utils.getTotalExperience(target) + "§8 (§7 Level " + target.getLevel() + "§8 )");
                player.sendMessage("§8▎ §a§lLOCATION§8 ▪ §7" + target.getLocation().getWorld().getName() + "§8, §7" + target.getLocation().getBlockX() + "§8, §7" + target.getLocation().getBlockY() + "§8, §7" + target.getLocation().getBlockZ());
                player.sendMessage("§8▎ §a§lIP§8 ▪ §7" + target.getAddress().getAddress().toString().replaceAll("/", ""));
                player.sendMessage("§8▎ §a§lSTADT§8 ▪ §7" + geoUtils.getCITY());
                player.sendMessage("§8▎ §a§lVPN§8 ▪ §r" + (geoUtils.getPROXY().equalsIgnoreCase("true") ? "§a§lTRUE" : "§c§lFALSE"));
                player.sendMessage("§8▎ §a§lGAMEMODE§8 ▪ §7" + target.getGameMode().toString().toUpperCase());
                player.sendMessage("§8▎ §a§lFLY§8 ▪ §7" + (target.getAllowFlight() ? "§a§lTRUE" : "§c§lFALSE"));
                player.sendMessage("§r");
                player.sendMessage(SkyPvP.PREFIX + "§7WhoIs von §a" + target.getName());
                return true;
            }
            player.sendMessage(SkyPvP.PREFIX + "§7Der Spieler ist nicht online§8.");
            return true;
        }
    }
}
