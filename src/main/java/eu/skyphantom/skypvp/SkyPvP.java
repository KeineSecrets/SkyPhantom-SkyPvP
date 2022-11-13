package eu.skyphantom.skypvp;

import eu.skyphantom.skypvp.api.NpcAPI;
import eu.skyphantom.skypvp.discord.Discord;
import eu.skyphantom.skypvp.events.NodeMutateListener;
import eu.skyphantom.skypvp.hooks.CommandManager;
import eu.skyphantom.skypvp.hooks.EventManager;
import eu.skyphantom.skypvp.hooks.GuiHandler;
import eu.skyphantom.skypvp.hooks.SchedulerManager;
import eu.skyphantom.skypvp.lms.LMS;
import eu.skyphantom.skypvp.provider.LocationProvider;
import eu.skyphantom.skypvp.provider.LuckPermsProvider;
import eu.skyphantom.skypvp.provider.TabCompleteProvider;
import eu.skyphantom.skypvp.provider.TablistProvider;
import eu.skyphantom.skypvp.utils.ChunkLoader;
import eu.skyphantom.skypvp.utils.advent.Kalender;
import eu.skyphantom.skypvp.utils.scoreboard.ScoreboardHelper;
import javafx.scene.control.Tab;
import net.luckperms.api.event.node.NodeMutateEvent;
import net.luckperms.api.event.user.UserDataRecalculateEvent;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyPvP extends JavaPlugin {

    public static String PREFIX = "§8▎ §a§lSKYPHANTOM§8 ▪ §7", NOPERM = PREFIX + "§cDazu hast du keine Berechtigung§8.", PUNISH = "§8▎ §c❁§8 ▪ §7";
    // Ränge: Spieler - Shark - Fage - Magic - Titan - Phantom

    private static SkyPvP instance;
    private NpcAPI npcAPI;
    private GuiHandler guiHandler;
    private CommandManager commandManager;
    private EventManager eventHandler;
    private SchedulerManager schedulerManager;
    private TablistProvider tablistProvider;
    private Discord discord;
    private TabCompleteProvider tabCompleteProvider;
    private ChunkLoader chunkLoader;

    public static SkyPvP getInstance() {
        return instance;
    }
    public EventManager getEventHandler() {
        return eventHandler;
    }
    public SchedulerManager getSchedulerManager() {
        return schedulerManager;
    }
    public CommandManager getCommandManager() {
        return commandManager;
    }
    public TablistProvider getTablistProvider() {
        return tablistProvider;
    }
    public Discord getDiscord() {
        return discord;
    }
    public GuiHandler getGuiHandler() {
        return guiHandler;
    }
    public ScoreboardHelper getScoreboardHelper() {
        return new ScoreboardHelper();
    }
    public NpcAPI getNpcAPI() {
        return npcAPI;
    }
    public TabCompleteProvider getTabCompleteProvider() {
        return tabCompleteProvider;
    }
    public ChunkLoader getChunkLoader() {
        return chunkLoader;
    }

    @Override
    public void onEnable() {
        instance = this;
        tablistProvider = new TablistProvider();
        guiHandler = new GuiHandler();
        commandManager = new CommandManager();
        eventHandler = new EventManager();
        schedulerManager = new SchedulerManager();
        discord = new Discord(this);
        npcAPI = new NpcAPI();
        tabCompleteProvider = new TabCompleteProvider();
        chunkLoader = new ChunkLoader();

        new LMS();
        new Kalender();

        LuckPermsProvider.registerProvider();
        LuckPermsProvider.get().getEventBus().subscribe(this, UserDataRecalculateEvent.class, NodeMutateListener::onGroupChange);

       // Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> Bukkit.getOnlinePlayers().forEach(TablistProvider::setPrefix), 0L, 20L);

        chunkLoader.load(new LocationProvider("spawn").get(), 10);
    }

    @Override
    public void onDisable() {

    }

}
