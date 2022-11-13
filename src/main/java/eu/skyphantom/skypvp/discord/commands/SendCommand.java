package eu.skyphantom.skypvp.discord.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.interactions.components.ButtonStyle;

import java.awt.*;
import java.time.OffsetDateTime;
import java.util.Collections;

public class SendCommand {

    public static void execute(SlashCommandEvent event) {
        if (event.getName().equals("send")) {
            String what = event.getOption("what").getAsString();
            if (what.equals("verify")) {
                if (event.getUser().getIdLong() != 943171984785821706L) return;
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setTitle("✅ × VERIFY");
                embedBuilder.setDescription("> *In diesem Kanal hast du die Möglichkeit, dich zu verifizieren.*\n" +
                        "> *Dadurch wird dein Minecraft-Account mit deinem Discord-Account verknüpft.*\n" +
                        "> *Um dich zu verifizieren, klicke einfach auf die Schaltfläche unter diesem Embed.*\n" +
                        "\n" +
                        "> **WICHTIG** » *Du **musst** private Nachrichten von unserem Server erlauben.*\n" +
                        "\n" +
                        "> *Danach erhältst du ein Verifizierungs-Token und weitere*\n" +
                        "> *Informationen über Direktnachricht von unserem Bot.*\n" +
                        "\n" +
                        "> *Nach einer erfolgreichen Verifizierung erhältst du die folgenden **Vorteile**:*\n" +
                        "\n" +
                        "> 1⃣ × Du bekommst Zugang zu exklusiven Belohnungen\n" +
                        "> 2⃣ × Du kannst an Events teilnehmen\n" +
                        "> 3⃣ × Du kannst am PvP teilnehmen und vieles mehr.");
                embedBuilder.setColor(Color.GREEN.darker());
                embedBuilder.setTimestamp(OffsetDateTime.now());
                event.getChannel().sendMessageEmbeds(embedBuilder.build()).setActionRow(Collections.singletonList(Button.of(ButtonStyle.SUCCESS, "verificationButton-8497w56", "✅" ))).queue();
            } else if (what.equals("ticket")) {
                if (event.getUser().getIdLong() != 943171984785821706L) return;
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setTitle("✅ × TICKETSYSTEM");
                embedBuilder.setDescription("> *Klicke auf den Knopf, um ein Ticket zu öffnen.*");
                embedBuilder.setColor(Color.GREEN.darker());
                embedBuilder.setTimestamp(OffsetDateTime.now());
                event.getChannel().sendMessageEmbeds(embedBuilder.build()).setActionRow(Button.success("openTicketNormal", "💐 › Allgemeine Frage"), Button.danger("openTicketAdmin", "⚠️ › Admins kontaktieren")).queue();
            } else if (what.equals("rewards")) {
                if (event.getUser().getIdLong() != 943171984785821706L) return;
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setTitle("✅ × REWARDS");
                embedBuilder.setDescription("> *Hole deine Rewards ab, sogar wenn der Server in Wartungsarbeiten ist!*");
                embedBuilder.setColor(Color.CYAN.darker());
                embedBuilder.setTimestamp(OffsetDateTime.now());
                event.getChannel().sendMessageEmbeds(embedBuilder.build()).setActionRow(Button.success("collectRewards1", "✅ › Rewards abholen")).queue();
            }
        }
    }

}
