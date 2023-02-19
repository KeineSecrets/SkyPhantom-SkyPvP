package eu.skyphantom.skypvp.provider;

import eu.skyphantom.skypvp.api.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.HashSet;
import java.util.Set;

public class WarpProvider {

    String name;
    static Config config = new Config("plugins/SkyPvP/", "warps.yml");

    public WarpProvider(String name) {
        this.name = "warps." + name;
    }

    public boolean exists() {
        return config.getConfig().getString(this.name) != null;
    }

    public void set(Location location) {
        if (location == null) config.getConfig().set(this.name, null);
        else
            config.getConfig().set(this.name, location.getWorld().getName() + ";" + location.getX() + ";" + location.getY() + ";" + location.getZ() + ";" + location.getYaw() + ";" + location.getPitch());
        config.saveConfig();
    }

    public static Set<String> getAll() {
        return (config.getConfig().getConfigurationSection("warps") != null ? config.getConfig().getConfigurationSection("warps").getKeys(false) : new HashSet<>());
    }

    public Location get() {
        double x, y, z;
        float yaw, pitch;
        if (config.getConfig().getString(this.name) == null) return null;
        String[] data = config.getConfig().getString(this.name).split(";");
        String world = data[0];
        x = Double.parseDouble(data[1]);
        y = Double.parseDouble(data[2]);
        z = Double.parseDouble(data[3]);
        yaw = Float.parseFloat(data[4]);
        pitch = Float.parseFloat(data[5]);
        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }

}
