package eu.skyphantom.skypvp.provider;


import eu.skyphantom.skypvp.api.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MaintenanceProvider {

    static Config config = new Config("plugins/SkyPvP/", "maintenance.yml");

    public static void set(boolean value) {
        config.getConfig().set("options.active", value);
        config.saveConfig();
    }

    public static boolean get() {
        return config.getConfig().getBoolean("options.active");
    }

    public static void add(UUID uuid) {
        List<String> list = (config.getConfig().getStringList("whitelisted") == null ? new ArrayList<>() : config.getConfig().getStringList("whitelisted"));
        if (list.contains(uuid.toString())) return;
        list.add(uuid.toString());
        config.getConfig().set("whitelisted", list);
        config.saveConfig();
    }

    public static void remove(UUID uuid) {
        if (config.getConfig().getStringList("whitelisted") == null) return;
        List<String> list = config.getConfig().getStringList("whitelisted");
        list.remove(uuid.toString());
        config.getConfig().set("whitelisted", list);
        config.saveConfig();
    }

    public static List<String> getWhitelisted() {
        return config.getConfig().getStringList("whitelisted");
    }

    public static void setReason(String reason) {
        config.getConfig().set("options.reason", reason);
        config.saveConfig();
    }

    public static String getReason() {
        return config.getConfig().getString("options.reason");
    }

}
