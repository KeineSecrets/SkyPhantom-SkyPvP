package eu.skyphantom.skypvp.treasure;

/**
 * JavaDoc this file!
 * Created: 27/12/2022
 *
 * @author WeLoveSpigotPlugins (welovespigotplugins@gmail.com)
 */


import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.utils.Utils;
import io.netty.util.internal.ThreadLocalRandom;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class SpinAPI {
    public static Hologram holo1;
    public static Hologram holo2;
    public static Hologram holo3;
    public static Hologram holo4;
    public static Hologram holo5;
    public static Hologram holo6;
    public static Hologram holo7;
    public static Hologram holo8;
    public static Hologram holo9;
    public static Hologram obenwin;
    public static Hologram untenwin;
    static int i;

    static {
        SpinAPI.i = 0;
    }

    public static void roll(final Player p, final double seconds, final String Crate) {
        final File file = new File("plugins/System/Treasure/", Crate + ".yml");
        final FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        final ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        final ArrayList<String> oben = new ArrayList<String>();
        final ArrayList<String> unten = new ArrayList<String>();
        for (int i = 0; i < 54; ++i) {
            if (cfg.getItemStack(Crate + ".Items." + i + ".items") != null) {
                final double chance = cfg.getDouble(Crate + ".Items." + i + ".chance");
                for (int chance2 = (int) Math.round(chance + 0.6), ia = 1; ia <= chance2 * 20; ++ia) {
                    final ItemStack item = cfg.getItemStack(Crate + ".Items." + i + ".items");
                    final String obenitem = cfg.getString(Crate + ".Items." + i + ".nameoben");
                    final String untenitem = cfg.getString(Crate + ".Items." + i + ".nameunten");
                    list.add(item);
                    oben.add(obenitem);
                    unten.add(untenitem);
                }
            }
        }
        if (list.size() < 1) {
            p.sendMessage(SkyPvP.getInstance().getTreasureManager().getprefix() + "§cDiese Crate ist leer.");
            return;
        }
        try {
            SkyPvP.getInstance().getTreasureManager().getLocation().getBlock().setTypeIdAndData(23, (byte) 6, true);
            SkyPvP.getInstance().getTreasureManager().setspin(true);
            final Random r = new Random();
            final File file2 = new File("plugins/System/Treasure", "treasure.yml");
            final FileConfiguration cfg2 = YamlConfiguration.loadConfiguration(file2);
            final double x1 = cfg2.getDouble("Location.1.x");
            final double y1 = cfg2.getDouble("Location.1.y");
            final double z1 = cfg2.getDouble("Location.1.z");
            final String w1 = cfg2.getString("Location.1.world");
            final Location loc1 = new Location(Bukkit.getWorld(w1), x1, y1, z1);
            final double x2 = cfg2.getDouble("Location.2.x");
            final double y2 = cfg2.getDouble("Location.2.y");
            final double z2 = cfg2.getDouble("Location.2.z");
            final String w2 = cfg2.getString("Location.2.world");
            final Location loc2 = new Location(Bukkit.getWorld(w2), x2, y2, z2);
            final double x3 = cfg2.getDouble("Location.3.x");
            final double y3 = cfg2.getDouble("Location.3.y");
            final double z3 = cfg2.getDouble("Location.3.z");
            final String w3 = cfg2.getString("Location.3.world");
            final Location loc3 = new Location(Bukkit.getWorld(w3), x3, y3, z3);
            final double x4 = cfg2.getDouble("Location.4.x");
            final double y4 = cfg2.getDouble("Location.4.y");
            final double z4 = cfg2.getDouble("Location.4.z");
            final String w4 = cfg2.getString("Location.4.world");
            final Location loc4 = new Location(Bukkit.getWorld(w4), x4, y4, z4);
            final double x5 = cfg2.getDouble("Location.5.x");
            final double y5 = cfg2.getDouble("Location.5.y");
            final double z5 = cfg2.getDouble("Location.5.z");
            final String w5 = cfg2.getString("Location.5.world");
            final Location loc5 = new Location(Bukkit.getWorld(w5), x5, y5, z5);
            final double x6 = cfg2.getDouble("Location.6.x");
            final double y6 = cfg2.getDouble("Location.6.y");
            final double z6 = cfg2.getDouble("Location.6.z");
            final String w6 = cfg2.getString("Location.6.world");
            final Location loc6 = new Location(Bukkit.getWorld(w6), x6, y6, z6);
            final double x7 = cfg2.getDouble("Location.7.x");
            final double y7 = cfg2.getDouble("Location.7.y");
            final double z7 = cfg2.getDouble("Location.7.z");
            final String w7 = cfg2.getString("Location.7.world");
            final Location loc7 = new Location(Bukkit.getWorld(w7), x7, y7, z7);
            final double x8 = cfg2.getDouble("Location.8.x");
            final double y8 = cfg2.getDouble("Location.8.y");
            final double z8 = cfg2.getDouble("Location.8.z");
            final String w8 = cfg2.getString("Location.8.world");
            final Location loc8 = new Location(Bukkit.getWorld(w8), x8, y8, z8);
            final double x9 = cfg2.getDouble("Location.9.x");
            final double y9 = cfg2.getDouble("Location.9.y");
            final double z9 = cfg2.getDouble("Location.9.z");
            final String w9 = cfg2.getString("Location.9.world");
            final Location loc9 = new Location(Bukkit.getWorld(w9), x9, y9, z9);
            if (SpinAPI.obenwin == null) {
                SpinAPI.obenwin = HologramsAPI.createHologram(SkyPvP.getInstance(), loc1.clone().add(0.0, 2.5, 0.0));
            } else {
                SpinAPI.obenwin.clearLines();
            }
            SpinAPI.obenwin.appendTextLine("§e§lDein Gewinn");
            if (SpinAPI.untenwin == null) {
                SpinAPI.untenwin = HologramsAPI.createHologram(SkyPvP.getInstance(), loc1.clone().add(0.0, 0.5, 0.0));
            } else {
                SpinAPI.untenwin.clearLines();
            }
            SpinAPI.untenwin.appendTextLine("§e§lDein Gewinn");
            final int n = r.nextInt(list.size());
            if (SpinAPI.holo1 == null) {
                SpinAPI.holo1 = HologramsAPI.createHologram(SkyPvP.getInstance(), loc1.clone().add(0.0, 2.0, 0.0));
            } else {
                SpinAPI.holo1.clearLines();
            }
            SpinAPI.holo1.appendTextLine(oben.get(n));
            SpinAPI.holo1.appendItemLine(list.get(n));
            SpinAPI.holo1.appendTextLine(unten.get(n));
            final int n2 = r.nextInt(list.size());
            if (SpinAPI.holo2 == null) {
                SpinAPI.holo2 = HologramsAPI.createHologram(SkyPvP.getInstance(), loc2.clone().add(0.0, 2.0, 0.0));
            } else {
                SpinAPI.holo2.clearLines();
            }
            SpinAPI.holo2.appendTextLine(oben.get(n2));
            SpinAPI.holo2.appendItemLine(list.get(n2));
            SpinAPI.holo2.appendTextLine(unten.get(n2));
            final int n3 = r.nextInt(list.size());
            if (SpinAPI.holo3 == null) {
                SpinAPI.holo3 = HologramsAPI.createHologram(SkyPvP.getInstance(), loc3.clone().add(0.0, 2.0, 0.0));
            } else {
                SpinAPI.holo3.clearLines();
            }
            SpinAPI.holo3.appendTextLine(oben.get(n3));
            SpinAPI.holo3.appendItemLine(list.get(n3));
            SpinAPI.holo3.appendTextLine(unten.get(n3));
            final int n4 = r.nextInt(list.size());
            if (SpinAPI.holo4 == null) {
                SpinAPI.holo4 = HologramsAPI.createHologram(SkyPvP.getInstance(), loc4.clone().add(0.0, 2.0, 0.0));
            } else {
                SpinAPI.holo4.clearLines();
            }
            SpinAPI.holo4.appendTextLine(oben.get(n4));
            SpinAPI.holo4.appendItemLine(list.get(n4));
            SpinAPI.holo4.appendTextLine(unten.get(n4));
            final int n5 = r.nextInt(list.size());
            if (SpinAPI.holo5 == null) {
                SpinAPI.holo5 = HologramsAPI.createHologram(SkyPvP.getInstance(), loc5.clone().add(0.0, 2.0, 0.0));
            } else {
                SpinAPI.holo5.clearLines();
            }
            SpinAPI.holo5.appendTextLine(oben.get(n5));
            SpinAPI.holo5.appendItemLine(list.get(n5));
            SpinAPI.holo5.appendTextLine(unten.get(n5));
            final int n6 = r.nextInt(list.size());
            if (SpinAPI.holo6 == null) {
                SpinAPI.holo6 = HologramsAPI.createHologram(SkyPvP.getInstance(), loc6.clone().add(0.0, 2.0, 0.0));
            } else {
                SpinAPI.holo6.clearLines();
            }
            SpinAPI.holo6.appendTextLine(oben.get(n6));
            SpinAPI.holo6.appendItemLine(list.get(n6));
            SpinAPI.holo6.appendTextLine(unten.get(n6));
            final int n7 = r.nextInt(list.size());
            if (SpinAPI.holo7 == null) {
                SpinAPI.holo7 = HologramsAPI.createHologram(SkyPvP.getInstance(), loc7.clone().add(0.0, 2.0, 0.0));
            } else {
                SpinAPI.holo7.clearLines();
            }
            SpinAPI.holo7.appendTextLine(oben.get(n7));
            SpinAPI.holo7.appendItemLine(list.get(n7));
            SpinAPI.holo7.appendTextLine(unten.get(n7));
            final int n8 = r.nextInt(list.size());
            if (SpinAPI.holo8 == null) {
                SpinAPI.holo8 = HologramsAPI.createHologram(SkyPvP.getInstance(), loc8.clone().add(0.0, 2.0, 0.0));
            } else {
                SpinAPI.holo8.clearLines();
            }
            SpinAPI.holo8.appendTextLine(oben.get(n8));
            SpinAPI.holo8.appendItemLine(list.get(n8));
            SpinAPI.holo8.appendTextLine(unten.get(n8));
            if (SpinAPI.holo9 == null) {
                SpinAPI.holo9 = HologramsAPI.createHologram(SkyPvP.getInstance(), loc9.clone().add(0.0, 2.0, 0.0));
            } else {
                SpinAPI.holo9.clearLines();
            }
            new BukkitRunnable() {
                double delay = 0.0;
                int ticks = 0;
                boolean done = false;

                public void run() {
                    if (this.done) {
                        return;
                    }
                    ++this.ticks;
                    this.delay += 1.0 / (20.0 * seconds);
                    if (this.ticks > this.delay * 10.0) {
                        this.ticks = 0;
                        try {
                            SpinAPI.holo9.teleport(SpinAPI.holo1.getLocation());
                            SpinAPI.holo1.teleport(SpinAPI.holo2.getLocation());
                            SpinAPI.holo2.teleport(SpinAPI.holo3.getLocation());
                            SpinAPI.holo3.teleport(SpinAPI.holo4.getLocation());
                            SpinAPI.holo4.teleport(SpinAPI.holo5.getLocation());
                            SpinAPI.holo5.teleport(SpinAPI.holo6.getLocation());
                            SpinAPI.holo6.teleport(SpinAPI.holo7.getLocation());
                            SpinAPI.holo7.teleport(SpinAPI.holo8.getLocation());
                            SpinAPI.holo8.teleport(SpinAPI.holo9.getLocation());
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        }
                        p.playSound(p.getLocation(), Sound.CLICK, 5.0f, 5.0f);
                        if (this.delay >= 1.0) {
                            this.done = true;
                            new BukkitRunnable() {
                                public void run() {
                                    SkyPvP.getInstance().getTreasureManager().getLocation().getBlock().setTypeIdAndData(23, (byte) 1, true);
                                    Bukkit.getWorld(SkyPvP.getInstance().getTreasureManager().getLocation().getWorld().getName()).strikeLightningEffect(SkyPvP.getInstance().getTreasureManager().getLocation());
                                    SpinAPI.holo1.clearLines();
                                    SpinAPI.holo2.clearLines();
                                    SpinAPI.holo3.clearLines();
                                    SpinAPI.holo4.clearLines();
                                    SpinAPI.holo5.clearLines();
                                    SpinAPI.holo6.clearLines();
                                    SpinAPI.holo7.clearLines();
                                    SpinAPI.holo8.clearLines();
                                    SpinAPI.holo9.clearLines();
                                    SpinAPI.obenwin.clearLines();
                                    SpinAPI.untenwin.clearLines();
                                    ++SpinAPI.i;
                                    ItemStack win = null;
                                    if (SpinAPI.i == 1) {
                                        win = list.get(n7);
                                    }
                                    if (SpinAPI.i == 2) {
                                        win = list.get(n5);
                                    }
                                    if (SpinAPI.i == 3) {
                                        win = list.get(n3);
                                    }
                                    if (SpinAPI.i == 4) {
                                        win = list.get(n);
                                        SpinAPI.i = 0;
                                    }
                                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                                    String itemname = "";
                                    if (win.hasItemMeta()) {
                                        itemname = win.getItemMeta().getDisplayName();
                                    } else {
                                        itemname = win.getType().name();
                                    }
                                    final String cratename = cfg.getString(Crate + ".Keyname");
                                    if (SkyPvP.getInstance().getTreasureManager().getBC(Crate, win.getItemMeta().getDisplayName())) {
                                        Bukkit.broadcastMessage(SkyPvP.getInstance().getTreasureManager().getprefix() + "§7Der Spieler §e" + p.getName() + "§7 hat " + itemname + " §7aus der " + cratename + " §7Treasure gezogen");
                                    } else {
                                        p.sendMessage(SkyPvP.getInstance().getTreasureManager().getprefix() + "§7Du hast §7" + itemname + " §7aus der " + cratename + " §7Treasure gezogen.");
                                    }
                                    if (cfg.getStringList("cmd." + itemname).isEmpty()) {
                                        Utils.addItem(p, win);
                                    } else {
                                        for (final String commands : cfg.getStringList("cmd." + itemname)) {
                                            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), commands.replaceAll("/", "").replaceAll("%p%", p.getName()));
                                        }
                                    }
                                    this.cancel();
                                    SkyPvP.getInstance().getTreasureManager().setspin(false);
                                }
                            }.runTaskLater(SkyPvP.getInstance(), 120L);
                            this.cancel();
                        }
                    }
                }
            }.runTaskTimer(SkyPvP.getInstance(), 0L, 1L);
        } catch (Exception ex) {
        }
    }

    public static ItemStack getRandomWin(final String Crate) {
        ItemStack win = new ItemStack(Material.AIR);
        double end = 0.0;
        final File file2 = new File("plugins/System/Crate/", Crate + ".yml");
        final FileConfiguration cfg2 = YamlConfiguration.loadConfiguration(file2);
        for (int i = 0; i < 54; ++i) {
            if (cfg2.get(Crate + ".Items." + i + ".items") != null) {
                final double chance = cfg2.getDouble(Crate + ".Items." + i + ".chance");
                if (chance > end) {
                    end = chance;
                }
            }
        }
        final double result = ThreadLocalRandom.current().nextDouble(0.0, end);
        for (int j = 0; j < 54; ++j) {
            if (cfg2.get(Crate + ".Items." + j + ".items") != null) {
                final ItemStack item = cfg2.getItemStack(Crate + ".Items." + j + ".items");
                final double chance2 = cfg2.getDouble(Crate + ".Items." + j + ".chance");
                if (result <= chance2 && chance2 > 0.0) {
                    win = item;
                }
            }
        }
        if (win.getType() == Material.AIR) {
            win = getChance(Crate);
        }
        return win;
    }

    private static ItemStack getChance(final String c) {
        ItemStack win = null;
        final double start = 0.0;
        final double end = 0.0;
        final double random = new Random().nextDouble();
        final double result = start + random * (end - start);
        final File file2 = new File("plugins/System/Crate/", c + ".yml");
        final FileConfiguration cfg2 = YamlConfiguration.loadConfiguration(file2);
        for (int i = 0; i < 54; ++i) {
            if (cfg2.get(c + ".Items." + i + ".items") != null) {
                final ItemStack item = cfg2.getItemStack(c + ".Items." + i + ".items");
                final double chance = cfg2.getDouble(c + ".Items." + i + ".chance");
                if (result <= chance && chance > 0.0) {
                    win = item;
                }
            }
        }
        return win;
    }
}

