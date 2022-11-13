package eu.skyphantom.skypvp.lms;

import eu.skyphantom.skypvp.lms.events.LMSCreateEvent;
import eu.skyphantom.skypvp.lms.events.LMSStopEvent;
import eu.skyphantom.skypvp.lms.tasks.LMSLobbyTask;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class LMSHandler { //min = 2, max = -1

    List<Player> players = new CopyOnWriteArrayList<>();
    List<Player> spectators = new CopyOnWriteArrayList<>();
    Map<Player, Integer> playerKills = new ConcurrentHashMap<>();

    public LMSHandler() {
        players.clear();
        playerKills.clear();
    }

    public void addPlayer(Player player) {
        players.add(player);
        playerKills.put(player, 0);
    }

    public void removePlayer(Player player) {
        players.remove(player);
        playerKills.remove(player);
        if (players.size() == 1 && LMS.getInstance().getState() == State.LOBBY) LMSLobbyTask.setCountdown(60);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Integer getPlayerKills(Player player) {
        return playerKills.get(player);
    }

    public void start() {
        LMS.getInstance().setState(State.LOBBY);
        LMSLobbyTask.startTask();
        Bukkit.getPluginManager().callEvent(new LMSCreateEvent());
    }

    public void stop(Player winner) {
        LMSLobbyTask.stopTask();
        Bukkit.getPluginManager().callEvent(new LMSStopEvent(getPlayers(), winner));
    }

    public List<Player> getSpectators() {
        return spectators;
    }

    public Map<Player, Integer> getPlayerKills() {
        return playerKills;
    }
}
