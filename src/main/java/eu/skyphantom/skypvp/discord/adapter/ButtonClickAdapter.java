package eu.skyphantom.skypvp.discord.adapter;

import eu.skyphantom.skypvp.SkyPvP;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.EnumSet;

public class ButtonClickAdapter extends ListenerAdapter {

    @Override
    public void onButtonClick(@NotNull ButtonClickEvent event) {
        if (event.getButton().getId().equals("openTicketNormal")) {
            Guild guild = event.getGuild();
            if (guild.getTextChannelsByName("ticket-" + event.getUser().getId(), false).isEmpty() || guild.getTextChannelsByName("ticket-" + event.getUser().getId(), false).get(0) == null) {

                if (event.getMember().getRoles().contains(guild.getRoleById(1076491532175151134L))) {
                    event.reply("` ❌ ` › Du kannst kein Ticket erstellen, da du ein Teammitglied bist.").setEphemeral(true).queue();
                    return;
                }
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setTitle("✅ × TICKET VON " + event.getUser().getName().toUpperCase());
                embedBuilder.setDescription("> *Herzlich Willkommen im Ticketsystem.*\n" + "> *Schildere schon einmal dein Problem, ein Teammitglied wird*\n" + "> *sich gleich um dich kümmern*");
                embedBuilder.setColor(Color.GREEN.darker());
                embedBuilder.setTimestamp(OffsetDateTime.now());

                guild.createTextChannel("ticket-" + event.getUser().getId(), guild.getCategoryById(1056116741362679860L)).addPermissionOverride(event.getMember(), EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_WRITE), null).addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_WRITE)).addPermissionOverride(guild.getRoleById(1076491532175151134L), EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_WRITE), null).complete().sendMessage("" + guild.getRoleById(1076491532175151134L).getAsMention() + "").queue(message -> {
                    message.getChannel().sendMessageEmbeds(embedBuilder.build()).setActionRow(Button.danger("closeThisTicket", "❌ › Ticket schließen")).queue(message1 -> {
                        event.reply("` ✅ ` › Dein Ticket wurde erstellt: <#" + message.getChannel().getIdLong() + ">").setEphemeral(true).queue(interactionHook -> {
                            EmbedBuilder embedBuilderLog = new EmbedBuilder();
                            embedBuilderLog.setTitle("Ticket | " + new SimpleDateFormat("dd.MM.yyyy, HH:mm").format(new Date()));
                            embedBuilderLog.setDescription("\n> **Ticket** × <#" + message.getChannel().getId() + ">\n> **Name** × " + event.getUser().getAsMention() + "\n> **Status** × ` ✅ `\n");
                            embedBuilderLog.setTimestamp(OffsetDateTime.now());
                            embedBuilderLog.setColor(Color.CYAN.darker());

                            SkyPvP.getInstance().getDiscord().getJda().getTextChannelById(1069023799372157010L).sendMessageEmbeds(embedBuilderLog.build()).queue();
                        });
                    });
                });
            } else {
                event.reply("` ❌ ` › Du hast bereits ein offenes Ticket: " + guild.getTextChannelsByName("ticket-" + event.getUser().getId(), false).get(0).getAsMention()).setEphemeral(true).queue();
            }
        } else if (event.getButton().getId().equals("openTicketAdmin")) {
            Guild guild = event.getGuild();
            if (guild.getTextChannelsByName("admin-" + event.getUser().getId(), false).isEmpty() || guild.getTextChannelsByName("admin-" + event.getUser().getId(), false).get(0) == null) {

                if (event.getMember().getRoles().contains(guild.getRoleById(1076491532175151134L))) {
                    event.reply("` ❌ ` › Du kannst kein Adminticket erstellen, da du ein Teammitglied bist.").setEphemeral(true).queue();
                    return;
                }
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setTitle("✅ × TICKET VON " + event.getUser().getName().toUpperCase());
                embedBuilder.setDescription("> *Herzlich Willkommen im Ticketsystem.*\n" + "> *Schildere schon einmal dein Problem, ein Administrator wird*\n" + "> *sich gleich um dich kümmern*");
                embedBuilder.setColor(Color.GREEN.darker());
                embedBuilder.setTimestamp(OffsetDateTime.now());

                guild.createTextChannel("admin-" + event.getUser().getId(), guild.getCategoryById(1056116741362679860L)).addPermissionOverride(event.getMember(), EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_WRITE), null).addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_WRITE)).addPermissionOverride(guild.getRoleById(1076491532175151134L), EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_WRITE), null).complete().sendMessage("" + guild.getRoleById(1076491532175151134L).getAsMention() + "").queue(message -> {
                    message.getChannel().sendMessageEmbeds(embedBuilder.build()).setActionRow(Button.danger("closeThisTicket", "❌ › Ticket schließen")).queue(message1 -> {
                        event.reply("` ✅ ` › Dein Ticket wurde erstellt: <#" + message.getChannel().getIdLong() + ">").setEphemeral(true).queue(interactionHook -> {
                            EmbedBuilder embedBuilderLog = new EmbedBuilder();
                            embedBuilderLog.setTitle("Adminticket | " + new SimpleDateFormat("dd.MM.yyyy, HH:mm").format(new Date()));
                            embedBuilderLog.setDescription("\n> **Adminticket** × <#" + message.getChannel().getId() + ">\n> **Name** × " + event.getUser().getAsMention() + "\n> **Status** × ` ✅ `\n");
                            embedBuilderLog.setTimestamp(OffsetDateTime.now());
                            embedBuilderLog.setColor(Color.CYAN.darker());

                            SkyPvP.getInstance().getDiscord().getJda().getTextChannelById(1069023799372157010L).sendMessageEmbeds(embedBuilderLog.build()).queue();
                        });
                    });
                });
            } else {
                event.reply("` ❌ ` › Du hast bereits ein offenes Ticket: " + guild.getTextChannelsByName("admin-" + event.getUser().getId(), false).get(0).getAsMention()).setEphemeral(true).queue();
            }
        } else if (event.getButton().getId().equals("closeThisTicket")) {
            Guild guild = event.getGuild();
            if (event.getMember().getRoles().contains(guild.getRoleById(1076491532175151134L))) {
                long id = event.getChannel().getIdLong();
                SkyPvP.getInstance().getDiscord().getJda().getTextChannelById(id).delete().queue(unused -> {
                    EmbedBuilder embedBuilderLog = new EmbedBuilder();
                    embedBuilderLog.setTitle("Ticket | " + new SimpleDateFormat("dd.MM.yyyy, HH:mm").format(new Date()));
                    embedBuilderLog.setDescription("\n> **Ticket** × " + id + "\n> **Name** × " + event.getUser().getAsMention() + "\n> **Status** × ` ❌ `\n");
                    embedBuilderLog.setTimestamp(OffsetDateTime.now());
                    embedBuilderLog.setColor(Color.CYAN.darker());

                    SkyPvP.getInstance().getDiscord().getJda().getTextChannelById(1069023799372157010L).sendMessageEmbeds(embedBuilderLog.build()).queue();
                });
            }
        } else if (event.getButton().getId().equals("collectRewards1")) {
            event.reply("` 🤔 ` › Benutze **/reward <Ingame-Name>**").setEphemeral(true).queue();
        }
    }
}

