package eu.skyphantom.skypvp.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import eu.skyphantom.skypvp.utils.font.DefaultFontInfo;
import eu.skyphantom.skypvp.utils.items.ItemBuilder;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_8_R3.World;
import net.minecraft.server.v1_8_R3.*;
import org.apache.commons.lang.RandomStringUtils;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import java.awt.Color;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Utils {

    private static final Field PROFILE_FIELD;
    private static final int CENTER_PX = 154;
    private static Class<?> skullMetaClass, tileEntityClass;
    private static Random random = new Random();
    private static List<UUID> godMode = new CopyOnWriteArrayList<>();

    static {
        Field f = null;
        try {
            skullMetaClass = Class.forName("org.bukkit.craftbukkit." + "v1_8_R3" + ".inventory.CraftMetaSkull");
            tileEntityClass = Class.forName("net.minecraft.server." + "v1_8_R3" + ".TileEntitySkull");
            Class<?> c = Class.forName("org.bukkit.craftbukkit.v1_8_R3.inventory.CraftMetaSkull");
            f = c.getDeclaredField("profile");
            f.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PROFILE_FIELD = f;
    }

    public static List<UUID> getGodMode() {
        return godMode;
    }

    public static String replaceAllSpecialCharacters(String string) {
        return string.replaceAll("[@#$%^&*_-]", "").replaceAll("[#+,.<>|!/()={}~€°'\"]", "").replace("\\", "").replace("[", "").replace("]", "");
    }

    public static String format(double aDouble, String color) {
        return new SimpleDateFormat("#,###.##").format(aDouble).replace(",", ".").replace(".", "§8'" + color);
    }

    public static int getRandomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    @Deprecated
    public static void broadcast(String message) {
        broadcast(message, false);
    }

    @Deprecated
    public static void broadcastCentered(String message) {
        broadcast(message, true);
    }

    public static void broadcast(String message, boolean centered) {
        if (!centered) {
            Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(message.replace("&", "§")));
            return;
        }
        Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(getCenteredMessage(message)));
    }

    public static void sendToPlayer(Player player, String message, boolean centered) {
        if (!centered) {
            player.sendMessage(message.replace("&", "§"));
            return;
        }
        player.sendMessage(getCenteredMessage(message));
    }

    public static void playSound(Player player, Sound sound, float volume, float pitch) {
        player.playSound(player.getLocation(), sound, volume, pitch);
    }

    public static void playAllSound(Location location, Sound sound, float volume, float pitch) {
        Bukkit.getOnlinePlayers().forEach(player -> player.playSound(location, sound, volume, pitch));
    }

    public static void playSound(Collection<? extends Player> players, Sound sound, float volume, float pitch) {
        players.forEach(player -> player.playSound(player.getLocation(), sound, volume, pitch));
    }

    public static void playSound(Collection<? extends Player> players, Location location, Sound sound, float volume, float pitch) {
        players.forEach(player -> player.playSound(location, sound, volume, pitch));
    }

    public static double getRandomID() {
        int min = 100000;
        int max = 999999;
        double random = Math.floor(Math.random() * (max - min + 1) + min);
        return random;
    }

    public static void fill(@Nonnull ItemStack itemStack, Inventory inventory) {
        for (int i = 0; i < inventory.getSize(); i++)
            inventory.setItem(i, itemStack);
    }

    public static void fillBorders(@Nonnull ItemStack itemStack, Inventory inventory) {
        int size = inventory.getSize();
        int rows = (size + 1) / 9;

        for (int i = 0; i < rows * 9; i++) {
            if ((i <= 8) || (i >= (rows * 9) - 9)
                    || i == 9 || i == 18
                    || i == 27 || i == 36
                    || i == 17 || i == 26
                    || i == 35 || i == 44)
                inventory.setItem(i, itemStack);
        }
    }

    public static String getRandomString(int length, boolean withNumbers, boolean withLetters) {
        if (!withLetters && !withNumbers) return "";
        return RandomStringUtils.random(length, withLetters, withNumbers);
    }

    public static String getOnlineTime(Player player) {
        final int TICKS_PER_SECOND = 20;
        final Duration ONE_TICK = Duration.ofSeconds(1).dividedBy(TICKS_PER_SECOND);
        int ticks = player.getStatistic(Statistic.PLAY_ONE_TICK);
        Duration plpTime = ONE_TICK.multipliedBy(ticks);
        long days = plpTime.toDays();
        plpTime = plpTime.minusDays(days);
        long hours = plpTime.toHours();
        plpTime = plpTime.minusHours(hours);
        long minutes = plpTime.toMinutes();
        plpTime = plpTime.minusMinutes(minutes);
        long seconds = (plpTime.toMillis() / 1000L);
        return String.format("%02d:%02d:%02d:%02d",
                days, hours, minutes, seconds);
    }

    public static String getCenteredMessage(String message) {
        if (message == null || message.equals("")) return "";
        message = ChatColor.translateAlternateColorCodes('&', message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for (char c : message.toCharArray()) {
            if (c == '§') {
                previousCode = true;
                continue;
            } else if (previousCode) {
                previousCode = false;
                if (c == 'l' || c == 'L') {
                    isBold = true;
                    continue;
                } else isBold = false;
            } else {
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while (compensated < toCompensate) {
            sb.append(" ");
            compensated += spaceLength;
        }
        return (sb.toString() + message);
    }

    public static void sendActionbar(Player player, String message) {
        String msg = message.replace("&", "§");
        msg = ChatColor.translateAlternateColorCodes('&', msg);
        IChatBaseComponent iChatBaseComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + msg + "\"}");
        PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(iChatBaseComponent, (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutChat);
    }

    public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
        PlayerConnection connection = (((CraftPlayer) player).getHandle()).playerConnection;
        PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, fadeIn.intValue(), stay.intValue(), fadeOut.intValue());
        connection.sendPacket((Packet) packetPlayOutTimes);
        if (subtitle != null) {
            subtitle = subtitle.replaceAll("%player%", player.getDisplayName());
            subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
            IChatBaseComponent titleSub = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
            PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, titleSub);
            connection.sendPacket((Packet) packetPlayOutSubTitle);
        }
        if (title != null) {
            title = title.replaceAll("%player%", player.getDisplayName());
            title = ChatColor.translateAlternateColorCodes('&', title);
            IChatBaseComponent titleMain = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
            PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleMain);
            connection.sendPacket((Packet) packetPlayOutTitle);
        }
    }

    public static void sendTabTitle(Player player, String header, String footer) {
        if (header == null) header = "";
        header = ChatColor.translateAlternateColorCodes('&', header);
        if (footer == null) footer = "";
        footer = ChatColor.translateAlternateColorCodes('&', footer);
        header = header.replaceAll("%player%", player.getDisplayName()).replace("%online%", String.valueOf(Bukkit.getOnlinePlayers().size())).replace("%max%", String.valueOf(Bukkit.getMaxPlayers()));
        footer = footer.replaceAll("%player%", player.getDisplayName()).replace("%online%", String.valueOf(Bukkit.getOnlinePlayers().size())).replace("%max%", String.valueOf(Bukkit.getMaxPlayers()));
        PlayerConnection connection = (((CraftPlayer) player).getHandle()).playerConnection;
        IChatBaseComponent tabTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + header + "\"}");
        IChatBaseComponent tabFoot = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + footer + "\"}");
        PacketPlayOutPlayerListHeaderFooter headerPacket = new PacketPlayOutPlayerListHeaderFooter(tabTitle);
        try {
            Field field = headerPacket.getClass().getDeclaredField("b");
            field.setAccessible(true);
            field.set(headerPacket, tabFoot);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.sendPacket((Packet) headerPacket);
        }
    }

    public static HoverEvent showItem(ItemStack itemStack) {
        return new HoverEvent(HoverEvent.Action.SHOW_ITEM, new BaseComponent[]{new TextComponent(CraftItemStack.asNMSCopy(itemStack).save(new NBTTagCompound()).toString())});
    }

	/*public static  void teleportToRandomPosition(Player p, int min, int max) {
		Location loc = null;
		Random rnd = new Random();
		while (loc == null || new LocationProvider("spawn").getLocation().distanceSquared(loc) < 1.0E8) {
			int x = rnd.nextInt(max - min + 1) + min;
			int z = rnd.nextInt(max - min + 1) + min;
			loc = p.getWorld().getHighestBlockAt(x, z).getLocation().add(0.5, 0.0, 0.5);
		}
		p.teleport(loc);
		if (!loc.subtract(0.0, 1.0, 0.0).getBlock().getType().isSolid()) {
			loc.getBlock().setType(Material.STONE);
		}
	}*/

    public static int getAvailableItems(Inventory inv, ItemStack item) {
        int counted = 0;
        ItemStack[] itemStackArray = inv.getContents();
        int n = itemStackArray.length;
        int n2 = 0;
        while (n2 < n) {
            ItemStack content = itemStackArray[n2];
            if (content != null && content.getType() != Material.AIR && content.getType() == item.getType() && content.getDurability() == item.getDurability()) {
                counted += content.getAmount();
            }
            ++n2;
        }
        return counted;
    }

    public static ItemStack stackItems(Inventory inv, ItemStack item) {
        int counted = 0;
        ItemStack[] itemStackArray = inv.getContents();
        int n = itemStackArray.length;
        int n2 = 0;
        while (n2 < n) {
            ItemStack content = itemStackArray[n2];
            if (content != null && content.getType() != Material.AIR && content.getType() == item.getType() && content.getDurability() == item.getDurability()) {
                counted += content.getAmount();
                removeItems(inv, item, content.getAmount());
            }
            ++n2;
        }
        return new ItemBuilder(item.getType()).amount(counted);
    }

    public static boolean hasEnoughItems(Inventory inv, ItemStack item, int amount) {
        return getAvailableItems(inv, item) >= amount;
    }

    public static String getTodayDate() {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(2, 1);
        calendar.set(5, 1);
        calendar.add(5, -1);
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String str = sdf.format(today).toString();
        return str;
    }

    public static String getTodayDay() {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(2, 1);
        calendar.set(5, 1);
        calendar.add(5, -1);
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(today);
        String[] arrayOfString = dateStr.split("-");
        return arrayOfString[2];
    }

    public static String getTodayMonth() {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(2, 1);
        calendar.set(5, 1);
        calendar.add(5, -1);
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(today);
        String[] arrayOfString = dateStr.split("-");
        return arrayOfString[1];
    }

    public static boolean removeItems(Inventory inv, ItemStack item, int amount) {
        if (!hasEnoughItems(inv, item, amount)) {
            return false;
        }
        int toRemove = amount;
        HashMap<Integer, ItemStack> slots = new HashMap<Integer, ItemStack>();
        int slot = 0;
        while (slot < inv.getSize()) {
            ItemStack slotItem = inv.getItem(slot);
            if (slotItem != null && slotItem.getType() != Material.AIR && slotItem.getType() == item.getType() && slotItem.getDurability() == item.getDurability()) {
                slots.put(slot, slotItem);
            }
            ++slot;
        }
        for (Map.Entry<Integer, ItemStack> entrySlots : slots.entrySet()) {
            if ((entrySlots.getValue()).getAmount() <= toRemove) {
                inv.setItem(entrySlots.getKey(), new ItemStack(Material.AIR));
                toRemove -= (entrySlots.getValue()).getAmount();
                continue;
            }
            ItemStack invItem = inv.getItem(entrySlots.getKey());
            invItem.setAmount(invItem.getAmount() - toRemove);
            break;
        }
        return true;
    }

    public static void addItem(Player p, ItemStack item) {
        if (p.getInventory().firstEmpty() == -1) {
            p.getWorld().dropItemNaturally(p.getLocation(), item);
        } else {
            p.getInventory().addItem(item);
        }
    }

    public static void sendTo(Collection<? extends Player> players, String message) {
        sendTo(players, message, false);
    }

    public static void sendTo(Collection<? extends Player> players, String message, boolean centered) {
        for (Player all : players) {
            all.sendMessage(getCenteredMessage(message));
        }
    }

    public static void addToLore(ItemStack item, String toAdd) {
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        if (lore != null) {
            lore.add(toAdd);
            meta.setLore(lore);
        }
        item.setItemMeta(meta);
    }

    public static Random getRandom() {
        return random;
    }

    public static Color getRandomColor() {
        Color color;
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        return new Color(r, g, b);
    }

    public static void playChestAction(Chest chest, boolean open) {
        Location location = chest.getLocation();
        World world = ((CraftWorld) location.getWorld()).getHandle();
        BlockPosition position = new BlockPosition(location.getX(), location.getY(), location.getZ());
        TileEntityChest tileChest = (TileEntityChest) world.getTileEntity(position);
        world.playBlockAction(position, tileChest.w(), 1, open ? 1 : 0);
    }

    public static void removeFromLore(ItemStack item, int index) {
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        if (lore == null || lore.size() < index) {
            return;
        }
        lore.remove(index);
        meta.setLore(lore);
        item.setItemMeta(meta);
    }

    public static void healPlayer(Player p) {
        p.setHealth(p.getMaxHealth());
        p.setFoodLevel(20);
        p.setFireTicks(0);
        for (PotionEffect effect : p.getActivePotionEffects()) {
            if (!p.hasPotionEffect(effect.getType())) continue;
            p.removePotionEffect(effect.getType());
        }
    }

    public static void removeItemFromHand(Player p, int amount) {
        ItemStack hand = p.getItemInHand();
        if (amount <= 0) {
            return;
        }
        if (hand == null || hand.getType() == Material.AIR) {
            return;
        }
        if (hand.getAmount() <= amount) {
            p.setItemInHand(new ItemStack(Material.AIR));
        } else {
            hand.setAmount(hand.getAmount() - amount);
        }
    }

    public static void clearInventory(Player p) {
        p.getInventory().setContents(new ItemStack[0]);
        p.getInventory().setHelmet(new ItemStack(Material.AIR));
        p.getInventory().setChestplate(new ItemStack(Material.AIR));
        p.getInventory().setLeggings(new ItemStack(Material.AIR));
        p.getInventory().setBoots(new ItemStack(Material.AIR));
    }

    public static boolean isInventoryEmpty(org.bukkit.inventory.PlayerInventory inv) {
        ItemStack[] itemStackArray = inv.getContents();
        int n = itemStackArray.length;
        int n2 = 0;
        while (n2 < n) {
            ItemStack contents = itemStackArray[n2];
            if (contents != null && contents.getType() != Material.AIR) {
                return false;
            }
            ++n2;
        }
        itemStackArray = inv.getArmorContents();
        n = itemStackArray.length;
        n2 = 0;
        while (n2 < n) {
            ItemStack armorcontents = itemStackArray[n2];
            if (armorcontents != null && armorcontents.getType() != Material.AIR) {
                return false;
            }
            ++n2;
        }
        return true;
    }

    public static ItemBuilder createSkull(String base64) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        ItemMeta m = item.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", base64));
        try {
            PROFILE_FIELD.set(m, profile);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        item.setItemMeta(m);
        return new ItemBuilder(item);
    }

    public static Vector calculate(Location startLocation, Location targetLocation) {
        Vector vector;

        double dX = startLocation.getX() - targetLocation.getX();
        double dY = startLocation.getY() - targetLocation.getY();
        double dZ = startLocation.getZ() - targetLocation.getZ();
        double yaw = Math.atan2(dZ, dX);
        double pitch = Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + Math.PI;
        double X = Math.sin(pitch) * Math.cos(yaw);
        double Y = Math.sin(pitch) * Math.sin(yaw);
        double Z = Math.cos(pitch);

        vector = new Vector(X, Z, Y);
        return vector;
    }

    public static List<Block> getBlocksBetween(Location loc1, Location loc2) {
        List<Block> blocks = new ArrayList<>();

        if (!loc1.getWorld().equals(loc2.getWorld())) return null;

        int minx = Math.min(loc1.getBlockX(), loc2.getBlockX()), miny = Math.min(loc1.getBlockY(), loc2.getBlockY()), minz = Math.min(loc1.getBlockZ(), loc2.getBlockZ()), maxx = Math.max(loc1.getBlockX(), loc2.getBlockX()), maxy = Math.max(loc1.getBlockY(), loc2.getBlockY()), maxz = Math.max(loc1.getBlockZ(), loc2.getBlockZ());
        for (int x = minx; x <= maxx; x++) {
            for (int y = miny; y <= maxy; y++) {
                for (int z = minz; z <= maxz; z++) {
                    Block b = loc1.getWorld().getBlockAt(x, y, z);

                    blocks.add(b);
                }
            }
        }

        return blocks;
    }

    public static ItemBuilder getSkull(GameProfile profile) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        try {
            Field profileField = skullMetaClass.getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        skull.setItemMeta(meta);
        return new ItemBuilder(skull);
    }


    public static String capitalizeFirstLetter(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public static void setTotalExperience(final Player player, final int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Experience is negative!");
        }
        player.setExp(0);
        player.setLevel(0);
        player.setTotalExperience(0);

        //This following code is technically redundant now, as bukkit now calulcates levels more or less correctly
        //At larger numbers however... player.getExp(3000), only seems to give 2999, putting the below calculations off.
        int amount = exp;
        while (amount > 0) {
            final int expToLevel = getExpAtLevel(player);
            amount -= expToLevel;
            if (amount >= 0) {
                // give until next level
                player.giveExp(expToLevel);
            } else {
                // give the rest
                amount += expToLevel;
                player.giveExp(amount);
                amount = 0;
            }
        }
    }

    private static int getExpAtLevel(final Player player) {
        return getExpAtLevel(player.getLevel());
    }

    //new Exp Math from 1.8
    public static int getExpAtLevel(final int level) {
        if (level <= 15) {
            return (2 * level) + 7;
        }
        if ((level >= 16) && (level <= 30)) {
            return (5 * level) - 38;
        }
        return (9 * level) - 158;

    }

    public static int getExpToLevel(final int level) {
        int currentLevel = 0;
        int exp = 0;

        while (currentLevel < level) {
            exp += getExpAtLevel(currentLevel);
            currentLevel++;
        }
        if (exp < 0) {
            exp = Integer.MAX_VALUE;
        }
        return exp;
    }

    public static int getTotalExperience(final Player player) {
        int exp = Math.round(getExpAtLevel(player) * player.getExp());
        int currentLevel = player.getLevel();

        while (currentLevel > 0) {
            currentLevel--;
            exp += getExpAtLevel(currentLevel);
        }
        if (exp < 0) {
            exp = Integer.MAX_VALUE;
        }
        return exp;
    }

    public static int getExpUntilNextLevel(final Player player) {
        final int exp = Math.round(getExpAtLevel(player) * player.getExp());
        final int nextLevel = player.getLevel();
        return getExpAtLevel(nextLevel) - exp;
    }

}
