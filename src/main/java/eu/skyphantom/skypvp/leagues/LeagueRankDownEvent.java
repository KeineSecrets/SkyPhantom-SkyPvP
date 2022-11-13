package eu.skyphantom.skypvp.leagues;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.awt.*;
import java.util.UUID;

public class LeagueRankDownEvent extends Event {

    League oldLeague, newLeague;
    UUID uuid;
    private static final HandlerList handlers = new HandlerList();

    public LeagueRankDownEvent(UUID uuid, League oldLeague, League newLeague) {
        this.uuid = uuid;
        this.oldLeague = oldLeague;
        this.newLeague = newLeague;
    }

    public League getOldLeague() {
        return oldLeague;
    }

    public League getNewLeague() {
        return newLeague;
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
