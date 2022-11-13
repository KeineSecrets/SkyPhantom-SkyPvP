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

public class StatsResetCommand extends Command {

    public StatsResetCommand() {
        super("statsreset", "", "", Collections.singletonList("resetstats"));
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("system.phantom")) {
                CooldownManager cooldownManager = new CooldownManager(player.getUniqueId());
                if (cooldownManager.isOnCooldown("statsreset")) {
                    long time = cooldownManager.getRemainingTime("statsreset");
                    Utils.sendTitle(player, 2, 15, 10, "§8» §c§lCOOLDOWN§8 «", "§c§n" + TimeUtil.timeToString(time, false).trim() + "§r");
                    Utils.playSound(player, Sound.ENDERDRAGON_HIT, 100.0f, 1.0f);
                    return true;
                }
                player.sendMessage(SkyPvP.PREFIX + "§7Deine Statistiken wurden zurückgesetzt§8.");
                cooldownManager.addCooldown("statsreset", 30, TimeUnit.DAYS);
                return true;
            }
            player.sendMessage(SkyPvP.NOPERM);
            return true;
        }
        return false;
    }
}
