package eu.skyphantom.skypvp.utils.time;

import eu.skyphantom.skypvp.api.Config;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class OnlineTime {

    private static final Config config = new Config("plugins/SkyPvP/", "onlinetime.yml");

    public static String get(UUID uuid, boolean shorten) {
        Player player = Bukkit.getPlayer(uuid);
        if (player != null) {
            return TimeUtil.timeToString(TimeUnit.SECONDS.toMillis((player.getStatistic(Statistic.PLAY_ONE_TICK) / 20)), shorten);
        }
        return TimeUtil.timeToString(config.getConfig().getLong(uuid.toString()), shorten);
    }

    public static void join(UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);
        config.getConfig().set(uuid.toString(), (player.getStatistic(Statistic.PLAY_ONE_TICK) / 20));
        config.saveConfig();
    }

    public static void leave(UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);
        config.getConfig().set(uuid.toString(), (player.getStatistic(Statistic.PLAY_ONE_TICK) / 20));
        config.saveConfig();
    }

}
