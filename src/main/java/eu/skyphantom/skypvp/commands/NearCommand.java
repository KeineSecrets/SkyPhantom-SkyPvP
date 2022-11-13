package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.provider.StaffmodeProvider;
import eu.skyphantom.skypvp.provider.VanishProvider;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import xyz.haoshoku.nick.api.NickAPI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NearCommand extends Command {

    public NearCommand() {
        super("near");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("system.magician")) {
                List<Entity> entities = player.getNearbyEntities(50,50,50);
                List<Player> near = new ArrayList<>();
                for (Entity entity : entities) {
                    if (!(entity instanceof Player)) continue;
                    if (entity.hasMetadata("NPC")) continue;
                    if (entity.getName().equals(player.getName())) continue;
                    if (VanishProvider.get((Player) entity)) continue;
                    if (StaffmodeProvider.get((Player) entity)) continue;
                    near.add(((Player) entity));
                }
                if (near.isEmpty()) {
                    player.sendMessage(SkyPvP.PREFIX + "§7Es befindet sich keiner in deiner Nähe§8.");
                    return true;
                }
                player.sendMessage(SkyPvP.PREFIX + "§7Es befinden sich §a" + near.size() + " Spieler§7 in der Nähe§8:");
                for (Player nearPlayer : near) {
                    double distance = player.getLocation().distance(nearPlayer.getLocation());
                    player.sendMessage(SkyPvP.PREFIX + "§8- §a" + (nearPlayer.getName()) + "§8: §2" + distance + "§7 Blöcke entfernt");
                }
                return true;
            }
            player.sendMessage(SkyPvP.NOPERM);
            return true;
        }
        return false;
    }
}
