package eu.skyphantom.skypvp.events;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.leagues.LeagueRankDownEvent;
import eu.skyphantom.skypvp.leagues.LeagueRankUpEvent;
import eu.skyphantom.skypvp.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LeagueListener implements Listener {

    @EventHandler()
    public void onLeagueRankUp(final LeagueRankUpEvent event) {
        Player player = Bukkit.getPlayer(event.getUuid());
        Utils.sendTitle(player, 5, 20, 10, event.getNewLeague().getDisplay(), "§8(§a+§8) §7Rankup §8(§a+§8)");
        Utils.playSound(player, Sound.ENDERMAN_TELEPORT, 100, 10);
    }

    @EventHandler()
    public void onLeagueRankDown(final LeagueRankDownEvent event) {
        Player player = Bukkit.getPlayer(event.getUuid());
        Utils.sendTitle(player, 5, 20, 10, event.getNewLeague().getDisplay(), "§8(§c-§8) §7Rankdown §8(§c-§8)");
        Utils.playSound(player, Sound.ANVIL_BREAK, 100, 10);
    }

}
