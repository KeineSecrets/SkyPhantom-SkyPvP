package eu.skyphantom.skypvp.battlepass;

import eu.skyphantom.skypvp.hooks.Gui;
import eu.skyphantom.skypvp.utils.items.ItemBuilder;
import eu.skyphantom.skypvp.utils.pagification.Pagifier;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class BattlepassGui extends Gui {

    Pagifier<ItemStack> pagifier;
    BattlepassManager battlepassManager;
    BattlepassRewardsManager battlepassRewardsManager;
    int page;

    public BattlepassGui(@NotNull Player player) {
        super("§8▎ §a§lBATTLEPASS", 3, player);

        fill(new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15).setName("§8-/-§r"));
        fillBorders(new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§8-/-§r"));

        battlepassManager = new BattlepassManager(player.getUniqueId());
        battlepassRewardsManager = new BattlepassRewardsManager(player.getUniqueId());

        pagifier = new Pagifier<>(7);

        for (ItemStack itemStack : battlepassRewardsManager.getAllAvailableRewards()) {
            pagifier.addItem(itemStack);
        }

        this.page = 0;

        update(this.page);

    }

    @Override
    public void onClick(Player player, ClickType clickType, int clickedSlot, ItemStack clickedItem) {
        if (clickedSlot == 0) {
            update(this.page-1);
            return;
        }
        if (clickedSlot == 1) {
            update(this.page+1);
            return;
        }
    }

    @Override
    public void onClose(Player player, InventoryView inventoryView) {

    }

    void update(int page) {

        fill(new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15).setName("§8-/-§r"));
        fillBorders(new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§8-/-§r"));

        this.page = page;
        int slot = 10;
        for (int i = 10; i <= 15; i++) {
            getInventory().setItem(i, new ItemStack(Material.AIR));
        }
        for (ItemStack itemStack : pagifier.getPage(page)) {
            getInventory().setItem(slot, itemStack);
            slot++;
        }
    }
}
