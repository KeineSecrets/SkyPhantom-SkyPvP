package eu.skyphantom.skypvp.events;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.provider.EnderChestProvider;
import eu.skyphantom.skypvp.provider.StatsProvider;
import eu.skyphantom.skypvp.utils.Gutscheine;
import eu.skyphantom.skypvp.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class GutscheinListener implements Listener {

    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        if (event.getPlayer().getItemInHand() != null && event.getPlayer().getItemInHand().getType() == Material.ENDER_CHEST) {
            if (event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§8▎ §a§lGUTSCHEIN§8 ▪ +§a1 §2Enderchest§8-§aSeite")) {
                event.setCancelled(true);
                EnderChestProvider enderChestProvider = new EnderChestProvider(player.getUniqueId());
                enderChestProvider.addPage(player);
            }
        } else if (event.getPlayer().getItemInHand() != null && event.getPlayer().getItemInHand().getType() == Material.DOUBLE_PLANT) {
            if (event.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains("§8▎ §a§lGUTSCHEIN§8 ▪ §6")) {
                event.setCancelled(true);
                double value = Gutscheine.getTokens(event.getPlayer().getItemInHand());
                Utils.removeItemFromHand(player, event.getPlayer().getItemInHand().getAmount());
                StatsProvider provider = new StatsProvider(player.getUniqueId());
                provider.addCoins(value);
                player.sendMessage(SkyPvP.PREFIX + "§7Dir wurden §6" + Utils.formatDouble(value) + " Tokens §7gutgeschrieben§8.");
            }
        }
    }

}
