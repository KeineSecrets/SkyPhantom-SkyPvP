package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.utils.Utils;
import eu.skyphantom.skypvp.utils.cooldowns.CooldownManager;
import eu.skyphantom.skypvp.utils.time.TimeUtil;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class FixCommand extends Command {

    public FixCommand() {
        super("fix", "", "", Collections.singletonList("repair"));
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("system.fage")) {
                CooldownManager cooldownManager = new CooldownManager(player.getUniqueId());
                if (cooldownManager.isOnCooldown("command_fix")) {
                    long time = cooldownManager.getRemainingTime("command_fix");
                    Utils.sendTitle(player, 2, 15, 10, "§8» §c§lCOOLDOWN§8 «", "§c§n" + TimeUtil.timeToString(time, false).trim() + "§r");
                    Utils.playSound(player, Sound.ENDERDRAGON_HIT, 100.0f, 1.0f);
                    return true;
                }
                ItemStack helmet = player.getItemInHand();
                if (helmet == null) {
                    player.sendMessage(SkyPvP.PREFIX + "§7Du hast keine Rüstung an§8.");
                    return true;
                }
                helmet.setDurability((short) 0);
                player.sendMessage(SkyPvP.PREFIX + "§7Das Item wurde repariert§8.");
                cooldownManager.addCooldown("command_fix", 30, TimeUnit.SECONDS);
                return true;
            }
            player.sendMessage(SkyPvP.NOPERM);
            return true;
        }
        return false;
    }
}
