package eu.skyphantom.skypvp.lms.listener;

import eu.skyphantom.skypvp.lms.LMS;
import eu.skyphantom.skypvp.lms.State;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class LMSEntityDamageByEntityListener implements Listener {

    @EventHandler
    public void onEntityDamageByEntity(final EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            final Player attacker = (Player) event.getDamager();
            final Player victim = (Player) event.getEntity();
            if (LMSStartListener.schutzzeit && (LMS.getInstance().getLMSHandler().getPlayers().contains(victim) && LMS.getInstance().getLMSHandler().getPlayers().contains(attacker))) {
                event.setCancelled(true);
                attacker.sendMessage(LMS.PREFIX + "§7Die Schutzzeit ist aktiv§8.");
            }
        }
    }

    @EventHandler
    public void onCommandPreprocess(final PlayerCommandPreprocessEvent event) {
        final Player player = event.getPlayer();
        if (LMS.getInstance().getLMSHandler().getPlayers().contains(player) && LMS.getInstance().getState() == State.INGAME) {
            if (!event.getMessage().startsWith("/lms")) {
                event.setCancelled(!player.isOp());
                player.sendMessage(LMS.PREFIX + "§7Verlasse erst das LMS§8,§7 um Commands zu benutzen§8.");
            }
        }
    }

}
