package eu.skyphantom.skypvp.events;

import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;
import eu.skyphantom.skypvp.SkyPvP;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class VoteListener implements Listener {

    @EventHandler
    public void onVote(final VotifierEvent event) {
        Player player = Bukkit.getPlayer(event.getVote().getUsername());
        if (player == null) return;
        Bukkit.broadcastMessage("§r");
        Bukkit.broadcastMessage(SkyPvP.PREFIX + "§7Der Spieler §a" + player.getName() + "§7 hat für uns gevotet§8.");
        Bukkit.broadcastMessage(SkyPvP.PREFIX + "§7Vote jetzt auch mit §8/§avote§7 und erhalte tolle Rewards§8!");
        Bukkit.broadcastMessage("§r");
    }

}
