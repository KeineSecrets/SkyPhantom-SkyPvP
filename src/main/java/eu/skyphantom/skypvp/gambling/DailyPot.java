package eu.skyphantom.skypvp.gambling;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.line.TextLine;
import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.api.RainbowTab;
import eu.skyphantom.skypvp.provider.LocationProvider;
import eu.skyphantom.skypvp.provider.StatsProvider;
import eu.skyphantom.skypvp.utils.MessageQueue;
import eu.skyphantom.skypvp.utils.Utils;
import eu.skyphantom.skypvp.utils.time.TimeUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created: 15/01/2023
 *
 * @author 🐸
 */
public class DailyPot {

    public final ArrayList<UUID> dailyPotUsers = new ArrayList<>();
    public int time = 60;
    Hologram hologram;

    public DailyPot() {
        this.start();
        this.updateHolo();
    }

    public ArrayList<UUID> getDailyPotUsers() {
        return dailyPotUsers;
    }

    public int getTime() {
        return time;
    }

    public long getAuslosungIn() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 18);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date d = cal.getTime();
        long dm = d.getTime();
        if (new Date().after(d)) {
            d.setTime(dm + 86400000);
        }
        dm = d.getTime();
        long dn = new Date().getTime();
        long diff = dm - dn;
        return diff;
    }

    public void start() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyPvP.getInstance(), () -> {
            String date = new SimpleDateFormat("HH:mm:ss").format(new Date());
            if (date.equalsIgnoreCase("01:59:29")) {
                Bukkit.broadcastMessage(SkyPvP.PREFIX + "§7Der DailyPot wird in §a16 Stunde§8(§an§8)§7 ausgelost§8.");
            } else if (date.equalsIgnoreCase("05:59:29")) {
                Bukkit.broadcastMessage(SkyPvP.PREFIX + "§7Der DailyPot wird in §a12 Stunde§8(§an§8)§7 ausgelost§8.");
            } else if (date.equalsIgnoreCase("06:59:29")) {
                Bukkit.broadcastMessage(SkyPvP.PREFIX + "§7Der DailyPot wird in §a11 Stunde§8(§an§8)§7 ausgelost§8.");
            } else if (date.equalsIgnoreCase("07:59:29")) {
                Bukkit.broadcastMessage(SkyPvP.PREFIX + "§7Der DailyPot wird in §a10 Stunde§8(§an§8)§7 ausgelost§8.");
            } else if (date.equalsIgnoreCase("08:59:29")) {
                Bukkit.broadcastMessage(SkyPvP.PREFIX + "§7Der DailyPot wird in §a9 Stunde§8(§an§8)§7 ausgelost§8.");
            } else if (date.equalsIgnoreCase("09:59:29")) {
                Bukkit.broadcastMessage(SkyPvP.PREFIX + "§7Der DailyPot wird in §a8 Stunde§8(§an§8)§7 ausgelost§8.");
            } else if (date.equalsIgnoreCase("10:59:29")) {
                Bukkit.broadcastMessage(SkyPvP.PREFIX + "§7Der DailyPot wird in §a7 Stunde§8(§an§8)§7 ausgelost§8.");
            } else if (date.equalsIgnoreCase("11:59:29")) {
                Bukkit.broadcastMessage(SkyPvP.PREFIX + "§7Der DailyPot wird in §a6 Stunde§8(§an§8)§7 ausgelost§8.");
            } else if (date.equalsIgnoreCase("12:59:29")) {
                Bukkit.broadcastMessage(SkyPvP.PREFIX + "§7Der DailyPot wird in §a5 Stunde§8(§an§8)§7 ausgelost§8.");
            } else if (date.equalsIgnoreCase("13:59:29")) {
                Bukkit.broadcastMessage(SkyPvP.PREFIX + "§7Der DailyPot wird in §a4 Stunde§8(§an§8)§7 ausgelost§8.");
            } else if (date.equalsIgnoreCase("14:59:29")) {
                Bukkit.broadcastMessage(SkyPvP.PREFIX + "§7Der DailyPot wird in §a3 Stunde§8(§an§8)§7 ausgelost§8.");
            } else if (date.equalsIgnoreCase("15:59:29")) {
                Bukkit.broadcastMessage(SkyPvP.PREFIX + "§7Der DailyPot wird in §a2 Stunde§8(§an§8)§7 ausgelost§8.");
            } else if (date.equalsIgnoreCase("16:59:29")) {
                Bukkit.broadcastMessage(SkyPvP.PREFIX + "§7Der DailyPot wird in §a1 Stunde§8(§an§8)§7 ausgelost§8.");
            } else if (date.equalsIgnoreCase("17:59:29")) {
                Bukkit.broadcastMessage(SkyPvP.PREFIX + "§7Der DailyPot wird in §a30 Sekunde§8(§an§8)§7 ausgelost§8.");
            } else if (date.equalsIgnoreCase("17:59:54")) {
                Bukkit.broadcastMessage(SkyPvP.PREFIX + "§7Der DailyPot wird in §a5 Sekunde§8(§an§8)§7 ausgelost§8.");
                Utils.playSound(Bukkit.getOnlinePlayers(), Sound.NOTE_PLING, 1.0f, 10.0f);
            } else if (date.equalsIgnoreCase("17:59:55")) {
                Bukkit.broadcastMessage(SkyPvP.PREFIX + "§7Der DailyPot wird in §a4 Sekunde§8(§an§8)§7 ausgelost§8.");
                Utils.playSound(Bukkit.getOnlinePlayers(), Sound.NOTE_PLING, 1.0f, 10.0f);
            } else if (date.equalsIgnoreCase("17:59:56")) {
                Bukkit.broadcastMessage(SkyPvP.PREFIX + "§7Der DailyPot wird in §a3 Sekunde§8(§an§8)§7 ausgelost§8.");
                Utils.playSound(Bukkit.getOnlinePlayers(), Sound.NOTE_PLING, 1.0f, 10.0f);
            } else if (date.equalsIgnoreCase("17:59:57")) {
                Bukkit.broadcastMessage(SkyPvP.PREFIX + "§7Der DailyPot wird in §a2 Sekunde§8(§an§8)§7 ausgelost§8.");
                Utils.playSound(Bukkit.getOnlinePlayers(), Sound.NOTE_PLING, 1.0f, 10.0f);
            } else if (date.equalsIgnoreCase("17:59:58")) {
                Bukkit.broadcastMessage(SkyPvP.PREFIX + "§7Der DailyPot wird in §a1 Sekunde§8(§an§8)§7 ausgelost§8.");
                Utils.playSound(Bukkit.getOnlinePlayers(), Sound.NOTE_PLING, 1.0f, 10.0f);
            } else if (date.equalsIgnoreCase("17:59:59")) {
                Random random = new Random();
                UUID randomWinner;
                if (dailyPotUsers.size() != 0) {
                    randomWinner = dailyPotUsers.get(random.nextInt(dailyPotUsers.size()));
                } else {
                    Bukkit.broadcastMessage(SkyPvP.PREFIX + "§cKeiner hat teilgenommen§8.");
                    return;
                }
                OfflinePlayer winner = Bukkit.getOfflinePlayer(randomWinner);
                Bukkit.broadcastMessage(SkyPvP.PREFIX + "§7Der Spieler §a" + winner.getName() + "§7 hat §a" + (100 * dailyPotUsers.size()) + " Tokens§7 gewonnen§8.");
                Bukkit.broadcastMessage(SkyPvP.PREFIX + "§7Er hatte eine Chance von §a" + (100 / dailyPotUsers.size()) + "%§8.");
                Utils.playSound(Bukkit.getOnlinePlayers(), Sound.LEVEL_UP, 1.0f, 0.1f);
                StatsProvider statsManager = new StatsProvider(randomWinner);
                statsManager.addCoins((100 * dailyPotUsers.size()));
                if (!winner.isOnline()) {
                    MessageQueue.add(randomWinner, SkyPvP.PREFIX + "§7Du hast bei dem letzten DailyPot §a" + (100 * dailyPotUsers.size()) + " Tokens§7 gewonnen§8.");
                }
                dailyPotUsers.clear();
            } else if (date.equalsIgnoreCase("18:00:02")) {
                Bukkit.broadcastMessage(SkyPvP.PREFIX + "§7Der nächste DailyPot wird morgen um §a18:00 Uhr §7ausgelost§8.");
                Utils.playSound(Bukkit.getOnlinePlayers(), Sound.HORSE_ARMOR, 1.0f, 0.1f);
            }
            //new Location(Bukkit.getWorld("Spawn"), -55.5, 87.5, 13.5, 1.0F, 90.0F)

        }, 0L, 20L);
    }

    public void updateHolo() {
        Bukkit.getScheduler().runTaskTimer(SkyPvP.getInstance(), () -> {
            if (hologram == null)
                hologram = HologramsAPI.createHologram(SkyPvP.getInstance(), new LocationProvider("dailypotholo").get());
            if (hologram.size() == 0) {
                hologram.appendTextLine("§8▎ §a§lSKYPHANTOM§8 ▪ §7DailyPot");
                hologram.appendTextLine("");
                hologram.appendTextLine("§8»│ §7Teilnahmegebühr§8: §aKostenlos");
                hologram.appendTextLine("§8»│ §7Chance§8: §a" + ((dailyPotUsers.size() == 0 ? "100" : (100 / dailyPotUsers.size()))) + "%");
                hologram.appendTextLine("§8»│ §7Geld im Pot§8: §a" + (dailyPotUsers.size() == 0 ? "0" : (100 * dailyPotUsers.size())) + " Tokens");
                hologram.appendTextLine("§8»│ §7Teilnehmer§8: §a" + (dailyPotUsers.size()) + " Spieler");
                hologram.appendTextLine("");
                hologram.appendTextLine("§8»│ §7Auslosung in§8: §a" + TimeUtil.timeToString(getAuslosungIn(), true));
                hologram.appendTextLine("");
                hologram.appendTextLine(RainbowTab.rainbowifyStrikethrough("------------------------------") + "§r");
                return;
            }
            ((TextLine) hologram.getLine(3)).setText("§8»│ §7Chance§8: §a" + ((dailyPotUsers.size() == 0 ? "100" : (100 / dailyPotUsers.size()))) + "%");
            ((TextLine) hologram.getLine(4)).setText("§8»│ §7Geld im Pot§8: §a" + (dailyPotUsers.size() == 0 ? "0" : (100 * dailyPotUsers.size())) + " Tokens");
            ((TextLine) hologram.getLine(5)).setText("§8»│ §7Teilnehmer§8: §a" + (dailyPotUsers.size()) + " Spieler");
            ((TextLine) hologram.getLine(7)).setText("§8»│ §7Auslosung in§8: §a" + TimeUtil.timeToString(getAuslosungIn(), true));
            ((TextLine) hologram.getLine(9)).setText(RainbowTab.rainbowifyStrikethrough("------------------------------") + "§r");
        }, 0L, 1L);
    }
}
