package eu.skyphantom.skypvp.utils.advent;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.api.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Kalender {
    private final Config config;
    private static Kalender instance;
    public static final String PREFIX = "§8▎ §f§LA§c§lD§f§lV§c§lE§f§lN§c§lT§8 ▪ §7";
    public static final String NOPERM = SkyPvP.NOPERM;

    public Kalender() {
        instance = this;
        config = new Config("plugins/SkyPvP/christmas/", "calendar.yml");
        System.out.println("[Kalender] Config wurde geladen.");
    }

    public static Kalender getInstance() {
        return instance;
    }

    public void setupPlayer(Player player, boolean async) {
        {
            if (config.getConfig().getConfigurationSection(player.getUniqueId().toString()) == null) {
                for (int i = 0; i < 24; i++) {
                    config.getConfig().set(player.getUniqueId().toString() + "." + i, false);
                    i++;
                }
            }
        }
    }

    public boolean hasOpened(Player player, int day) {
        return config.getConfig().getBoolean(player.getUniqueId().toString() + "." + day);
    }

    public void setOpened(Player player, int day) {
        config.getConfig().set(player.getUniqueId().toString() + "." + day, true);
        config.saveConfig();
    }


}
