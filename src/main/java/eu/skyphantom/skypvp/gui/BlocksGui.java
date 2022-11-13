package eu.skyphantom.skypvp.gui;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.hooks.Gui;
import eu.skyphantom.skypvp.utils.Utils;
import eu.skyphantom.skypvp.utils.items.ItemBuilder;
import eu.skyphantom.skypvp.utils.pagification.Pagifier;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class BlocksGui extends Gui {

    Pagifier<ItemStack> pagifier;
    int page;

    public BlocksGui(@Nonnull Player player) {
        super("§8▎ §a§lBLÖCKE", 5, player);

        this.pagifier = new Pagifier<>(21);

        this.pagifier.addItem(new ItemBuilder(Material.STONE).setDataId(0));
        this.pagifier.addItem(new ItemBuilder(Material.GRASS).setDataId(0));
        this.pagifier.addItem(new ItemBuilder(Material.WOOD).setDataId(0));
        this.pagifier.addItem(new ItemBuilder(Material.WOOD).setDataId(1));
        this.pagifier.addItem(new ItemBuilder(Material.WOOD).setDataId(2));
        this.pagifier.addItem(new ItemBuilder(Material.WOOD).setDataId(3));
        this.pagifier.addItem(new ItemBuilder(Material.WOOD).setDataId(4));
        this.pagifier.addItem(new ItemBuilder(Material.SAND).setDataId(0));
        this.pagifier.addItem(new ItemBuilder(Material.SAND).setDataId(1));
        this.pagifier.addItem(new ItemBuilder(Material.GLASS).setDataId(0));
        this.pagifier.addItem(new ItemBuilder(Material.WOOL).setDataId(0));
        this.pagifier.addItem(new ItemBuilder(Material.WOOL).setDataId(1));
        this.pagifier.addItem(new ItemBuilder(Material.WOOL).setDataId(2));
        this.pagifier.addItem(new ItemBuilder(Material.WOOL).setDataId(3));
        this.pagifier.addItem(new ItemBuilder(Material.WOOL).setDataId(4));
        this.pagifier.addItem(new ItemBuilder(Material.WOOL).setDataId(5));
        this.pagifier.addItem(new ItemBuilder(Material.WOOL).setDataId(6));
        this.pagifier.addItem(new ItemBuilder(Material.WOOL).setDataId(7));
        this.pagifier.addItem(new ItemBuilder(Material.WOOL).setDataId(8));
        this.pagifier.addItem(new ItemBuilder(Material.WOOL).setDataId(9));
        this.pagifier.addItem(new ItemBuilder(Material.WOOL).setDataId(10));
        this.pagifier.addItem(new ItemBuilder(Material.WOOL).setDataId(11));
        this.pagifier.addItem(new ItemBuilder(Material.WOOL).setDataId(12));
        this.pagifier.addItem(new ItemBuilder(Material.WOOL).setDataId(13));
        this.pagifier.addItem(new ItemBuilder(Material.WOOL).setDataId(14));
        this.pagifier.addItem(new ItemBuilder(Material.WOOL).setDataId(15));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_GLASS).setDataId(0));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_GLASS).setDataId(1));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_GLASS).setDataId(2));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_GLASS).setDataId(3));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_GLASS).setDataId(4));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_GLASS).setDataId(5));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_GLASS).setDataId(6));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_GLASS).setDataId(7));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_GLASS).setDataId(8));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_GLASS).setDataId(9));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_GLASS).setDataId(10));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_GLASS).setDataId(11));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_GLASS).setDataId(12));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_GLASS).setDataId(13));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_GLASS).setDataId(14));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_GLASS).setDataId(15));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_CLAY).setDataId(0));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_CLAY).setDataId(1));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_CLAY).setDataId(2));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_CLAY).setDataId(3));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_CLAY).setDataId(4));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_CLAY).setDataId(5));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_CLAY).setDataId(6));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_CLAY).setDataId(7));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_CLAY).setDataId(8));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_CLAY).setDataId(9));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_CLAY).setDataId(10));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_CLAY).setDataId(11));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_CLAY).setDataId(12));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_CLAY).setDataId(13));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_CLAY).setDataId(14));
        this.pagifier.addItem(new ItemBuilder(Material.STAINED_CLAY).setDataId(15));

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
        if (!clickedItem.getItemMeta().hasDisplayName() || !clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("§8-/-§r")) {
            if (clickedSlot == 18 || clickedSlot == 26) return;
            ItemStack itemStack = clickedItem.clone();
            itemStack.setAmount(16);
            Utils.addItem(player, itemStack);
            player.sendMessage(SkyPvP.PREFIX + "§7Du hast dir §e16§8x '§e" + itemStack.getType().name().toUpperCase() + "§8'§7 gegeben§8.");
        }
    }

    @Override
    public void onClose(Player player, InventoryView inventoryView) {

    }

    void update(int page) {
        this.page = page;
        for (int i = 10; i < 35; i++) {
            if (i == 17 || i == 18 || i == 26 || i == 27) continue;
            getInventory().setItem(i, new ItemStack(Material.AIR));
        }
        int slot = 10;
        for (ItemStack prefixItem : pagifier.getPage(page)) {
            if (slot == 17) slot = 19;
            if (slot == 26) slot = 28;
            getInventory().setItem(slot, prefixItem);
            slot++;
        }
    }
}

