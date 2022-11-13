package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.utils.Utils;
import eu.skyphantom.skypvp.utils.cooldowns.CooldownManager;
import eu.skyphantom.skypvp.utils.time.TimeUtil;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class StackCommand extends Command {

    public StackCommand() {
        super("stack");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("system.magician")) {
                CooldownManager cooldownManager = new CooldownManager(player.getUniqueId());
                if (cooldownManager.isOnCooldown("stack")) {
                    long time = cooldownManager.getRemainingTime("stack");
                    Utils.sendTitle(player, 2, 15, 10, "§8» §c§lCOOLDOWN§8 «", "§c§n" + TimeUtil.timeToString(time, false).trim() + "§r");
                    Utils.playSound(player, Sound.ENDERDRAGON_HIT, 100.0f, 1.0f);
                    return true;
                }
                if (player.getItemInHand() != null && player.getItemInHand().getType() != Material.AIR) {
                    cooldownManager.addCooldown("stack", 5, TimeUnit.MINUTES);
                    ItemStack hand = player.getItemInHand();
                    ItemStack newHand = Utils.stackItems(player.getInventory(), hand);
                    player.setItemInHand(newHand);
                    player.updateInventory();
                    player.sendMessage(SkyPvP.PREFIX + "§7Items wurden gestackt§8.");
                    return true;
                }
                player.sendMessage(SkyPvP.PREFIX + "§7Halte ein Item in der Hand§8.");
                return true;
            }
            player.sendMessage(SkyPvP.NOPERM);
            return true;
        }
        return false;
    }
}
