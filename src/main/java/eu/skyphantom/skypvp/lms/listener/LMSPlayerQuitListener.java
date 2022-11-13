package eu.skyphantom.skypvp.lms.listener;

import eu.skyphantom.skypvp.lms.LMS;
import eu.skyphantom.skypvp.lms.State;
import eu.skyphantom.skypvp.lms.events.LMSPlayerJoinEvent;
import eu.skyphantom.skypvp.lms.events.LMSPlayerQuitEvent;
import eu.skyphantom.skypvp.provider.LocationProvider;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class LMSPlayerQuitListener implements Listener {

    @EventHandler
    public void onLMSPlayerQuit(final LMSPlayerQuitEvent event) {
        final Player player = event.getPlayer();
        try {
            player.teleport(new LocationProvider("spawn").get());
        } catch (Exception ignored) { }
        if (LMS.getInstance().getState() != State.LOBBY && LMS.getInstance().getState() != State.NONE) {
            player.getInventory().clear();
            for (int i : LMS.inv.get(player.getUniqueId()).keySet()) {
                ItemStack item = LMS.inv.get(player.getUniqueId()).get(i);
                if (item == null) continue;
                player.getInventory().setItem(i, item);
            }
            player.updateInventory();
        }
    }

}
