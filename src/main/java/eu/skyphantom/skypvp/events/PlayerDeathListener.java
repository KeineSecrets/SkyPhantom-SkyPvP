package eu.skyphantom.skypvp.events;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.provider.KitProvider;
import eu.skyphantom.skypvp.provider.LocationProvider;
import eu.skyphantom.skypvp.provider.StatsProvider;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * JavaDoc this file!
 * Created: 29/12/2022
 *
 * @author WeLoveSpigotPlugins (welovespigotplugins@gmail.com)
 */
public class PlayerDeathListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerDeath(final PlayerDeathEvent event) {
        Player player = event.getEntity();
        StatsProvider statsProvider = new StatsProvider(player.getUniqueId());
        event.getDrops().clear();
        player.spigot().respawn();
        LocationProvider locationProvider = new LocationProvider("spawn");
        player.teleport(locationProvider.get());
        if (statsProvider.getRespawnKitLevel() == 0) statsProvider.setRespawnKitLevel(1);
        KitProvider.getRespawnKit(player, statsProvider.getRespawnKitLevel());
        player.sendMessage(SkyPvP.PREFIX + "§7Du hast das Respawn Kit §8(§eLevel " + statsProvider.getRespawnKitLevel() + "§8) §7erhalten§8.");
        player.sendMessage(SkyPvP.PREFIX + "§7Zum upgraden§8, §7gebe §8/§erespawnkit §7im Chat ein§8.");
    }

}
