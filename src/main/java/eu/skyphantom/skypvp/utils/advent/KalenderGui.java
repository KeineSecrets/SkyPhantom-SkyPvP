package eu.skyphantom.skypvp.utils.advent;

import eu.skyphantom.skypvp.hooks.Gui;
import eu.skyphantom.skypvp.utils.Utils;
import eu.skyphantom.skypvp.utils.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class KalenderGui extends Gui {

    public KalenderGui(@NotNull Player player) {
        super("§8▎ §f§LA§c§lD§f§lV§c§lE§f§lN§c§lT§8 ▪ §7Kalender", 5, player);

        fill(new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15).setName("§8-/-§r"));
        fillBorders(new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§8-/-§r"));


        getInventory().setItem(4, new ItemBuilder(Material.WOOD_DOOR).setName("§8▎ §7Türchen §8#§c1"));
        getInventory().setItem(10, new ItemBuilder(Material.WOOD_DOOR).setName("§8▎ §7Türchen §8#§c2"));
        getInventory().setItem(11, new ItemBuilder(Material.WOOD_DOOR).setName("§8▎ §7Türchen §8#§c3"));
        getInventory().setItem(12, new ItemBuilder(Material.WOOD_DOOR).setName("§8▎ §7Türchen §8#§c4"));
        getInventory().setItem(13, new ItemBuilder(Material.WOOD_DOOR).setName("§8▎ §7Türchen §8#§c5"));
        getInventory().setItem(14, new ItemBuilder(Material.WOOD_DOOR).setName("§8▎ §7Türchen §8#§c6"));
        getInventory().setItem(15, new ItemBuilder(Material.WOOD_DOOR).setName("§8▎ §7Türchen §8#§c7"));
        getInventory().setItem(16, new ItemBuilder(Material.WOOD_DOOR).setName("§8▎ §7Türchen §8#§c8"));
        getInventory().setItem(19, new ItemBuilder(Material.WOOD_DOOR).setName("§8▎ §7Türchen §8#§c9"));
        getInventory().setItem(20, new ItemBuilder(Material.WOOD_DOOR).setName("§8▎ §7Türchen §8#§c10"));
        getInventory().setItem(21, new ItemBuilder(Material.WOOD_DOOR).setName("§8▎ §7Türchen §8#§c11"));
        getInventory().setItem(22, new ItemBuilder(Material.WOOD_DOOR).setName("§8▎ §7Türchen §8#§c12"));
        getInventory().setItem(23, new ItemBuilder(Material.WOOD_DOOR).setName("§8▎ §7Türchen §8#§c13"));
        getInventory().setItem(24, new ItemBuilder(Material.WOOD_DOOR).setName("§8▎ §7Türchen §8#§c14"));
        getInventory().setItem(25, new ItemBuilder(Material.WOOD_DOOR).setName("§8▎ §7Türchen §8#§c15"));
        getInventory().setItem(28, new ItemBuilder(Material.WOOD_DOOR).setName("§8▎ §7Türchen §8#§c16"));
        getInventory().setItem(29, new ItemBuilder(Material.WOOD_DOOR).setName("§8▎ §7Türchen §8#§c17"));
        getInventory().setItem(30, new ItemBuilder(Material.WOOD_DOOR).setName("§8▎ §7Türchen §8#§c18"));
        getInventory().setItem(31, new ItemBuilder(Material.WOOD_DOOR).setName("§8▎ §7Türchen §8#§c19"));
        getInventory().setItem(32, new ItemBuilder(Material.WOOD_DOOR).setName("§8▎ §7Türchen §8#§c20"));
        getInventory().setItem(33, new ItemBuilder(Material.WOOD_DOOR).setName("§8▎ §7Türchen §8#§c21"));
        getInventory().setItem(34, new ItemBuilder(Material.WOOD_DOOR).setName("§8▎ §7Türchen §8#§c22"));
        getInventory().setItem(39, new ItemBuilder(Material.WOOD_DOOR).setName("§8▎ §7Türchen §8#§c23"));
        getInventory().setItem(41, new ItemBuilder(Material.WOOD_DOOR).setName("§8▎ §7Türchen §8#§c24"));

        getInventory().setItem(40, new ItemBuilder(Material.SIGN).setName("§8▎ §7Informationen").lore("§8▪ §7Datum§8: §c" + Utils.getTodayDay() + "§8'§c" + Utils.getTodayMonth() + "§8'§c2023"));

        player.playSound(player.getLocation(), Sound.CHEST_OPEN, 0.5F, 90.F);
    }

    @Override
    public void onClick(Player player, ClickType clickType, int clickedSlot, ItemStack clickedItem) {
        if (clickedItem.getItemMeta().getDisplayName().contains("§8▎ §7Türchen §8#§c")) {
            int day = Integer.parseInt(Utils.getTodayDay());
            int month = Integer.parseInt(Utils.getTodayMonth());
            if (month != 12) {
                player.sendMessage(Kalender.PREFIX + "§7Es ist noch nicht Dezember§8.");
                return;
            }
            int num = Integer.parseInt((clickedItem.getItemMeta().getDisplayName().replace("§8▎ §7Türchen §8#§c", "")));
            if (day < num) {
                player.sendMessage(Kalender.PREFIX + "§7Es ist noch nicht der §c" + num + "§8'§c" + month + "§8.");
                return;
            }
            if (num >= 1 && num <= 23) {
                if (Kalender.getInstance().hasOpened(player, num)) {
                    player.sendMessage(Kalender.PREFIX + "§7Du hast dieses Türchen bereits geöffnet§8.");
                    return;
                }
                Kalender.getInstance().setOpened(player, num);
                player.sendMessage(Kalender.PREFIX + "§7Du hast Türchen §8#§c" + num + "§7 geöffnet§8.");
                //TODO: Rewards
                return;
            }
            if (num == 24) {
                if (Kalender.getInstance().hasOpened(player, num)) {
                    player.sendMessage(Kalender.PREFIX + "§7Du hast dieses Türchen bereits geöffnet§8.");
                    return;
                }
                Kalender.getInstance().setOpened(player, num);
                player.sendMessage(Kalender.PREFIX + "§7Du hast Türchen §8#§c" + num + "§7 geöffnet§8.");
                //TODO: Mega-Reward
            }
        }
    }

    @Override
    public void onClose(Player player, InventoryView inventoryView) {

    }
}
