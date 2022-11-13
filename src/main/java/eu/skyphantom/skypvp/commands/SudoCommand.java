package eu.skyphantom.skypvp.commands;

import com.comphenix.protocol.PacketType;
import eu.skyphantom.skypvp.SkyPvP;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.command.ColouredConsoleSender;
import org.bukkit.entity.Player;

import javax.swing.text.PlainDocument;

public class SudoCommand extends Command {

    public SudoCommand() {
        super("sudo");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("*")) {
                if (args.length < 3) {
                    player.sendMessage(SkyPvP.PREFIX + "§8/§asudo §8<§2Spieler§8> <§2cmd§8> <§2Text§8>");
                    player.sendMessage(SkyPvP.PREFIX + "§8/§asudo §8<§2Spieler§8> <§2chat§8> <§2Text§8>");
                    return true;
                }
                String playerName = args[0];
                String option = args[1];
                Player target = Bukkit.getPlayerExact(playerName);
                if (target == null) {
                    player.sendMessage(SkyPvP.PREFIX + "§cDer Spieler ist nicht online§8.");
                    return true;
                }
                StringBuilder builder = new StringBuilder();
                for (int i = 2; i < args.length; i++) builder.append(args[i]).append(" ");
                String value = builder.toString().trim();
                if (option.equalsIgnoreCase("cmd") && !value.startsWith("/")) value = "/" + value;
                if (option.equalsIgnoreCase("chat") && value.startsWith("/")) value = value.substring(1);
                target.chat(value);
                if (option.equalsIgnoreCase("cmd")) {
                    player.sendMessage(SkyPvP.PREFIX + "§7Der Spieler §a" + playerName + "§7 hat den Command §8/§a" + value.substring(1) + "§7 ausgeführt§8.");
                } else {
                    player.sendMessage(SkyPvP.PREFIX + "§7Der Spieler §a" + playerName + "§7 hat §a" + value + "§7 im Chat geschrieben§8.");
                }
                return true;
            }
            player.sendMessage(SkyPvP.NOPERM);
            return true;
        } else if (commandSender instanceof ColouredConsoleSender) {
            ColouredConsoleSender player = (ColouredConsoleSender) commandSender;
            if (args.length < 3) {
                player.sendMessage(SkyPvP.PREFIX + "§8/§asudo §8<§2Spieler§8> <§2cmd§8> <§2Text§8>");
                player.sendMessage(SkyPvP.PREFIX + "§8/§asudo §8<§2Spieler§8> <§2chat§8> <§2Text§8>");
                return true;
            }
            String playerName = args[0];
            String option = args[1];
            Player target = Bukkit.getPlayerExact(playerName);
            if (target == null) {
                player.sendMessage(SkyPvP.PREFIX + "§cDer Spieler ist nicht online§8.");
                return true;
            }
            StringBuilder builder = new StringBuilder();
            for (int i = 2; i < args.length; i++) builder.append(args[i]).append(" ");
            String value = builder.toString().trim();
            if (option.equalsIgnoreCase("cmd") && !value.startsWith("/")) value = "/" + value;
            if (option.equalsIgnoreCase("chat") && value.startsWith("/")) value = value.substring(1);
            target.chat(value);
            if (option.equalsIgnoreCase("cmd")) {
                player.sendMessage(SkyPvP.PREFIX + "§7Der Spieler §a" + playerName + "§7 hat den Command §8/§a" + value.substring(1) + "§7 ausgeführt§8.");
            } else {
                player.sendMessage(SkyPvP.PREFIX + "§7Der Spieler §a" + playerName + "§7 hat §a" + value + "§7 im Chat geschrieben§8.");
            }
            return true;
        }
        return false;
    }
}
