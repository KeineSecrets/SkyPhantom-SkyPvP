package eu.skyphantom.skypvp.events;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.lms.LMS;
import eu.skyphantom.skypvp.lms.events.LMSPlayerQuitEvent;
import eu.skyphantom.skypvp.provider.TablistProvider;
import eu.skyphantom.skypvp.utils.scoreboard.ScoreboardHelper;
import eu.skyphantom.skypvp.utils.time.OnlineTime;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        event.setQuitMessage(null);

        ScoreboardHelper.removeScoreboard(player);
        Bukkit.getScheduler().runTaskLater(SkyPvP.getInstance(), () -> Bukkit.getOnlinePlayers().forEach(TablistProvider::setPrefix), 20L);
        if (LMS.getInstance().getLMSHandler().getPlayers().contains(player)) {
            Bukkit.getPluginManager().callEvent(new LMSPlayerQuitEvent(player));
        }
    }

}
