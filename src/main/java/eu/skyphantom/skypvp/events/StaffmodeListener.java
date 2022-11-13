package eu.skyphantom.skypvp.events;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.provider.StaffmodeProvider;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class StaffmodeListener implements Listener {

    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (StaffmodeProvider.get(player)) {
            event.setCancelled(true);
            if (!player.getItemInHand().hasItemMeta() && !player.getItemInHand().getItemMeta().hasDisplayName()) return;
            if (player.getItemInHand().getItemMeta().getDisplayName().contains("TP")) {
                String name = player.getItemInHand().getItemMeta().getDisplayName().replaceAll("§8» §7TP§8: §a", "");
                Player target = Bukkit.getPlayer(name);
                if (target == null) {
                    player.sendMessage(SkyPvP.PREFIX + "§7Der Spieler ist nicht mehr online§8.");
                    return;
                }
                player.teleport(target);
                player.sendMessage(SkyPvP.PREFIX + "§7Du wurdest zu §a" + target.getName() + "§7 teleportiert§8.");
                return;
            }
            if (player.getItemInHand().getItemMeta().getDisplayName().contains("Staffmode verlassen")) {
                player.chat("/staffmode");
                return;
            }
        }
    }

}
