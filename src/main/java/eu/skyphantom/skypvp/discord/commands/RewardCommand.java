package eu.skyphantom.skypvp.discord.commands;

import eu.skyphantom.skypvp.provider.StatsProvider;
import eu.skyphantom.skypvp.utils.UUIDFetcher;
import eu.skyphantom.skypvp.utils.cooldowns.DiscordCooldownManager;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class RewardCommand {

    public static void execute(SlashCommandEvent event) {
        if (event.getCommandString().equals("reward")) {
            DiscordCooldownManager cooldownManager = new DiscordCooldownManager(event.getMember().getIdLong());
            if (!cooldownManager.isOnCooldown("reward")) {
                String ingame = event.getOption("ingameName").getAsString();
                UUID uuid = UUIDFetcher.getUUID(ingame);
                StatsProvider statsProvider = new StatsProvider(uuid);
                statsProvider.addCoins(250D);
                long time = (System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1));
                event.reply("` ✅ ` › Du hast **250 Tokens** erhalten. Komme in <t:" + time + ":R> wieder!").setEphemeral(true).queue();
                cooldownManager.addCooldown("dailyReward", 1, TimeUnit.DAYS);
                return;
            }
            long time = cooldownManager.getRemainingTime("dailyReward");
            event.reply("` ❌ ` › Du hast deinen heutigen Reward bereits erhalten. Komme in <t:" + time + ":R> wieder!").setEphemeral(true).queue();
        }
    }

}
