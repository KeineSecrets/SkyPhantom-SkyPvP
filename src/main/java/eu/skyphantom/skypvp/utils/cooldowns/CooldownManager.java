package eu.skyphantom.skypvp.utils.cooldowns;

import eu.skyphantom.skypvp.api.Config;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class CooldownManager {

    UUID uuid;
    Config config;

    public CooldownManager(UUID uuid) {
        this.uuid = uuid;
        this.config = new Config("plugins/SkyPvP/cooldowns/", uuid + ".yml");
    }

    public void addCooldown(String name, long time, TimeUnit timeUnit) {
        long end = System.currentTimeMillis() + (timeUnit.toMillis(time));
        config.getConfig().set(name, end);
        config.saveConfig();
    }

    public long getRemainingTime(String name) {
        return (config.getConfig().getLong(name) - System.currentTimeMillis());
    }

    public long getTime(String name) {
        return (config.getConfig().getLong(name));
    }

    public boolean isOnCooldown(String name) {
        long end = getTime(name) - System.currentTimeMillis();
        if (end <= 0) removeCooldown(name);
        return (end > 0);
    }

    public void removeCooldown(String name) {
        config.getConfig().set(name, null);
        config.saveConfig();
    }

}
