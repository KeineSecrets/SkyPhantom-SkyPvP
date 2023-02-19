package eu.skyphantom.skypvp.provider;

import eu.skyphantom.skypvp.SkyPvP;
import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class VanishProvider {

    static List<UUID> vanish = new CopyOnWriteArrayList<>();

    public static void add(Player player) {
        vanish.add(player.getUniqueId());
        Bukkit.getOnlinePlayers().forEach(player1 -> {
            if (!player1.hasPermission("system.team")) {
                player1.hidePlayer(player);
            }
        });

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Vanish | " + new SimpleDateFormat("dd.MM.yyyy, HH:mm").format(new Date()));
        embedBuilder.setDescription("\n> **Teammitglied** × " + player.getName() + "\n> **Status** × ` ✅ `\n");
        embedBuilder.setTimestamp(OffsetDateTime.now());
        embedBuilder.setColor(Color.YELLOW.darker());

        SkyPvP.getInstance().getDiscord().getJda().getTextChannelById(1069023799372157010L).sendMessageEmbeds(embedBuilder.build()).queue();
    }

    public static void remove(Player player) {
        vanish.remove(player.getUniqueId());
        Bukkit.getOnlinePlayers().forEach(player1 -> {
            player1.showPlayer(player);
        });

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Vanish | " + new SimpleDateFormat("dd.MM.yyyy, HH:mm").format(new Date()));
        embedBuilder.setDescription("\n> **Teammitglied** × " + player.getName() + "\n> **Status** × ` ❌ `\n");
        embedBuilder.setTimestamp(OffsetDateTime.now());
        embedBuilder.setColor(Color.YELLOW.darker());

        SkyPvP.getInstance().getDiscord().getJda().getTextChannelById(1069023799372157010L).sendMessageEmbeds(embedBuilder.build()).queue();
    }

    public static boolean get(Player player) {
        return vanish.contains(player.getUniqueId());
    }

}
