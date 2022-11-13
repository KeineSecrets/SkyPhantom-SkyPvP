package eu.skyphantom.skypvp.discord.adapter;

import eu.skyphantom.skypvp.discord.commands.RewardCommand;
import eu.skyphantom.skypvp.discord.commands.SendCommand;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandAdapter extends ListenerAdapter {

    @Override
    public void onSlashCommand(@NotNull SlashCommandEvent event) {
        SendCommand.execute(event);
        RewardCommand.execute(event);
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        List<CommandData> commandDataList = new ArrayList<>();
        commandDataList.add(new CommandData("send", "sends an embed").addOption(OptionType.STRING, "what", "What", true));
        commandDataList.add(new CommandData("reward", "Bekomme 250 Coins pro Tag!").addOption(OptionType.STRING, "ingame", "Ingame-Name", true));
        event.getJDA().getGuilds().forEach(guild -> {
            guild.updateCommands().addCommands(commandDataList).queue();
        });
    }

}
