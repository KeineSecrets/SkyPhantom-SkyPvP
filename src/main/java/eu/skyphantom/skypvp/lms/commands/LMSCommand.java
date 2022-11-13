package eu.skyphantom.skypvp.lms.commands;

import eu.skyphantom.skypvp.lms.LMS;
import eu.skyphantom.skypvp.lms.LMSHandler;
import eu.skyphantom.skypvp.lms.State;
import eu.skyphantom.skypvp.lms.events.LMSPlayerJoinEvent;
import eu.skyphantom.skypvp.lms.events.LMSPlayerQuitEvent;
import eu.skyphantom.skypvp.lms.tasks.LMSLobbyTask;
import eu.skyphantom.skypvp.provider.LocationProvider;
import eu.skyphantom.skypvp.utils.scoreboard.ScoreboardHelper;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LMSCommand extends Command {

    public LMSCommand() {
        super("lms");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (args.length == 0) {
                sendHelp(player);
                return true;
            }
            if (args[0].equalsIgnoreCase("create")) {
                if (player.hasPermission("system.lms.create")) {
                    if (LMS.getInstance().getState() != State.NONE) {
                        player.sendMessage(LMS.PREFIX + "§7Derzeit läuft bereits ein LMS§8.");
                        return true;
                    }
                    LMS.getInstance().getLMSHandler().start();
                    LMS.getInstance().getLMSHandler().getPlayers().add(player);
                    return true;
                }
                player.sendMessage(LMS.NOPERM);
                return true;
            }
            if (args[0].equalsIgnoreCase("stop")) {
                if (player.hasPermission("system.lms.stop")) {
                    if (LMS.getInstance().getState() == State.NONE) {
                        player.sendMessage(LMS.PREFIX + "§7Derzeit läuft kein LMS§8.");
                        return true;
                    }
                    LMS.getInstance().getLMSHandler().stop(null);
                    return true;
                }
                player.sendMessage(LMS.NOPERM);
                return true;
            }
            if (args[0].equalsIgnoreCase("skip")) {
                if (player.hasPermission("system.lms.skip")) {
                    if (LMS.getInstance().getState() != State.LOBBY) {
                        player.sendMessage(LMS.PREFIX + "§7Das LMS Event befindet sich nicht in der Lobby-Phase§8.");
                        return true;
                    }
                    LMSLobbyTask.setCountdown(10);
                    for (Player inLobby : LMS.getInstance().getLMSHandler().getPlayers()) {
                        inLobby.sendMessage(LMS.PREFIX + "§7Der Cooldown wurde auf §910 Sekunden§7 gesetzt§8.");
                    }
                    return true;
                }
                player.sendMessage(LMS.NOPERM);
                return true;
            }
            if (args[0].equalsIgnoreCase("spectate")) {
                if (LMS.getInstance().getState() != State.INGAME) {
                    player.sendMessage(LMS.PREFIX + "§7Das LMS Event befindet sich nicht in der Ingame-Phase§8.");
                    return true;
                }
                if (LMS.getInstance().getLMSHandler().getPlayers().contains(player)) {
                    player.sendMessage(LMS.PREFIX + "§7Du kannst nicht zuschauen§8, §7da du noch im Spiel bist§8.");
                    return true;
                }
                if (LMS.getInstance().getLMSHandler().getSpectators().contains(player)) {
                    LMS.getInstance().getLMSHandler().getSpectators().remove(player);
                    player.setGameMode(GameMode.SURVIVAL);
                    player.teleport(new LocationProvider("spawn").get());
                    player.sendMessage(LMS.PREFIX + "§7Du schaust nun nicht mehr zu§8.");
                } else {
                    LMS.getInstance().getLMSHandler().getSpectators().add(player);
                    player.setGameMode(GameMode.SPECTATOR);
                    player.teleport(new LocationProvider("lms").get());
                    player.sendMessage(LMS.PREFIX + "§7Du schaust nun zu§8.");
                }
                return true;
            }
            if (args[0].equalsIgnoreCase("join")) {
                if (LMS.getInstance().getState() != State.LOBBY) {
                    player.sendMessage(LMS.PREFIX + "§7Das LMS Event befindet sich nicht in der Lobby-Phase§8.");
                    return true;
                }
                if (LMS.getInstance().getLMSHandler().getPlayers().contains(player)) {
                    player.sendMessage(LMS.PREFIX + "§7Du bist bereits im Spiel§8.");
                    return true;
                }
                Bukkit.getPluginManager().callEvent(new LMSPlayerJoinEvent(player));
                LMS.getInstance().getLMSHandler().addPlayer(player);
                player.sendMessage(LMS.PREFIX + "§7Du hast das LMS §abetreten§8.");
                ScoreboardHelper.updateScoreboard(player);
                for (Player ingame : LMS.getInstance().getLMSHandler().getPlayers()) {
                    ingame.sendMessage(LMS.PREFIX + "§a" + player.getName() + "§7 hat das Spiel betreten§8.");
                }
                return true;
            }
            if (args[0].equalsIgnoreCase("leave")) {
                if (LMS.getInstance().getState() == State.NONE) {
                    player.sendMessage(LMS.PREFIX + "§7Derzeit läuft kein LMS§8.");
                    return true;
                }
                if (!LMS.getInstance().getLMSHandler().getPlayers().contains(player)) {
                    player.sendMessage(LMS.PREFIX + "§7Du bist nicht im Spiel§8.");
                    return true;
                }
                Bukkit.getPluginManager().callEvent(new LMSPlayerQuitEvent(player));
                LMS.getInstance().getLMSHandler().removePlayer(player);
                player.sendMessage(LMS.PREFIX + "§7Du hast das LMS §cverlassen§8.");
                ScoreboardHelper.updateScoreboard(player);
                for (Player ingame : LMS.getInstance().getLMSHandler().getPlayers()) {
                    ingame.sendMessage(LMS.PREFIX + "§c" + player.getName() + "§7 hat das Spiel verlassen§8.");
                }
                return true;
            }
        }
        return false;
    }

    void sendHelp(Player player) {
        player.sendMessage(LMS.PREFIX + "§7Derzeit läuft " + (LMS.getInstance().getState() != State.NONE ? "§aein" : "§ckein") + "§7 LMS§8.");
        if (player.hasPermission("system.lms.create")) player.sendMessage(LMS.PREFIX + "§8/§alms §8<§2create§8>");
        if (player.hasPermission("system.lms.stop")) player.sendMessage(LMS.PREFIX + "§8/§alms §8<§2stop§8>");
        if (player.hasPermission("system.lms.skip")) player.sendMessage(LMS.PREFIX + "§8/§alms §8<§2skip§8>");
        player.sendMessage(LMS.PREFIX + "§8/§alms §8<§2spectate§8>");
        player.sendMessage(LMS.PREFIX + "§8/§alms §8<§2join§8>");
        player.sendMessage(LMS.PREFIX + "§8/§alms §8<§2leave§8>");
    }
}
