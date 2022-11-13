package eu.skyphantom.skypvp.events;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCInteractListener implements Listener {

    @EventHandler
    public void onNpcRightClick(final NPCRightClickEvent event) {
        NPC npc = event.getNPC();
        Player player = event.getClicker();
        if (npc.getId() == 12) {
            event.setCancelled(true);
            player.chat("/rewards");
        }
        if (npc.getId() == 13) {
            event.setCancelled(true);
            player.chat("/advent");
        }
    }

}
