package eu.skyphantom.skypvp.lms.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.List;

public class LMSStopEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    List<Player> players;
    Player winner;

    public LMSStopEvent(List<Player> players, Player winner) {
        this.players = players;
        this.winner = winner;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getWinner() {
        return winner;
    }


}
