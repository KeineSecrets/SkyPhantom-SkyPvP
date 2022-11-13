package eu.skyphantom.skypvp.gui.warps;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.hooks.Gui;
import eu.skyphantom.skypvp.provider.WarpProvider;
import eu.skyphantom.skypvp.utils.items.ItemBuilder;
import eu.skyphantom.skypvp.utils.pagification.Pagifier;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class WarpListGui extends Gui {

    Pagifier<ItemStack> pagifier;
    int page;

    public WarpListGui(@NotNull Player player) {
        super("§8▎ §a§lWARPS§8 ▪ §7Liste", 6, player);
        this.page = 0;
        this.pagifier = new Pagifier<>(28);

        if (!WarpProvider.getAll().isEmpty()) {
            for (String warp : WarpProvider.getAll()) {
                WarpProvider warpProvider = new WarpProvider(warp);
                if (warpProvider.exists()) {
                    pagifier.addItem(new ItemBuilder(Material.EMERALD).setName("§8▎ §a§lWARP§8 ▪ §7" + warp).lore(
                            "§r",
                            "§8▎ §7Location§8:",
                            "§8▪ §a" + warpProvider.get().getWorld().getName(),
                            "§r"
                    ));
                }
            }
        }

        fill(new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15).setName("§8-/-§r"));
        fillBorders(new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§8-/-§r"));

        getInventory().setItem(26, new ItemBuilder(Material.SKULL_ITEM).setDataId(3).skullOwner("MHF_ArrowRight")
                .setName("§8» §7nächste Seite"));

        getInventory().setItem(18, new ItemBuilder(Material.SKULL_ITEM).setDataId(3).skullOwner("MHF_ArrowLeft")
                .setName("§8« §7vorherige Seite"));

        update(0);

    }

    @Override
    public void onClick(Player player, ClickType clickType, int clickedSlot, ItemStack clickedItem) {
        int page = this.page;
        int maxPages = pagifier.getPages().size();
        if (clickedSlot == 26) {
            if(this.pagifier.getPages().size() > page + 1) {
                update(page+1);
            } else {
                player.sendMessage(SkyPvP.PREFIX + "§7Du bist bereits auf der letzten Seite§8.");
                player.playSound(player.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
            }
        }
        if (clickedSlot == 18) {
            if(page > 0) {
                update(page-1);
            } else {
                player.sendMessage(SkyPvP.PREFIX + "§7Du bist bereits auf der ersten Seite§8.");
                player.playSound(player.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
            }
        }
        if (clickedItem.getType() == Material.EMERALD) {
            String warp = clickedItem.getItemMeta().getDisplayName().replace("§8▎ §a§lWARP§8 ▪ §7", "");
            WarpProvider warpProvider = new WarpProvider(warp);
            if (warpProvider.exists()) {
                player.teleport(warpProvider.get());
                player.sendMessage(SkyPvP.PREFIX + "§7Du wurdest zu dem Warp §8'§7" + warp + "§8'§7 teleportiert§8.");
                return;
            }
            player.sendMessage(SkyPvP.PREFIX + "§7Der Warp steht derzeit nicht zur Verfügung§8.");
            return;
         }
    }

    @Override
    public void onClose(Player player, InventoryView inventoryView) {

    }

    void update(int page) {

        this.page = page;
        for (int i = 10; i < 44; i++) {
            if (i == 17 || i == 18 || i == 26 || i == 27 || i == 35 || i == 36) continue;
            getInventory().setItem(i, new ItemStack(Material.AIR));
        }
        int slot = 10;
        for (ItemStack prefixItem : pagifier.getPage(page)) {
            if (slot == 17) slot = 19;
            if (slot == 26) slot = 28;
            if (slot == 35) slot = 37;
            getInventory().setItem(slot, prefixItem);
            slot++;
        }

        getInventory().setItem(26, new ItemBuilder(Material.SKULL_ITEM).setDataId(3).skullOwner("MHF_ArrowRight")
                .setName("§8» §7nächste Seite"));

        getInventory().setItem(18, new ItemBuilder(Material.SKULL_ITEM).setDataId(3).skullOwner("MHF_ArrowLeft")
                .setName("§8« §7vorherige Seite"));
    }
}
