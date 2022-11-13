package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.provider.StaffmodeProvider;
import eu.skyphantom.skypvp.provider.TablistProvider;
import eu.skyphantom.skypvp.provider.VanishProvider;
import eu.skyphantom.skypvp.utils.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StaffmodeCommand extends Command {

    public StaffmodeCommand() {
        super("staffmode");
    }

    Map<UUID, Map<Integer, ItemStack>> inv = new HashMap<UUID, Map<Integer, ItemStack>>();

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("system.team")) {
                Bukkit.getOnlinePlayers().forEach(TablistProvider::setPrefix);
                if (StaffmodeProvider.get(player)) {
                    player.getInventory().clear();
                    VanishProvider.remove(player);
                    StaffmodeProvider.remove(player);
                    for (int i : inv.get(player.getUniqueId()).keySet()) {
                        ItemStack item = inv.get(player.getUniqueId()).get(i);
                        if (item == null) continue;
                        player.getInventory().setItem(i, item);
                    }
                    player.updateInventory();
                    player.sendMessage(SkyPvP.PREFIX + "§7Staffmode §cdeaktiviert§8.");
                    return true;
                }
                if (args.length > 0) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        player.sendMessage(SkyPvP.PREFIX + "§8/§astaffmode §8<§2Spieler§8>");
                        return true;
                    }
                    VanishProvider.add(player);
                    StaffmodeProvider.add(player);
                    Map<Integer, ItemStack> temp = new HashMap<>();
                    for (int i = 0; i < player.getInventory().getContents().length; i++) {
                        temp.put(i, player.getInventory().getContents()[i]);
                    }
                    inv.put(player.getUniqueId(), temp);
                    player.getInventory().clear();

                    player.getInventory().setItem(0, new ItemBuilder(Material.HOPPER_MINECART).setName("§8» §7TP§8: §a" + target.getName()));
                    player.getInventory().setItem(2, new ItemBuilder(Material.COMMAND_MINECART).setName("§8» §7Freeze§8: §a" + target.getName()));
                    player.getInventory().setItem(3, new ItemBuilder(Material.POWERED_MINECART).setName("§8» §7Kick§8: §a" + target.getName()));
                    player.getInventory().setItem(4, new ItemBuilder(Material.COMMAND).setName("§8» §aTrollmenü §8( §c§oSoon§8 )"));
                    player.getInventory().setItem(5, new ItemBuilder(Material.EXPLOSIVE_MINECART).setName("§8» §7Ban§8: §a" + target.getName()));
                    player.getInventory().setItem(6, new ItemBuilder(Material.STORAGE_MINECART).setName("§8» §7Mute§8: §a" + target.getName()));
                    player.getInventory().setItem(8, new ItemBuilder(Material.MINECART).setName("§8» §cStaffmode verlassen"));

                    player.updateInventory();
                    player.sendMessage(SkyPvP.PREFIX + "§7Staffmode §aaktiviert§8.");
                }
                player.sendMessage(SkyPvP.PREFIX + "§8/§astaffmode §8<§2Spieler§8>");
                return true;
            }
            player.sendMessage(SkyPvP.NOPERM);
            return true;
        }
        return false;
    }
}
