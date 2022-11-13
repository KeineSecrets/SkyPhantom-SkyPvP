package eu.skyphantom.skypvp.lms.listener;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.lms.LMS;
import eu.skyphantom.skypvp.lms.State;
import eu.skyphantom.skypvp.lms.events.LMSStartEvent;
import eu.skyphantom.skypvp.lms.events.LMSStopEvent;
import eu.skyphantom.skypvp.lms.tasks.LMSGameIdleTask;
import eu.skyphantom.skypvp.lms.tasks.LMSLobbyTask;
import eu.skyphantom.skypvp.provider.LocationProvider;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LMSStopListener implements Listener {

    @EventHandler
    public void onLMSStop(final LMSStopEvent event) {
        LMS.getInstance().setState(State.NONE);
        LMSGameIdleTask.stopTask();
        LMSLobbyTask.stopTask();
        for (Player player : LMS.getInstance().getLMSHandler().getSpectators()) {
            player.teleport(new LocationProvider("spawn").get());
        }
        for (Player player : LMS.getInstance().getLMSHandler().getPlayers()) {
            player.teleport(new LocationProvider("spawn").get());
        }
        if (event.getWinner() == null) {
            Bukkit.broadcastMessage("§r");
            Bukkit.broadcastMessage(LMS.PREFIX + "§7Das §9LMS§7 wurde gestoppt§8.");
            Bukkit.broadcastMessage(LMS.PREFIX + "§7Es haben insgesamt §9" + LMS.getInstance().getLMSHandler().getPlayers().size() + "§7 Spieler teilgenommen§8.");
            Bukkit.broadcastMessage("§r");
        } else {
            Bukkit.broadcastMessage("§r");
            Bukkit.broadcastMessage(LMS.PREFIX + "§7Das §9LMS§7 wurde gestoppt§8.");
            Bukkit.broadcastMessage(LMS.PREFIX + "§7Es haben insgesamt §9" + LMS.getInstance().getLMSHandler().getPlayers().size() + "§7 Spieler teilgenommen§8.");
            Bukkit.broadcastMessage(LMS.PREFIX + "§7Gewonnen hat §9" + event.getWinner().getName() + "§8.");
            Bukkit.broadcastMessage("§r");
            event.getWinner().teleport(new LocationProvider("spawn").get());
        }
        LMS.getInstance().getLMSHandler().getSpectators().clear();
        LMS.getInstance().getLMSHandler().getPlayers().clear();
    }

}
