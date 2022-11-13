package eu.skyphantom.skypvp.provider;


import eu.skyphantom.skypvp.api.Config;
import eu.skyphantom.skypvp.leagues.League;
import eu.skyphantom.skypvp.leagues.LeagueRankDownEvent;
import eu.skyphantom.skypvp.leagues.LeagueRankUpEvent;
import org.bukkit.Bukkit;

import java.text.DecimalFormat;
import java.util.UUID;

public class StatsProvider {

    UUID uuid;
    Config config;

    public StatsProvider(UUID uuid) {
        this.uuid = uuid;
        this.config = new Config("plugins/SkyPvP/users/", uuid.toString());
    }

    public void setLeague(League league) {
        if (getLeague().ordinal() < league.ordinal()) {
            Bukkit.getPluginManager().callEvent(new LeagueRankUpEvent(uuid, getLeague(), league));
        }
        if (getLeague().ordinal() > league.ordinal()) {
            Bukkit.getPluginManager().callEvent(new LeagueRankDownEvent(uuid, getLeague(), league));
        }
        config.getConfig().set("league", league.name());
        config.saveConfig();
    }

    public League getLeague() {
        return (config.getConfig().getString("league") != null ? League.valueOf(config.getConfig().getString("league")) : League.UNRANKED);
    }

    public void setCoins(double value) {
        this.config.getConfig().set("coins", value);
        this.config.saveConfig();
    }

    public double getCoins() {
        return this.config.getConfig().getDouble("coins");
    }

    public void addCoins(double value) {
        double coins = getCoins() + value;
        setCoins(coins);
    }

    public void removeCoins(double value) {
        double coins = getCoins() - value;
        if (coins < 0) coins = 0;
        setCoins(coins);
    }

    public void setKills(double value) {
        this.config.getConfig().set("kills", value);
        this.config.saveConfig();
    }

    public double getKills() {
        return this.config.getConfig().getDouble("kills");
    }

    public void addKills(double value) {
        double kills = getKills() + value;
        setCoins(kills);
    }

    public void removeKills(double value) {
        double kills = getKills() - value;
        if (kills < 0) kills = 0;
        setCoins(kills);
    }

    public void setDeaths(double value) {
        this.config.getConfig().set("deaths", value);
        this.config.saveConfig();
    }

    public double getDeaths() {
        return this.config.getConfig().getDouble("deaths");
    }

    public void addDeaths(double value) {
        double deaths = getDeaths() + value;
        setCoins(deaths);
    }

    public void removeDeaths(double value) {
        double deaths = getDeaths() - value;
        if (deaths < 0) deaths = 0;
        setCoins(deaths);
    }

    public String getKDr() {
        double kills = getKills();
        double deaths = getDeaths();
        if (getDeaths() == 0) return new DecimalFormat("#,###.##").format(kills).replace(",", ".").replace(".", "") + "§8.§300";
        return String.format("%.2f", (kills / deaths)).replace(".", "§8.§3");
    }

    public String getKDr(String color) {
        return getKDr().replace("§3", color);
    }

}
