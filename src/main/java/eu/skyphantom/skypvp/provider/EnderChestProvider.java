package eu.skyphantom.skypvp.provider;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.api.Config;
import eu.skyphantom.skypvp.utils.UUIDFetcher;
import eu.skyphantom.skypvp.utils.Utils;
import eu.skyphantom.skypvp.utils.items.ItemBuilder;
import eu.skyphantom.skypvp.utils.items.SerializationProvider;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EnderChestProvider {

    private static final Config config = new Config("plugins/SkyPvP/", "enderchests.yml");
    private final UUID uuid;

    public EnderChestProvider(UUID uuid) {
        this.uuid = uuid;
        if (config.getConfig().getInt(uuid + ".pages") == 0) {
            config.getConfig().set(uuid + ".pages", 1);
            config.saveConfig();
        }
    }

    public void addPage(Player player) {
        if (config.getConfig().getInt(uuid + ".pages") == 3) {
            player.sendMessage(SkyPvP.PREFIX + "§7Du hast bereits drei Seiten§8.");
            return;
        }
        Utils.removeItemFromHand(player, 1);
        config.getConfig().set(uuid + ".pages", (config.getConfig().getInt(uuid + ".pages")+1));
        config.saveConfig();
        player.sendMessage(SkyPvP.PREFIX + "§7Du hast nun eine Seite mehr§8. ( /§aenderchest§8 )");
    }

    public static Config getConfig() {
        return config;
    }

    public static void onSave(Inventory inventory, Player player) {
        if (!inventory.getTitle().contains("§8▎ §a§lEC§8 ▪ #§7")) return;
        int page = Integer.parseInt(inventory.getTitle().replace("§8▎ §a§lEC§8 ▪ #§7", ""));
        UUID owner = UUIDFetcher.getUUID(inventory.getItem(49).getItemMeta().getLore().get(0).replaceAll("§8▪ §7Inhaber§8: §a", "").replaceAll("§aDu", player.getName()));
        if ((!owner.equals(player.getUniqueId())) && (!player.hasPermission("system.team"))) return;
        List<ItemStack> toSave = new ArrayList<>();
        for (int i = 0; i < 44; i++) {
            toSave.add(inventory.getItem(i));
        }
        ItemStack[] toSaveArray = toSave.toArray(new ItemStack[0]);
        String data = SerializationProvider.itemStackArrayToBase64(toSaveArray);
        config.getConfig().set(owner + "." + page, data);
        config.saveConfig();
    }

    public static boolean cancelClick(Inventory inventory, Player player) {
        return ((!inventory.getItem(49).getItemMeta().getLore().get(0).contains("§aDu")) && (!player.hasPermission("system.team")));
    }

    public void openEnderchest(Player to, int page) {
        if (config.getConfig().getInt(uuid + ".pages") >= page) {
            Inventory inventory = Bukkit.createInventory(null, 9 * 6, "§8▎ §a§lEC§8 ▪ #§7" + page);
            String items = (config.getConfig().getString(this.uuid + "." + page) == null ? "" : config.getConfig().getString(this.uuid + "." + page));
            if (items != null) {
                ItemStack[] itemStacks = SerializationProvider.itemStackArrayFromBase64(items);
                if (itemStacks != null) {
                    for (int i = 0; i < itemStacks.length; i++) {
                        inventory.setItem(i, itemStacks[i]);
                    }
                }
            }
            inventory.setItem(54 - 9, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15).setName("§8-/-§r"));
            inventory.setItem(55 - 9, new ItemBuilder(Utils.createSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDU5YmUxNTU3MjAxYzdmZjFhMGIzNjk2ZDE5ZWFiNDEwNDg4MGQ2YTljZGI0ZDVmYTIxYjZkYWE5ZGIyZDEifX19")).setName("§8« §7vorherige Seite"));
            inventory.setItem(56 - 9, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15).setName("§8-/-§r"));
            inventory.setItem(57 - 9, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15).setName("§8-/-§r"));
            inventory.setItem(58 - 9, new ItemBuilder(Material.SIGN).setName("§8▎ §a§lSEITE§8 ▪ #§7" + page).lore("§8▪ §7Inhaber§8: §a" + (to.getUniqueId().equals(this.uuid) ? "§aDu" : UUIDFetcher.getName(this.uuid))));
            inventory.setItem(59 - 9, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15).setName("§8-/-§r"));
            inventory.setItem(60 - 9, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15).setName("§8-/-§r"));
            inventory.setItem(61 - 9, new ItemBuilder(Utils.createSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDJiMGMwN2ZhMGU4OTIzN2Q2NzllMTMxMTZiNWFhNzVhZWJiMzRlOWM5NjhjNmJhZGIyNTFlMTI3YmRkNWIxIn19fQ==")).setName("§8« §7nächste Seite"));
            inventory.setItem(62 - 9, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15).setName("§8-/-§r"));
            to.openInventory(inventory);
            to.sendMessage(SkyPvP.PREFIX + "§7Du hast die Enderchest §8#§a" + page + "§7 von §a" + (uuid.equals(to.getUniqueId()) ? "dir" : UUIDFetcher.getName(this.uuid) + "§7 geöffnet§8."));
            return;
        }
        to.sendMessage(SkyPvP.PREFIX + "§7Der Spieler hat diese Seite nicht freigeschaltet§8.");
    }

}
