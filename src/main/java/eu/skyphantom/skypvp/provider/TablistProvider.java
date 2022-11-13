package eu.skyphantom.skypvp.provider;

import eu.skyphantom.skypvp.clans.ClanManager;
import net.luckperms.api.model.group.Group;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import xyz.haoshoku.nick.api.NickAPI;

public class TablistProvider {

    private static Scoreboard scoreboard;

    public TablistProvider() {
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        scoreboard.registerNewTeam("01owner");
        scoreboard.registerNewTeam("02admin");
        scoreboard.registerNewTeam("03staff");
        scoreboard.registerNewTeam("04jrstaff");
        scoreboard.registerNewTeam("05famous");
        scoreboard.registerNewTeam("06phantom");
        scoreboard.registerNewTeam("07titan");
        scoreboard.registerNewTeam("08magician");
        scoreboard.registerNewTeam("09fage");
        scoreboard.registerNewTeam("10shark");
        scoreboard.registerNewTeam("99spieler");

        scoreboard.getTeam("01owner").setPrefix("§4§lOWNER§8 ▎ §7");
        scoreboard.getTeam("02admin").setPrefix("§4§lADMIN§8 ▎ §7");
        scoreboard.getTeam("03staff").setPrefix("§c§lSTAFF§8 ▎ §7");
        scoreboard.getTeam("04jrstaff").setPrefix("§cJRSTAFF§8 ▎ §7");
        scoreboard.getTeam("05famous").setPrefix("§eFAMOUS§8 ▎ §7");
        scoreboard.getTeam("06phantom").setPrefix("§a§lP§8 ▎ §7");
        scoreboard.getTeam("07titan").setPrefix("§9§lT§8 ▎ §7");
        scoreboard.getTeam("08magician").setPrefix("§5§lM§8 ▎ §7");
        scoreboard.getTeam("09fage").setPrefix("§f§lF§8 ▎ §7");
        scoreboard.getTeam("10shark").setPrefix("§6§lS§8 ▎ §7");
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
        } else if (group.getName().equalsIgnoreCase("Owner")) {
            team = "01owner";
        } else if (group.getName().equalsIgnoreCase("Admin")) {
            team = "02admin";
        } else if (group.getName().equalsIgnoreCase("Staff")) {
            team = "03staff";
        } else if (group.getName().equalsIgnoreCase("JrStaff")) {
            team = "04jrstaff";
        } else if (group.getName().equalsIgnoreCase("Famous")) {
            team = "05famous";
        } else if (group.getName().equalsIgnoreCase("Phantom")) {
            team = "06phantom";
        } else if (group.getName().equalsIgnoreCase("Titan")) {
            team = "07titan";
        } else if (group.getName().equalsIgnoreCase("Magician")) {
            team = "08magician";
        } else if (group.getName().equalsIgnoreCase("Fage")) {
            team = "09fage";
        } else if (group.getName().equalsIgnoreCase("Shark")) {
            team = "10shark";
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
            player.setPlayerListName(scoreboard.getTeam(team).getPrefix() + (NickAPI.isNicked(player) ? NickAPI.getName(player) : player.getName()) + " §8[" + clanManager.getClanColor(clanManager.getClan(player.getUniqueId())) + clanManager.getClanTag(player.getUniqueId()) + "§8]" + (VanishProvider.get(player) ? " §8(§b§lV§8)" : ""));
        } else {
            player.setPlayerListName(scoreboard.getTeam(team).getPrefix() + (NickAPI.isNicked(player) ? NickAPI.getName(player) : player.getName()) + (VanishProvider.get(player) ? " §8(§b§lV§8)" : ""));
        }
        player.setDisplayName(scoreboard.getTeam(team).getPrefix() + (NickAPI.isNicked(player) ? NickAPI.getName(player) : player.getName()));

    }

}
