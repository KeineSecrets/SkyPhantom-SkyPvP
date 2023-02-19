package eu.skyphantom.skypvp.gui;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.hooks.Gui;
import eu.skyphantom.skypvp.provider.KitProvider;
import eu.skyphantom.skypvp.provider.StatsProvider;
import eu.skyphantom.skypvp.utils.Utils;
import eu.skyphantom.skypvp.utils.cooldowns.CooldownManager;
import eu.skyphantom.skypvp.utils.items.ItemBuilder;
import eu.skyphantom.skypvp.utils.time.TimeUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class KitGui extends Gui {
    // Ränge: Spieler - Shark - Fage - Magician - Titan - Phantom

    CooldownManager cooldownManager;
    StatsProvider statsProvider;
    int task;

    public KitGui(@NotNull Player player) {
        super("§8▎ §a§lKITS", 1, player);
        cooldownManager = new CooldownManager(player.getUniqueId());
        statsProvider = new StatsProvider(player.getUniqueId());

        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyPvP.getInstance(), this::update, 0L, 10L);

    }

    @Override
    public void onClick(Player player, ClickType clickType, int clickedSlot, ItemStack clickedItem) {
        if (clickedSlot == 1) {
            if (cooldownManager.isOnCooldown("kit_spieler")) {
                double priceForKit = (TimeUnit.MILLISECONDS.toSeconds(cooldownManager.getRemainingTime("kit_spieler")));
                if (statsProvider.hasEnoughCoins(priceForKit)) {
                    statsProvider.removeCoins(priceForKit);
                    KitProvider.getPlayerKit(player);
                    cooldownManager.addCooldown("kit_spieler", 5, TimeUnit.MINUTES);
                    player.sendMessage(SkyPvP.PREFIX + "§7Du hast das Spieler§8-§7Kit für §2" + Utils.format(priceForKit, "§2") + " Tokens§7 gekauft§8.");
                    return;
                }
                player.sendMessage(SkyPvP.PREFIX + "§cDir fehlen dazu " + Utils.format((priceForKit - statsProvider.getCoins()), "§c") + " Tokens§8.");
                return;
            }
            cooldownManager.addCooldown("kit_spieler", 5, TimeUnit.MINUTES);
            KitProvider.getPlayerKit(player);
            player.sendMessage(SkyPvP.PREFIX + "§7Du hast das Spieler§8-§7Kit erhalten§8.");
            return;
        }
        if (clickedSlot == 2) {
            if (player.hasPermission("system.shark")) {
                if (cooldownManager.isOnCooldown("kit_shark")) {
                    double priceForKit = (TimeUnit.MILLISECONDS.toSeconds(cooldownManager.getRemainingTime("kit_shark")));
                    if (statsProvider.hasEnoughCoins(priceForKit)) {
                        statsProvider.removeCoins(priceForKit);
                        KitProvider.getSharkKit(player);
                        cooldownManager.addCooldown("kit_shark", 10, TimeUnit.MINUTES);
                        player.sendMessage(SkyPvP.PREFIX + "§7Du hast das §6Shark§8-§7Kit für §2" + Utils.format(priceForKit, "§2") + " Tokens§7 gekauft§8.");
                        return;
                    }
                    player.sendMessage(SkyPvP.PREFIX + "§cDir fehlen dazu " + Utils.format((priceForKit - statsProvider.getCoins()), "§c") + " Tokens§8.");
                    return;
                }
                cooldownManager.addCooldown("kit_shark", 10, TimeUnit.MINUTES);
                KitProvider.getSharkKit(player);
                player.sendMessage(SkyPvP.PREFIX + "§7Du hast das §6Shark§8-§7Kit erhalten§8.");
                return;
            }
            player.sendMessage(SkyPvP.NOPERM);
            return;
        }
        if (clickedSlot == 3) {
            if (player.hasPermission("system.fage")) {
                if (cooldownManager.isOnCooldown("kit_fage")) {
                    double priceForKit = (TimeUnit.MILLISECONDS.toSeconds(cooldownManager.getRemainingTime("kit_fage")));
                    if (statsProvider.hasEnoughCoins(priceForKit)) {
                        statsProvider.removeCoins(priceForKit);
                        KitProvider.getFageKit(player);
                        cooldownManager.addCooldown("kit_fage", 15, TimeUnit.MINUTES);
                        player.sendMessage(SkyPvP.PREFIX + "§7Du hast das §fFage§8-§7Kit für §2" + Utils.format(priceForKit, "§2") + " Tokens§7 gekauft§8.");
                        return;
                    }
                    player.sendMessage(SkyPvP.PREFIX + "§cDir fehlen dazu " + Utils.format((priceForKit - statsProvider.getCoins()), "§c") + " Tokens§8.");
                    return;
                }
                cooldownManager.addCooldown("kit_fage", 15, TimeUnit.MINUTES);
                KitProvider.getFageKit(player);
                player.sendMessage(SkyPvP.PREFIX + "§7Du hast das §fFage§8-§7Kit erhalten§8.");
                return;
            }
            player.sendMessage(SkyPvP.NOPERM);
            return;
        }
        if (clickedSlot == 5) {
            if (player.hasPermission("system.magician")) {
                if (cooldownManager.isOnCooldown("kit_magician")) {
                    double priceForKit = (TimeUnit.MILLISECONDS.toSeconds(cooldownManager.getRemainingTime("kit_magician")));
                    if (statsProvider.hasEnoughCoins(priceForKit)) {
                        statsProvider.removeCoins(priceForKit);
                        KitProvider.getMagicianKit(player);
                        cooldownManager.addCooldown("kit_magician", 30, TimeUnit.MINUTES);
                        player.sendMessage(SkyPvP.PREFIX + "§7Du hast das §5Magician§8-§7Kit für §2" + Utils.format(priceForKit, "§2") + " Tokens§7 gekauft§8.");
                        return;
                    }
                    player.sendMessage(SkyPvP.PREFIX + "§cDir fehlen dazu " + Utils.format((priceForKit - statsProvider.getCoins()), "§c") + " Tokens§8.");
                    return;
                }
                cooldownManager.addCooldown("kit_magician", 30, TimeUnit.MINUTES);
                KitProvider.getMagicianKit(player);
                player.sendMessage(SkyPvP.PREFIX + "§7Du hast das §5Magician§8-§7Kit erhalten§8.");
                return;
            }
            player.sendMessage(SkyPvP.PREFIX);
            return;
        }
        if (clickedSlot == 6) {
            if (player.hasPermission("system.titan")) {
                if (cooldownManager.isOnCooldown("kit_titan")) {
                    double priceForKit = (TimeUnit.MILLISECONDS.toSeconds(cooldownManager.getRemainingTime("kit_titan")));
                    if (statsProvider.hasEnoughCoins(priceForKit)) {
                        statsProvider.removeCoins(priceForKit);
                        KitProvider.getTitanKit(player);
                        cooldownManager.addCooldown("kit_titan", 45, TimeUnit.MINUTES);
                        player.sendMessage(SkyPvP.PREFIX + "§7Du hast das §9Titan§8-§7Kit für §2" + Utils.format(priceForKit, "§2") + " Tokens§7 gekauft§8.");
                        return;
                    }
                    player.sendMessage(SkyPvP.PREFIX + "§cDir fehlen dazu " + Utils.format((priceForKit - statsProvider.getCoins()), "§c") + " Tokens§8.");
                    return;
                }
                cooldownManager.addCooldown("kit_titan", 45, TimeUnit.MINUTES);
                KitProvider.getTitanKit(player);
                player.sendMessage(SkyPvP.PREFIX + "§7Du hast das §9Titan§8-§7Kit erhalten§8.");
                return;
            }
            player.sendMessage(SkyPvP.NOPERM);
            return;
        }
        if (clickedSlot == 7) {
            if (player.hasPermission("system.phantom")) {
                if (cooldownManager.isOnCooldown("kit_phantom")) {
                    double priceForKit = (TimeUnit.MILLISECONDS.toSeconds(cooldownManager.getRemainingTime("kit_phantom")));
                    if (statsProvider.hasEnoughCoins(priceForKit)) {
                        statsProvider.removeCoins(priceForKit);
                        KitProvider.getPhantomKit(player);
                        cooldownManager.addCooldown("kit_phantom", 60, TimeUnit.MINUTES);
                        player.sendMessage(SkyPvP.PREFIX + "§7Du hast das §a§lPhantom§8-§7Kit für §2" + Utils.format(priceForKit, "§2") + " Tokens§7 gekauft§8.");
                        return;
                    }
                    player.sendMessage(SkyPvP.PREFIX + "§cDir fehlen dazu " + Utils.format((priceForKit - statsProvider.getCoins()), "§c") + " Tokens§8.");
                    return;
                }
                cooldownManager.addCooldown("kit_phantom", 60, TimeUnit.MINUTES);
                KitProvider.getPhantomKit(player);
                player.sendMessage(SkyPvP.PREFIX + "§7Du hast das §a§lPhantom§8-§7Kit erhalten§8.");
                return;
            }
            player.sendMessage(SkyPvP.NOPERM);
        }
    }

    @Override
    public void onClose(Player player, InventoryView inventoryView) {
        Bukkit.getScheduler().cancelTask(task);
    }

    public void update() {
        fill(new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15).setName("§8-/-§r"));
        if (cooldownManager.isOnCooldown("kit_spieler")) {
            getInventory().setItem(1, new ItemBuilder(Material.IRON_DOOR).amount(0).setName("§8▎ §7Spieler §8▪ §c" + TimeUtil.timeToString(cooldownManager.getRemainingTime("kit_spieler"), true)).lore(
                    "§r",
                    "§8▎ §7Du hast einen aktiven Cooldown auf dieses Kit§8.",
                    "§8▪ §7Du kannst es §a§njetzt§7 für §2" + Utils.format((TimeUnit.MILLISECONDS.toSeconds(cooldownManager.getRemainingTime("kit_spieler"))), "§2") + " Tokens§7 erhalten§8.",
                    "§r"
            ));
        } else {
            getInventory().setItem(1, new ItemBuilder(Material.ACACIA_DOOR_ITEM).setName("§8▎ §7Spieler §8▪ §aAbholbereit").lore(
                    "§r",
                    "§8▎ §7Du kannst dir dieses Kit mit rechtsklicken abholen§8.",
                    "§r"
            ));
        }
        if (getPlayer().hasPermission("system.shark")) {
            if (cooldownManager.isOnCooldown("kit_shark")) {
                getInventory().setItem(2, new ItemBuilder(Material.IRON_DOOR).amount(0).setName("§8▎ §6Shark §8▪ §c" + TimeUtil.timeToString(cooldownManager.getRemainingTime("kit_shark"), true)).lore(
                        "§r",
                        "§8▎ §7Du hast einen aktiven Cooldown auf dieses Kit§8.",
                        "§8▪ §7Du kannst es §a§njetzt§7 für §2" + Utils.format((TimeUnit.MILLISECONDS.toSeconds(cooldownManager.getRemainingTime("kit_shark"))), "§2") + " Tokens§7 erhalten§8.",
                        "§r"
                ));
            } else {
                getInventory().setItem(2, new ItemBuilder(Material.getMaterial(324)).setName("§8▎ §6Shark §8▪ §aAbholbereit").lore(
                        "§r",
                        "§8▎ §7Du kannst dir dieses Kit mit rechtsklicken abholen§8.",
                        "§r"
                ));
            }
        } else {
            getInventory().setItem(2, new ItemBuilder(Material.BARRIER).setName("§8▎ §6Shark §8▪ §cKeine Berechtigung"));
        }
        if (getPlayer().hasPermission("system.fage")) {
            if (cooldownManager.isOnCooldown("kit_fage")) {
                getInventory().setItem(3, new ItemBuilder(Material.IRON_DOOR).amount(0).setName("§8▎ §fFage §8▪ §c" + TimeUtil.timeToString(cooldownManager.getRemainingTime("kit_fage"), true)).lore(
                        "§r",
                        "§8▎ §7Du hast einen aktiven Cooldown auf dieses Kit§8.",
                        "§8▪ §7Du kannst es §a§njetzt§7 für §2" + Utils.format((TimeUnit.MILLISECONDS.toSeconds(cooldownManager.getRemainingTime("kit_fage"))), "§2") + " Tokens§7 erhalten§8.",
                        "§r"
                ));
            } else {
                getInventory().setItem(3, new ItemBuilder(Material.SPRUCE_DOOR_ITEM).setName("§8▎ §fFage §8▪ §aAbholbereit").lore(
                        "§r",
                        "§8▎ §7Du kannst dir dieses Kit mit rechtsklicken abholen§8.",
                        "§r"
                ));
            }
        } else {
            getInventory().setItem(3, new ItemBuilder(Material.BARRIER).setName("§8▎ §fFage §8▪ §cKeine Berechtigung"));
        }
        if (getPlayer().hasPermission("system.magician")) {
            if (cooldownManager.isOnCooldown("kit_magician")) {
                getInventory().setItem(5, new ItemBuilder(Material.IRON_DOOR).amount(0).setName("§8▎ §5Magician §8▪ §c" + TimeUtil.timeToString(cooldownManager.getRemainingTime("kit_magician"), true)).lore(
                        "§r",
                        "§8▎ §7Du hast einen aktiven Cooldown auf dieses Kit§8.",
                        "§8▪ §7Du kannst es §a§njetzt§7 für §2" + Utils.format((TimeUnit.MILLISECONDS.toSeconds(cooldownManager.getRemainingTime("kit_magician"))), "§2") + " Tokens§7 erhalten§8.",
                        "§r"
                ));
            } else {
                getInventory().setItem(5, new ItemBuilder(Material.SPRUCE_DOOR_ITEM).setName("§8▎ §5Magician §8▪ §aAbholbereit").lore(
                        "§r",
                        "§8▎ §7Du kannst dir dieses Kit mit rechtsklicken abholen§8.",
                        "§r"
                ));
            }
        } else {
            getInventory().setItem(5, new ItemBuilder(Material.BARRIER).setName("§8▎ §5Magician §8▪ §cKeine Berechtigung"));
        }
        if (getPlayer().hasPermission("system.titan")) {
            if (cooldownManager.isOnCooldown("kit_titan")) {
                getInventory().setItem(6, new ItemBuilder(Material.IRON_DOOR).amount(0).setName("§8▎ §9Titan §8▪ §c" + TimeUtil.timeToString(cooldownManager.getRemainingTime("kit_titan"), true)).lore(
                        "§r",
                        "§8▎ §7Du hast einen aktiven Cooldown auf dieses Kit§8.",
                        "§8▪ §7Du kannst es §a§njetzt§7 für §2" + Utils.format((TimeUnit.MILLISECONDS.toSeconds(cooldownManager.getRemainingTime("kit_titan"))), "§2") + " Tokens§7 erhalten§8.",
                        "§r"
                ));
            } else {
                getInventory().setItem(6, new ItemBuilder(Material.getMaterial(324)).setName("§8▎ §9Titan §8▪ §aAbholbereit").lore(
                        "§r",
                        "§8▎ §7Du kannst dir dieses Kit mit rechtsklicken abholen§8.",
                        "§r"
                ));
            }
        } else {
            getInventory().setItem(6, new ItemBuilder(Material.BARRIER).setName("§8▎ §9Titan §8▪ §cKeine Berechtigung"));
        }
        if (getPlayer().hasPermission("system.phantom")) {
            if (cooldownManager.isOnCooldown("kit_phantom")) {
                getInventory().setItem(7, new ItemBuilder(Material.IRON_DOOR).amount(0).setName("§8▎ §a§lPhantom §8▪ §c" + TimeUtil.timeToString(cooldownManager.getRemainingTime("kit_phantom"), true)).lore(
                        "§r",
                        "§8▎ §7Du hast einen aktiven Cooldown auf dieses Kit§8.",
                        "§8▪ §7Du kannst es §a§njetzt§7 für §2" + Utils.format((TimeUnit.MILLISECONDS.toSeconds(cooldownManager.getRemainingTime("kit_phantom"))), "§2") + " Tokens§7 erhalten§8.",
                        "§r"
                ));
            } else {
                getInventory().setItem(7, new ItemBuilder(Material.ACACIA_DOOR_ITEM).setName("§8▎ §a§lPhantom §8▪ §aAbholbereit").lore(
                        "§r",
                        "§8▎ §7Du kannst dir dieses Kit mit rechtsklicken abholen§8.",
                        "§r"
                ));
            }
        } else {
            getInventory().setItem(7, new ItemBuilder(Material.BARRIER).setName("§8▎ §a§lPhantom §8▪ §cKeine Berechtigung"));
        }
    }
}
