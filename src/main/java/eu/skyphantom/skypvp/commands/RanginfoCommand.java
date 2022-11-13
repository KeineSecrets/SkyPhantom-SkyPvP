package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.gui.RanginfoGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RanginfoCommand extends Command {

    public RanginfoCommand() {
        super("ranginfo");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            new RanginfoGui(player).openGUI();
        }
        return false;
    }
}
