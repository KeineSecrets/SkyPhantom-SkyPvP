package eu.skyphantom.skypvp.events;

import eu.skyphantom.skypvp.provider.TablistProvider;
import net.luckperms.api.event.node.NodeMutateEvent;
import net.luckperms.api.event.user.UserDataRecalculateEvent;
import org.bukkit.Bukkit;

public class NodeMutateListener {

    public static void onGroupChange(final UserDataRecalculateEvent event) {
        Bukkit.getOnlinePlayers().forEach(TablistProvider::setPrefix);
    }

}
