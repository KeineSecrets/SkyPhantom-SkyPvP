package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.battlepass.BattlepassGui;
import eu.skyphantom.skypvp.gui.BlocksGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;

public class BattlepassCommand extends Command {

    public BattlepassCommand() {
        super("battlepass", "", "", Collections.singletonList("bp"));
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            new BattlepassGui(player).openGUI();
            return true;
        }
        return false;
    }
}
