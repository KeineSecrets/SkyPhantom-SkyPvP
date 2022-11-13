package eu.skyphantom.skypvp.lms.listener;

import eu.skyphantom.skypvp.lms.LMS;
import eu.skyphantom.skypvp.lms.State;
import eu.skyphantom.skypvp.lms.events.LMSCreateEvent;
import eu.skyphantom.skypvp.lms.events.LMSStartEvent;
import eu.skyphantom.skypvp.lms.tasks.LMSGameIdleTask;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LMSCreateListener implements Listener {

    @EventHandler
    public void onLMSCreate(final LMSCreateEvent event) {
        LMS.getInstance().setState(State.LOBBY);
        Bukkit.broadcastMessage("§r");
        Bukkit.broadcastMessage(LMS.PREFIX + "§7Ein §9LMS§8-§9Event§7 wurde gestartet§8.");
        Bukkit.broadcastMessage(LMS.PREFIX + "§8/§9lms join");
        Bukkit.broadcastMessage("§r");
    }

}
