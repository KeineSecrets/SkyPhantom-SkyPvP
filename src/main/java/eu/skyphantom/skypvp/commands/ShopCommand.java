package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.gui.RanginfoGui;
import eu.skyphantom.skypvp.gui.shop.ShopMainGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;

public class ShopCommand extends Command {

    public ShopCommand() {
        super("shop", "", "", Collections.singletonList("pvp-shop"));
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            new ShopMainGui(player).openGUI();
        }
        return false;
    }
}
