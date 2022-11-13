package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.api.VerifyAPI;
import eu.skyphantom.skypvp.utils.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;
import java.time.OffsetDateTime;

public class VerifyCommand extends Command {

    public VerifyCommand() {
        super("verify");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (VerifyAPI.isVerified(player.getUniqueId())) {
                player.sendMessage(SkyPvP.PREFIX + "§7Du bist bereits verifiziert§8.");
                return true;
            }
            if (args.length != 0) {
                String code = args[0].trim();
                if (!VerifyAPI.contains(code)) {
                    player.sendMessage(SkyPvP.PREFIX + "§7Der Code §8'§a" + code + "§8'§7 konnte nicht gefunden werden§8.");
                    player.sendMessage(SkyPvP.PREFIX + "§7Achte auf Groß und Kleinschreibung und probiere es erneut§8.");
                    return true;
                }
                long discordUserId = VerifyAPI.getDiscordUserId(code);
                VerifyAPI.verify(player.getUniqueId(), discordUserId);
                Utils.sendTitle(player, 0, 50, 10, SkyPvP.PREFIX + "§a§lVERIFY §8▎", "§a" + SkyPvP.getInstance().getDiscord().getJda().getUserById(discordUserId).getAsTag().replace("#", "§8#§a"));
                player.sendMessage(SkyPvP.PREFIX + "§7Du wurdest mit dem folgenden Discord§8-§7Account verlinkt§8:");
                player.sendMessage(SkyPvP.PREFIX + "§a" + SkyPvP.getInstance().getDiscord().getJda().getUserById(discordUserId).getAsTag().replace("#", "§8#§a"));
                SkyPvP.getInstance().getDiscord().getJda().getUserById(discordUserId).openPrivateChannel().queue(privateChannel -> {
                    EmbedBuilder embedBuilder = new EmbedBuilder();

                    embedBuilder.setTitle("✅ × VERIFY");
                    embedBuilder.setDescription("> " + SkyPvP.getInstance().getDiscord().getJda().getUserById(discordUserId).getAsMention() + "\n" +
                            "\n" +
                            "> Du wurdest verifiziert × **" + player.getName() + "**\n" +
                            ""
                    );
                    embedBuilder.setThumbnail("https://minotar.net/avatar/" + player.getName());
                    embedBuilder.setColor(Color.GREEN.darker());
                    embedBuilder.setTimestamp(OffsetDateTime.now());
                    privateChannel.sendMessageEmbeds(embedBuilder.build()).queue();
                    privateChannel.close().queue();
                });
                return true;
            }
            player.sendMessage(SkyPvP.PREFIX + "§8/§averify §8<§2Code§8>");
        }
        return false;
    }
}
