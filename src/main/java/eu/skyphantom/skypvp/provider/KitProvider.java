package eu.skyphantom.skypvp.provider;

import eu.skyphantom.skypvp.utils.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created: 20/12/2022, at 19:14
 *
 * @author cvtx_
 */
public class KitProvider {

    public static final String KIT = "§8▎ §7Kit§8 ▪ §7";

    public static void getRespawnKit(final Player player, int level) {
        if (level < 1) level = 1;
        if (level > 3) level = 3;
        ItemStack sword, bow, goldenapple, arrow, helmed, chestplate, leggings, boots;
        sword = new ItemBuilder(Material.DIAMOND_SWORD).amount(1).glow().setName(KIT + "Respawn §8(§eLevel " + level + "§8)");
        bow = new ItemBuilder(Material.BOW).amount(1).setName(KIT + "Respawn §8(§eLevel " + level + "§8)");
        helmed = new ItemBuilder(Material.DIAMOND_HELMET).amount(1).glow().setName(KIT + "Respawn §8(§eLevel " + level + "§8)");
        chestplate = new ItemBuilder(Material.DIAMOND_CHESTPLATE).amount(1).glow().setName(KIT + "Respawn §8(§eLevel " + level + "§8)");
        leggings = new ItemBuilder(Material.DIAMOND_LEGGINGS).amount(1).glow().setName(KIT + "Respawn §8(§eLevel " + level + "§8)");
        boots = new ItemBuilder(Material.DIAMOND_BOOTS).amount(1).glow().setName(KIT + "Respawn §8(§eLevel " + level + "§8)");

        if (level == 2) {
            sword = new ItemBuilder(Material.DIAMOND_SWORD).amount(1).addEnchant(Enchantment.DAMAGE_ALL, 1).setName(KIT + "Respawn §8(§eLevel " + level + "§8)");
            bow = new ItemBuilder(Material.BOW).amount(1).addEnchant(Enchantment.ARROW_DAMAGE, 1).setName(KIT + "Respawn §8(§eLevel " + level + "§8)");
            helmed = new ItemBuilder(Material.DIAMOND_HELMET).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setName(KIT + "Respawn §8(§eLevel " + level + "§8)");
            chestplate = new ItemBuilder(Material.DIAMOND_CHESTPLATE).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setName(KIT + "Respawn §8(§eLevel " + level + "§8)");
            leggings = new ItemBuilder(Material.DIAMOND_LEGGINGS).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setName(KIT + "Respawn §8(§eLevel " + level + "§8)");
            boots = new ItemBuilder(Material.DIAMOND_BOOTS).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setName(KIT + "Respawn §8(§eLevel " + level + "§8)");
        }
        if (level == 3) {
            sword = new ItemBuilder(Material.DIAMOND_SWORD).amount(1).addEnchant(Enchantment.DAMAGE_ALL, 2).setName(KIT + "Respawn §8(§eLevel " + level + "§8)");
            bow = new ItemBuilder(Material.BOW).amount(1).addEnchant(Enchantment.ARROW_DAMAGE, 2).setName(KIT + "Respawn §8(§eLevel " + level + "§8)");
            helmed = new ItemBuilder(Material.DIAMOND_HELMET).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).setName(KIT + "Respawn §8(§eLevel " + level + "§8)");
            chestplate = new ItemBuilder(Material.DIAMOND_CHESTPLATE).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).setName(KIT + "Respawn §8(§eLevel " + level + "§8)");
            leggings = new ItemBuilder(Material.DIAMOND_LEGGINGS).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).setName(KIT + "Respawn §8(§eLevel " + level + "§8)");
            boots = new ItemBuilder(Material.DIAMOND_BOOTS).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).setName(KIT + "Respawn §8(§eLevel " + level + "§8)");
        }
        goldenapple = new ItemBuilder(Material.GOLDEN_APPLE).amount(level).setName(KIT + "Respawn §8(§eLevel " + level + "§8)");
        arrow = new ItemBuilder(Material.ARROW).amount((16 * level)).setName(KIT + "Respawn §8(§eLevel " + level + "§8)");

        player.getInventory().addItem(sword);
        player.getInventory().addItem(bow);
        player.getInventory().addItem(goldenapple);
        player.getInventory().addItem(arrow);

        player.getInventory().addItem(helmed);
        player.getInventory().addItem(chestplate);
        player.getInventory().addItem(leggings);
        player.getInventory().addItem(boots);
    }

    public static void getPlayerKit(final Player player) {
        final ItemStack sword = new ItemBuilder(Material.DIAMOND_SWORD).amount(1).addEnchant(Enchantment.DAMAGE_ALL, 2).addEnchant(Enchantment.DURABILITY, 2).setName(KIT + "Spieler");
        final ItemStack bow = new ItemBuilder(Material.BOW).amount(1).addEnchant(Enchantment.ARROW_DAMAGE, 2).addEnchant(Enchantment.DURABILITY, 2).setName(KIT + "Spieler");
        final ItemStack helmed = new ItemBuilder(Material.DIAMOND_HELMET).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).setName(KIT + "Spieler");
        final ItemStack chestplate = new ItemBuilder(Material.DIAMOND_CHESTPLATE).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).setName(KIT + "Spieler");
        final ItemStack leggings = new ItemBuilder(Material.DIAMOND_LEGGINGS).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).setName(KIT + "Spieler");
        final ItemStack boots = new ItemBuilder(Material.DIAMOND_BOOTS).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).setName(KIT + "Spieler");
        final ItemStack goldenapple = new ItemBuilder(Material.GOLDEN_APPLE).amount(10).setName(KIT + "Spieler");
        final ItemStack opgoldenapple = new ItemBuilder(Material.GOLDEN_APPLE).setDataId(1).amount(1).setName(KIT + "Spieler");
        final ItemStack arrow = new ItemBuilder(Material.ARROW).amount(32).setName(KIT + "Spieler");

        player.getInventory().addItem(sword);
        player.getInventory().addItem(bow);
        player.getInventory().addItem(goldenapple);
        player.getInventory().addItem(opgoldenapple);
        player.getInventory().addItem(arrow);

        player.getInventory().addItem(helmed);
        player.getInventory().addItem(chestplate);
        player.getInventory().addItem(leggings);
        player.getInventory().addItem(boots);
    }

    public static void getSharkKit(final Player player) {
        final ItemStack sword = new ItemBuilder(Material.DIAMOND_SWORD).amount(1).addEnchant(Enchantment.DAMAGE_ALL, 2).addEnchant(Enchantment.DURABILITY, 3).setName(KIT + "§6Shark");
        final ItemStack bow = new ItemBuilder(Material.BOW).amount(1).addEnchant(Enchantment.ARROW_DAMAGE, 2).setName(KIT + "§6Shark");
        final ItemStack helmed = new ItemBuilder(Material.DIAMOND_HELMET).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addEnchant(Enchantment.DURABILITY, 1).setName(KIT + "§6Shark");
        final ItemStack chestplate = new ItemBuilder(Material.DIAMOND_CHESTPLATE).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addEnchant(Enchantment.DURABILITY, 1).setName(KIT + "§6Shark");
        final ItemStack leggings = new ItemBuilder(Material.DIAMOND_LEGGINGS).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addEnchant(Enchantment.DURABILITY, 1).setName(KIT + "§6Shark");
        final ItemStack boots = new ItemBuilder(Material.DIAMOND_BOOTS).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addEnchant(Enchantment.DURABILITY, 1).setName(KIT + "§6Shark");
        final ItemStack goldenapple = new ItemBuilder(Material.GOLDEN_APPLE).amount(32).setName(KIT + "§6Shark");
        final ItemStack opgoldenapple = new ItemBuilder(Material.GOLDEN_APPLE).setDataId(1).amount(5).setName(KIT + "§6Shark");
        final ItemStack arrow = new ItemBuilder(Material.ARROW).amount(48).setName(KIT + "§6Shark");

        player.getInventory().addItem(sword);
        player.getInventory().addItem(bow);
        player.getInventory().addItem(opgoldenapple);
        player.getInventory().addItem(goldenapple);
        player.getInventory().addItem(arrow);


        player.getInventory().addItem(helmed);
        player.getInventory().addItem(chestplate);
        player.getInventory().addItem(leggings);
        player.getInventory().addItem(boots);
    }

    public static void getFageKit(final Player player) {
        final ItemStack sword = new ItemBuilder(Material.DIAMOND_SWORD).amount(1).addEnchant(Enchantment.DAMAGE_ALL, 2).addEnchant(Enchantment.DURABILITY, 2).setName(KIT + "§fFage");
        final ItemStack bow = new ItemBuilder(Material.BOW).amount(1).addEnchant(Enchantment.ARROW_DAMAGE, 2).addEnchant(Enchantment.DURABILITY, 2).setName(KIT + "§fFage");
        final ItemStack helmed = new ItemBuilder(Material.DIAMOND_HELMET).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addEnchant(Enchantment.DURABILITY, 2).setName(KIT + "§fFage");
        final ItemStack chestplate = new ItemBuilder(Material.DIAMOND_CHESTPLATE).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addEnchant(Enchantment.DURABILITY, 2).setName(KIT + "§fFage");
        final ItemStack leggings = new ItemBuilder(Material.DIAMOND_LEGGINGS).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addEnchant(Enchantment.DURABILITY, 2).setName(KIT + "§fFage");
        final ItemStack boots = new ItemBuilder(Material.DIAMOND_BOOTS).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addEnchant(Enchantment.DURABILITY, 2).setName(KIT + "§fFage");
        final ItemStack goldenapple = new ItemBuilder(Material.GOLDEN_APPLE).amount(32).setName(KIT + "§fFage");
        final ItemStack opgoldenapple = new ItemBuilder(Material.GOLDEN_APPLE).setDataId(1).amount(10).setName(KIT + "§fFage");
        final ItemStack arrow = new ItemBuilder(Material.ARROW).amount(64).setName(KIT + "§fFage");

        player.getInventory().addItem(sword);
        player.getInventory().addItem(bow);
        player.getInventory().addItem(opgoldenapple);
        player.getInventory().addItem(goldenapple);
        player.getInventory().addItem(arrow);

        player.getInventory().addItem(helmed);
        player.getInventory().addItem(chestplate);
        player.getInventory().addItem(leggings);
        player.getInventory().addItem(boots);
    }

    public static void getMagicianKit(final Player player) {
        final ItemStack sword = new ItemBuilder(Material.DIAMOND_SWORD).amount(1).addEnchant(Enchantment.DAMAGE_ALL, 3).addEnchant(Enchantment.DURABILITY, 3).setName(KIT + "§5Magician");
        final ItemStack bow = new ItemBuilder(Material.BOW).amount(1).addEnchant(Enchantment.ARROW_DAMAGE, 3).addEnchant(Enchantment.DURABILITY, 3).setName(KIT + "§5Magician");
        final ItemStack helmed = new ItemBuilder(Material.DIAMOND_HELMET).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addEnchant(Enchantment.DURABILITY, 3).setName(KIT + "§5Magician");
        final ItemStack chestplate = new ItemBuilder(Material.DIAMOND_CHESTPLATE).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addEnchant(Enchantment.DURABILITY, 3).setName(KIT + "§5Magician");
        final ItemStack leggings = new ItemBuilder(Material.DIAMOND_LEGGINGS).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addEnchant(Enchantment.DURABILITY, 3).setName(KIT + "§5Magician");
        final ItemStack boots = new ItemBuilder(Material.DIAMOND_BOOTS).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addEnchant(Enchantment.DURABILITY, 3).setName(KIT + "§5Magician");
        final ItemStack goldenapple = new ItemBuilder(Material.GOLDEN_APPLE).amount(64).setName(KIT + "§5Magician");
        final ItemStack opgoldenapple = new ItemBuilder(Material.GOLDEN_APPLE).setDataId(1).amount(15).setName(KIT + "§5Magician");
        final ItemStack arrow = new ItemBuilder(Material.ARROW).amount(64).setName(KIT + "§5Magician");

        player.getInventory().addItem(sword);
        player.getInventory().addItem(bow);
        player.getInventory().addItem(opgoldenapple);
        player.getInventory().addItem(goldenapple);
        player.getInventory().addItem(arrow);

        player.getInventory().addItem(helmed);
        player.getInventory().addItem(chestplate);
        player.getInventory().addItem(leggings);
        player.getInventory().addItem(boots);
    }

    public static void getTitanKit(final Player player) {
        final ItemStack sword = new ItemBuilder(Material.DIAMOND_SWORD).amount(1).addEnchant(Enchantment.DAMAGE_ALL, 5).addEnchant(Enchantment.DURABILITY, 3).addEnchant(Enchantment.FIRE_ASPECT, 2).setName(KIT + "§9Titan");
        final ItemStack bow = new ItemBuilder(Material.BOW).amount(1).addEnchant(Enchantment.ARROW_DAMAGE, 5).addEnchant(Enchantment.ARROW_KNOCKBACK, 2).addEnchant(Enchantment.ARROW_FIRE, 1).addEnchant(Enchantment.ARROW_INFINITE, 1).addEnchant(Enchantment.DURABILITY, 2).setName(KIT + "§9Titan");
        final ItemStack helmed = new ItemBuilder(Material.DIAMOND_HELMET).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3).setName(KIT + "§9Titan");
        final ItemStack chestplate = new ItemBuilder(Material.DIAMOND_CHESTPLATE).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3).setName(KIT + "§9Titan");
        final ItemStack leggings = new ItemBuilder(Material.DIAMOND_LEGGINGS).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3).setName(KIT + "§9Titan");
        final ItemStack boots = new ItemBuilder(Material.DIAMOND_BOOTS).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3).setName(KIT + "§9Titan");
        final ItemStack goldenapple = new ItemBuilder(Material.GOLDEN_APPLE).amount(64).setName(KIT + "§9Titan");
        final ItemStack opgoldenapple = new ItemBuilder(Material.GOLDEN_APPLE).setDataId(1).amount(32).setName(KIT + "§9Titan");
        final ItemStack arrow = new ItemBuilder(Material.ARROW).amount(64).setName(KIT + "§9Titan");
        final ItemStack speedpot = new ItemBuilder(Material.POTION).setDataId(8194).amount(1).setName(KIT + "§9Titan");
        final ItemStack strenghpot = new ItemBuilder(Material.POTION).setDataId(8201).amount(1).setName(KIT + "§9Titan");

        player.getInventory().addItem(sword);
        player.getInventory().addItem(bow);
        player.getInventory().addItem(opgoldenapple);
        player.getInventory().addItem(goldenapple);
        player.getInventory().addItem(arrow);
        player.getInventory().addItem(speedpot);
        player.getInventory().addItem(strenghpot);

        player.getInventory().addItem(helmed);
        player.getInventory().addItem(chestplate);
        player.getInventory().addItem(leggings);
        player.getInventory().addItem(boots);
    }

    public static void getPhantomKit(final Player player) {
        final ItemStack sword = new ItemBuilder(Material.DIAMOND_SWORD).amount(1).addEnchant(Enchantment.DAMAGE_ALL, 5).addEnchant(Enchantment.DURABILITY, 3).addEnchant(Enchantment.FIRE_ASPECT, 2).setName(KIT + "§a§lPhantom");
        final ItemStack bow = new ItemBuilder(Material.BOW).amount(1).addEnchant(Enchantment.ARROW_DAMAGE, 5).addEnchant(Enchantment.ARROW_KNOCKBACK, 2).addEnchant(Enchantment.ARROW_FIRE, 1).addEnchant(Enchantment.ARROW_INFINITE, 1).addEnchant(Enchantment.DURABILITY, 3).setName(KIT + "§a§lPhantom");
        final ItemStack helmed = new ItemBuilder(Material.DIAMOND_HELMET).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 4).setName(KIT + "§a§lPhantom");
        final ItemStack chestplate = new ItemBuilder(Material.DIAMOND_CHESTPLATE).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 4).setName(KIT + "§a§lPhantom");
        final ItemStack leggings = new ItemBuilder(Material.DIAMOND_LEGGINGS).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 4).setName(KIT + "§a§lPhantom");
        final ItemStack boots = new ItemBuilder(Material.DIAMOND_BOOTS).amount(1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 4).setName(KIT + "§a§lPhantom");
        final ItemStack goldenapple = new ItemBuilder(Material.GOLDEN_APPLE).amount(64).setName(KIT + "§a§lPhantom");
        final ItemStack opgoldenapple = new ItemBuilder(Material.GOLDEN_APPLE).setDataId(1).amount(64).setName(KIT + "§a§lPhantom");
        final ItemStack arrow = new ItemBuilder(Material.ARROW).amount(64).setName(KIT + "§a§lPhantom");
        final ItemStack speedpot = new ItemBuilder(Material.POTION).setDataId(8194).amount(2).setName(KIT + "§a§lPhantom");
        final ItemStack strenghpot = new ItemBuilder(Material.POTION).setDataId(8201).amount(2).setName(KIT + "§a§lPhantom");

        player.getInventory().addItem(sword);
        player.getInventory().addItem(bow);
        player.getInventory().addItem(opgoldenapple);
        player.getInventory().addItem(goldenapple);
        player.getInventory().addItem(arrow);
        player.getInventory().addItem(speedpot);
        player.getInventory().addItem(strenghpot);

        player.getInventory().addItem(helmed);
        player.getInventory().addItem(chestplate);
        player.getInventory().addItem(leggings);
        player.getInventory().addItem(boots);
    }
}
