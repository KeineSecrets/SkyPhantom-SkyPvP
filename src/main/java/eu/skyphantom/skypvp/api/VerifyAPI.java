package eu.skyphantom.skypvp.api;

import eu.skyphantom.skypvp.utils.Utils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class VerifyAPI {

    static Config config = new Config("plugins/SkyPvP/", "verifies.yml");

    public static Map<Long, String> discordVerifyMap = new LinkedHashMap<>();

    public static Map<Long, String> getDiscordVerifyMap() {
        return discordVerifyMap;
    }

    public static long getDiscordUserId(String code) {
        for (Long l : discordVerifyMap.keySet()) {
            if (discordVerifyMap.get(l).equals(code)) return l;
        }
        return 0L;
    }

    public static String getCode(long discordUserId) {
        return discordVerifyMap.get(discordUserId);
    }

    public static String add(long discordUserId) {
        if (!discordVerifyMap.containsKey(discordUserId)) {
            discordVerifyMap.put(discordUserId, Utils.getRandomString(7, true, true));
        }
        return discordVerifyMap.get(discordUserId);
    }

    public static boolean contains(long discordUserId) {
        return discordVerifyMap.containsKey(discordUserId);
    }

    public static boolean contains(String code) {
        return discordVerifyMap.containsValue(code);
    }

    public static boolean isVerified(UUID uuid) {
        return config.getConfig().getLong(uuid.toString()) != 0L;
    }

    public static boolean isVerified(long discordUserId) {
        Config userConfig = new Config("plugins/SkyPvP/discord/", discordUserId + ".yml");
        return (userConfig.getConfig().getString("uuid") != null);
    }

    public static UUID getUUID(long discordUserId) {
        Config userConfig = new Config("plugins/SkyPvP/discord/", discordUserId + ".yml");
        return UUID.fromString(userConfig.getConfig().getString("uuid"));
    }

    public static void verify(UUID uuid, long discordUserId) {
        Config userConfig = new Config("plugins/SkyPvP/discord/", discordUserId + ".yml");
        userConfig.getConfig().set("uuid", uuid.toString());
        config.getConfig().set(uuid.toString(), discordUserId);
        config.getConfig().set(String.valueOf(discordUserId), uuid.toString());
        userConfig.saveConfig();
        config.saveConfig();
    }
}
