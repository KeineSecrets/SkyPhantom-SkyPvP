package eu.skyphantom.skypvp.gambling;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.line.TextLine;
import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.api.RainbowTab;
import eu.skyphantom.skypvp.provider.LocationProvider;
import eu.skyphantom.skypvp.utils.Utils;
import eu.skyphantom.skypvp.utils.time.TickUnit;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created: 15/01/2023
 *
 * @author 🐸
 */
public class Crash {

    Map<Player, Double> originalBets = new HashMap<>();
    Map<Player, Double> participants = new LinkedHashMap<>();
    Hologram hologram;
    int timeUntilStart = 20 * 10;
    double multiplier = 1.00;
    int delay = 10;
    double secondsStop = 0, wait = 25;
    private CrashState state;
    private BukkitTask task;

    public Crash() {
        state = CrashState.WAITING;
        runnable();
    }

    public CrashState getState() {
        return state;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public Map<Player, Double> getParticipants() {
        return participants;
    }

    public Map<Player, Double> getOriginalBets() {
        return originalBets;
    }

    public void addParticipant(Player player, double tokens) {
        participants.put(player, tokens);
        originalBets.put(player, tokens);
    }

    public void removeParticipant(Player player) {
        participants.remove(player);
        originalBets.remove(player);
        if (participants.isEmpty()) timeUntilStart = 20 * 10;
    }

    public Hologram getHologram() {
        return hologram;
    }

    public BukkitTask getTask() {
        return task;
    }

    public void runnable() {
        task = new BukkitRunnable() {
            @Override
            public void run() {
                updateHolo();
                if (state == CrashState.WAITING) {
                    if (participants.isEmpty()) {
                        timeUntilStart = 20 * 10;
                        return;
                    }
                    if (timeUntilStart > 0) {
                        timeUntilStart--;
                        return;
                    }
                    state = CrashState.COUNTING;
                    secondsStop = Utils.getRandom().nextDouble() + 0.14; //0.04000000 - 1.04000000
                    timeUntilStart = 20 * 10;
                    multiplier = 1.00;
                    return;
                }
                if (state == CrashState.COUNTING) {
                    if (delay > 0) {
                        delay--;
                        return;
                    }
                    delay = 1;
                    multiplier += 0.005;
                    for (Player player : participants.keySet()) {
                        double tokens = originalBets.get(player);
                        tokens *= multiplier;
                        participants.put(player, tokens);
                        Utils.playSound(player, Sound.WOOD_CLICK, 0.1f, 1.0f);
                    }
                    if ((multiplier - 1) > secondsStop) {
                        state = CrashState.CRASHED;
                        wait = 50;
                        timeUntilStart = 20 * 10;
                    }
                }
                if (state == CrashState.CRASHED) {
                    for (Player player : Bukkit.getWorld("Casino").getPlayers()) {
                        for (Player participant : participants.keySet()) {
                            player.sendMessage(SkyPvP.PREFIX + "§7Der Spieler §c" + participant.getName() + "§7 hat soeben §c" + Utils.format(participants.get(participant), "§c") + " Tokens§7 gecrasht§8. (§c" + getMultiplier() + "§8)");
                            participant.sendMessage(SkyPvP.PREFIX + "§c" + Utils.format(participants.get(participant), "§c") + " Tokens");
                        }
                    }
                    participants.clear();
                    originalBets.clear();
                    if (wait > 0) {
                        wait--;
                        return;
                    }
                    state = CrashState.WAITING;
                }
            }
        }.runTaskTimer(SkyPvP.getInstance(), 0L, 1L);
    }

    public void updateHolo() {
        if (hologram == null) {
            hologram = HologramsAPI.createHologram(SkyPvP.getInstance(), new LocationProvider("crashholo").get());
            hologram.appendTextLine("§8▎ §a§lSKYPHANTOM§8 ▪ §7Crash");
            hologram.appendTextLine("");
            hologram.appendTextLine("§8»│ §7Status§8: §r" + state.getDisplay());
            hologram.appendTextLine("§8»│ §7Nächste Runde in§8: §a" + (state == CrashState.COUNTING ? "Läuft" : (state == CrashState.CRASHED ? "§c10s" : TickUnit.TICK.toSeconds(timeUntilStart) + "s")));
            hologram.appendTextLine("");
            hologram.appendTextLine("§8»│ §7Multiplikator§8: " + (state == CrashState.COUNTING ? "§2" : "§c") + String.format("%.2f", multiplier) + "x");
            hologram.appendTextLine("§8»│ §7Letzter Multiplikator§8: §c1.00x");
            hologram.appendTextLine("");
            hologram.appendTextLine(RainbowTab.rainbowifyStrikethrough("------------------------------") + "§r");
            return;
        }
        ((TextLine) hologram.getLine(2)).setText("§8»│ §7Status§8: §r" + state.getDisplay());
        ((TextLine) hologram.getLine(3)).setText("§8»│ §7Nächste Runde in§8: §a" + (timeUntilStart == 0 ? "Läuft" : (state == CrashState.CRASHED ? "§c10s" : (TickUnit.TICK.toSeconds(timeUntilStart)) + "s")));
        ((TextLine) hologram.getLine(5)).setText("§8»│ §7Multiplikator§8: " + (state == CrashState.COUNTING ? "§2" : "§c") + String.format("%.2f", multiplier) + "x");
        ((TextLine) hologram.getLine(6)).setText((state == CrashState.CRASHED ? "§8»│ §7Letzter Multiplikator§8: §c" + String.format("%.2f", multiplier) + "x" : ((TextLine) hologram.getLine(6)).getText()));
        ((TextLine) hologram.getLine(8)).setText(RainbowTab.rainbowifyStrikethrough("------------------------------") + "§r");
    }

}
