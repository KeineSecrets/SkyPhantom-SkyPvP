package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.gui.RanginfoGui;
import eu.skyphantom.skypvp.utils.Utils;
import eu.skyphantom.skypvp.utils.cooldowns.CooldownManager;
import eu.skyphantom.skypvp.utils.time.TimeUtil;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.TimeUnit;

public class FixArmorCommand extends Command {

    public FixArmorCommand() {
        super("fixarmor");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("system.phantom")) {
                CooldownManager cooldownManager = new CooldownManager(player.getUniqueId());
                if (cooldownManager.isOnCooldown("command_fixarmor")) {
                    long time = cooldownManager.getRemainingTime("command_fixarmor");
                    Utils.sendTitle(player, 2, 15, 10, "§8» §c§lCOOLDOWN§8 «", "§c§n" + TimeUtil.timeToString(time, false).trim() + "§r");
                    Utils.playSound(player, Sound.ENDERDRAGON_HIT, 100.0f, 1.0f);
                    return true;
                }
                ItemStack helmet = player.getInventory().getHelmet();
                ItemStack chestplate = player.getInventory().getChestplate();
                ItemStack leggings = player.getInventory().getLeggings();
                ItemStack boots = player.getInventory().getBoots();
                if (helmet == null && chestplate == null && leggings == null && boots == null) {
                    player.sendMessage(SkyPvP.PREFIX + "§7Du hast keine Rüstung an§8.");
                    return true;
                }
                if (helmet != null) {
                    helmet.setDurability((short) 0);
                }
                if (chestplate != null) {
                    chestplate.setDurability((short) 0);
                }
                if (leggings != null) {
                    leggings.setDurability((short) 0);
                }
                if (boots != null) {
                    boots.setDurability((short) 0);
                }
                player.sendMessage(SkyPvP.PREFIX + "§7Deine Rüstung wurde repariert§8.");
                cooldownManager.addCooldown("command_fixarmor", 5, TimeUnit.MINUTES);
                return true;
            }
            player.sendMessage(SkyPvP.NOPERM);
            return true;
        }
        return false;
    }
}
