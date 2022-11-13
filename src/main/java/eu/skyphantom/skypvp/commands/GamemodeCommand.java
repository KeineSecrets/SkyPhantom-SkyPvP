package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.SkyPvP;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;

public class GamemodeCommand extends Command {


    public GamemodeCommand() {
        super("gamemode", "", "", Collections.singletonList("gm"));
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("system.admin")) {
                if (args.length == 0) {
                    player.sendMessage(SkyPvP.PREFIX + "§8/§agamemode §8<§20§8/§21§8/§22§8/§23§8> <§2Spieler§8>");
                    return true;
                }
                if (args.length == 1) {
                    GameMode gameMode = GameMode.SURVIVAL;
                    switch (args[0]) {
                        default:
                        case "0":
                            gameMode = GameMode.SURVIVAL;
                            break;
                        case "1":
                            gameMode = GameMode.CREATIVE;
                            break;
                        case "2":
                            gameMode = GameMode.ADVENTURE;
                            break;
                        case "3":
                            gameMode = GameMode.SPECTATOR;
                            break;
                    }
                    player.sendMessage(SkyPvP.PREFIX + "§7Du bist nun in folgendem Spielmodus§8: §a" + gameMode.name().toUpperCase());
                    player.setGameMode(gameMode);
                    return true;
                }
                GameMode gameMode = GameMode.SURVIVAL;
                switch (args[0]) {
                    default:
                    case "0":
                        gameMode = GameMode.SURVIVAL;
                        break;
                    case "1":
                        gameMode = GameMode.CREATIVE;
                        break;
                    case "2":
                        gameMode = GameMode.ADVENTURE;
                        break;
                    case "3":
                        gameMode = GameMode.SPECTATOR;
                        break;
                }
                Player target = Bukkit.getPlayer(args[1]);
                if (target != null) {
                    player.sendMessage(SkyPvP.PREFIX + "§a" + target.getName() + "§7 ist nun im folgendem Spielmodus§8: §a" + gameMode.name().toUpperCase());
                    target.sendMessage(SkyPvP.PREFIX + "§7Du bist nun in folgendem Spielmodus§8: §a" + gameMode.name().toUpperCase());
                    target.setGameMode(gameMode);
                    return true;
                }
                player.sendMessage(SkyPvP.PREFIX + "§7Der Spieler ist nicht online§8.");
                return true;
            }
            player.sendMessage(SkyPvP.NOPERM);
            return true;
        }
        return false;
    }
}
