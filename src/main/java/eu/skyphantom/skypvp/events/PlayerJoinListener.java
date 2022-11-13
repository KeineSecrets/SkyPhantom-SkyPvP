package eu.skyphantom.skypvp.events;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.api.VerifyAPI;
import eu.skyphantom.skypvp.provider.LoginStreakProvider;
import eu.skyphantom.skypvp.provider.StatsProvider;
import eu.skyphantom.skypvp.provider.TablistProvider;
import eu.skyphantom.skypvp.utils.AntiLabyModCrasher;
import eu.skyphantom.skypvp.utils.LabyModProtocol;
import eu.skyphantom.skypvp.utils.Utils;
import eu.skyphantom.skypvp.utils.scoreboard.ScoreboardHelper;
import eu.skyphantom.skypvp.utils.text.TextComponentBuilder;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Arrays;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final StatsProvider statsProvider = new StatsProvider(player.getUniqueId());
        final LoginStreakProvider loginStreakProvider = new LoginStreakProvider(player.getUniqueId());
        player.setCustomName(player.getName());
        event.setJoinMessage(null);
        ScoreboardHelper.setScoreboard(player);
        Bukkit.getOnlinePlayers().forEach(TablistProvider::setPrefix);
        for (int i = 0; i < 256; i++) player.sendMessage("§r");
        if (!player.hasPlayedBefore()) {
            statsProvider.setCoins(2500D);
            statsProvider.setKills(0D);
            statsProvider.setDeaths(0D);
            Utils.sendTo(Bukkit.getOnlinePlayers(), "§r");
            Utils.sendTo(Bukkit.getOnlinePlayers(), SkyPvP.PREFIX + "§7Der Spieler §a" + player.getDisplayName() + "§8( #§a" + (Bukkit.getOfflinePlayers().length) + "§8 )§7 spielt");
            Utils.sendTo(Bukkit.getOnlinePlayers(), SkyPvP.PREFIX + "§7das erste mal auf §a§lSKYPHANTOM§8!");
            Utils.sendTo(Bukkit.getOnlinePlayers(), "§r");
            Utils.sendTo(Bukkit.getOnlinePlayers(), "§8» §7Jeder Spieler erhält §a100 Tokens§8. «", true);
            Utils.sendTo(Bukkit.getOnlinePlayers(), "§r");
        }
        player.sendMessage("§r");
        player.sendMessage(SkyPvP.PREFIX + "§7Willkommen zurück§8, §a" + player.getDisplayName() + "§8!");
        player.sendMessage("§r");

        TextComponent message = new TextComponent("§r              §r");

        TextComponent discord = new TextComponentBuilder("§8(§9§lDISCORD§8)§r").setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{new TextComponent("§8▪ §7Klicke um zum §9Discord§7 zu gelangen§8.")})).setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/skyphantom/")).toTextComponent();
        TextComponent shop = new TextComponentBuilder("§8(§e§lSTORE§8)§r").setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{new TextComponent("§8▪ §7Klicke um zum §eShop§7 zu gelangen§8.")})).setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://store.skyphantom.eu/")).toTextComponent();
        TextComponent vote = new TextComponentBuilder("§8(§c§lVOTE§8)§r").setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{new TextComponent("§8▪ §7Klicke um für uns zu §cvoten§8.")})).setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://minecraft-server.eu/server/index/22877/")).toTextComponent();
        TextComponent empty1 = new TextComponent("§r   §r"), empty2 = new TextComponent("§r   §r"), empty3 = new TextComponent("§r   §r");

        message.addExtra(discord);
        message.addExtra(empty2);
        message.addExtra(shop);
        message.addExtra(empty1);
        message.addExtra(vote);

        player.spigot().sendMessage(message);
        player.sendMessage("§r");
        if (!VerifyAPI.isVerified(player.getUniqueId())) {
            player.sendMessage(SkyPvP.PREFIX + "§7Bitte verifiziere dich§8. /§averify");
            player.sendMessage("§r");
        }

        LabyModProtocol.disableVoiceChat(player);
        LabyModProtocol.sendCurrentPlayingGamemode(player, true, SkyPvP.PREFIX + "SkyPvP with §clove §8▎");
        LabyModProtocol.sendDiscordHook(player);

        if (!player.hasPermission("system.team"))
            new AntiLabyModCrasher().disableMod(player, Arrays.asList(AntiLabyModCrasher.LabyMod.values()));

    }

}
