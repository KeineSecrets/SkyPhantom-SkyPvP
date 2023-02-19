package eu.skyphantom.skypvp.clans;

import java.util.List;
import java.util.UUID;

/**
 * @author KeineSecrets
 * @ChatGPT SkyPvP
 * Created: 26/01/2023 | 17:52
 */

public class Clan {

    private UUID owner;
    private int id;
    private List<UUID> moderators, members;
    private String name, tag;
    private long createdAt;
    private long kills, deaths;
    private String clanColor;
    private int clanBanner;

    public Clan(UUID owner, int id, List<UUID> moderators, List<UUID> members, String name, String tag, long createdAt, long kills, long deaths, String clanColor, int clanBanner) {
        this.owner = owner;
        this.id = id;
        this.moderators = moderators;
        this.members = members;
        this.name = name;
        this.tag = tag;
        this.createdAt = createdAt;
        this.kills = kills;
        this.deaths = deaths;
        this.clanColor = clanColor;
        this.clanBanner = clanBanner;
    }

    public int getClanBanner() {
        return clanBanner;
    }

    public void setClanBanner(int clanBanner) {
        this.clanBanner = clanBanner;
    }

    public String getClanColor() {
        return clanColor;
    }

    public void setClanColor(String clanColor) {
        this.clanColor = clanColor;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<UUID> getModerators() {
        return moderators;
    }

    public void setModerators(List<UUID> moderators) {
        this.moderators = moderators;
    }

    public List<UUID> getMembers() {
        return members;
    }

    public void setMembers(List<UUID> members) {
        this.members = members;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDeaths() {
        return deaths;
    }

    public void setDeaths(long deaths) {
        this.deaths = deaths;
    }

    public long getKills() {
        return kills;
    }

    public void setKills(long kills) {
        this.kills = kills;
    }
}
