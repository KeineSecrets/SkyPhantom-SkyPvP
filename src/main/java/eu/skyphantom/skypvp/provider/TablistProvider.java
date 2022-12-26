package eu.skyphantom.skypvp.provider;

import eu.skyphantom.skypvp.clans.ClanManager;
import net.luckperms.api.model.group.Group;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

public class TablistProvider {

    private static Scoreboard scoreboard;

    public TablistProvider() {
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        scoreboard.registerNewTeam("01owner");
        scoreboard.registerNewTeam("99spieler");

        scoreboard.getTeam("01owner").setPrefix("§4§lOWNER§8 ▎ §7");
        scoreboard.getTeam("99spieler").setPrefix("§7");
    }



    public static Scoreboard getScoreboard() {
        return scoreboard;
    }

    public static void setPrefix(Player player) {
        final String team;
        final Group group = LuckPermsProvider.getGroup(player.getUniqueId());
        if (group == null || group.getName().equalsIgnoreCase("default")) {
            team = "99spieler";
        } else {
            team = "99spieler";
        }

        if (scoreboard.getTeam(team) == null) {
            scoreboard.registerNewTeam(team);
        } else {
            scoreboard.getTeam(team).addPlayer(player);
        }

        Bukkit.getOnlinePlayers().forEach(all -> all.setScoreboard(scoreboard));
        ClanManager clanManager = new ClanManager();
        if (!player.isOnline()) return;
        if (clanManager.isInClan(player.getUniqueId())) {
            player.setPlayerListName(scoreboard.getTeam(team).getPrefix() + (player.getName()) + " §8[" + clanManager.getClanColor(clanManager.getClan(player.getUniqueId())) + clanManager.getClanTag(player.getUniqueId()) + "§8]" + (VanishProvider.get(player) ? " §8(§b§lV§8)" : ""));
        } else {
            player.setPlayerListName(scoreboard.getTeam(team).getPrefix() + (player.getName()) + (VanishProvider.get(player) ? " §8(§b§lV§8)" : ""));
        }
        player.setDisplayName(scoreboard.getTeam(team).getPrefix() + (player.getName()));

    }

}
