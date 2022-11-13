package eu.skyphantom.skypvp.discord.adapter;

import eu.skyphantom.skypvp.discord.roles.Roles;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class TeamAdapter extends ListenerAdapter {

    @Override
    public void onGuildMemberRoleRemove(@NotNull GuildMemberRoleRemoveEvent event) {
        Member member = event.getMember();
        Roles finalRole = null;
        for (Roles roles : Roles.values()) {
            Role role = event.getGuild().getRoleById(roles.getId());
            if (member.getRoles().contains(role)) {
                finalRole = roles;
                break;
            }
        }
        if (finalRole == null) return;
        String nick = member.getEffectiveName();
        Roles finalRole1 = finalRole;
        if (nick.contains(finalRole.getToRename())) nick = nick.replace(finalRole.getToRename(), "");
        member.modifyNickname(finalRole1.getToRename() + nick).queue();
    }

    @Override
    public void onGuildMemberRoleAdd(@NotNull GuildMemberRoleAddEvent event) {
        Member member = event.getMember();
        Roles finalRole = null;
        for (Roles roles : Roles.values()) {
            Role role = event.getGuild().getRoleById(roles.getId());
            if (member.getRoles().contains(role)) {
                finalRole = roles;
                break;
            }
        }
        if (finalRole == null) return;
        String nick = member.getEffectiveName();
        Roles finalRole1 = finalRole;
        if (nick.contains(finalRole.getToRename())) nick = nick.replace(finalRole.getToRename(), "");
        member.modifyNickname(finalRole1.getToRename() + nick).queue();
    }
}
