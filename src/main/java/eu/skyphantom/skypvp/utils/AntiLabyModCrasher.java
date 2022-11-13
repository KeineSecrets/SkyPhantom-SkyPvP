package eu.skyphantom.skypvp.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutCustomPayload;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;

public class AntiLabyModCrasher {

    public enum LabyMod {
        FOOD, NICK, BLOCKBUILD, EXTRAS, ANIMATIONS, POTIONS, ARMOR, DAMAGEINDICATOR, MINIMAP_RADAR, SATURATION_BAR, CHAT, GUI_POTION_EFFECTS, GUI_ARMOR_HUD, GUI_ITEM_HUD, TAGS, IMPROVED_LAVA, CROSSHAIR_SYNC, REFILL_FIX;
    }

    public void disableMod(Player p, List<LabyMod> mods) {
        try {
            HashMap<String, Boolean> dList = new HashMap<>();
            for (LabyMod mod : mods)
                dList.put(mod.toString(), false);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream objectOut = new ObjectOutputStream(out);
            objectOut.writeObject(dList);
            ByteBuf bb = Unpooled.copiedBuffer(out.toByteArray());
            PacketDataSerializer serializer = new PacketDataSerializer(bb);
            PacketPlayOutCustomPayload packet = new PacketPlayOutCustomPayload("LABYMOD", serializer);
            (((CraftPlayer) p).getHandle()).playerConnection.sendPacket((Packet) packet);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
