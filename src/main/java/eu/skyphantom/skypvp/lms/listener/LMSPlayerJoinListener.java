package eu.skyphantom.skypvp.lms.listener;

import eu.skyphantom.skypvp.lms.LMS;
import eu.skyphantom.skypvp.lms.events.LMSPlayerJoinEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LMSPlayerJoinListener implements Listener {

    @EventHandler
    public void onLMSPlayerJoin(final LMSPlayerJoinEvent event) {
        final Player player = event.getPlayer();
    }

}
