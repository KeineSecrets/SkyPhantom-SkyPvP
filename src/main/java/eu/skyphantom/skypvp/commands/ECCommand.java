package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.gui.BlocksGui;
import eu.skyphantom.skypvp.provider.EnderChestProvider;
import eu.skyphantom.skypvp.utils.UUIDFetcher;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.UUID;

public class ECCommand extends Command {

    public ECCommand() {
        super("ec", "", "", Collections.singletonList("enderchest"));
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("system.team")) {
                if (args.length != 0) {
                    UUID uuid = UUIDFetcher.getUUID(args[0]);
                    EnderChestProvider enderChestProvider = new EnderChestProvider(uuid);
                    enderChestProvider.openEnderchest(player, 1);
                    return true;
                }
                EnderChestProvider enderChestProvider = new EnderChestProvider(player.getUniqueId());
                enderChestProvider.openEnderchest(player, 1);
                return true;
            }
            if (player.hasPermission("system.shark")) {
                EnderChestProvider enderChestProvider = new EnderChestProvider(player.getUniqueId());
                enderChestProvider.openEnderchest(player, 1);
                return true;
            }
            if (player.hasPermission("system.command.ec")) {
                EnderChestProvider enderChestProvider = new EnderChestProvider(player.getUniqueId());
                enderChestProvider.openEnderchest(player, 1);
                return true;
            }
            player.sendMessage(SkyPvP.NOPERM);
            return true;
        }
        return false;
    }
}
