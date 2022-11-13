package eu.skyphantom.skypvp.lms.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.List;

public class LMSStartEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    List<Player> players;
    long started;

    public LMSStartEvent(List<Player> players, long started) {
        this.players = players;
        this.started = started;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public long getStarted() {
        return started;
    }
}
