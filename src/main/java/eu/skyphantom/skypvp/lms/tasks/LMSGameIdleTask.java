package eu.skyphantom.skypvp.lms.tasks;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.lms.LMS;
import eu.skyphantom.skypvp.lms.State;
import eu.skyphantom.skypvp.utils.Utils;
import eu.skyphantom.skypvp.utils.time.TimeUtil;
import org.bukkit.Bukkit;

import java.util.concurrent.TimeUnit;

public class LMSGameIdleTask {

    public static long end, endMillis;
    private static int taskId;

    public static void startTask() {
        end = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15);

        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyPvP.getInstance(), () -> {
            endMillis = (end - System.currentTimeMillis());
            boolean end = (endMillis <= 0);
            String time = TimeUtil.timeToString(endMillis, true);
            int size = LMS.getInstance().getLMSHandler().getPlayers().size();
            if (end) {
                int winner = Utils.getRandom().nextInt(size);
                LMS.getInstance().getLMSHandler().stop(LMS.getInstance().getLMSHandler().getPlayers().get(winner));
                stopTask();
            } else if (size == 0) {
                LMS.getInstance().getLMSHandler().stop(null);
                stopTask();
            } else if (LMS.getInstance().getState() == State.INGAME && LMS.getInstance().getLMSHandler().getPlayers().size() == 1) {
                LMS.getInstance().getLMSHandler().stop(LMS.getInstance().getLMSHandler().getPlayers().get(0));
                stopTask();
            }
        }, 0L, 10L);
    }

    public static void stopTask() {
        Bukkit.getScheduler().cancelTask(taskId);
    }

}
