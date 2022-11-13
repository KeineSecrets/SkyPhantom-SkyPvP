package eu.skyphantom.skypvp.crates.animations;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.crates.CrateHandler;
import eu.skyphantom.skypvp.utils.Utils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CrateAnimation {

    public static List<Player> noClick = new ArrayList<>();
    static Random random = new Random();
    int taskId, index, selec;
    Inventory inventory;

    public void run(String crate, Player player) { //index >= 10;


        final CrateHandler crateHandler = new CrateHandler(crate);

        index = 0;
        List<ItemStack> crateItemList = crateHandler.getCrateItems();

        if (noClick.contains(player)) {
            player.sendMessage(SkyPvP.PREFIX + "§7Du öffnest bereits eine Crate§8.");
            return;
        }

        if (!crateHandler.crateExist()) {
            player.sendMessage(SkyPvP.PREFIX + "§7Diese Crate existiert nicht§8.");
            return;
        }

        if (crateHandler.getCrateItems().size() < 1) {
            player.sendMessage(SkyPvP.PREFIX + "§7Diese Crate beinhaltet keine Items§8.");
            return;
        }
        Utils.removeItemFromHand(player, 1);
        ItemStack winitem = getRandom(crateItemList);
        crateHandler.setCrateOpened(crateHandler.getCrateOpened() + 1);
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 50F, 14.8F);
        player.getInventory().addItem(winitem);
        noClick.remove(player);
        player.sendMessage(SkyPvP.PREFIX + "§7Du hast §a" + winitem.getAmount() + "§8x §a" + (winitem.getItemMeta().hasDisplayName() ? winitem.getItemMeta().getDisplayName() : winitem.getType().name().toUpperCase()) + " §7aus der Crate gezogen§8.");


    }

    public ItemStack getRandom(List<ItemStack> list) {
        int size = list.size();
        int index = random.nextInt(size);
        return list.get(index);
    }
}
