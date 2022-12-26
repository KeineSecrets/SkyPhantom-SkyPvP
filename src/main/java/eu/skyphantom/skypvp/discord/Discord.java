package eu.skyphantom.skypvp.discord;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.discord.adapter.ButtonClickAdapter;
import eu.skyphantom.skypvp.discord.adapter.CommandAdapter;
import eu.skyphantom.skypvp.discord.adapter.VerificationAdapter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import javax.security.auth.login.LoginException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Discord {

    JDA jda;
    JDABuilder jdaBuilder;
    Plugin plugin;
    Executor executor = Executors.newSingleThreadExecutor();
    int task;

    public Discord(Plugin plugin) {
        this.plugin = plugin;

        jdaBuilder = JDABuilder.createDefault(SkyPvP.getInstance().getConfig().getString("token"));
        jdaBuilder.enableIntents(GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS));
        jdaBuilder.setMemberCachePolicy(MemberCachePolicy.ALL);
        jdaBuilder.setChunkingFilter(ChunkingFilter.ALL);
        jdaBuilder.setAutoReconnect(true);

        jdaBuilder.addEventListeners(new CommandAdapter(), new VerificationAdapter(), new ButtonClickAdapter());

        try {
            jda = jdaBuilder.build();
            jda.awaitReady();
            System.out.println("[Bot] Logged in as " + jda.getSelfUser().getAsTag() + "!");
        } catch (InterruptedException | LoginException e) {
            e.printStackTrace();
        }

        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyPvP.getInstance(), () -> {
            executor.execute(() -> jda.getPresence().setActivity(Activity.streaming("mit " + Bukkit.getOnlinePlayers().size() + " Spielern", "https://www.youtube.com/watch?v=GbZSx2B6nUY")));
        }, 0L, 20*5L);

    }

    public JDABuilder getJdaBuilder() {
        return jdaBuilder;
    }

    public JDA getJda() {
        return jda;
    }

    public Plugin getPlugin() {
        return plugin;
    }
}
