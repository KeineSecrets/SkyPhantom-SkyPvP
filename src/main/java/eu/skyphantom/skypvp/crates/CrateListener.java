package eu.skyphantom.skypvp.crates;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.crates.animations.CrateAnimation;
import eu.skyphantom.skypvp.crates.inventorys.CrateInventory;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;

public class CrateListener implements Listener {

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        final Player player = (Player)event.getWhoClicked();

        if(event.getClickedInventory() == null) return;

        if(event.getInventory().getName().contains("§8▎ §a§lCRATE§8 ▪ §7")) {

            event.setCancelled(true);

            String crate = event.getInventory().getName().replaceAll("§8▎ §a§lCRATE§8 ▪ §7", "").trim();


            if (event.getRawSlot() == 51) {

                int pageSize = (Integer.parseInt(player.getOpenInventory().getItem(49).getItemMeta().getLore().get(player.getOpenInventory().getItem(49).getItemMeta().getLore().size() - 2).replace("§7Seite §a", "").replace(("§8/§a" + CrateInventory.getPagifier().getPages().size()), "")) - 1);


                if (CrateInventory.getPagifier().getPages().size() > pageSize + 1) {
                    player.playSound(player.getLocation(), Sound.ARROW_HIT, 50, 18.4F);
                    new CrateInventory(player).openCrateVorschau(crate, pageSize + 1);

                } else {
                    player.playSound(player.getLocation(), Sound.NOTE_BASS, 50, 18.4F);
                    player.sendMessage(SkyPvP.PREFIX + "§7Du bist bereits auf der letzten Seite§8.");
                }
                return;
            }

            if (event.getRawSlot() == 47) {

                int pageSize = (Integer.parseInt(player.getOpenInventory().getItem(49).getItemMeta().getLore().get(player.getOpenInventory().getItem(49).getItemMeta().getLore().size() - 2).replace("§7Seite §a", "").replace(("§8/§a" + CrateInventory.getPagifier().getPages().size()), "")) - 1);


                if (pageSize > 0) {
                    player.playSound(player.getLocation(), Sound.ARROW_HIT, 50, 18.4F);
                    new CrateInventory(player).openCrateVorschau(crate, pageSize - 1);
                } else {
                    player.playSound(player.getLocation(), Sound.NOTE_BASS, 50, 18.4F);
                    player.sendMessage(SkyPvP.PREFIX + "§7Du bist bereits auf der ersten Seite§8.");
                }
            }
            return;

        }


        if (player.getOpenInventory().getType() == InventoryType.CHEST && CrateAnimation.noClick.contains(player) && player.getOpenInventory().getTitle().contains("§8▎ §a§lANIMATION §8▪ §7")) event.setCancelled(true);

    }

    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent event) {

        final Player player = event.getPlayer();

        if (player.getItemInHand() == null || player.getItemInHand().getType() == Material.AIR) return;
        if (!player.getItemInHand().getItemMeta().hasDisplayName()) return;
        if (!player.getItemInHand().hasItemMeta()) return;

        if(player.getItemInHand().getItemMeta().getDisplayName().contains("§8▎ §a§lCRATE§8 ▪ §7") && player.getItemInHand().getItemMeta() != null) {

            String cratename = player.getItemInHand().getItemMeta().getDisplayName().replaceAll("§8▎ §a§lCRATE§8 ▪ §7", "").trim();

            if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                new CrateInventory(player).openCrateVorschau(cratename, 0);
                return;
            }

            if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                new CrateAnimation().run(cratename, player);
                return;
            }
        }
    }

}
