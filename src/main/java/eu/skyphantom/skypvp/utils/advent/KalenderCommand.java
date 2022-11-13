package eu.skyphantom.skypvp.utils.advent;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class KalenderCommand extends Command {

    public KalenderCommand() {
        super("advent", "", "", Arrays.asList("kalender", "adventskalender", "calendar", "christmascalendar"));
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        new KalenderGui(((Player)commandSender)).openGUI();
        return false;
    }
}
