package eu.skyphantom.skypvp.lms.listener;

import eu.skyphantom.skypvp.lms.LMS;
import eu.skyphantom.skypvp.lms.LMSHandler;
import eu.skyphantom.skypvp.lms.State;
import eu.skyphantom.skypvp.lms.events.LMSPlayerQuitEvent;
import eu.skyphantom.skypvp.provider.LocationProvider;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LMSPlayerKillListener implements Listener {

    @EventHandler
    public void onEntityKill(final PlayerDeathEvent event) {

        final Player player = event.getEntity().getPlayer();
        final Player attacker = event.getEntity().getKiller();
        final LMSHandler lmsHandler = LMS.getInstance().getLMSHandler();

        if (LMS.getInstance().getState() == State.INGAME) {
            lmsHandler.getPlayerKills().remove(player);
            lmsHandler.removePlayer(player);

            player.sendMessage(LMS.PREFIX + "§7Du bist gestorben und somit ausgeschieden§8.");
            player.sendMessage(LMS.PREFIX + "§7Du kannst trotzdem mit §8/§9lms spectate§7 zuschauen§8.");
            player.playSound(player.getLocation(), Sound.SKELETON_DEATH, 50, 18.4F);
            Bukkit.getPluginManager().callEvent(new LMSPlayerQuitEvent(player));
            if (attacker != null)  {
                for(Player all : lmsHandler.getPlayers()) {
                    all.sendMessage(LMS.PREFIX + "§9" + attacker.getName() + " §7hat §3" + player.getName() + " §7ausgeschaltet§8.");
                }
                lmsHandler.getPlayerKills().put(attacker, (lmsHandler.getPlayerKills().get(attacker)+1));
                attacker.playSound(attacker.getLocation(), Sound.SUCCESSFUL_HIT, 50, 18.4F);
            }
        }
    }
}
