package eu.skyphantom.skypvp.commands;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.crates.CrateHandler;
import eu.skyphantom.skypvp.crates.CrateRarity;
import eu.skyphantom.skypvp.utils.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CrateCommand extends Command {


    public CrateCommand() {
        super("crate");
    }

    void syntax(Player player) {

        player.sendMessage("");
        player.sendMessage(SkyPvP.PREFIX + "§8/§7Crate §7create §8'§aName§8' §8'§aSeltenheit§8'");
        player.sendMessage(SkyPvP.PREFIX + "§8/§7Crate §7setitem §8'§aCrate§8'");
        player.sendMessage(SkyPvP.PREFIX + "§8/§7Crate §7additem §8'§aCrate§8'");
        player.sendMessage(SkyPvP.PREFIX + "§8/§7Crate §7give §8'§aCrate§8'");
        player.sendMessage(SkyPvP.PREFIX + "§8/§7Crate §7info §8'§aID§8'");
        player.sendMessage(SkyPvP.PREFIX + "§8/§7Crate §7list");
        player.sendMessage("");
        player.sendMessage(SkyPvP.PREFIX + "§7Seltenheiten§8:");
        for (CrateRarity rarity : CrateRarity.values()) {
            player.sendMessage(SkyPvP.PREFIX + "§8- §r" + rarity.getDisplay());
        }
        player.sendMessage("");

    }


    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player) sender;

            if (!player.hasPermission("system.owner")) {
                player.sendMessage(SkyPvP.NOPERM);
            } else {

                if (args.length == 3 && args[0].equalsIgnoreCase("create")) {
                    final String crate = args[1];

                    try {
                        final CrateRarity rarity = CrateRarity.valueOf(args[2].toUpperCase());


                        CrateHandler crateHandler = new CrateHandler(crate);

                        if (crateHandler.crateExist()) {
                            player.sendMessage(SkyPvP.PREFIX + "§7Diese Crate existiert bereits§8.");
                            return true;
                        }

                        crateHandler.createCrate(rarity);
                        player.sendMessage(SkyPvP.PREFIX + "§7Du hast erfolgreich die Crate §8'§a" + crate + "§8' §7erstellt§8!");
                        player.sendMessage(SkyPvP.PREFIX + "§7ID§8: §f" + crateHandler.getId());
                        player.sendMessage(SkyPvP.PREFIX + "§7Item§8: §f" + (crateHandler.getItem() == null ? "§c§lNONE" : crateHandler.getItem().getType()));
                        player.sendMessage(SkyPvP.PREFIX + "§7Seltenheit§8: §f" + Utils.capitalizeFirstLetter(crateHandler.getSeltenheit().name().toLowerCase()));

                    } catch (Exception e) {
                        syntax(player);
                    }
                    return true;
                }

                if (args.length == 2 && args[0].equalsIgnoreCase("additem")) {
                    final String crate = args[1];


                    CrateHandler crateHandler = new CrateHandler(crate);

                    if (!crateHandler.crateExist()) {
                        player.sendMessage(SkyPvP.PREFIX + "§7Diese Crate existiert nicht§8.");
                        return true;
                    }

                    if (player.getItemInHand() == null || player.getItemInHand().getType() == Material.AIR) {
                        player.sendMessage(SkyPvP.PREFIX + "§7Bitte halte ein gültiges Item in deiner Hand§8.");
                        return true;
                    }

                    crateHandler.addItem(player.getItemInHand());

                    player.sendMessage(SkyPvP.PREFIX + "§7Die Crate §8'§a" + crate + "§8' §7enthält nun §a" + crateHandler.getCrateItems().size() + " §7Items§8.");


                    return true;
                }

                if (args.length == 2 && args[0].equalsIgnoreCase("delete")) {
                    final String crate = args[1];


                    CrateHandler crateHandler = new CrateHandler(crate);

                    if (!crateHandler.crateExist()) {
                        player.sendMessage(SkyPvP.PREFIX + "§7Diese Crate existiert nicht§8.");
                        return true;
                    }


                    crateHandler.deleteCrate();
                    player.sendMessage(SkyPvP.PREFIX + "§7Die Crate §8'§a" + crate + "§8' §7wurde gelöscht§8.");
                    return true;
                }

                if (args.length == 2 && args[0].equalsIgnoreCase("give")) {
                    final String crate = args[1];


                    CrateHandler crateHandler = new CrateHandler(crate);

                    if (!crateHandler.crateExist()) {
                        player.sendMessage(SkyPvP.PREFIX + "§7Diese Crate existiert nicht§8.");
                        return true;
                    }

                    if (crateHandler.getItem() == null) {
                        player.sendMessage(SkyPvP.PREFIX + "§7Die Crate besitzt kein gültiges Item§8.");
                        return true;
                    }


                    //player.getInventory().addItem(ItemSkullBuilder.getSkull("gdfgdfgdf", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWIxNmRmOGMwY2E1NDc0MTkxNzIzODk1MzgyOTYwNTlhMTQ5MWMwNDJjOGUyOTgxZTc5MTRmZmM2YWZkMDM0ZCJ9fX0=", "gdfgdfgdf"));


                    player.getInventory().addItem(crateHandler.getCrateItem());
                    player.sendMessage(SkyPvP.PREFIX + "§7Du hast die Crate §8'§a" + crate + "§8' §7erhalten§8.");


                    return true;
                }

                if (args.length == 2 && args[0].equalsIgnoreCase("setitem")) {
                    final String crate = args[1];


                    CrateHandler crateHandler = new CrateHandler(crate);

                    if (!crateHandler.crateExist()) {
                        player.sendMessage(SkyPvP.PREFIX + "§7Diese Crate existiert nicht§8.");
                        return true;
                    }


                    if (player.getItemInHand() == null || player.getItemInHand().getType() == Material.AIR) {
                        player.sendMessage(SkyPvP.PREFIX + "§7Bitte halte ein gültiges Item in deiner Hand§8.");
                        return true;
                    }

                    if (player.getItemInHand().getType() != Material.SKULL_ITEM) {
                        player.sendMessage(SkyPvP.PREFIX + "§7Das Item muss dem Typ §aSKULL_ITEM §7entsprechen§8.");
                        return true;
                    }

                    crateHandler.setItem(player.getItemInHand());
                    player.sendMessage(SkyPvP.PREFIX + "§7Das Item der Crate §8'§a" + crate + "§8' §7wurde gesetzt§8.");

                    return true;
                }

                if (args.length == 1 && args[0].equalsIgnoreCase("list")) {

                    CrateHandler crateHandler = new CrateHandler(null);

                    if (crateHandler.getCrates().size() < 1) {
                        player.sendMessage(SkyPvP.PREFIX + "§7Derzeit existieren keine Crates§8.");
                        return true;
                    }

                    player.sendMessage(SkyPvP.PREFIX + "§7Folgende Crates wurde gefunden§8:");

                    for (String cn : crateHandler.getCrates()) {

                        crateHandler = new CrateHandler(cn);

                        player.sendMessage(SkyPvP.PREFIX + "§a" + cn + " " +
                                "§8(§fID: " + crateHandler.getId() + "§8) §8- " +
                                "§8(§f" + (crateHandler.getItem() == null ? "§c§lNONE" : crateHandler.getItem().getType()) + "§8) §8- " +
                                "§8(§c" + crateHandler.getCrateItems().size() + " §fItems§8) §8- " +
                                "§8(§c" + String.format("%.2f", crateHandler.getCrateOpened()) + " §fOpened§8)"
                        );
                    }
                    return true;
                }


                if (args.length == 2 && args[0].equalsIgnoreCase("info")) {

                    try {

                        final int id = Integer.parseInt(args[1]);


                        CrateHandler crateHandler = new CrateHandler(null);

                        if (crateHandler.getCratebyId(id) == null) {
                            player.sendMessage(SkyPvP.PREFIX + "§7Es wurde keine Crate mit dieser ID gefunden§8.");
                            return true;
                        }

                        player.sendMessage(SkyPvP.PREFIX + "§7Die Crate §8'§a" + crateHandler.getCratebyId(id) + "§8' §7wurde gefunden§8.");
                        player.sendMessage(SkyPvP.PREFIX + "§7Item§8: §a" + (crateHandler.getItem() == null ? "§c§lNONE" : crateHandler.getItem().getType()));


                    } catch (Exception ignored) {
                        player.sendMessage(SkyPvP.PREFIX + "§7Gebe eine gültige Anzahl an§8.");
                    }

                    return true;
                }

                syntax(player);
            }
        }
        return false;
    }
}
