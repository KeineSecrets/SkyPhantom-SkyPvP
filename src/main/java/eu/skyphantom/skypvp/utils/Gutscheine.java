package eu.skyphantom.skypvp.utils;

import eu.skyphantom.skypvp.utils.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Gutscheine {

    static ItemStack enderChest = new ItemBuilder(Material.ENDER_CHEST).setName("§8▎ §a§lGUTSCHEIN§8 ▪ +§a1 §2Enderchest§8-§aSeite").lore(
            "§r",
            "§8▎ §c§l! §7Information",
            "§8▪ §7Dies ist ein Gutschein§8. §7Bei einem Rechtsklick",
            "§8▪ §7wird dieser Gutschein eingelöst§8. §7Es öffnet",
            "§8▪ §7sich keine Bestätigung§8.",
            "§r"
    ).glow();

    static ItemStack tokens = new ItemBuilder(Material.DOUBLE_PLANT).setDataId(0).setName("§8▎ §a§lGUTSCHEIN§8 ▪ §6# Tokens").lore(
            "§r",
            "§8▎ §c§l! §7Information",
            "§8▪ §7Dies ist ein Gutschein§8. §7Bei einem Rechtsklick",
            "§8▪ §7wird dieser Gutschein eingelöst§8. §7Es öffnet",
            "§8▪ §7sich keine Bestätigung§8.",
            "§r"
    );

    public static ItemStack getTokens(double value) {
        ItemStack itemStack = tokens.clone();
        ItemMeta itemMeta = itemStack.getItemMeta();
        String display = itemMeta.getDisplayName();
        display = display.replace("#", (Utils.formatDouble(value)));
        itemMeta.setDisplayName(display);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack getEnderchest() {
        return enderChest;
    }

    public static double getTokens(ItemStack itemStack) {
        int amount = itemStack.getAmount();
        double tokens = Utils.parseFormattedNumber(itemStack.getItemMeta().getDisplayName().replace("§8▎ §a§lGUTSCHEIN§8 ▪ §6", "").replace("§8'§6", "").replace(" Tokens", ""));
        if (tokens >= 1) {
            return (amount * tokens);
        } else {
            return 0.0D;
        }
    }

}
