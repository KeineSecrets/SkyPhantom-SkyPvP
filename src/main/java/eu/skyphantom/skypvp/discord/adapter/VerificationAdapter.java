package eu.skyphantom.skypvp.discord.adapter;

import eu.skyphantom.skypvp.api.Config;
import eu.skyphantom.skypvp.api.VerifyAPI;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.time.OffsetDateTime;

public class VerificationAdapter extends ListenerAdapter {

    private MessageEmbed getEmbed(User user) {
        String code;
        if (VerifyAPI.isVerified(user.getIdLong())) {
            EmbedBuilder embedBuilder = new EmbedBuilder();

            embedBuilder.setTitle("✅ × VERIFY");
            embedBuilder.setDescription("> " + user.getAsMention() + "\n" + "\n" + "> Du bist bereits verifiziert.\n" + "");

            embedBuilder.setColor(Color.GREEN.darker());
            embedBuilder.setTimestamp(OffsetDateTime.now());

            return embedBuilder.build();
        }
        if (VerifyAPI.getDiscordVerifyMap().containsKey(user.getIdLong())) {
            code = VerifyAPI.getDiscordVerifyMap().get(user.getIdLong());
        } else {
            code = VerifyAPI.add(user.getIdLong());
        }
        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setTitle("✅ × VERIFY");
        embedBuilder.setDescription("> " + user.getAsMention() + "\n" + "\n" + "> *Um diesen Verifizierungsprozess abzuschließen, joine unserem*\n" + "> *Minecraft-Server und gib den folgenden Befehl ein*\n" + "> ` /verify " + code + " `.\n" + "");

        embedBuilder.setColor(Color.GREEN.darker());
        embedBuilder.setTimestamp(OffsetDateTime.now());

        return embedBuilder.build();
    }

    @Override
    public void onButtonClick(@NotNull ButtonClickEvent event) {
        String id = event.getButton().getId();
        Config userConfig = new Config("plugins/SkyPvP/discord/", event.getUser().getIdLong() + ".yml");
        if (id.equals("verificationButton-8497w56")) {
            event.reply("> *Überprüfe deine DMs!*").setEphemeral(true).queue(interactionHook -> {
                event.getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessageEmbeds(getEmbed(event.getUser())).queue());
            });
        }
    }

}
