package eu.skyphantom.skypvp.lms.listener;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.lms.LMS;
import eu.skyphantom.skypvp.lms.State;
import eu.skyphantom.skypvp.lms.events.LMSStartEvent;
import eu.skyphantom.skypvp.lms.tasks.LMSGameIdleTask;
import eu.skyphantom.skypvp.provider.LocationProvider;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LMSStartListener implements Listener {

    public static boolean schutzzeit;

    @EventHandler
    public void onLMSStart(final LMSStartEvent event) {
        LMS.getInstance().setState(State.INGAME);
        LMSGameIdleTask.startTask();
        schutzzeit = true;
        for (Player player : event.getPlayers()) {
            Map<Integer, ItemStack> temp = new HashMap<>();
            for (int i = 0; i < player.getInventory().getContents().length; i++) {
                temp.put(i, player.getInventory().getContents()[i]);
            }
            LMS.inv.put(player.getUniqueId(), temp);
            player.getInventory().clear();
            player.teleport(new LocationProvider("lms").get());
            player.sendMessage(LMS.PREFIX + "§7Die Schutzzeit ist in §930 Sekunden§7 vorbei§8.");
            LMS.getInstance().getLMSHandler().getPlayerKills().put(player, 0);
        }
        Bukkit.getScheduler().runTaskLater(SkyPvP.getInstance(), () -> {
            for (Player player : event.getPlayers()) {
                player.sendMessage(LMS.PREFIX + "§7Die Schutzzeit ist in §915 Sekunden§7 vorbei§8.");
                LMS.getInstance().getLMSHandler().getPlayerKills().put(player, 0);
            }
        }, 20*15);
        Bukkit.getScheduler().runTaskLater(SkyPvP.getInstance(), () -> {
            for (Player player : event.getPlayers()) {
                player.sendMessage(LMS.PREFIX + "§7Die Schutzzeit ist in §95 Sekunden§7 vorbei§8.");
                LMS.getInstance().getLMSHandler().getPlayerKills().put(player, 0);
            }
        }, 20*25);
        Bukkit.getScheduler().runTaskLater(SkyPvP.getInstance(), () -> {
            for (Player player : event.getPlayers()) {
                player.sendMessage(LMS.PREFIX + "§7Die Schutzzeit ist in §94 Sekunden§7 vorbei§8.");
                LMS.getInstance().getLMSHandler().getPlayerKills().put(player, 0);
            }
        }, 20*26);
        Bukkit.getScheduler().runTaskLater(SkyPvP.getInstance(), () -> {
            for (Player player : event.getPlayers()) {
                player.sendMessage(LMS.PREFIX + "§7Die Schutzzeit ist in §93 Sekunden§7 vorbei§8.");
                LMS.getInstance().getLMSHandler().getPlayerKills().put(player, 0);
            }
        }, 20*27);
        Bukkit.getScheduler().runTaskLater(SkyPvP.getInstance(), () -> {
            for (Player player : event.getPlayers()) {
                player.sendMessage(LMS.PREFIX + "§7Die Schutzzeit ist in §92 Sekunden§7 vorbei§8.");
                LMS.getInstance().getLMSHandler().getPlayerKills().put(player, 0);
            }
        }, 20*28);
        Bukkit.getScheduler().runTaskLater(SkyPvP.getInstance(), () -> {
            for (Player player : event.getPlayers()) {
                player.sendMessage(LMS.PREFIX + "§7Die Schutzzeit ist in §91 Sekunden§7 vorbei§8.");
                LMS.getInstance().getLMSHandler().getPlayerKills().put(player, 0);
            }
        }, 20*29);
        Bukkit.getScheduler().runTaskLater(SkyPvP.getInstance(), () -> {
            for (Player player : event.getPlayers()) {
                player.sendMessage(LMS.PREFIX + "§7Die Schutzzeit ist nun vorbei§8.");
                schutzzeit = false;
            }
        }, 20*30);
    }

}
