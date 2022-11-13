package eu.skyphantom.skypvp.crates.inventorys;
import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.crates.CrateHandler;
import eu.skyphantom.skypvp.utils.Utils;
import eu.skyphantom.skypvp.utils.items.ItemBuilder;
import eu.skyphantom.skypvp.utils.pagification.Pagifier;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CrateInventory {

    Player player;
    private static Pagifier<ItemStack> pagifier;

    public static Pagifier<ItemStack> getPagifier() {
        return pagifier;
    }

    public CrateInventory(Player player) {
        this.player = player;
    }

    public void openCrateVorschau(String crate, int page) {

        pagifier = new Pagifier<>(28);

        CrateHandler crateHandler;

        crateHandler = new CrateHandler(crate);

        if(!crateHandler.crateExist()) {
            player.sendMessage(SkyPvP.PREFIX + "§7Diese Crate existiert nicht§8.");
            return;
        }

        if(crateHandler.getCrateItems().size() < 1) {
            player.sendMessage(SkyPvP.PREFIX + "§7Diese Crate beinhaltet keine Items§8.");
            return;
        }

        Inventory inventory = Bukkit.createInventory(null, 54, "§8▎ §a§lCRATE§8 ▪ §7" + crate);

        ItemStack border = new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(15).setName("§r");
        ItemStack in = new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§r");

        Utils.fill(in, inventory);
        Utils.fillBorders(border, inventory);


        for(ItemStack s : crateHandler.getCrateItems()) {

            ItemBuilder itemBuilder = new ItemBuilder(s).setName("§8▎ §7" + (s.getItemMeta().hasDisplayName() ? s.getItemMeta().getDisplayName() : Utils.capitalizeFirstLetter(s.getType().name().toLowerCase())));
            pagifier.addItem(itemBuilder);

        }

        player.playSound(player.getLocation(), Sound.ENDERDRAGON_WINGS, 10, 18.4F);
        player.openInventory(inventory);
        update(inventory, page);

    }

    public static void update(Inventory inventory, int page) {


        for (int i = 10; i < 44; i++) {
            if (i == 17 || i == 18 || i == 26 || i == 27 || i == 35 || i == 36) continue;
            inventory.setItem(i, new ItemStack(Material.AIR));
        }
        int slot = 10;
        for (ItemStack item : pagifier.getPage(page)) {
            if (slot == 17) slot += 2;
            if (slot == 26) slot += 2;
            if (slot == 35) slot += 2;

            inventory.setItem(slot, new ItemBuilder(item));
            slot++;
        }



        /*
            TODO: AKTUELLE SEITE (49)
            TODO: SEITE VOR (51)
            TODO: SEITE ZURUECK (47)
         */

        inventory.setItem(49, new ItemBuilder(Material.SIGN).setName("§8▎ §aAktuelle Seite").lore("", "§7Seite §a" + (page + 1) + "§8/§a" + pagifier.getPages().size(), ""));


        if (pagifier.getPages().size() > (page + 1)) {
            inventory.setItem(49, new ItemBuilder(Material.SIGN).setName("§8▎ §aAktuelle Seite").lore("", "§7Seite §a" + (page + 1) + "§8/§a" + pagifier.getPages().size(), ""));
        } else {
            inventory.setItem(47, new ItemBuilder(Material.PAPER).setName("§8▎ §aSeite zurück").lore("", "§7Du bist bereits auf der letzten Seite§8.", ""));
        }
        if (page <= 0) {
            inventory.setItem(51, new ItemBuilder(Material.PAPER).setName("§8▎ §aSeite vor").lore("", "§7Du bist bereits auf der ersten Seite§8.", ""));
        } else {
            inventory.setItem(49, new ItemBuilder(Material.SIGN).setName("§8▎ §aAktuelle Seite").lore("", "§7Seite §a" + (page + 1) + "§8/§a" + pagifier.getPages().size(), ""));
        }
    }
}
