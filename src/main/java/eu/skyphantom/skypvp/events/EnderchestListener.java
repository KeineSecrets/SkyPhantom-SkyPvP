package eu.skyphantom.skypvp.events;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.provider.EnderChestProvider;
import eu.skyphantom.skypvp.utils.UUIDFetcher;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.UUID;

public class EnderchestListener implements Listener {

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        final Player player = (Player) event.getWhoClicked();
        if (event.getClickedInventory() != null && event.getClickedInventory().getTitle().contains("§8▎ §a§lEC§8 ▪ #§7")) {
            if (event.getSlot() >= 45) event.setCancelled(true);
            else event.setCancelled(EnderChestProvider.cancelClick(event.getClickedInventory(), player));
            int page = Integer.parseInt(event.getClickedInventory().getTitle().replace("§8▎ §a§lEC§8 ▪ #§7", ""));
            UUID owner = UUIDFetcher.getUUID(event.getClickedInventory().getItem(49).getItemMeta().getLore().get(0).replaceAll("§8▪ §7Inhaber§8: §a", "").replaceAll("§aDu", player.getName()));
            EnderChestProvider enderChestProvider = new EnderChestProvider(owner);
            if (event.getSlot() == 46) {
                if (page == 1) {
                    player.sendMessage(SkyPvP.PREFIX + "§7Du bist auf der ersten Seite§8.");
                    return;
                }
                enderChestProvider.openEnderchest(player, (page - 1));
                return;
            }
            if (event.getSlot() == 52) {
                enderChestProvider.openEnderchest(player, (page + 1));
                return;
            }
        }
    }

    @EventHandler
    public void onInventoryClose(final InventoryCloseEvent event) {
        if (!(event.getPlayer() instanceof Player)) return;
        final Player player = (Player) event.getPlayer();
        if (event.getView() != null && event.getView().getTitle().contains("§8▎ §a§lEC§8 ▪ #§7")) {
            EnderChestProvider.onSave(event.getInventory(), (Player) event.getPlayer());
        }
    }

}
