package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.provider.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.haoshoku.nick.api.NickAPI;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class NickCommand extends Command {

    public NickCommand() {
        super("nick");
    }

    Map<UUID, String> groups = new ConcurrentHashMap<>();

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("system.famous")) {
                if (args.length == 0) {
                    player.sendMessage(SkyPvP.PREFIX + "§8/§anick §8<§2reset§8>");
                    player.sendMessage(SkyPvP.PREFIX + "§8/§anick §8<§2Nickname§8>");
                    return true;
                }
                if (args[0].equalsIgnoreCase("reset")) {
                    NickAPI.resetNick(player);
                    NickAPI.resetSkin(player);
                    NickAPI.refreshPlayer(player);
                    player.sendMessage(SkyPvP.PREFIX + "§7Du hast deinen Nicknamen zurückgesetzt§8.");
                    if (player.hasPermission("system.team")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent set " + groups.get(player.getUniqueId()));
                    }
                    if (player.hasPermission("system.owner")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission set * true");
                    }
                } else {
                    String name = args[0];
                    if (name.length() > 16) {
                        player.sendMessage(SkyPvP.PREFIX + "§7Der Name darf nur bis zu 16 Zeichen beinhalten§8.");
                        return false;
                    }
                    if (name.length() < 3) {
                        player.sendMessage(SkyPvP.PREFIX + "§7Der Name muss minimal 3 Zeichen beinhalten§8.");
                        return false;
                    }
                    if (Bukkit.getPlayerExact(name) != null || NickAPI.isNickedName(name)) {
                        player.sendMessage(SkyPvP.PREFIX + "§7Der Nickname ist bereits vergeben§8.");
                        return true;
                    }
                    if (player.hasPermission("system.team")) {
                        groups.put(player.getUniqueId(), LuckPermsProvider.getGroupName(player.getUniqueId()));
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent set phantom");
                    }
                    if (player.hasPermission("system.owner")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission set * true");
                    }
                    NickAPI.nick(player, name);
                    NickAPI.setSkin(player, name);
                    NickAPI.refreshPlayer(player);
                    player.sendMessage(SkyPvP.PREFIX + "§7Du hast deinen Nicknamen zu §a" + name + "§7 gesetzt§8.");
                    return false;
                }
            }
            player.sendMessage(SkyPvP.NOPERM);
            return true;
        }
        return false;
    }
}
