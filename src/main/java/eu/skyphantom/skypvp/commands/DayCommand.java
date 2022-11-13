package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.SkyPvP;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;

public class DayCommand extends Command {

    public DayCommand() {
        super("day", "", "", Collections.singletonList("tag"));
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("system.magician")) {
                Bukkit.getWorlds().forEach(world -> world.setFullTime(1000L));
                player.sendMessage(SkyPvP.PREFIX + "§7Du hast die Zeit zu §aTag§7 gesetzt§8.");
                Bukkit.broadcastMessage(SkyPvP.PREFIX + "§a" + player.getName() + "§7 hat die Zeit zu §aTag§7 gesetzt§8.");
                return true;
            }
            player.sendMessage(SkyPvP.NOPERM);
            return true;
        }
        return false;
    }
}
