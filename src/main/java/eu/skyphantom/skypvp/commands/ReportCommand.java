package eu.skyphantom.skypvp.commands;

import com.sun.scenario.effect.Offset;
import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.reports.Reports;
import eu.skyphantom.skypvp.utils.Utils;
import eu.skyphantom.skypvp.utils.cooldowns.CooldownManager;
import eu.skyphantom.skypvp.utils.time.TimeUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ReportCommand extends Command {

    public ReportCommand() {
        super("report");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            CooldownManager cooldownManager = new CooldownManager(player.getUniqueId());
            if (cooldownManager.isOnCooldown("report")) {
                long time = cooldownManager.getRemainingTime("report");
                Utils.sendTitle(player, 2, 15, 10, "§8» §c§lCOOLDOWN§8 «", "§c§n" + TimeUtil.timeToString(time, false).trim() + "§r");
                Utils.playSound(player, Sound.ENDERDRAGON_HIT, 100.0f, 1.0f);
                return true;
            }
            if (args.length < 2) {
                player.sendMessage(SkyPvP.PREFIX + "§8/§areport §8<§2Spieler§8> <§2ID§8>");
                player.sendMessage("§r");
                for (Reports value : Reports.values()) {
                    player.sendMessage(SkyPvP.PREFIX + "§7ID §2" + value.getId() + "§8 × §a" + value.getName());
                }
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                if (target.getName().equals(player.getName())) {
                    player.sendMessage(SkyPvP.PREFIX + "§7Du kannst dich nicht selbst melden§8.");
                    return true;
                }
                try {
                    int id = Integer.parseInt(args[1]);
                    Reports report = Reports.getReportById(id);
                    if (report != null) {
                        int teamOnline = 0;
                        for (Player team : Bukkit.getOnlinePlayers()) {
                            if (!team.hasPermission("system.team")) continue;
                            teamOnline++;
                            team.sendMessage("§r");
                            team.sendMessage(SkyPvP.PREFIX + "§7Ein neuer Report ist eingegangen§8.");
                            team.sendMessage(SkyPvP.PREFIX + "§7Reporter§8: §a" + player.getName());
                            team.sendMessage(SkyPvP.PREFIX + "§7Spieler§8: §a" + target.getName() + "§8 × §7Grund§8: §a" + report.getName());

                            TextComponent component1 = new TextComponent(SkyPvP.PREFIX);
                            TextComponent component2 = new TextComponent("§a§nTeleportieren und Staffmode aktivieren§r");
                            component2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{new TextComponent("§8▪ §7Klicke um den Report §aanzunehmen§8.")}));
                            component2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/staffmode " + target.getName()));

                            component1.addExtra(component2);

                            team.spigot().sendMessage(component1);
                            team.sendMessage("§r");

                        }

                        EmbedBuilder embedBuilder = new EmbedBuilder();
                        embedBuilder.setTitle("Neuer Report | " + new SimpleDateFormat("dd.MM.yyyy, HH:mm").format(new Date()));
                        embedBuilder.setColor(Color.RED.darker());
                        embedBuilder.setTimestamp(OffsetDateTime.now());
                        embedBuilder.setDescription("\n> **Reporter** × " + player.getName() + "\n> **Spieler** × " + target.getName() + "\n> **Grund** × " + report.getName() + "\n");

                        SkyPvP.getInstance().getDiscord().getJda().getTextChannelById(1037084805596196976L).sendMessageEmbeds(embedBuilder.build()).queue();

                        cooldownManager.addCooldown("report", 5, TimeUnit.MINUTES);
                        Utils.sendTitle(player, 2, 25, 10, "§8» §a§lVIELEN DANK§8 «", "§aDanke§8, §adass du die Community sicher machst§8.");
                        player.sendMessage(SkyPvP.PREFIX + "§7Danke für deine Meldung§8! §7Ein §aTeammitglied");
                        player.sendMessage(SkyPvP.PREFIX + "§7wird sich zeitnah um den Report kümmern§8.");
                        player.sendMessage(SkyPvP.PREFIX + "§7Derzeit sind §2" + teamOnline + " §aTeammitglieder §7online§8.");
                        return true;
                    }
                    player.sendMessage(SkyPvP.PREFIX + "§7Ein Reportgrund mit dieser ID existiert nicht§8.");
                    return true;
                } catch (NumberFormatException e) {
                    player.sendMessage(SkyPvP.PREFIX + "§7Gebe eine gültige ID an§8.");
                    return true;
                }
            }
            player.sendMessage(SkyPvP.PREFIX + "§7Der Spieler ist nicht online§8.");
            return true;
        }
        return false;
    }
}

