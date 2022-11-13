package eu.skyphantom.skypvp.utils;

import eu.skyphantom.skypvp.SkyPvP;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ChunkLoader implements Listener {

    @EventHandler
    public void onChunkUnload(final ChunkUnloadEvent event) {
        Chunk chunk = event.getChunk();
        assert chunk != null;
        if (noUnload.contains(String.valueOf(chunk.getX()) + ":" + chunk.getZ()))
            event.setCancelled(true);
    }



    private List<String> noUnload = new ArrayList<>();
    private int chunk;
    private float amountOfChunks = 0;
    public ChunkLoader() {
        chunk = 0;
    }

    public void load(Location location, int size) {
        Location center = location;
        center.add(size, 0.0D, size);
        Chunk leftTopCorner = center.getChunk();
        center.add(-size * 2.0D, 0.0D, 0.0D);
        Chunk rightTopCorner = center.getChunk();
        center.add(0.0D, 0.0D, -size * 2.0D);
        center.add(size * 2.0D, 0.0D, 0.0D);
        Chunk leftBottomCorner = center.getChunk();
        int z = leftTopCorner.getZ();
        while (z >= leftBottomCorner.getZ()) {
            for (int i = leftTopCorner.getX() + 1; i >= rightTopCorner.getX(); i--)
                this.amountOfChunks++;
            z--;
        }
        noUnload.clear();
        load(leftTopCorner.getZ(), leftBottomCorner, leftTopCorner, rightTopCorner,
                location.getWorld());
        System.out.println("Starting loading of Chunks");
    }

    public void load(final int z, final Chunk leftBottomCorner, final Chunk leftTopCorner, final Chunk rightTopCorner, final World world) {
        if (z >= leftBottomCorner.getZ()) {
            new BukkitRunnable() {
                public void run() {
                    for (int i = leftTopCorner.getX() + 1; i >= rightTopCorner.getX(); --i) {
                        final Chunk chunke = world.getChunkAt(i, z);
                        chunke.load(true);
                        noUnload.add(String.valueOf(chunke.getX()) + ":" + chunke.getZ());
                        chunk++;
                    }
                    Bukkit.getConsoleSender().sendMessage(SkyPvP.PREFIX + ChatColor.YELLOW + chunk + ChatColor.GRAY + " chunks have been loaded so far " + ChatColor.DARK_GRAY + "(" + ChatColor.GRAY + new DecimalFormat("#0.00").format(chunk / amountOfChunks * 100.0f) + "%" + ChatColor.DARK_GRAY + ")");
                    load(z - 1, leftBottomCorner, leftTopCorner, rightTopCorner, world);
                }
            }.runTaskLater((Plugin) SkyPvP.getInstance(), 20L);
        } else {
            Bukkit.getConsoleSender().sendMessage(SkyPvP.PREFIX + ChatColor.GREEN + "The world has been successfully pre-rendered with " + ChatColor.YELLOW + chunk + " chunks.");
        }
    }

}
