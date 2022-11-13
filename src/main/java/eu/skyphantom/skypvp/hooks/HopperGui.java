package eu.skyphantom.skypvp.hooks;

/*
                (SkyLira - v2 (SkyPvP))
      This Class was created by MaxIstLegit
             21/02/2022 | 19:31
*/

import eu.skyphantom.skypvp.SkyPvP;
import net.minecraft.server.v1_8_R3.ChatMessage;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutOpenWindow;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public abstract class HopperGui {

	private final Inventory inventory;
	private final String inventoryName;
	private final Player player;

	public String getInventoryName() {
		return inventoryName;
	}

	public Player getPlayer() {
		return player;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public HopperGui(@Nonnull String guiName, @Nonnull Player player) {
		this.inventoryName = guiName;
		this.inventory = Bukkit.createInventory(null, InventoryType.HOPPER, guiName);
		this.player = player;
		this.player.closeInventory();
	}

	public void openGUI() {
		SkyPvP.getInstance().getGuiHandler().addPlayerGUI(this);
		this.player.openInventory(this.inventory);
	}

	public abstract void onClick(Player player, ClickType clickType, int clickedSlot, ItemStack clickedItem);

	public abstract void onClose(Player player, InventoryView inventoryView);

	public void fill(@Nonnull ItemStack itemStack) {
		for (int i = 0; i < 5; i++)
			inventory.setItem(i, itemStack);
	}

	public void setTitle(String title) {
		EntityPlayer entityPlayer = ((CraftPlayer) this.player).getHandle();
		PacketPlayOutOpenWindow packet = new PacketPlayOutOpenWindow(entityPlayer.activeContainer.windowId, "minecraft:hopper", new ChatMessage(title), this.player.getOpenInventory().getTopInventory().getSize());
		entityPlayer.playerConnection.sendPacket(packet);
		entityPlayer.updateInventory(entityPlayer.activeContainer);
	}

	public void fillBorders(@Nonnull ItemStack itemStack) {
		fill(itemStack);
	}
}


