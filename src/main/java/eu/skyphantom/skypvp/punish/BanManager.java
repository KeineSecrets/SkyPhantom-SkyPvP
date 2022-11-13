package eu.skyphantom.skypvp.punish;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.api.Config;
import eu.skyphantom.skypvp.utils.time.TimeUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BanManager {

	private static final Config config = new Config("plugins/SkyPvP/punishments/", "bans.yml");

	private final UUID uuid;

	public BanManager(UUID uuid) {
		this.uuid = uuid;
	}

	public boolean isBanned() {
		return (System.currentTimeMillis() < config.getConfig().getLong(uuid + ".endDate"));
	}

	public void ban(String reason, long endDate) {
		config.getConfig().set(uuid + ".reason", reason);
		if (endDate == -1) {
			config.getConfig().set(uuid + ".endDate", -1);
		} else {
			config.getConfig().set(uuid + ".endDate", (System.currentTimeMillis()+endDate));
		}
		config.saveConfig();
		if (Bukkit.getPlayer(uuid) != null) {
			Player player = Bukkit.getPlayer(uuid);
			player.kickPlayer("§r\n\n" + SkyPvP.PREFIX + " §7SkyPvP with §clove §8▎\n\n§7Du wurdest gebannt§8.\n\n§7Grund§8: §a" + reason + "\n§7Verbleibend§8: §a" + (endDate == -1 ? "Permanent" : TimeUtil.timeToString(endDate-1000L, false)) + "\n\n§7Fehlban§8? §adiscord.gg/skyphantom\n\n§r");
		}
	}

	public long getEndDate() {
		if (isBanned()) {
			return (config.getConfig().getLong(uuid + ".endDate")-System.currentTimeMillis());
		}
		return 0;
	}

	public void setEndDate(long endDate) {
		if (isBanned()) {
			config.getConfig().set(uuid + ".endDate", endDate);
			config.saveConfig();
		}
	}

	public String getReason() {
		return config.getConfig().getString(uuid + ".reason");
	}

	public void unban() {
		config.getConfig().set(uuid + ".reason", null);
		config.getConfig().set(uuid + ".endDate", null);
		config.saveConfig();
	}

}
