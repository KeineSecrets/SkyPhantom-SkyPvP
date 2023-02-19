package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.gui.KitGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;

public class KitCommand extends Command {

    public KitCommand() {
        super("kit", "", "", Collections.singletonList("kits"));
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            new KitGui(player).openGUI();
        }
        return false;
    }
}
