package eu.skyphantom.skypvp.lms;

import eu.skyphantom.skypvp.lms.tasks.LMSGameIdleTask;
import eu.skyphantom.skypvp.lms.tasks.LMSLobbyTask;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LMS {

    public static String PREFIX = "§8▎ §9§lEVENT§8 ▪ §7", NOPERM = "§8▎ §a§lSKYPHANTOM§8 ▪ §cDazu hast du keine Berechtigung§8.";
    private static State state;
    private static LMS instance;
    private LMSHandler lmsHandler;


    public static Map<UUID, Map<Integer, ItemStack>> inv = new HashMap<UUID, Map<Integer, ItemStack>>();

    public LMS() {
        instance = this;
        lmsHandler = new LMSHandler();
        LMS.getInstance().setState(State.NONE);
        LMSLobbyTask.stopTask();
        LMSGameIdleTask.stopTask();
        LMS.getInstance().getLMSHandler().getSpectators().clear();
        LMS.getInstance().getLMSHandler().getPlayers().clear();
    }

    public static LMS getInstance() {
        return instance;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        LMS.state = state;
    }

    public LMSHandler getLMSHandler() {
        return lmsHandler;
    }

    public void setLMSHandler(LMSHandler lmsHandler) {
        this.lmsHandler = lmsHandler;
    }
}
