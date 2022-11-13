package eu.skyphantom.skypvp.lms.tasks;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.lms.LMS;
import eu.skyphantom.skypvp.lms.State;
import eu.skyphantom.skypvp.lms.events.LMSStartEvent;
import eu.skyphantom.skypvp.utils.scoreboard.ScoreboardHelper;
import net.minecraft.server.v1_8_R3.ScoreboardObjective;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LMSLobbyTask {

    private static int taskId;
    private static int countdown = 60;
    private static final int minimumPlayers = 2;

    public static void startTask() {
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyPvP.getInstance(), () -> {
            if (LMS.getInstance().getState() == State.LOBBY) {
                if (LMS.getInstance().getLMSHandler().getPlayers().size() >= minimumPlayers) {
                    countdown--;
                    for (Player player : LMS.getInstance().getLMSHandler().getPlayers()) {
                        ScoreboardHelper.updateScoreboard(player);
                    }
                }
                if (countdown == 0) {
                    stopTask();
                    Bukkit.getPluginManager().callEvent(new LMSStartEvent(LMS.getInstance().getLMSHandler().getPlayers(), System.currentTimeMillis()));
                    countdown = 60;
                }
            }
        }, 0L, 20L);
    }

    public static void stopTask() {
        Bukkit.getScheduler().cancelTask(taskId);
    }

    public static int getCountdown() {
        return countdown;
    }

    public static void setCountdown(int countdown) {
        LMSLobbyTask.countdown = countdown;
    }
}
