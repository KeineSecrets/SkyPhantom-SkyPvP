package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.provider.WarpProvider;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;

public class SetWarpCommand extends Command {

    public SetWarpCommand() {
        super("setwarp", "", "", Collections.singletonList("setwarps"));
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("system.owner")) {
                if (args.length < 2) {
                    player.sendMessage(SkyPvP.PREFIX + "§8/§a" + s + "§8 <§2set§8> <§2Name§8>");
                    player.sendMessage(SkyPvP.PREFIX + "§8/§a" + s + "§8 <§2tp§8> <§2Name§8>");
                    player.sendMessage(SkyPvP.PREFIX + "§8/§a" + s + "§8 <§2delete§8> <§2Name§8>");
                    return true;
                }
                if (args[0].equalsIgnoreCase("set")) {
                    WarpProvider locationProvider = new WarpProvider(args[1].trim());
                    locationProvider.set(player.getLocation());
                    player.sendMessage(SkyPvP.PREFIX + "§7Location gesetzt§8.");
                    return true;
                }
                if (args[0].equalsIgnoreCase("delete")) {
                    WarpProvider locationProvider = new WarpProvider(args[1].trim());
                    locationProvider.set(null);
                    player.sendMessage(SkyPvP.PREFIX + "§7Location gelöscht§8.");
                    return true;
                }
                if (args[0].equalsIgnoreCase("tp")) {
                    WarpProvider locationProvider = new WarpProvider(args[1].trim());
                    player.teleport(locationProvider.get());
                    player.sendMessage(SkyPvP.PREFIX + "§7Du wurdest zur Location teleportiert§8.");
                    return true;
                }
                player.sendMessage(SkyPvP.PREFIX + "§8/§a" + s + "§8 <§2set§8> <§2Name§8>");
                player.sendMessage(SkyPvP.PREFIX + "§8/§a" + s + "§8 <§2tp§8> <§2Name§8>");
                player.sendMessage(SkyPvP.PREFIX + "§8/§a" + s + "§8 <§2delete§8> <§2Name§8>");
                return true;
            }
            player.sendMessage(SkyPvP.NOPERM);
            return true;
        }
        return false;
    }
}
