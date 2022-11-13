package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.utils.Utils;
import eu.skyphantom.skypvp.utils.cooldowns.CooldownManager;
import eu.skyphantom.skypvp.utils.items.ItemBuilder;
import eu.skyphantom.skypvp.utils.time.TimeUtil;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class EnchanterCommand extends Command {

    public EnchanterCommand() {
        super("enchanter");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("system.phantom")) {
                player.openEnchanting(player.getLocation(), true);
                player.sendMessage(SkyPvP.PREFIX + "§7Du hast einen Zaubertisch geöffnet§8.");
                player.getOpenInventory().setItem(1, new ItemBuilder(Material.INK_SACK).setDataId(4).amount(64));
                return true;
            }
            player.sendMessage(SkyPvP.NOPERM);
            return true;
        }
        return false;
    }
}
