package eu.skyphantom.skypvp.clans;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.util.UUIDTypeAdapter;
import eu.skyphantom.skypvp.api.Config;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author KeineSecrets
 * @ChatGPT SkyPvP
 * Created: 26/01/2023 | 17:57
 */
public class ClanManager {

    private static final Gson gson = new GsonBuilder().registerTypeAdapter(UUID.class, new UUIDTypeAdapter()).create();
    private static final Config config = new Config("plugins/SkyPvP/", "clans.yml");
    private final Map<UUID, List<Clan>> invites = new LinkedHashMap<>();

    private final List<Clan> clanList;

    public ClanManager() {
        clanList = new CopyOnWriteArrayList<>();
        if (config.getConfig().getStringList("clans") == null || config.getConfig().getStringList("clans").isEmpty())
            return;
        for (String json : config.getConfig().getStringList("clans")) {
            clanList.add(fromJson(json));
        }
        if (config.getConfig().getConfigurationSection("invites") == null) return;
        for (String uuidStr : config.getConfig().getConfigurationSection("invites").getKeys(false)) {
            List<Clan> temp = new CopyOnWriteArrayList<>();
            UUID uuid = UUID.fromString(uuidStr);
            for (String clanName : config.getConfig().getStringList("invites." + uuidStr)) {
                Clan clan = getByName(clanName);
                temp.add(clan);
            }
            invites.put(uuid, temp);
        }
    }

    private String toJson(Clan clan) {
        return gson.toJson(clan, Clan.class);
    }

    private Clan fromJson(String json) {
        return gson.fromJson(json, Clan.class);
    }

    public void save() {
        List<String> jsonList = new CopyOnWriteArrayList<>();
        for (Clan clan : clanList) {
            jsonList.add(toJson(clan));
        }
        config.getConfig().set("clans", jsonList);
        for (UUID uuid : invites.keySet()) {
            List<String> clanNames = new ArrayList<>();
            for (Clan clan : invites.get(uuid)) clanNames.add(clan.getName());
            config.getConfig().set("invites." + uuid.toString(), clanNames);
        }
        config.saveConfig();
    }

    //

    public boolean isInClan(UUID uuid) {
        for (Clan clan : clanList) {
            if (clan.getOwner().equals(uuid)) return true;
            if (clan.getModerators().contains(uuid)) return true;
            if (clan.getMembers().contains(uuid)) return true;
        }
        return false;
    }

    public Clan getClan(UUID uuid) {
        for (Clan clan : clanList) {
            if (clan.getOwner().equals(uuid)) return clan;
            if (clan.getModerators().contains(uuid)) return clan;
            if (clan.getMembers().contains(uuid)) return clan;
        }
        return null;
    }

    public boolean isModerator(UUID uuid) {
        if (isInClan(uuid)) {
            return getClan(uuid).getModerators().contains(uuid);
        }
        return false;
    }

    public boolean isOwner(UUID uuid) {
        if (isInClan(uuid)) {
            return getClan(uuid).getOwner().equals(uuid);
        }
        return false;
    }

    //

    public Clan getByName(String name) {
        return clanList.stream().filter((clan -> clan.getName().equals(name))).findFirst().orElse(null);
    }

    public Clan getByTag(String tag) {
        return clanList.stream().filter((clan -> clan.getTag().equals(tag))).findFirst().orElse(null);
    }

    public void addToList(Clan clan) {
        clanList.add(clan);
    }

    public void removeFromList(Clan clan) {
        clanList.remove(clan);
    }

    //

    public int createClan(String name, String tag, UUID owner) {
        Clan clan = new Clan(owner, (clanList.size() + 1), new ArrayList<>(), new ArrayList<>(), name, tag, System.currentTimeMillis(), 0L, 0L, "§e", 7);
        addToList(clan);
        return clan.getId();
    }

    public void deleteClan(Clan clan) {
        clanList.remove(clan);
        removeFromList(clan);
    }

    public void inviteMember(Clan clan, UUID toBeInvited) {
        List<Clan> clanInvites = (invites.get(toBeInvited) != null ? invites.get(toBeInvited) : new ArrayList<>());
        clanInvites.add(clan);
        invites.put(toBeInvited, clanInvites);
    }

    public List<Clan> getInvites(UUID uuid) {
        return (invites.get(uuid) != null ? invites.get(uuid) : new ArrayList<>());
    }

    public void acceptInvite(UUID uuid, Clan clan) {
        List<Clan> clanInvites = getInvites(uuid);
        if (clanInvites.contains(clan)) {
            clanInvites.remove(clan);
            removeFromList(clan);
            List<UUID> members = clan.getMembers();
            members.add(uuid);
            clan.setMembers(members);
            addToList(clan);
        }
    }

    public void rejectInvite(UUID uuid, Clan clan) {
        List<Clan> clanInvites = getInvites(uuid);
        clanInvites.remove(clan);
    }

    public void upgradeRank(UUID toBeUpgraded, Clan clan) {
        List<UUID> members = clan.getMembers(), moderators = clan.getModerators();
        if (members.contains(toBeUpgraded)) {
            removeFromList(clan);
            members.remove(toBeUpgraded);
            moderators.add(toBeUpgraded);
            addToList(clan);
        }
    }

    public void downgradeRank(UUID toBeDowngraded, Clan clan) {
        List<UUID> members = clan.getMembers(), moderators = clan.getModerators();
        if (moderators.contains(toBeDowngraded)) {
            removeFromList(clan);
            moderators.remove(toBeDowngraded);
            members.add(toBeDowngraded);
            addToList(clan);
        }
    }

    public String getRank(UUID uuid, Clan clan) {
        List<UUID> members = clan.getMembers(), moderators = clan.getModerators();
        if (clan.getOwner().toString().equalsIgnoreCase(uuid.toString())) {
            return "§4Owner";
        }
        if (moderators.contains(uuid)) return "§cModerator";
        return "§aMember";
    }

}
