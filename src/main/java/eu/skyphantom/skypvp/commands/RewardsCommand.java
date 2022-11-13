package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.gui.RanginfoGui;
import eu.skyphantom.skypvp.gui.RewardsGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;

public class RewardsCommand extends Command {

    public RewardsCommand() {
        super("rewards", "", "", Collections.singletonList("reward"));
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            new RewardsGui(player).openGUI();
        }
        return false;
    }
}
