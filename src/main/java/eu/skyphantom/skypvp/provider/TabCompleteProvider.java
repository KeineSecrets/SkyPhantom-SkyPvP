package eu.skyphantom.skypvp.provider;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import eu.skyphantom.skypvp.SkyPvP;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class TabCompleteProvider {

    public TabCompleteProvider() {
        final ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        manager.addPacketListener(new PacketAdapter(SkyPvP.getInstance(), PacketType.Play.Client.TAB_COMPLETE) {
            @SuppressWarnings("rawtypes")
            public void onPacketReceiving(PacketEvent event) {
                if ((event.getPacketType() == PacketType.Play.Client.TAB_COMPLETE) && (event.getPacket().getStrings().read(0).startsWith("/")) && (event.getPacket().getStrings().read(0).split(" ").length == 1)) {

                    if (event.getPlayer().isOp()) return;

                    String[] tabList = (new String[0]);

                    PacketContainer tabComplete = manager.createPacket(PacketType.Play.Server.TAB_COMPLETE);
                    tabComplete.getStringArrays().write(0, tabList);

                    try {
                        manager.sendServerPacket(event.getPlayer(), tabComplete);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
