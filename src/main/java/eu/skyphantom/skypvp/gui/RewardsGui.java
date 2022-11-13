package eu.skyphantom.skypvp.gui;

import com.sun.corba.se.spi.encoding.CorbaOutputObject;
import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.crates.CrateHandler;
import eu.skyphantom.skypvp.hooks.Gui;
import eu.skyphantom.skypvp.utils.Utils;
import eu.skyphantom.skypvp.utils.cooldowns.CooldownManager;
import eu.skyphantom.skypvp.utils.items.ItemBuilder;
import eu.skyphantom.skypvp.utils.time.TimeUtil;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RewardsGui extends Gui {

    CooldownManager cooldownManager;
    int taskId;

    public RewardsGui(@NotNull Player player) {
        super("§8▎ §a§lREWARDS", 5, player);
        this.cooldownManager = new CooldownManager(player.getUniqueId());

        fill(new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15).setName("§8-/-§r"));
        fillBorders(new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§8-/-§r"));

        update();
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyPvP.getInstance(), this::update, 0L, 10L);
    }

    private void update() {
        Player player = getPlayer();
        getInventory().setItem(20, new ItemBuilder(Utils.createSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmUzMDlkZjNjM2MwYjFiZTEwM2NhODc3OTkzYmFmMDJjNzIzNTY5YWQxYTdmYjBmNmY3MTEzZThjZjZhYjk1In19fQ=="))
                .setName("§8▎ §a§lTÄGLICH§8 ▪ §9Titan§8-§7Reward").lore(
                        "§r",
                        "§8▎ " + (!cooldownManager.isOnCooldown("reward_titan") ? (player.hasPermission("system.titan") ? "§a✔ §8(§7§oAbholbereit§8)" : "§c✘ §8(§7§oFehlende Rechte§8)") : "§c✘ §8(§7§oCooldown§8§o: §c§o§n" + TimeUtil.timeToString(cooldownManager.getRemainingTime("reward_titan"), false) + "§8)"),
                        "§r",
                        "§8ID c341ad1d7381"
                ));

        getInventory().setItem(21, new ItemBuilder(Utils.createSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzc1YTVkYzMxMDg3ZjNhMTZmMjdhNGZmY2YyNTQyZWU4ZDUyMmYyNWViMTlkMDg5NWVmYzMyY2I5YzI1NDgifX19"))
                .setName("§8▎ §a§lTÄGLICH§8 ▪ §a§lPhantom§8-§7Reward").lore(
                        "§r",
                        "§8▎ " + (!cooldownManager.isOnCooldown("reward_phantom") ? (player.hasPermission("system.phantom") ? "§a✔ §8(§7§oAbholbereit§8)" : "§c✘ §8(§7§oFehlende Rechte§8)") : "§c✘ §8(§7§oCooldown§8§o: §c§o§n" + TimeUtil.timeToString(cooldownManager.getRemainingTime("reward_phantom"), false) + "§8)"),
                        "§r",
                        "§8ID 420f7b5ca4fd"
                ));

        getInventory().setItem(22, new ItemBuilder(Utils.createSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTg2NmI0NjJiNzc2Mzc0MzI4ZDhkYWYyY2U5ZTVhYmMzZWNjNjQ0ODFkNTRhNjljZDRjMmIyNWU3NjMwYzZhZiJ9fX0="))
                .setName("§8▎ §a§lTÄGLICH§8 ▪ §7Spieler§8-§7Reward").lore(
                        "§r",
                        "§8▎ " + (!cooldownManager.isOnCooldown("reward_daily") ? "§a✔ §8(§7§oAbholbereit§8)" : "§c✘ §8(§7§oCooldown§8§o: §c§o§n" + TimeUtil.timeToString(cooldownManager.getRemainingTime("reward_daily"), false) + "§8)"),
                        "§r",
                        "§8ID e63ed52082ac"
                ));

        getInventory().setItem(23, new ItemBuilder(Utils.createSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzU3YjFmNmViN2NmMGZkMjA0OTlhNWJlNmI4MmIyNWNmNWNiZDhlNTdkYWFjNzM1MWM4NWM0NWZiNWZlNWMifX19"))
                .setName("§8▎ §a§lWÖCHENTLICH§8 ▪ §7Spieler§8-§7Reward").lore(
                        "§r",
                        "§8▎ " + (!cooldownManager.isOnCooldown("reward_weekly") ? "§a✔ §8(§7§oAbholbereit§8)" : "§c✘ §8(§7§oCooldown§8§o: §c§o§n" + TimeUtil.timeToString(cooldownManager.getRemainingTime("reward_weekly"), false) + "§8)"),
                        "§r",
                        "§8ID cf312e6fb7e8"
                ));

        getInventory().setItem(24, new ItemBuilder(Utils.createSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWZjMzJjOTE0Mjc2ZjY4NjE0MTc5NmE1YTAyM2MzOWVmZGZlZDE0ZDNkN2M5YzQyNzkyNTEzODQ2ZjdmYTRiMyJ9fX0="))
                .setName("§8▎ §a§lTÄGLICH§8 ▪ /§atcrate§8-§7Reward").lore(
                        "§r",
                        "§8▎ " + (!cooldownManager.isOnCooldown("reward_tcrate") ? (player.hasPermission("system.titan") ? "§a✔ §8(§7§oAbholbereit§8)" : "§c✘ §8(§7§oFehlende Rechte§8)") : "§c✘ §8(§7§oCooldown§8§o: §c§o§n" + TimeUtil.timeToString(cooldownManager.getRemainingTime("reward_tcrate"), false) + "§8)"),
                        "§r",
                        "§8ID bf6a99beaf59"
                ));
    }

    @Override
    public void onClick(Player player, ClickType clickType, int clickedSlot, ItemStack clickedItem) {
        if (clickedItem.getType() == Material.SKULL_ITEM) {
            String lore = clickedItem.getItemMeta().getLore().get(1);
            String name = clickedItem.getItemMeta().getDisplayName();
            if (name.equalsIgnoreCase("§8▎ §a§lTÄGLICH§8 ▪ /§atcrate§8-§7Reward")) {
                System.out.println(cooldownManager.isOnCooldown("reward_tcrate"));
                if (!player.hasPermission("system.titan")) {
                    player.sendMessage(SkyPvP.NOPERM);
                    return;
                }
                if (cooldownManager.isOnCooldown("reward_tcrate")) {
                    String time = TimeUtil.playTimeToString(cooldownManager.getRemainingTime("reward_tcrate"), false);
                    Utils.sendTitle(player, 2, 15, 10, "§8» §c§lCOOLDOWN§8 «", "§c§n" + time.trim() + "§r");
                    Utils.playSound(player, Sound.ENDERDRAGON_HIT, 100.0f, 1.0f);
                    return;
                }
                player.sendMessage(SkyPvP.PREFIX + "§7Du hast folgende Crates erhalten§8:");
                CrateHandler crateHandler = new CrateHandler("Test");
                List<String> rndmCrate = new ArrayList<>(crateHandler.getCrates());
                for (int i = 0; i < 10; i++) {
                    int rndm = Utils.getRandom().nextInt(rndmCrate.size());
                    crateHandler = new CrateHandler(rndmCrate.get(rndm));
                    ItemStack crate = crateHandler.getCrateItem();
                    TextComponent message = new TextComponent(SkyPvP.PREFIX + "§8- ");
                    TextComponent hover = new TextComponent("§8'§a" + crate.getItemMeta().getDisplayName() + "§8'§r");
                    hover.setHoverEvent(Utils.showItem(crate));
                    message.addExtra(hover);
                    player.spigot().sendMessage(message);
                }
                cooldownManager.addCooldown("reward_tcrate", 1, TimeUnit.DAYS);
                System.out.println(cooldownManager.isOnCooldown("reward_tcrate"));
                update();
            } else if (name.equalsIgnoreCase("§8▎ §a§lWÖCHENTLICH§8 ▪ §7Spieler§8-§7Reward")) {
                if (cooldownManager.isOnCooldown("reward_weekly")) {
                    String time = TimeUtil.playTimeToString(cooldownManager.getRemainingTime("reward_weekly"), false);
                    Utils.sendTitle(player, 2, 15, 10, "§8» §c§lCOOLDOWN§8 «", "§c§n" + time.trim() + "§r");
                    Utils.playSound(player, Sound.ENDERDRAGON_HIT, 100.0f, 1.0f);
                    return;
                }
                int integer = Utils.getRandomInt(1, 10);
                double tokens = 1000 * integer;
                CrateHandler crateHandler = new CrateHandler("Test");
                List<String> rndmCrate = new ArrayList<>(crateHandler.getCrates());
                int crateInt = Utils.getRandomInt(0, (rndmCrate.size() - 1));
                String crate = rndmCrate.get(crateInt);
                crateHandler = new CrateHandler(crate);
                ItemStack crateItem = crateHandler.getCrateItem();
                TextComponent hover = new TextComponent("§8'§a" + crateItem.getItemMeta().getDisplayName() + "§8'§r");
                hover.setHoverEvent(Utils.showItem(crateItem));
                player.sendMessage(SkyPvP.PREFIX + "§7Dir wurden §6" + tokens + " Tokens§7 gutgeschrieben§8.");
                player.spigot().sendMessage(new TextComponent(SkyPvP.PREFIX + "§7Du hast §a1§8x "), hover, new TextComponent("§8-§7Crate §7erhalten§8."));
                cooldownManager.addCooldown("reward_weekly", 7, TimeUnit.DAYS);
                update();
            }
        }
    }

    @Override
    public void onClose(Player player, InventoryView inventoryView) {
        Bukkit.getScheduler().cancelTask(taskId);
    }
}
