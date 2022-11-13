package eu.skyphantom.skypvp.utils.skulls;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import eu.skyphantom.skypvp.api.Config;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;

public final class SkullProfileManager {

    private static final HashMap<UUID, GameProfile> storedProfiles = new HashMap<>();
    private static final HashMap<String, GameProfile> tempProfiles = new HashMap<>();
    private static final Field LEGACY_FIELD;
    private static final Config config;
    private static boolean initialized = false;

    static {
        Field f = null;
        config = new Config("plugins/SkyPvP/", "profiles.yml");
        try {
            f = GameProfile.class.getDeclaredField("legacy");
            f.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        LEGACY_FIELD = f;
    }

    public static void init() {
        if (initialized) {
            return;
        }
        if (config.getConfig().getConfigurationSection("profiles") == null || config.getConfig().getConfigurationSection("profiles").getKeys(false) == null) {
            initialized = true;
            return;
        }
        for (String uuidString : config.getConfig().getConfigurationSection("profiles").getKeys(false)) {
            UUID uuid = UUID.fromString(config.getConfig().getString("profiles." + uuidString + ".id"));
            GameProfile gameProfile = fetchProfile(uuid);
            addProfile(gameProfile);
        }
        initialized = true;
    }

    public static GameProfile getByName(String name) {
        Player p = Bukkit.getPlayerExact(name);
        if (p != null) {
            GameProfile profile = getByPlayer(p);
            if (!storedProfiles.containsKey(profile.getId())) {
                update(profile);
            }
            return profile;
        }
        GameProfile profile = tempProfiles.get(name.toLowerCase(Locale.ROOT));
        if (profile != null) {
            return profile;
        }
        return getByUUID(fetchUUID(name));
    }

    public static GameProfile getByUUID(UUID uuid) {
        if (uuid == null) {
            return null;
        }
        Player p = Bukkit.getPlayer(uuid);
        if (p != null) {
            GameProfile profile = getByPlayer(p);
            if (!storedProfiles.containsKey(uuid)) {
                update(profile);
            }
            return profile;
        }
        GameProfile profile = storedProfiles.get(uuid);
        if (profile != null) {
            return profile;
        }
        profile = fetchProfile(uuid);
        if (profile != null) {
            update(profile);
        }
        return profile;
    }

    public static GameProfile getByPlayer(Player player) {
        if (tempProfiles.containsKey(player.getName().toLowerCase()))
            return tempProfiles.get(player.getName().toLowerCase());
        if (storedProfiles.containsKey(player.getUniqueId())) return storedProfiles.get(player.getUniqueId());
        GameProfile profile = ((CraftPlayer) player).getProfile();
        update(profile);
        return profile;
    }

    public static void update(GameProfile profile) {
        addProfile(profile);
    }

    private static void addProfile(GameProfile profile) {
        storedProfiles.put(profile.getId(), profile);
        tempProfiles.put(profile.getName().toLowerCase(Locale.ROOT), profile);
        for (GameProfile gameProfile : storedProfiles.values()) {
            config.getConfig().set("profiles." + gameProfile.getId() + ".id", gameProfile.getId().toString());
            config.getConfig().set("profiles." + gameProfile.getId() + ".name", gameProfile.getName().toLowerCase());
        }
        config.saveConfig();
    }

    private static UUID fetchUUID(String name) {
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) new URL("https://api.mojang.com/users/profiles/minecraft/" + name)
                    .openConnection();
            JsonObject o = (JsonObject) new JsonParser().parse(new InputStreamReader(con.getInputStream()));
            return fromPlainUUID(o.get("id").getAsString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        return null;
    }

    private static GameProfile fetchProfile(UUID uuid) {
        if (uuid == null) {
            return null;
        }
        HttpURLConnection con = null;
        try {
            String replace = uuid.toString().toLowerCase(Locale.ROOT).replace("-", "");
            System.out.println(uuid + " " + replace);
            con = (HttpURLConnection) new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + replace)
                    .openConnection();
            JsonObject o = (JsonObject) new JsonParser().parse(new InputStreamReader(con.getInputStream()));
            GameProfile profile = new GameProfile(fromPlainUUID(o.get("id").getAsString()), o.get("name").getAsString());
            boolean legacy = false;
            if (o.has("legacy")) {
                legacy = o.get("legacy").getAsBoolean();
            }
            try {
                LEGACY_FIELD.set(profile, legacy);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (o.has("properties")) {
                //System.out.println(o.get("properties").toString());
                for (JsonElement jsonElement : o.get("properties").getAsJsonArray()) {
                    JsonObject object = jsonElement.getAsJsonObject();
                    Property prop;
                    if (object.has("signature")) {
                        prop = new Property(object.get("name").getAsString(), object.get("value").getAsString(), object.get("signature").getAsString());
                    } else {
                        prop = new Property(object.get("name").getAsString(), object.get("value").getAsString());
                    }
                    profile.getProperties().put(prop.getName(), prop);
                }
            }
            return profile;
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            System.err.println("RATE LIMITED " + uuid);
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        return null;
    }

    private static UUID fromPlainUUID(String s) {
        return new UUID(
                new BigInteger(s.substring(0, 16), 16).longValue(),
                new BigInteger(s.substring(16), 16).longValue()
        );
    }
}


