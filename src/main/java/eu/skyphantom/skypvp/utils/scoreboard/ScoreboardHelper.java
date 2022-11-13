package eu.skyphantom.skypvp.utils.scoreboard;

import eu.skyphantom.skypvp.lms.LMS;
import eu.skyphantom.skypvp.lms.State;
import eu.skyphantom.skypvp.lms.tasks.LMSLobbyTask;
import eu.skyphantom.skypvp.provider.StatsProvider;
import eu.skyphantom.skypvp.utils.time.TimeUtil;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import static eu.skyphantom.skypvp.lms.tasks.LMSGameIdleTask.endMillis;

public class ScoreboardHelper {

    static Map<UUID, FastBoard> boards = new ConcurrentHashMap<>();

    public static void setScoreboard(Player player) {
        FastBoard fastBoard = new FastBoard(player);
        boards.put(player.getUniqueId(), fastBoard);

    }

    public static void removeScoreboard(Player player) {
        FastBoard fastBoard = boards.get(player.getUniqueId());
        boards.remove(player.getUniqueId());
        if (fastBoard == null) return;
        fastBoard.delete();
    }

    public static void updateScoreboard(Player player) {
        try {
            FastBoard fastBoard = boards.get(player.getUniqueId());
            StatsProvider statsProvider = new StatsProvider(player.getUniqueId());
            String coins = new DecimalFormat("#,###.##").format(statsProvider.getCoins()).replace(",", ".").replace(".", "§8'§6");
            if (LMS.getInstance().getState() == State.LOBBY && LMS.getInstance().getLMSHandler().getPlayers().contains(player)) {
                fastBoard.updateTitle("§8▎ §9§lLMS§8 ▪ §7Lobby");
                fastBoard.updateLines(
                        "§r",
                        "§8▎ §3§l⚔ §8» §7Start§8:",
                        "§8▪ §3" + LMSLobbyTask.getCountdown() + " Sekunden",
                        "§r"
                );
            } else if (LMS.getInstance().getState() == State.INGAME && (LMS.getInstance().getLMSHandler().getPlayers().contains(player))) {
                fastBoard.updateTitle("§8▎ §9§lLMS§8 ▪ §7" + (player.getName().length() > 14 ? player.getName().substring(0, 13) : player.getName()));
                fastBoard.updateLines(
                        "§r",
                        "§8▎ §6❁ §8» §7Verbleibend§8:",
                        "§8▪ §6" + TimeUtil.timeToString(endMillis, true),
                        "§r",
                        "§8▎ §3§l⚔ §8» §7Kills§8:",
                        "§8▪ §3" + LMS.getInstance().getLMSHandler().getPlayerKills(player),
                        "§r",
                        "§8▎ §a❤ §8» §7Spieler§8:",
                        "§8▪ §a" + LMS.getInstance().getLMSHandler().getPlayers().size(),
                        "§r"
                );
            } else if (LMS.getInstance().getState() == State.INGAME && (LMS.getInstance().getLMSHandler().getSpectators().contains(player))) {
                fastBoard.updateTitle("§8▎ §9§lLMS§8 ▪ §7" + (player.getName().length() > 14 ? player.getName().substring(0, 13) : player.getName()));
                fastBoard.updateLines(
                        "§r",
                        "§8▎ §6❁ §8» §7Verbleibend§8:",
                        "§8▪ §6" + TimeUtil.timeToString(endMillis, true),
                        "§r",
                        "§8▎ §a❤ §8» §7Spieler§8:",
                        "§8▪ §a" + LMS.getInstance().getLMSHandler().getPlayers().size(),
                        "§r"
                );
            } else {
                fastBoard.updateTitle("§8▎ §a§lSKYPHANTOM§8 ▪ §7SkyPvP");
                fastBoard.updateLines(
                        "§r",
                        "§8▎ §5✎ §8» §7Profil§8:",
                        "§8▪ §5" + player.getName(),
                        "§r",
                        "§8▎ §6⛂ §8» §7Tokens§8:",
                        "§8▪ §6" + (coins.length() >= 25 ? "§8/§6stats" : coins),
                        "§r",
                        "§8▎ §3§l⚔ §8» §7KDr§8:",
                        "§8▪ §3" + statsProvider.getKDr(),
                        "§r",
                        "§8▎ " + statsProvider.getLeague().getDisplay().substring(0, 2) + "❤ §8» §7Liga§8:",
                        "§8▪ " + statsProvider.getLeague().getDisplay(),
                        "§r"
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
