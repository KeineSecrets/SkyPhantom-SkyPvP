package eu.skyphantom.skypvp.provider;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.api.Config;
import eu.skyphantom.skypvp.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BanProvider {

    UUID uuid;
    Config config;

    public BanProvider(UUID uuid) {
        this.uuid = uuid;
        this.config = new Config("plugins/SkyPvP/punishments/", "bans.yml");
    }

    public void ban(String reason, long endDate) {
        Player player = Bukkit.getPlayer(uuid);
        config.getConfig().set("ban." + uuid + ".reason", reason);
        config.getConfig().set("ban." + uuid + ".endDate", endDate);
        config.saveConfig();
        if (player == null) return;
        Utils.broadcast("§r", false);
        Utils.broadcast(SkyPvP.PUNISH + "§7Der Spieler §c" + player.getDisplayName() + "§7 wurde ausgeschlossen§8.", false);
        Utils.broadcast(SkyPvP.PUNISH + "§7Grund§8: §e" + reason, false);
        Utils.broadcast("§r", false);
    }

    public void pvpban(String reason, long endDate) {
        Player player = Bukkit.getPlayer(uuid);
        config.getConfig().set("pvpban." + uuid + ".reason", reason);
        config.getConfig().set("pvpban." + uuid + ".endDate", endDate);
        config.saveConfig();
        if (player == null) return;
        Utils.broadcast("§r", false);
        Utils.broadcast(SkyPvP.PUNISH + "§7Der Spieler §c" + player.getDisplayName() + "§7 wurde aus dem PvP", false);
        Utils.broadcast(SkyPvP.PUNISH + "§7ausgeschlossen§8. ▪ §7Grund§8: §e" + reason, false);
        Utils.broadcast("§r", false);
    }

    public void unban() {
        config.getConfig().set("ban." + uuid + ".reason", null);
        config.getConfig().set("ban." + uuid + ".endDate", null);
    }

    public void unpvpban() {
        config.getConfig().set("pvpban." + uuid + ".reason", null);
        config.getConfig().set("pvpban." + uuid + ".endDate", null);
    }

    public long getEndTimeBan() {
        return config.getConfig().getLong("ban." + uuid + ".endDate");
    }

    public long getEndTimePvPBan() {
        return config.getConfig().getLong("pvpban." + uuid + ".endDate");
    }

    public boolean isBanned() {
        return config.getConfig().getString("ban." + uuid + ".reason") != null;
    }

    public boolean isPvPBanned() {
        return config.getConfig().getString("pvpban." + uuid + ".reason") != null;
    }

    public String getReasonBan() {
        return config.getConfig().getString("ban." + uuid + ".reason");
    }

    public String getReasonPvPBan() {
        return config.getConfig().getString("pvpban." + uuid + ".reason");
    }



}
