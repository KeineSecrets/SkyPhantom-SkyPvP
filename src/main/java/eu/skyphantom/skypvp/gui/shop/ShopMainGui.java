package eu.skyphantom.skypvp.gui.shop;

import eu.skyphantom.skypvp.hooks.Gui;
import eu.skyphantom.skypvp.utils.Utils;
import eu.skyphantom.skypvp.utils.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ShopMainGui extends Gui {

    public ShopMainGui(@NotNull Player player) {
        super("§8▎ §a§lSHOP§8 ▪ §7Menü", 6, player);

        fill(new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15).setName("§8-/-§r"));
        fillBorders(new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§8-/-§r"));

        getInventory().setItem(20, new ItemBuilder(Utils.createSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzNkMTQ1NjFiYmQwNjNmNzA0MjRhOGFmY2MzN2JmZTljNzQ1NjJlYTM2ZjdiZmEzZjIzMjA2ODMwYzY0ZmFmMSJ9fX0=")).setName("§8▎ §a§lKATEGORIE§8 ▪ §7Rechte").lore("§8▪ §7In dieser Kategorie findest du Rechte§8,", "§8▪ §7die du dir mit Tokens kaufen kannst§8."));

        getInventory().setItem(22, new ItemBuilder(Utils.createSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGZjM2MyNDNmYzA4OTRhYTQwMjhkMzJiMTlhODMwYTJmY2FkYzI5MzI3MGI0Y2IzMmMxYmFlNDJjNzhjMDhiZSJ9fX0=")).setName("§8▎ §a§lKATEGORIE§8 ▪ §7Ränge").lore("§8▪ §7In dieser Kategorie findest du Ränge§8,", "§8▪ §7die du dir mit Tokens kaufen kannst§8."));

        getInventory().setItem(24, new ItemBuilder(Utils.createSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTgyNTQxOWU0MjlhZmMwNDBjOWU2OGIxMDUyM2I5MTdkN2I4MDg3ZDYzZTc2NDhiMTA4MDdkYThiNzY4ZWUifX19")).setName("§8▎ §a§lKATEGORIE§8 ▪ §7Gutscheine").lore("§8▪ §7In dieser Kategorie findest du Gutscheine§8,", "§8▪ §7die du dir mit Tokens kaufen kannst§8."));

        getInventory().setItem(30, new ItemBuilder(Utils.createSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTdkZjBlN2ZmZTZiZTUzMzZmZjEzNzNjMzEzYTFmNWMxOWFkODQ1Y2RiODkwMjE1YzgzYmZhYjlmOGQ5ODliOSJ9fX0=")).setName("§8▎ §a§lKATEGORIE§8 ▪ §7PvP").lore("§8▪ §7In dieser Kategorie findest du PvP§8-§7Stuff§8,", "§8▪ §7den du dir mit Tokens kaufen kannst§8."));

        getInventory().setItem(32, new ItemBuilder(Utils.createSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzVjOWNjY2Y2MWE2ZTYyODRmZTliYmU2NDkxNTViZTRkOWNhOTZmNzhmZmNiMjc5Yjg0ZTE2MTc4ZGFjYjUyMiJ9fX0=")).setName("§8▎ §a§lKATEGORIE§8 ▪ §7Admin§8-§7Items").lore("§8▪ §7In dieser Kategorie findest du §7Admin§8-§7Items§8,", "§8▪ §7die du dir mit Tokens kaufen kannst§8."));

    }

    @Override
    public void onClick(Player player, ClickType clickType, int clickedSlot, ItemStack clickedItem) {

    }

    @Override
    public void onClose(Player player, InventoryView inventoryView) {

    }
}
