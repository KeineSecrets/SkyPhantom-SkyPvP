package eu.skyphantom.skypvp.treasure;

/**
 * JavaDoc this file!
 * Created: 27/12/2022
 *
 * @author WeLoveSpigotPlugins (welovespigotplugins@gmail.com)
 */


import eu.skyphantom.skypvp.SkyPvP;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class TreasureManager {
    private static String prefix;
    private static boolean inspin;

    static {
        TreasureManager.prefix = SkyPvP.PREFIX;
        TreasureManager.inspin = false;
    }

    private final HashMap<Player, String> treasurebearbeiten;
    private final HashMap<Player, String> treasurechance;

    public TreasureManager() {
        //Bukkit.getServer().getPluginManager().registerEvents((Listener)new TreasureInteract_Listener(), (Plugin)Main.getInstance());
        //Bukkit.getServer().getPluginManager().registerEvents((Listener)new TreasureInventoryClose_Listener(), (Plugin)Main.getInstance());
        //Bukkit.getServer().getPluginManager().registerEvents((Listener)new TreasureInventoryClick_Listener(), (Plugin)Main.getInstance());
        //Bukkit.getServer().getPluginManager().registerEvents((Listener)new TreasureBlockPlace_Listener(), (Plugin)Main.getInstance());
        this.treasurebearbeiten = new HashMap<Player, String>();
        this.treasurechance = new HashMap<Player, String>();
    }

    public String getprefix() {
        return TreasureManager.prefix;
    }

    public boolean getspin() {
        return TreasureManager.inspin;
    }

    public void setspin(final boolean set) {
        TreasureManager.inspin = set;
    }

    public HashMap<Player, String> getBearbeitem() {
        return this.treasurebearbeiten;
    }

    public void addBearbeiten(final Player p, final String treasurename) {
        if (!this.getBearbeitem().containsKey(p)) {
            this.getBearbeitem().put(p, treasurename);
        }
    }

    public void removeBearbeiten(final Player p) {
        this.getChance().remove(p);
    }

    public HashMap<Player, String> getChance() {
        return this.treasurechance;
    }

    public void addChance(final Player p, final String treasurename) {
        if (!this.getChance().containsKey(p)) {
            this.getChance().put(p, treasurename);
        }
    }

    public void removeChance(final Player p) {
        this.getChance().remove(p);
    }

    public void addBC(final String Treasurename, final String itemname) {
        final File file = new File("plugins/SkyPvP/Treasure/", Treasurename + ".yml");
        final FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        final List<String> list = cfg.getStringList("Broadcastlist");
        list.add(itemname);
        cfg.set("Broadcastlist", list);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeBC(final String Treasurename, final String itemname) {
        final File file = new File("plugins/SkyPvP/Treasure/", Treasurename + ".yml");
        final FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        final List<String> list = cfg.getStringList("Broadcastlist");
        if (list.contains(itemname)) {
            list.remove(itemname);
            cfg.set("Broadcastlist", list);
            try {
                cfg.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean getBC(final String Treasurename, final String itemname) {
        boolean bc = false;
        final File file = new File("plugins/SkyPvP/Treasure/", Treasurename + ".yml");
        final FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        final List<String> list = cfg.getStringList("Broadcastlist");
        bc = list.contains(itemname);
        return bc;
    }

    public Location getLocation() {
        final File file = new File("plugins/SkyPvP/Treasure", "location.yml");
        final FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        final double x = cfg.getDouble("Location.X");
        final double y = cfg.getDouble("Location.Y");
        final double z = cfg.getDouble("Location.Z");
        final String w = cfg.getString("Location.World");
        final Location loc = new Location(Bukkit.getWorld(w), x, y, z);
        return loc;
    }

    public void setLocation(final Location l) {
        final File file = new File("plugins/SkyPvP/Treasure", "location.yml");
        final FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        final double x = l.getX();
        final double y = l.getY();
        final double z = l.getZ();
        final String w = l.getWorld().getName();
        cfg.set("Location.X", x);
        cfg.set("Location.Y", y);
        cfg.set("Location.Z", z);
        cfg.set("Location.World", w);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

