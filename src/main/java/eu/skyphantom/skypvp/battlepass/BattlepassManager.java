package eu.skyphantom.skypvp.battlepass;

import eu.skyphantom.skypvp.api.Config;

import java.util.UUID;

public class BattlepassManager {

    UUID uuid;
    Config config = new Config("plugins/SkyPvP/", "battlepass.yml");

    public BattlepassManager(UUID uuid) {
        this.uuid = uuid;
    }

    public void setCurrentStufe(int i) {
        config.getConfig().set(this.uuid + ".stufe", i);
        config.saveConfig();
    }

    public int getCurrentStufe() {
        return config.getConfig().getInt(this.uuid + ".stufe");
    }

    public void setRewardGot(int i, boolean v) {
        config.getConfig().set(this.uuid + ".stufen." + i, v);
        config.saveConfig();
    }

    public boolean gotReward(int i) {
        return config.getConfig().getBoolean(this.uuid + ".stufen." + i);
    }

}
