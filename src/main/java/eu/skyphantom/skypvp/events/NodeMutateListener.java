package eu.skyphantom.skypvp.events;

import eu.skyphantom.skypvp.provider.TablistProvider;
import net.luckperms.api.event.node.NodeMutateEvent;
import org.bukkit.Bukkit;

public class NodeMutateListener {

    public static void onGroupChange(final NodeMutateEvent event) {
        Bukkit.getOnlinePlayers().forEach(TablistProvider::setPrefix);
    }

}
