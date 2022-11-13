package eu.skyphantom.skypvp.provider;

import eu.skyphantom.skypvp.api.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationProvider {

    String name;
    Config config;

    public LocationProvider(String name) {
        this.name = name;
        this.config = new Config("plugins/SkyPvP/", "locations.yml");
    }

    public boolean exists() {
        return config.getConfig().getString(this.name) != null;
    }

    public void set(Location location) {
        config.getConfig().set(this.name, location.getWorld().getName() + ";" + location.getX() + ";" + location.getY() + ";" + location.getZ() + ";" + location.getYaw() + ";" + location.getPitch());
        config.saveConfig();
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
