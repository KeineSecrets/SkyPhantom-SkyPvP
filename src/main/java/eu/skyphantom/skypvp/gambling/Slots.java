package eu.skyphantom.skypvp.gambling;

import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.hooks.Gui;
import eu.skyphantom.skypvp.provider.StatsProvider;
import eu.skyphantom.skypvp.utils.Utils;
import eu.skyphantom.skypvp.utils.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created: 17/01/2023
 *
 * @author 🐸
 */
public class Slots {

    public static class SlotsGui extends Gui {

        List<ItemStack> slots = new ArrayList<>();
        SlotsState state;
        double bet;
        StatsProvider statsManager;

        public SlotsGui(@NotNull Player player, double bet) {
            super("§7Menü", 3, player);
            this.bet = bet;
            this.state = SlotsState.IDLE;
            this.statsManager = new StatsProvider(player.getUniqueId());

            slots.add(new ItemBuilder(Material.DIAMOND_BLOCK).glow().setName("§8»│ §a§lHauptgewinn").lore("§8[ §2⛃§8 ] §a" + Utils.format(Utils.roundUp((bet * 10)), "§a") + "§2 Tokens"));
            slots.add(new ItemBuilder(Material.DIAMOND_BLOCK).glow().setName("§8»│ §a§lHauptgewinn").lore("§8[ §2⛃§8 ] §a" + Utils.format(Utils.roundUp((bet * 10)), "§a") + "§2 Tokens"));
            slots.add(new ItemBuilder(Material.IRON_BLOCK).glow().setName("§8»│ §e§lGewinn").lore("§8[ §6⛃§8 ] §e" + Utils.format(Utils.roundUp((bet * 2)), "§e") + "§6 Tokens"));
            slots.add(new ItemBuilder(Material.IRON_BLOCK).glow().setName("§8»│ §e§lGewinn").lore("§8[ §6⛃§8 ] §e" + Utils.format(Utils.roundUp((bet * 2)), "§e") + "§6 Tokens"));
            slots.add(new ItemBuilder(Material.IRON_BLOCK).glow().setName("§8»│ §e§lGewinn").lore("§8[ §6⛃§8 ] §e" + Utils.format(Utils.roundUp((bet * 2)), "§e") + "§6 Tokens"));
            slots.add(new ItemBuilder(Material.HARD_CLAY).setName("§8»│ §c§lNiete").lore("§8[ §4⛃§8 ] §c0 §4Tokens"));
            slots.add(new ItemBuilder(Material.HARD_CLAY).setName("§8»│ §c§lNiete").lore("§8[ §4⛃§8 ] §c0 §4Tokens"));
            slots.add(new ItemBuilder(Material.HARD_CLAY).setName("§8»│ §c§lNiete").lore("§8[ §4⛃§8 ] §c0 §4Tokens"));
            slots.add(new ItemBuilder(Material.HARD_CLAY).setName("§8»│ §c§lNiete").lore("§8[ §4⛃§8 ] §c0 §4Tokens"));

            fill(new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(7).setName("§8-/-§r"));

            getInventory().setItem(10, new ItemBuilder(Material.LEVER).setName("§8[ §6⛃§8 ]§7 Klicke§8, §7um zu spielen§8!"));

            getInventory().setItem(11, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(5).setName("§8-/-§r"));
            getInventory().setItem(13, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(5).setName("§8-/-§r"));
            getInventory().setItem(15, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(5).setName("§8-/-§r"));
            getInventory().setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE).setDataId(5).setName("§8-/-§r"));

