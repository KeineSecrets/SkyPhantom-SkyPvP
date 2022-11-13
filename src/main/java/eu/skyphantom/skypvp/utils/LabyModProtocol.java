package eu.skyphantom.skypvp.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.EncoderException;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutCustomPayload;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.nio.charset.StandardCharsets;

public class LabyModProtocol {

	public static void sendLabyModMessage(Player player, String key, JsonElement messageContent) {
		byte[] bytes = getBytesToSend(key, messageContent.toString());
		PacketDataSerializer pds = new PacketDataSerializer(Unpooled.wrappedBuffer(bytes));
		PacketPlayOutCustomPayload payloadPacket = new PacketPlayOutCustomPayload("labymod3:main", pds);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(payloadPacket);
	}

	public static byte[] getBytesToSend(String messageKey, String messageContents) {
		ByteBuf byteBuf = Unpooled.buffer();
		writeString(byteBuf, messageKey);
		writeString(byteBuf, messageContents);
		byte[] bytes = new byte[byteBuf.readableBytes()];
		byteBuf.readBytes(bytes);
		byteBuf.release();
		return bytes;
	}

	private static void writeVarIntToBuffer(ByteBuf buf, int input) {
		while ((input & -128) != 0) {
			buf.writeByte(input & 127 | 128);
			input >>>= 7;
		}
		buf.writeByte(input);
	}

	private static void writeString(ByteBuf buf, String string) {
		byte[] abyte = string.getBytes(StandardCharsets.UTF_8);
		if (abyte.length > Short.MAX_VALUE) {
			throw new EncoderException("String too big (was " + string.length() + " bytes encoded, max " + Short.MAX_VALUE + ")");
		} else {
			writeVarIntToBuffer(buf, abyte.length);
			buf.writeBytes(abyte);
		}
	}

	public static int readVarIntFromBuffer(ByteBuf buf) {
		int i = 0;
		int j = 0;

		byte b0;
		do {
			b0 = buf.readByte();
			i |= (b0 & 127) << j++ * 7;
			if (j > 5) {
				throw new RuntimeException("VarInt too big");
			}
		} while ((b0 & 128) == 128);
		return i;
	}

	public static String readString(ByteBuf buf, int maxLength) {
		int i = readVarIntFromBuffer(buf);

		if (i > maxLength * 4) {
			throw new DecoderException("The received encoded string buffer length is longer than maximum allowed (" + i + " > " + maxLength * 4 + ")");
		} else if (i < 0) {
			throw new DecoderException("The received encoded string buffer length is less than zero! Weird string!");
		} else {
			byte[] bytes = new byte[i];
			buf.readBytes(bytes);

			String s = new String(bytes, StandardCharsets.UTF_8);
			if (s.length() > maxLength) {
				throw new DecoderException("The received string length is longer than maximum allowed (" + i + " > " + maxLength + ")");
			} else {
				return s;
			}
		}
	}


	public static void sendCurrentPlayingGamemode(Player player, boolean visible, String gamemodeName) {
		JsonObject object = new JsonObject();
		object.addProperty("show_gamemode", visible);
		object.addProperty("gamemode_name", gamemodeName);
		LabyModProtocol.sendLabyModMessage(player, "server_gamemode", object);
	}

	public static void disableVoiceChat(Player player) {
		JsonObject object = new JsonObject();
		object.addProperty("allowed", false);
		LabyModProtocol.sendLabyModMessage(player, "voicechat", object);
	}

	public static void sendServerBanner(Player player, String imageUrl) {
		JsonObject object = new JsonObject();
		object.addProperty("url", imageUrl);
		LabyModProtocol.sendLabyModMessage(player, "server_banner", object);
	}

	public static void sendDiscordHook(Player player) {
		JsonObject obj = new JsonObject();
		obj.addProperty("hasGame", "true");
		obj.addProperty("game_mode", "SkyPhantom.EU SkyPvP");
		obj.addProperty("game_startTime", System.currentTimeMillis());
		obj.addProperty("game_endTime", 0L);
		LabyModProtocol.sendLabyModMessage(player, "discord_rpc", obj);
	}
}
