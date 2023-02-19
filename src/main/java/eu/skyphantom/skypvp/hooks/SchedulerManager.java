package eu.skyphantom.skypvp.hooks;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.api.RainbowTab;
import eu.skyphantom.skypvp.utils.Utils;
import eu.skyphantom.skypvp.utils.scoreboard.ScoreboardHelper;
import org.bukkit.Bukkit;

public class SchedulerManager {

    public SchedulerManager() {
        this.startScoreboardTask();
        this.startTablistHaFTask();
        this.startRainbowTabTask();
    }

    public void startScoreboardTask() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyPvP.getInstance(), () -> {
            Bukkit.getOnlinePlayers().forEach(ScoreboardHelper::updateScoreboard);
        }, 0L, 10L);

    }

    public void startRainbowTabTask() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyPvP.getInstance(), RainbowTab::rainbow, 0L, 2L);
    }

    public void startTablistHaFTask() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyPvP.getInstance(), () -> {
            Bukkit.getOnlinePlayers().forEach(player -> {
                Utils.sendTabTitle(player,
                        "§r\n" +
                                "§8▎ §a§lSKYPHANTOM§8 ▪ §7SkyPvP with §clove §8➟ §a1§8.§a8 §8▎\n" +
                                "§7Online§8 ▪ §a" + Bukkit.getOnlinePlayers().size() + "§8/§a" + Bukkit.getMaxPlayers() + "\n§7Registrierte Spieler§8 ▪ #§a" + Bukkit.getOfflinePlayers().length + "§r\n§r",
                        "§r\n§7       TeamSpeak§8 ▪ §askyphantom§8.§aeu       §r\n§7Discord§8 ▪ §adiscord§8.§agg§8/§askyphantom\n" +
                                "§7Event§8 ▪ §a" + String.format(Utils.EVENT, "Freitag§8", "§a18§8:§a30") + "\n§r");
            });
        }, 0L, 10L);
    }

}
