package eu.skyphantom.skypvp.api;

import eu.skyphantom.skypvp.provider.TablistProvider;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class RainbowTab {

    public static Map<Player, Boolean> activePlayers = new HashMap<>();
    public static boolean bold = true;
    static String[] colors = new String[]{"§4", "§4", "§4", "§c", "§c", "§c", "§6", "§6", "§6", "§e", "§e", "§e", "§a", "§a", "§a", "§2", "§2", "§2", "§b", "§b", "§b", "§3", "§3", "§3", "§9", "§9", "§9", "§1", "§1", "§1", "§5", "§5", "§5", "§d", "§d", "§d", "§4", "§4", "§4", "§c", "§c", "§c", "§6", "§6", "§6", "§e", "§e", "§e", "§a", "§a", "§a", "§2", "§2", "§2", "§b", "§b", "§b", "§3", "§3", "§3", "§9", "§9", "§9", "§1", "§1", "§1", "§5", "§5", "§5", "§d", "§d", "§d", "§4", "§4", "§4", "§c", "§c", "§c", "§6", "§6", "§6", "§e", "§e", "§e", "§a", "§a", "§a", "§2", "§2", "§2", "§b", "§b", "§b", "§3", "§3", "§3", "§9", "§9", "§9", "§1", "§1", "§1", "§5", "§5", "§5", "§d", "§d", "§d", "§4", "§4", "§4", "§c", "§c", "§c", "§6", "§6", "§6", "§e", "§e", "§e", "§a", "§a", "§a", "§2", "§2", "§2", "§b", "§b", "§b", "§3", "§3", "§3", "§9", "§9", "§9", "§1", "§1", "§1", "§5", "§5", "§5", "§d", "§d", "§d", "§4", "§4", "§4", "§c", "§c", "§c", "§6", "§6", "§6", "§e", "§e", "§e", "§a", "§a", "§a", "§2", "§2", "§2", "§b", "§b", "§b", "§3", "§3", "§3", "§9", "§9", "§9", "§1", "§1", "§1", "§5", "§5", "§5", "§d", "§d", "§d", "§c", "§c", "§c"};
    private static boolean active = true;

    public static void rainbow() {
        String c = colors[0];
        for (int i = 0; i < (colors.length - 1); i++) {
            colors[i] = colors[(i + 1)];
        }
        colors[colors.length - 1] = c;
        for (Player player : Bukkit.getOnlinePlayers()) TablistProvider.setPrefix(player);
    }

    public static void set(boolean active) {
        RainbowTab.active = active;
    }

    public static boolean get() {
        return RainbowTab.active;
    }

    public static void set(Player player, boolean in) {
        activePlayers.put(player, in);
    }

    public static String rainbowify(String string) {
        String[] chars = string.split("");
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i].equalsIgnoreCase("▎")) {
                builder.append("§8▎§r");
                continue;
            }
            builder.append(colors[i]).append(bold ? "§l" : "").append(chars[i]);
        }
        return builder.toString().trim();
    }

    public static String rainbowifyBold(String string) {
        String[] chars = string.split("");
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            builder.append(colors[i]).append("§l").append(chars[i]);
        }
        return builder.toString().trim();
    }

    public static String rainbowifyNotBold(String string) {
        String[] chars = string.split("");
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            builder.append(colors[i]).append(chars[i]);
        }
        return builder.toString().trim();
    }

    public static String rainbowifyStrikethrough(String string) {
        String[] chars = string.split("");
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            builder.append(colors[i]).append("§m").append(chars[i]);
        }
        return builder.toString().trim();
    }

}