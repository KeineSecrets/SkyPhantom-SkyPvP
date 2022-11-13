package eu.skyphantom.skypvp.gui.warps;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.hooks.Gui;
import eu.skyphantom.skypvp.provider.LocationProvider;
import eu.skyphantom.skypvp.provider.WarpProvider;
import eu.skyphantom.skypvp.utils.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class WarpMainGui extends Gui {

    public WarpMainGui(@NotNull Player player) {
        super("§8▎ §a§lWARPS§8 ▪ §7Menü", 6, player);

        fill(new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15).setName("§8-/-§r"));
        fillBorders(new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§8-/-§r"));

        getInventory().setItem(21, new ItemBuilder(Material.CARROT_ITEM).setName("§8▎ §a§lWARP§8 ▪ §7Farmwelt §8(§7§oOverworld§8)").lore("§8▪ §7Teleportiere dich zur Farmwelt §8(§7§oOverworld§8)"));
        getInventory().setItem(22, new ItemBuilder(Material.NETHER_STAR).setName("§8▎ §a§lWARP§8 ▪ §7Spawn").lore("§8▪ §7Teleportiere dich zum Spawn"));
        getInventory().setItem(23, new ItemBuilder(Material.NETHER_STALK).setName("§8▎ §a§lWARP§8 ▪ §7Farmwelt §8(§7§oNether§8)").lore("§8▪ §7Teleportiere dich zur Farmwelt §8(§7§oNether§8)"));
        getInventory().setItem(29, new ItemBuilder(Material.GRASS).setName("§8▎ §a§lWARP§8 ▪ §7Plots").lore("§8▪ §7Teleportiere dich zur Farmwelt §8(§7§oNether§8)"));
        getInventory().setItem(31, new ItemBuilder(Material.IRON_CHESTPLATE).setName("§8▎ §a§lWARP§8 ▪ §7Arena").lore("§8▪ §7Teleportiere dich zur Arena"));
        getInventory().setItem(33, new ItemBuilder(Material.GOLD_NUGGET).setName("§8▎ §a§lWARP§8 ▪ §7Casino §8(§c§oSoon§8)").lore("§8▪ §cDieser Warp ist noch nicht verfügbar§8."));

        getInventory().setItem(49, new ItemBuilder(Material.EMERALD).setName("§8▎ §a§lWARPS§8 ▪ §7Andere").lore("§8▪ §7Sehe alle anderen verfügbaren Warps"));
    }

    @Override
    public void onClick(Player player, ClickType clickType, int clickedSlot, ItemStack clickedItem) {
        if (clickedSlot == 21) {
            WarpProvider warpProvider = new WarpProvider("Farmwelt1");
            if (warpProvider.exists()) {
                player.teleport(warpProvider.get());
                player.sendMessage(SkyPvP.PREFIX + "§7Du wurdest zur §8'§7Farmwelt §8(§7§oOverworld§8)'§7 teleportiert§8.");
                return;
            }
            player.sendMessage(SkyPvP.PREFIX + "§7Der Warp wurde noch nicht gesetzt§8.");
            return;
        }
        if (clickedSlot == 22) {
            LocationProvider locationProvider = new LocationProvider("spawn");
            if (locationProvider.exists()) {
                player.teleport(locationProvider.get());
                player.sendMessage(SkyPvP.PREFIX + "§7Du wurdest zum Spawn teleportiert§8.");
                return;
            }
            player.sendMessage(SkyPvP.PREFIX + "§7Der Warp wurde noch nicht gesetzt§8.");
            return;
        }
        if (clickedSlot == 23) {
            WarpProvider warpProvider = new WarpProvider("Farmwelt2");
            if (warpProvider.exists()) {
                player.teleport(warpProvider.get());
                player.sendMessage(SkyPvP.PREFIX + "§7Du wurdest zur §8'§7Farmwelt §8(§7§oNether§8)'§7 teleportiert§8.");
                return;
            }
            player.sendMessage(SkyPvP.PREFIX + "§7Der Warp wurde noch nicht gesetzt§8.");
            return;
        }
        if (clickedSlot == 29) {
            WarpProvider warpProvider = new WarpProvider("Plots");
            if (warpProvider.exists()) {
                player.teleport(warpProvider.get());
                player.sendMessage(SkyPvP.PREFIX + "§7Du wurdest zu den §8'§7Plots§8'§7 teleportiert§8.");
                return;
            }
            player.sendMessage(SkyPvP.PREFIX + "§7Der Warp wurde noch nicht gesetzt§8.");
            return;
        }
        if (clickedSlot == 31) {
            WarpProvider warpProvider = new WarpProvider("Arena");
            if (warpProvider.exists()) {
                player.teleport(warpProvider.get());
                player.sendMessage(SkyPvP.PREFIX + "§7Du wurdest zur §8'§7Arena§8'§7 teleportiert§8.");
                return;
            }
            player.sendMessage(SkyPvP.PREFIX + "§7Der Warp wurde noch nicht gesetzt§8.");
            return;
        }
        if (clickedSlot == 33) {
            player.sendMessage(SkyPvP.PREFIX + "§7Der Warp steht derzeit nicht zur Verfügung§8.");
            return;
        }
        if (clickedSlot == 49) {
            player.closeInventory();
            Bukkit.getScheduler().runTaskLater(SkyPvP.getInstance(), () -> new WarpListGui(player).openGUI(), 3L);
            return;
        }
    }

    @Override
    public void onClose(Player player, InventoryView inventoryView) {

    }
}
