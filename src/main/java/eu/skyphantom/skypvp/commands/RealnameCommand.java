package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.SkyPvP;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.haoshoku.nick.api.NickAPI;

import java.util.Collections;

public class RealnameCommand extends Command {

    public RealnameCommand() {
        super("realname", "", "", Collections.singletonList("rn"));
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("system.magician")) {
                if (args.length > 0) {
                    String nick = args[0];
                    if (NickAPI.isNickedName(nick)) {
                        Player nicker = NickAPI.getPlayerOfNickedName(nick);
                        if (nicker != null) {
                            player.sendMessage(SkyPvP.PREFIX + "§a" + nick + "§7 ist §a" + NickAPI.getOriginalName(nicker));
                            return true;
                        }
                        player.sendMessage(SkyPvP.PREFIX + "§7Diesen Nickname gibt es nicht§8.");
                        return true;
                    }
                    player.sendMessage(SkyPvP.PREFIX + "§7Diesen Nickname gibt es nicht§8.");
                    return true;
                }
                player.sendMessage(SkyPvP.PREFIX + "§8/§arealname §8<§2Spieler§8>");
                return true;
            }
            player.sendMessage(SkyPvP.NOPERM);
            return true;
        }
        return false;
    }
}
