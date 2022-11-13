package eu.skyphantom.skypvp.utils.cooldowns;

import eu.skyphantom.skypvp.api.Config;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class DiscordCooldownManager {

    long uuid;
    Config config = new Config("plugins/SkyPvP/discord/", "cooldowns.yml");

    public DiscordCooldownManager(long uuid) {
        this.uuid = uuid;
    }

    public void addCooldown(String name, long time, TimeUnit timeUnit) {
        long end = System.currentTimeMillis() + (timeUnit.toMillis(time));
        config.getConfig().set(uuid + "." + name, end);
        config.saveConfig();
    }

    public long getRemainingTime(String name) {
        return (config.getConfig().getLong(uuid + "." + name) - System.currentTimeMillis());
    }

    public long getTime(String name) {
        return (config.getConfig().getLong(uuid + "." + name));
    }

    public boolean isOnCooldown(String name) {
        long end = getTime(uuid + "." + name) - System.currentTimeMillis();
        if (end <= 0) removeCooldown(uuid + "." + name);
        return (end > 0);
    }

    public void removeCooldown(String name) {
        config.getConfig().set(uuid + "." + name, null);
        config.saveConfig();
    }

}
