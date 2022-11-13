package eu.skyphantom.skypvp.perks;

import eu.skyphantom.skypvp.api.Config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class PerkManager {

    UUID uuid;
    Config config = new Config("plugins/SkyPvP/", "perks.yml");

    public PerkManager(UUID uuid) {
        this.uuid = uuid;
    }

    public boolean hasPerk(Perks perk) {
        if (config.getConfig().getStringList(this.uuid + ".bought") != null) {
            return (config.getConfig().getStringList(this.uuid + ".bought").contains(perk.name().toLowerCase()));
        }
        config.getConfig().set(this.uuid + ".bought", new ArrayList<String>());
        config.saveConfig();
        return false;
    }

    public boolean isEnabled(Perks perk) {
        if (hasPerk(perk)) {
            if (config.getConfig().getStringList(this.uuid + ".active") != null) {
                return (config.getConfig().getStringList(this.uuid + ".active").contains(perk.name().toLowerCase()));
            }
            config.getConfig().set(this.uuid + ".active", new ArrayList<String>());
            config.saveConfig();
            return false;
        }
        return false;
    }

    public List<String> getEnabled() {
        if (config.getConfig().getStringList(this.uuid + ".active") != null) {
            return (config.getConfig().getStringList(this.uuid + ".active"));
        }
        return new ArrayList<>();
    }

    public void addPerk(Perks perk) {
        if (!hasPerk(perk)) {
            if (config.getConfig().getStringList(this.uuid + ".bought") != null) {
                List<String> stringList = config.getConfig().getStringList(this.uuid + ".bought");
                stringList.add(perk.name().toLowerCase());
                config.getConfig().set(this.uuid + ".bought", stringList);
                config.saveConfig();
            }
            config.getConfig().set(this.uuid + ".bought", new ArrayList<String>(Collections.singleton(perk.name().toLowerCase())));
            config.saveConfig();
        }
    }

    public void removePerk(Perks perk) {
        if (hasPerk(perk)) {
            if (config.getConfig().getStringList(this.uuid + ".bought") != null) {
                List<String> stringList = config.getConfig().getStringList(this.uuid + ".bought");
                stringList.remove(perk.name().toLowerCase());
                config.getConfig().set(this.uuid + ".bought", stringList);
                config.saveConfig();
            }
            config.getConfig().set(this.uuid + ".bought", new ArrayList<String>());
            config.saveConfig();
        }
    }

}