            getInventory().setItem(3, new ItemStack(Material.AIR));
            getInventory().setItem(5, new ItemStack(Material.AIR));
            getInventory().setItem(7, new ItemStack(Material.AIR));
            getInventory().setItem(3 + 9, new ItemStack(Material.AIR));
            getInventory().setItem(5 + 9, new ItemStack(Material.AIR));
            getInventory().setItem(7 + 9, new ItemStack(Material.AIR));
            getInventory().setItem(3 + 18, new ItemStack(Material.AIR));
            getInventory().setItem(5 + 18, new ItemStack(Material.AIR));
            getInventory().setItem(7 + 18, new ItemStack(Material.AIR));

        }

        @Override
        public void onClick(Player player, ClickType clickType, int clickedSlot, ItemStack clickedItem) {
            if (clickedSlot == 10) {
                if (state == SlotsState.IDLE) {
                    if (this.statsManager.getCoins() < bet) {
                        player.sendMessage(SkyPvP.PREFIX + "§7Dazu hast du nicht genügend Tokens§8.");
                        return;
                    }
                    this.statsManager.removeCoins(bet);
                    player.sendMessage(SkyPvP.PREFIX + "§c- " + Utils.format(bet, "§c") + " Tokens");

                    getInventory().setItem(3, new ItemStack(Material.AIR));
                    getInventory().setItem(5, new ItemStack(Material.AIR));
                    getInventory().setItem(7, new ItemStack(Material.AIR));
                    getInventory().setItem(3 + 9, new ItemStack(Material.AIR));
                    getInventory().setItem(5 + 9, new ItemStack(Material.AIR));
                    getInventory().setItem(7 + 9, new ItemStack(Material.AIR));
                    getInventory().setItem(3 + 18, new ItemStack(Material.AIR));
                    getInventory().setItem(5 + 18, new ItemStack(Material.AIR));
                    getInventory().setItem(7 + 18, new ItemStack(Material.AIR));

                    state = SlotsState.ROLLING_1;
                    getInventory().setItem(3, Utils.getRandomItemOutOfList(slots));
                    getInventory().setItem(3 + 9, Utils.getRandomItemOutOfList(slots));
                    getInventory().setItem(3 + 18, Utils.getRandomItemOutOfList(slots));
                    Utils.playSound(player, Sound.WOOD_CLICK, 1.0f, 1.0f);
                    AtomicInteger i = new AtomicInteger(0);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (i.get() < 25) {
                                getInventory().setItem(3 + 18, getInventory().getItem(3 + 9));
                                getInventory().setItem(3 + 9, getInventory().getItem(3));
                                getInventory().setItem(3, Utils.getRandomItemOutOfList(slots));
                                Utils.playSound(player, Sound.WOOD_CLICK, 1.0f, 1.0f);
                                i.incrementAndGet();
                                return;
                            }
                            getInventory().setItem(3 + 18, getInventory().getItem(3 + 9));
                            getInventory().setItem(3 + 9, getInventory().getItem(3));
                            getInventory().setItem(3, Utils.getRandomItemOutOfList(slots));
                            Utils.playSound(player, Sound.LEVEL_UP, 1.0f, 10.0f);
                            this.cancel();
                        }
                    }.runTaskTimer(SkyPvP.getInstance(), 3L, 3L);
                    return;
                }
                if (state == SlotsState.ROLLING_1) {
                    state = SlotsState.ROLLING_2;
                    getInventory().setItem(5, Utils.getRandomItemOutOfList(slots));
                    getInventory().setItem(5 + 9, Utils.getRandomItemOutOfList(slots));
                    getInventory().setItem(5 + 18, Utils.getRandomItemOutOfList(slots));
                    Utils.playSound(player, Sound.WOOD_CLICK, 1.0f, 1.0f);
                    AtomicInteger i = new AtomicInteger(0);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (i.get() < 25) {
                                getInventory().setItem(5 + 18, getInventory().getItem(5 + 9));
                                getInventory().setItem(5 + 9, getInventory().getItem(5));
                                getInventory().setItem(5, Utils.getRandomItemOutOfList(slots));
                                Utils.playSound(player, Sound.WOOD_CLICK, 1.0f, 1.0f);
                                i.incrementAndGet();
                                return;
                            }
                            getInventory().setItem(5 + 18, getInventory().getItem(5 + 9));
                            getInventory().setItem(5 + 9, getInventory().getItem(5));
                            getInventory().setItem(5, Utils.getRandomItemOutOfList(slots));
                            Utils.playSound(player, Sound.LEVEL_UP, 1.0f, 10.0f);
                            this.cancel();
                        }
                    }.runTaskTimer(SkyPvP.getInstance(), 3L, 3L);
                    return;
                }
                if (state == SlotsState.ROLLING_2) {
                    state = SlotsState.ROLLING_3;
                    getInventory().setItem(7, Utils.getRandomItemOutOfList(slots));
                    getInventory().setItem(7 + 9, Utils.getRandomItemOutOfList(slots));
                    getInventory().setItem(7 + 18, Utils.getRandomItemOutOfList(slots));
                    Utils.playSound(player, Sound.WOOD_CLICK, 1.0f, 1.0f);
                    AtomicInteger i = new AtomicInteger(0);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (i.get() < 25) {
                                getInventory().setItem(7 + 18, getInventory().getItem(7 + 9));
                                getInventory().setItem(7 + 9, getInventory().getItem(7));
                                getInventory().setItem(7, Utils.getRandomItemOutOfList(slots));
                                Utils.playSound(player, Sound.WOOD_CLICK, 1.0f, 1.0f);
                                i.incrementAndGet();
                                return;
                            }
                            getInventory().setItem(7 + 18, getInventory().getItem(7 + 9));
                            getInventory().setItem(7 + 9, getInventory().getItem(7));
                            getInventory().setItem(7, Utils.getRandomItemOutOfList(slots));
                            if (Utils.sameItem(getInventory().getItem(3 + 9), getInventory().getItem(5 + 9)) && Utils.sameItem(getInventory().getItem(5 + 9), getInventory().getItem(7 + 9)) && Utils.sameItem(getInventory().getItem(3 + 9), getInventory().getItem(7 + 9))) {
                                String display = getInventory().getItem(3 + 9).getItemMeta().getDisplayName();
                                if (display.equalsIgnoreCase("§8»│ §a§lHauptgewinn")) {
                                    Utils.playBigSuccessSound(player);
                                    Utils.broadcast(SkyPvP.PREFIX + "§b" + player.getName() + "§7 hat den §b§lJackpot§7 geknackt§8! (§b" + Utils.format(Utils.roundUp((bet * 10L)), "§b") + " Tokens§8)", false);
                                    player.sendMessage(SkyPvP.PREFIX + "§7Du hast §b" + Utils.format(Utils.roundUp((bet * 10L)), "§b") + " Tokens§7 gewonnen§8.");
                                    statsManager.addCoins(Utils.roundUp((bet * 10L)));
                                    state = SlotsState.IDLE;
                                    this.cancel();
                                    return;
                                }
                                if (display.equalsIgnoreCase("§8»│ §e§lGewinn")) {
                                    Utils.playSuccessSound(player);
                                    player.sendMessage(SkyPvP.PREFIX + "§7Du hast §b" + Utils.format(Utils.roundUp((bet * 2)), "§b") + " Tokens§7 gewonnen§8.");
                                    statsManager.addCoins(Utils.roundUp((bet * 2L)));
                                    state = SlotsState.IDLE;
                                    this.cancel();
                                    return;
                                }
                                Utils.playUnsuccessSound(player);
                                player.sendMessage(SkyPvP.PREFIX + "§7Du hast §cnichts§7 gewonnen§8.");
                                state = SlotsState.IDLE;
                                this.cancel();
                                return;
                            }
                            Utils.playUnsuccessSound(player);
                            player.sendMessage(SkyPvP.PREFIX + "§7Du hast §cnichts§7 gewonnen§8.");
                            state = SlotsState.IDLE;
                            this.cancel();
                        }
                    }.runTaskTimer(SkyPvP.getInstance(), 3L, 3L);
                }
            }
        }

        @Override
        public void onClose(Player player, InventoryView inventoryView) {

        }
    }


}
