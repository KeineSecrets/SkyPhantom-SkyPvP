package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.SkyPvP;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

public class HatCommand extends Command {

    public HatCommand() {
        super("day", "", "", Collections.singletonList("tag"));
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("system.magician")) {
                if (player.getItemInHand() != null && player.getItemInHand().getType() != Material.AIR) {
                    ItemStack hand = player.getItemInHand();
                    ItemStack helmet = player.getInventory().getHelmet();
                    if (helmet == null) {
                        player.getInventory().setHelmet(hand);
                        player.setItemInHand(new ItemStack(Material.AIR));
                    } else {
                        player.getInventory().setHelmet(hand);
                        player.setItemInHand(helmet);
                    }
                    player.sendMessage(SkyPvP.PREFIX + "§7Viel Spaß mit deinem neuen Hut§8.");
                    return true;
                }
                player.sendMessage(SkyPvP.PREFIX + "§7Halte einen Block in der Hand§8.");
                return true;
            }
            player.sendMessage(SkyPvP.NOPERM);
            return true;
        }
        return false;
    }
}
