package eu.skyphantom.skypvp.utils;

import eu.skyphantom.skypvp.api.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created: 15/01/2023
 *
 * @author 🐸
 */
public class MessageQueue {

    static Config config = new Config("plugins/SkyPvP/", "messageQueue.yml");

    public static List<String> get(UUID uuid) {
        return config.getConfig().getStringList(uuid.toString());
    }

    public static void add(UUID uuid, String message) {
        List<String> get = get(uuid);
        get.add(message);
        config.getConfig().set(uuid.toString(), get);
        config.saveConfig();
    }

    public static void clear(UUID uuid) {
        config.getConfig().set(uuid.toString(), new ArrayList<String>());
        config.saveConfig();
    }

}
