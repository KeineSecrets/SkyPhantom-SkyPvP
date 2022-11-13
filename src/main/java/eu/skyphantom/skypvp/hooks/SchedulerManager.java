package eu.skyphantom.skypvp.hooks;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.leagues.League;
import eu.skyphantom.skypvp.leagues.LeagueRankDownEvent;
import eu.skyphantom.skypvp.leagues.LeagueRankUpEvent;
import eu.skyphantom.skypvp.provider.StatsProvider;
import eu.skyphantom.skypvp.utils.Utils;
import eu.skyphantom.skypvp.utils.scoreboard.ScoreboardHelper;
import org.bukkit.Bukkit;

public class SchedulerManager {

    public SchedulerManager() {
        this.startScoreboardTask();
        this.startTablistHaFTask();
    }

    public void startScoreboardTask() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyPvP.getInstance(), () -> {
            Bukkit.getOnlinePlayers().forEach(ScoreboardHelper::updateScoreboard);
        }, 0L, 10L);
    }

    public void startTablistHaFTask() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyPvP.getInstance(), () -> {
            Bukkit.getOnlinePlayers().forEach(player -> {
                Utils.sendTabTitle(player,
                        "§r\n" +
                                "§8▎ §a§lSKYPHANTOM§8 ▪ §7SkyPvP with §clove §8➟ §a1§8.§a8 §8▎\n" +
                                "§7Online§8 ▪ §a" + Bukkit.getOnlinePlayers().size() + "§8/§a" + Bukkit.getMaxPlayers() + "\n§7Registrierte Spieler§8 ▪ #§a" + Bukkit.getOfflinePlayers().length + "§r\n§r",
                        "§r\n§7       TeamSpeak§8 ▪ §askyphantom§8.§aeu       §r\n§7Discord§8 ▪ §adiscord§8.§agg§8/§askyphantom\n" +
                                "§7Event§8 ▪ §anull\n§r");
            });
        }, 0L, 10L);
    }

    public void startLeague() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyPvP.getInstance(), () -> {
            Bukkit.getOnlinePlayers().forEach(player -> {
                StatsProvider playerConfig = new StatsProvider(player.getUniqueId());
                if (playerConfig.getKills() > League.BRONZE_ONE.getKillsToRankup() && playerConfig.getLeague() != League.BRONZE_ONE) {
                    if (playerConfig.getKills() > League.BRONZE_TWO.getKillsToRankup() && playerConfig.getLeague() != League.BRONZE_TWO) {
                        if (playerConfig.getKills() > League.BRONZE_THREE.getKillsToRankup() && playerConfig.getLeague() != League.BRONZE_THREE) {
                            if (playerConfig.getKills() > League.BRONZE_FOUR.getKillsToRankup() && playerConfig.getLeague() != League.BRONZE_FOUR) {
                                if (playerConfig.getKills() > League.BRONZE_FIVE.getKillsToRankup() && playerConfig.getLeague() != League.BRONZE_FIVE) {
                                    if (playerConfig.getKills() > League.SILVER_ONE.getKillsToRankup() && playerConfig.getLeague() != League.SILVER_ONE) {
                                        if (playerConfig.getKills() > League.SILVER_TWO.getKillsToRankup() && playerConfig.getLeague() != League.SILVER_TWO) {
                                            if (playerConfig.getKills() > League.SILVER_THREE.getKillsToRankup() && playerConfig.getLeague() != League.SILVER_THREE) {
                                                if (playerConfig.getKills() > League.SILVER_FOUR.getKillsToRankup() && playerConfig.getLeague() != League.SILVER_FOUR) {
                                                    if (playerConfig.getKills() > League.SILVER_FIVE.getKillsToRankup() && playerConfig.getLeague() != League.SILVER_FIVE) {
                                                        if (playerConfig.getKills() > League.GOLD_ONE.getKillsToRankup() && playerConfig.getLeague() != League.GOLD_ONE) {
                                                            if (playerConfig.getKills() > League.GOLD_TWO.getKillsToRankup() && playerConfig.getLeague() != League.GOLD_TWO) {
                                                                if (playerConfig.getKills() > League.GOLD_THREE.getKillsToRankup() && playerConfig.getLeague() != League.GOLD_THREE) {
                                                                    if (playerConfig.getKills() > League.GOLD_FOUR.getKillsToRankup() && playerConfig.getLeague() != League.GOLD_FOUR) {
                                                                        if (playerConfig.getKills() > League.GOLD_FIVE.getKillsToRankup() && playerConfig.getLeague() != League.GOLD_FIVE) {
                                                                            if (playerConfig.getKills() > League.MASTER_ONE.getKillsToRankup() && playerConfig.getLeague() != League.MASTER_ONE) {
                                                                                Bukkit.getPluginManager().callEvent(new LeagueRankUpEvent(player.getUniqueId(), playerConfig.getLeague(), League.MASTER_ONE));
                                                                                return;
                                                                            }
                                                                            Bukkit.getPluginManager().callEvent(new LeagueRankUpEvent(player.getUniqueId(), playerConfig.getLeague(), League.GOLD_FIVE));
                                                                            return;
                                                                        }
                                                                        Bukkit.getPluginManager().callEvent(new LeagueRankUpEvent(player.getUniqueId(), playerConfig.getLeague(), League.GOLD_FOUR));
                                                                        return;
                                                                    }
                                                                    Bukkit.getPluginManager().callEvent(new LeagueRankUpEvent(player.getUniqueId(), playerConfig.getLeague(), League.GOLD_THREE));
                                                                    return;
                                                                }
                                                                Bukkit.getPluginManager().callEvent(new LeagueRankUpEvent(player.getUniqueId(), playerConfig.getLeague(), League.GOLD_TWO));
                                                                return;
                                                            }
                                                            Bukkit.getPluginManager().callEvent(new LeagueRankUpEvent(player.getUniqueId(), playerConfig.getLeague(), League.GOLD_ONE));
                                                            return;
                                                        }
                                                        Bukkit.getPluginManager().callEvent(new LeagueRankUpEvent(player.getUniqueId(), playerConfig.getLeague(), League.SILVER_FIVE));
                                                        return;
                                                    }
                                                    Bukkit.getPluginManager().callEvent(new LeagueRankUpEvent(player.getUniqueId(), playerConfig.getLeague(), League.SILVER_FIVE));
                                                    return;
                                                }
                                                Bukkit.getPluginManager().callEvent(new LeagueRankUpEvent(player.getUniqueId(), playerConfig.getLeague(), League.SILVER_FOUR));
                                                return;
                                            }
                                            Bukkit.getPluginManager().callEvent(new LeagueRankUpEvent(player.getUniqueId(), playerConfig.getLeague(), League.SILVER_THREE));
                                            return;
                                        }
                                        Bukkit.getPluginManager().callEvent(new LeagueRankUpEvent(player.getUniqueId(), playerConfig.getLeague(), League.SILVER_TWO));
                                        return;
                                    }
                                    Bukkit.getPluginManager().callEvent(new LeagueRankUpEvent(player.getUniqueId(), playerConfig.getLeague(), League.SILVER_ONE));
                                    return;
                                }
                                Bukkit.getPluginManager().callEvent(new LeagueRankUpEvent(player.getUniqueId(), playerConfig.getLeague(), League.BRONZE_FIVE));
                                return;
                            }
                            Bukkit.getPluginManager().callEvent(new LeagueRankUpEvent(player.getUniqueId(), playerConfig.getLeague(), League.BRONZE_FOUR));
                            return;
                        }
                        Bukkit.getPluginManager().callEvent(new LeagueRankUpEvent(player.getUniqueId(), playerConfig.getLeague(), League.BRONZE_THREE));
                        return;
                    }
                    Bukkit.getPluginManager().callEvent(new LeagueRankUpEvent(player.getUniqueId(), playerConfig.getLeague(), League.BRONZE_TWO));
                    return;
                }
                Bukkit.getPluginManager().callEvent(new LeagueRankUpEvent(player.getUniqueId(), playerConfig.getLeague(), League.BRONZE_ONE));
            });
            Bukkit.getOnlinePlayers().forEach(player -> {
                StatsProvider playerConfig = new StatsProvider(player.getUniqueId());
                if (playerConfig.getKills() < League.BRONZE_ONE.getKillsToRankup() && playerConfig.getLeague() == League.BRONZE_ONE) {
                    Bukkit.getPluginManager().callEvent(new LeagueRankUpEvent(player.getUniqueId(), playerConfig.getLeague(), League.UNRANKED));
                    return;
                }
                if (playerConfig.getKills() < League.BRONZE_TWO.getKillsToRankup() && playerConfig.getLeague()  == League.BRONZE_TWO) {
                    Bukkit.getPluginManager().callEvent(new LeagueRankUpEvent(player.getUniqueId(), playerConfig.getLeague(), League.BRONZE_ONE));
                    return;
                }
                if (playerConfig.getKills() < League.BRONZE_THREE.getKillsToRankup() && playerConfig.getLeague() == League.BRONZE_THREE) {
                    Bukkit.getPluginManager().callEvent(new LeagueRankUpEvent(player.getUniqueId(), playerConfig.getLeague(), League.BRONZE_TWO));
                    return;
                }
                if (playerConfig.getKills() < League.BRONZE_FOUR.getKillsToRankup() && playerConfig.getLeague() == League.BRONZE_FOUR) {
                    Bukkit.getPluginManager().callEvent(new LeagueRankUpEvent(player.getUniqueId(), playerConfig.getLeague(), League.BRONZE_THREE));
                    return;
                }
                if (playerConfig.getKills() < League.BRONZE_FIVE.getKillsToRankup() && playerConfig.getLeague() == League.BRONZE_FIVE) {
                    Bukkit.getPluginManager().callEvent(new LeagueRankUpEvent(player.getUniqueId(), playerConfig.getLeague(), League.BRONZE_FOUR));
                    return;
                }
                if (playerConfig.getKills() < League.SILVER_ONE.getKillsToRankup() && playerConfig.getLeague() == League.SILVER_ONE) {
                    Bukkit.getPluginManager().callEvent(new LeagueRankUpEvent(player.getUniqueId(), playerConfig.getLeague(), League.BRONZE_FIVE));
                    return;
                }
                if (playerConfig.getKills() < League.SILVER_TWO.getKillsToRankup() && playerConfig.getLeague() == League.SILVER_TWO) {
                    Bukkit.getPluginManager().callEvent(new LeagueRankUpEvent(player.getUniqueId(), playerConfig.getLeague(), League.SILVER_ONE));
                    return;
                }
                if (playerConfig.getKills() < League.SILVER_THREE.getKillsToRankup() && playerConfig.getLeague() == League.SILVER_THREE) {
                    Bukkit.getPluginManager().callEvent(new LeagueRankUpEvent(player.getUniqueId(), playerConfig.getLeague(), League.SILVER_TWO));
                    return;
                }
                if (playerConfig.getKills() < League.SILVER_FOUR.getKillsToRankup() && playerConfig.getLeague() == League.SILVER_FOUR) {
                    Bukkit.getPluginManager().callEvent(new LeagueRankDownEvent(player.getUniqueId(), playerConfig.getLeague(), League.SILVER_THREE));
                    return;
                }
                if (playerConfig.getKills() < League.SILVER_FIVE.getKillsToRankup() && playerConfig.getLeague() == League.SILVER_FIVE) {
                    Bukkit.getPluginManager().callEvent(new LeagueRankDownEvent(player.getUniqueId(), playerConfig.getLeague(), League.SILVER_FOUR));
                    return;
                }
                if (playerConfig.getKills() < League.GOLD_ONE.getKillsToRankup() && playerConfig.getLeague() == League.GOLD_ONE) {
                    Bukkit.getPluginManager().callEvent(new LeagueRankDownEvent(player.getUniqueId(), playerConfig.getLeague(), League.SILVER_FIVE));
                    return;
                }
                if (playerConfig.getKills() < League.GOLD_TWO.getKillsToRankup() && playerConfig.getLeague() == League.GOLD_TWO) {
                    Bukkit.getPluginManager().callEvent(new LeagueRankDownEvent(player.getUniqueId(), playerConfig.getLeague(), League.GOLD_ONE));
                    return;
                }
                if (playerConfig.getKills() < League.GOLD_THREE.getKillsToRankup() && playerConfig.getLeague() == League.GOLD_THREE) {
                    Bukkit.getPluginManager().callEvent(new LeagueRankDownEvent(player.getUniqueId(), playerConfig.getLeague(), League.GOLD_TWO));
                    return;
                }
                if (playerConfig.getKills() < League.GOLD_FOUR.getKillsToRankup() && playerConfig.getLeague() == League.GOLD_FOUR) {
                    Bukkit.getPluginManager().callEvent(new LeagueRankDownEvent(player.getUniqueId(), playerConfig.getLeague(), League.GOLD_THREE));
                    return;
                }
                if (playerConfig.getKills() < League.GOLD_FIVE.getKillsToRankup() && playerConfig.getLeague() == League.GOLD_FIVE) {
                    Bukkit.getPluginManager().callEvent(new LeagueRankDownEvent(player.getUniqueId(), playerConfig.getLeague(), League.GOLD_FOUR));
                    return;
                }
                if (playerConfig.getKills() < League.MASTER_ONE.getKillsToRankup() && playerConfig.getLeague() == League.MASTER_ONE) {
                    Bukkit.getPluginManager().callEvent(new LeagueRankDownEvent(player.getUniqueId(), playerConfig.getLeague(), League.GOLD_FIVE));
                    return;
                }
            });
        }, 0L, 10L);
    }

}
