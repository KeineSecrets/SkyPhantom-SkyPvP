package eu.skyphantom.skypvp.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class InventoryCloseListener implements Listener {

    @EventHandler
    public void onInventoryClose(final InventoryCloseEvent event) {
        if (event.getView().getType() == InventoryType.ENCHANTING) {
            event.getView().setItem(1, new ItemStack(Material.AIR));
        }
    }

}
