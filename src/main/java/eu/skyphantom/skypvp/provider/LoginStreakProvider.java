package eu.skyphantom.skypvp.provider;

import eu.skyphantom.skypvp.api.Config;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class LoginStreakProvider {

    UUID uuid;
    Config config = new Config("plugins/SkyPvP/", "loginstreaks.yml");

    public LoginStreakProvider(UUID uuid) {
        this.uuid = uuid;
    }

}
