package eu.skyphantom.skypvp.hooks;

/*
                (SkyLira - v2 (SkyPvP))
      This Class was created by MaxIstLegit
             21/02/2022 | 19:31
*/

import eu.skyphantom.skypvp.SkyPvP;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class GuiHandler implements Listener {

	private final Map<Player, Gui> playerGuiMap = new HashMap<>();
	private final Map<Player, HopperGui> playerHopperGuiMap = new HashMap<>();

	public GuiHandler() {
		Bukkit.getPluginManager().registerEvents(this, SkyPvP.getInstance());
	}

	public void addPlayerGUI(@Nonnull Gui gui) {
		removePlayerGUI(gui);
		playerGuiMap.put(gui.getPlayer(), gui);
	}

	public void addPlayerGUI(@Nonnull HopperGui gui) {
		removePlayerGUI(gui);
		playerHopperGuiMap.put(gui.getPlayer(), gui);
	}

	public void removePlayerGUI(@Nonnull Gui gui) {
		playerGuiMap.remove(gui.getPlayer());
	}
	public void removePlayerGUI(@Nonnull HopperGui gui) {
		playerHopperGuiMap.remove(gui.getPlayer());
	}

	private boolean isValidInventory(Inventory inventory) {
		return inventory != null && inventory.getName() != null;
	}

	private boolean hasSameName(@Nonnull Inventory inventory, @Nonnull Inventory inventory2) {
		return inventory.getName().equalsIgnoreCase(inventory2.getName());
	}

	@EventHandler
	public void InventoryClick(final InventoryClickEvent event) {
		if (event.getWhoClicked() instanceof Player && !event.isCancelled()) {
			final Player player = (Player) event.getWhoClicked();
			if (event.getClickedInventory() != null && event.getClickedInventory().getType() == InventoryType.HOPPER) {
				if (playerHopperGuiMap.containsKey(player)) {
					event.setCancelled(true);
					if (isValidInventory(event.getInventory()) && isValidInventory(event.getClickedInventory()) && isValidInventory(player.getOpenInventory().getTopInventory())) {
						if (hasSameName(event.getInventory(), event.getClickedInventory()) && hasSameName(event.getInventory(), player.getOpenInventory().getTopInventory())) {
							if (event.getInventory().getViewers().contains(player) && event.getClickedInventory().getViewers().contains(player) && event.getCurrentItem() != null) {
								HopperGui gui = playerHopperGuiMap.get(player);
								gui.onClick(player, event.getClick(), event.getSlot(), event.getCurrentItem());
							}
						}
					}
				}
			} else {
				if (playerGuiMap.containsKey(player)) {
					event.setCancelled(true);
					if (isValidInventory(event.getInventory()) && isValidInventory(event.getClickedInventory()) && isValidInventory(player.getOpenInventory().getTopInventory())) {
						if (hasSameName(event.getInventory(), event.getClickedInventory()) && hasSameName(event.getInventory(), player.getOpenInventory().getTopInventory())) {
							if (event.getInventory().getViewers().contains(player) && event.getClickedInventory().getViewers().contains(player) && event.getCurrentItem() != null) {
								Gui gui = playerGuiMap.get(player);
								gui.onClick(player, event.getClick(), event.getSlot(), event.getCurrentItem());
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onInventoryClose(final InventoryCloseEvent event) {
		final Player player = (Player) event.getPlayer();
		if (playerGuiMap.containsKey(player)) {
			if (isValidInventory(event.getInventory()) && isValidInventory(player.getOpenInventory().getTopInventory())) {
				if (hasSameName(event.getInventory(), player.getOpenInventory().getTopInventory())) {
					if (event.getInventory().getViewers().contains(player)) {
						Gui gui = playerGuiMap.get(player);
						gui.onClose(player, event.getView());
						removePlayerGUI(gui);
					}
				}
			}
		} else if (playerHopperGuiMap.containsKey(player)) {
			if (isValidInventory(event.getInventory()) && isValidInventory(player.getOpenInventory().getTopInventory())) {
				if (hasSameName(event.getInventory(), player.getOpenInventory().getTopInventory())) {
					if (event.getInventory().getViewers().contains(player)) {
						HopperGui gui = playerHopperGuiMap.get(player);
						gui.onClose(player, event.getView());
						removePlayerGUI(gui);
					}
				}
			}
		}
	}

	/*public void shutdown() {
		this.playerMap.forEach((players, object) -> {
			if (players.getOpenInventory() != null)
				players.closeInventory();
		});
		this.playerMap.clear();
	}*/
}