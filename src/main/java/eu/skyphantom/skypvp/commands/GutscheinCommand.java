package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.utils.Gutscheine;
import eu.skyphantom.skypvp.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;

public class GutscheinCommand extends Command {

    public GutscheinCommand() {
        super("gutschein", "", "", Collections.singletonList("gutscheine"));
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("*")) {
                if (args.length < 1) {
                    player.sendMessage(SkyPvP.PREFIX + "§8/§agutschein §8<§2Tokens§8> <§2Amount§8>");
                    player.sendMessage(SkyPvP.PREFIX + "§8/§agutschein §8<§2Enderchest§8>");
                    return true;
                }
                if (args[0].equalsIgnoreCase("enderchest")) {
                    Utils.addItem(player, Gutscheine.getEnderchest());
                    player.sendMessage(SkyPvP.PREFIX + "§7Enderchest§8-§7Gutschein erhalten§8.");
                    return true;
                }
                if (args[0].equalsIgnoreCase("tokens") && args.length >= 2) {
                    Double coins = Utils.parseFormattedNumber(args[1]);
                    if (coins >= 1) {
                        Utils.addItem(player, Gutscheine.getTokens(coins));
                        player.sendMessage(SkyPvP.PREFIX + "§7Coin§8-§7Gutschein erhalten§8.");
                        return true;
                    }
                    player.sendMessage(SkyPvP.PREFIX + "§8/§agutschein §8<§2Tokens§8> <§2Amount§8>");
                    player.sendMessage(SkyPvP.PREFIX + "§8/§agutschein §8<§2Enderchest§8>");
                    return true;
                }
                player.sendMessage(SkyPvP.PREFIX + "§8/§agutschein §8<§2Tokens§8> <§2Amount§8>");
                player.sendMessage(SkyPvP.PREFIX + "§8/§agutschein §8<§2Enderchest§8>");
                return true;
            }
            player.sendMessage(SkyPvP.NOPERM);
            return true;
        }
        return false;
    }
}
