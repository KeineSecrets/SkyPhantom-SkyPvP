package eu.skyphantom.skypvp.clans;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.api.Config;
import eu.skyphantom.skypvp.utils.UUIDFetcher;
import eu.skyphantom.skypvp.utils.text.TextComponentBuilder;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class ClanManager {

    Config config = new Config("plugins/SkyPvP/", "clans.yml");
    public Map<UUID, UUID> invites = new HashMap<>();

    public boolean isInClan(UUID uuid) {
        return this.config.getConfig().getString("Spieler." + uuid + ".Clan") != null;
    }

    public String getClan(UUID uuid) {
        String clan = this.config.getConfig().getString("Spieler." + uuid + ".Clan");
        return clan;
    }

    public String getClanTag(UUID uuid) {
        String tag = this.config.getConfig().getString("Clans." + getClan(uuid) + ".Name");
        return tag;
    }

    public String getClanTag(String clanName) {
        String tag = this.config.getConfig().getString("Clans." + clanName + ".Name");
        return tag;
    }

    public String getClanOwner(String clanName) {
        String tag = this.config.getConfig().getString("Clans." + clanName + ".Owner");
        return UUIDFetcher.getName(UUID.fromString(tag));
    }

    public List<String> getPlayers(String clan) {
        List<String> list = this.config.getConfig().getStringList("Clans." + clan + ".Members");
        return list;
    }

    public List<String> getAllClans() {
        List<String> clans = new ArrayList<>();
        if (this.config.getConfig().getConfigurationSection("Clans") == null) return clans;
        clans.addAll(this.config.getConfig().getConfigurationSection("Clans").getKeys(false));
        return clans;
    }

    public List<String> getAllClansByName(String clan) {
        List<String> clans = new ArrayList<>();
        if (this.config.getConfig().getConfigurationSection("Clans") == null) return clans;
        for (String name : this.config.getConfig().getConfigurationSection("Clans").getKeys(false)) {
            if (name.equalsIgnoreCase(clan)) clans.add(name);
        }
        return clans;
    }

    public List<String> getAllClansByTag(String tag) {
        List<String> clans = new ArrayList<>();
        if (this.config.getConfig().getConfigurationSection("Clans") == null) return clans;
        for (String name : this.config.getConfig().getConfigurationSection("Clans").getKeys(false)) {
            if (config.getConfig().getString("Clans." + name + ".Name").equalsIgnoreCase(tag)) clans.add(name);
        }
        return clans;
    }

    public void addPlayer(String clan, UUID uuid) {
        List<String> members = this.config.getConfig().getStringList("Clans." + clan + ".Members");
        members.add(uuid.toString());
        this.config.getConfig().set("Clans." + clan + ".Members", members);
        this.config.getConfig().set("Spieler." + uuid + ".Clan", clan);
        config.saveConfig();
    }

    public void removePlayer(String clan, UUID uuid) {
        List<String> members = this.config.getConfig().getStringList("Clans." + clan + ".Members");
        members.remove(uuid.toString());
        this.config.getConfig().set("Clans." + clan + ".Members", members);
        this.config.getConfig().set("Spieler." + uuid + ".Clan", clan);
        config.saveConfig();
        for (String member : members) {
            Player t = Bukkit.getPlayer(member);
            if (t != null) t.getPlayer().sendMessage(SkyPvP.PREFIX + "§7Der Spieler §a" + UUIDFetcher.getName(uuid) + "§7 hat den Clan verlassen§8.");
        }
    }

    public boolean clanExists(String clan) {
        return this.config.getConfig().getString("Clans." + clan + ".Owner") != null;
    }

    public boolean clanTagExists(String tag) {
        if (config.getConfig().getConfigurationSection("Clans") == null) return false;
        for (String s : config.getConfig().getConfigurationSection("Clans").getKeys(false)) {
            if (config.getConfig().getString("Clans." + s + ".Name") == null) continue;
            if (config.getConfig().getString("Clans." + s + ".Name").equalsIgnoreCase(tag)) return true;
        }
        return false;
    }

    public void createClan(String clanName, String clanTag, UUID uuid) {
        this.config.getConfig().set("Clans." + clanName + ".Name", clanTag);
        this.config.getConfig().set("Clans." + clanName + ".Color", "§f");
        this.config.getConfig().set("Clans." + clanName + ".Kills", 0.0D);
        if (this.config.getConfig().getConfigurationSection("Clans") != null)
            this.config.getConfig().set("Clans." + clanName + ".ID", (this.config.getConfig().getConfigurationSection("Clans").getKeys(false).size()));
        else this.config.getConfig().set("Clans." + clanName + ".ID", 1);
        this.config.getConfig().set("Clans." + clanName + ".Owner", uuid.toString());
        this.config.getConfig().set("Spieler." + uuid + ".Clan", clanName);
        List<String> m = this.config.getConfig().getStringList("Clans." + clanName + ".Members");
        m.add(uuid.toString());
        this.config.getConfig().set("Clans." + clanName + ".Members", m);
        config.saveConfig();
    }

    public int getClanId(String clanName) {
        return this.config.getConfig().getInt("Clans." + clanName + ".ID");
    }

    public String getClanColor(String clanName) {
        double clanKills = this.config.getConfig().getDouble("Clans." + clanName + ".Kills");
        String clanColor = this.config.getConfig().getString("Clans." + clanName + ".Color");
        if (clanName.equalsIgnoreCase("team")) {
            return "§c";
        }
        if (clanKills < 10) {
            clanColor = "§a";
        }
        if (clanKills < 25) {
            clanColor = "§a";
        }
        if (clanKills < 75) {
            clanColor = "§6";
        }
        if (clanKills < 125) {
            clanColor = "§2§l";
        }
        if (clanKills < 150) {
            clanColor = "§d§l";
        }
        if (clanKills < 220) {
            clanColor = "§5§l";
        }
        return clanColor;
    }

    public String getClanColor(UUID uuid) {
        String clanName = getClan(uuid);
        double clanKills = this.config.getConfig().getDouble("Clans." + clanName + ".Kills");
        String clanColor = this.config.getConfig().getString("Clans." + clanName + ".Color");
        if (clanName.equalsIgnoreCase("team")) {
            return "§c";
        }
        if (clanKills < 10) {
            clanColor = "§a";
        }
        if (clanKills < 25) {
            clanColor = "§a";
        }
        if (clanKills < 75) {
            clanColor = "§6";
        }
        if (clanKills < 125) {
            clanColor = "§2§l";
        }
        if (clanKills < 150) {
            clanColor = "§d§l";
        }
        if (clanKills < 220) {
            clanColor = "§5§l";
        }
        return clanColor;
    }

    public void deleteClan(String clan) {
        List<String> members = this.config.getConfig().getStringList("Clans." + clan + ".Members");
        for (String member : members) {
            Player t = Bukkit.getPlayer(member);
            if (t != null) t.getPlayer().sendMessage(SkyPvP.PREFIX + "§7Der Clan wurde gelöscht§8.");
        }
        for (String member : members) this.config.getConfig().set("Spieler." + member + ".Clan", null);
        this.config.getConfig().set("Clans." + clan, null);
        config.saveConfig();
    }

    public void invitePlayer(UUID uuid, UUID inviter) {
        Player pInviter = Bukkit.getPlayer(inviter);
        Player pInvited = Bukkit.getPlayer(uuid);
        if (this.invites.containsKey(uuid)) {
            if (pInviter != null) pInviter.sendMessage(SkyPvP.PREFIX + "§7Der Spieler wurde bereits eingeladen§8.");
            return;
        }
        this.invites.put(uuid, inviter);
        pInviter.sendMessage(SkyPvP.PREFIX + "§7Du hast den Spieler §a" + (pInvited != null ? pInvited.getDisplayName() : UUIDFetcher.getName(uuid)) + "§7 in deinen Clan eingeladen§8.");
        if (pInvited != null) {
            TextComponentBuilder textComponentBuilder1 = new TextComponentBuilder(SkyPvP.PREFIX + "§7Du §7wurdest §7in §7den §a" + getClan(inviter) + "§8-§7Clan §7eingeladen§8.\n");
            TextComponentBuilder textComponentBuilder2 = new TextComponentBuilder(SkyPvP.PREFIX + "§7Annehmen§8? §a§lKLICKE §a§lHIER\n").setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/clan accept " + getClan(inviter)));
            TextComponentBuilder textComponentBuilder3 = new TextComponentBuilder(SkyPvP.PREFIX + "§7Ablehnen§8? §c§lKLICKE §c§lHIER").setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/clan deny " + getClan(inviter)));

            TextComponent componentOne = textComponentBuilder1.toTextComponent();
            TextComponent componentTwo = textComponentBuilder2.toTextComponent();
            TextComponent componentThree = textComponentBuilder3.toTextComponent();

            pInvited.spigot().sendMessage(componentOne, componentTwo, componentThree);
        }
    }

    public void acceptInvite(UUID whoWasInvited) {
        UUID inviter = this.invites.get(whoWasInvited);
        Player pInvited = Bukkit.getPlayer(whoWasInvited);
        String clanName = getClan(inviter);
        if (!isInClan(whoWasInvited)) {
            addPlayer(clanName, whoWasInvited);
            if (Bukkit.getPlayer(inviter) != null) Bukkit.getPlayer(inviter).sendMessage(SkyPvP.PREFIX + "§7Der Spieler §a" + (pInvited != null ? pInvited.getDisplayName() : UUIDFetcher.getName(whoWasInvited)) + "§7 ist dem Clan beigetreten§8.");
            if (pInvited != null) pInvited.sendMessage(SkyPvP.PREFIX + "§7Du bist dem Clan §a" + clanName + "§7 beigetreten§8.");
            this.invites.remove(whoWasInvited);
            return;
        }
        if (pInvited != null) pInvited.sendMessage(SkyPvP.PREFIX + "§7Du bist bereits in einem Clan§8.");
    }

    public void denyInvite(UUID whoWasInvited) {
        UUID inviter = this.invites.get(whoWasInvited);
        Player pInvited = Bukkit.getPlayer(whoWasInvited);
        String clanName = getClan(inviter);
        if (Bukkit.getPlayer(inviter) != null) Bukkit.getPlayer(inviter).sendMessage(SkyPvP.PREFIX + "§7Der Spieler §a" + (pInvited != null ? pInvited.getDisplayName() : UUIDFetcher.getName(whoWasInvited)) + "§7 hat die Einladung abgelehnt§8.");
        this.invites.remove(whoWasInvited);
        if (pInvited != null) pInvited.sendMessage(SkyPvP.PREFIX + "§7Du hast die Einladung abgelehnt§8.");
    }

    public boolean isOwner(UUID uuid) {
        String owner = this.config.getConfig().getString("Clans." + getClan(uuid) + ".Owner");
        return owner != null;
    }

    public boolean isValid(String code) {
        return code.matches("[A-Za-z0-9_-]*");
    }

    public void sendToClanchat(String message, Player player) {
        String clanName = getClan(player.getUniqueId());
        for (String name : getPlayers(clanName)) {
            if (Bukkit.getPlayer(UUID.fromString(name)) == null) continue;
            Bukkit.getPlayer(UUID.fromString(name)).sendMessage(SkyPvP.PREFIX + " §8(§a§lCC§8) §7" + (isOwner(player.getUniqueId()) ? "§c" : "§a") + player.getName() + "§8: §7" + message.trim());
        }
    }


}
