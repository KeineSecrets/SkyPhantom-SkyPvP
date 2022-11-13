package eu.skyphantom.skypvp.provider;

import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class StaffmodeProvider {

    static List<UUID> staffMode = new CopyOnWriteArrayList<>();

    public static void add(Player player) {
        staffMode.add(player.getUniqueId());
    }

    public static void remove(Player player) {
        staffMode.remove(player.getUniqueId());
    }

    public static boolean get(Player player) {
        return staffMode.contains(player.getUniqueId());
    }

}
