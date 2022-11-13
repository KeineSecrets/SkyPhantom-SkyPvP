package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.SkyPvP;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.omg.CORBA.NO_PERMISSION;

public class RangCommand extends Command {

    public RangCommand() {
        super("Rang");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (!player.hasPermission("system.admin")) {
                player.sendMessage(SkyPvP.NOPERM);
                return true;
            }
        }
        return false;
    }
}
