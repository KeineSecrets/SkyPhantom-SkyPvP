package eu.skyphantom.skypvp.api;



import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.DespawnReason;
import net.citizensnpcs.api.npc.MemoryNPCDataStore;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.HashMap;
import java.util.Map;

public class NpcAPI {

    NPCRegistry registry;
    Map<Integer, String> commandMap;
    Config config;

    public NpcAPI() {
        this.registry = CitizensAPI.createAnonymousNPCRegistry(new MemoryNPCDataStore());
        this.commandMap = new HashMap<>();
        CitizensAPI.getNPCRegistry().forEach(npc -> {
            String command = config.getConfig().getString(String.valueOf(npc.getId()));
            if (command != null) {
                this.addCommand(npc, command);
            }
        });
    }

    public NPC createNpc(EntityType type, String name) {
        return this.registry.createNPC(type, name);
    }

    public boolean spawnNpc(NPC npc, Location location) {
        return npc.spawn(location);
    }

    public boolean despawnNpc(NPC npc) {
        return npc.despawn(DespawnReason.REMOVAL);
    }

    public void addCommand(NPC npc, String command) {
        this.commandMap.put(npc.getId(), command);
        config.getConfig().set(String.valueOf(npc.getId()), command);
        config.saveConfig();
    }

    public Map<Integer, String> getCommandMap() {
        return commandMap;
    }

    public NPCRegistry getRegistry() {
        return registry;
    }
}

