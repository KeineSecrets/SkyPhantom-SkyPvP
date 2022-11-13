package eu.skyphantom.skypvp.gui;

import eu.skyphantom.skypvp.hooks.HopperGui;
import eu.skyphantom.skypvp.utils.Utils;
import eu.skyphantom.skypvp.utils.items.ItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class RanginfoGui extends HopperGui {
    // Ränge: Spieler - Shark - Fage - Magician - Titan - Phantom

    public RanginfoGui(@NotNull Player player) {
        super("§8▎ §a§lRÄNGE", player);

        getInventory().setItem(0, new ItemBuilder(Utils.createSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTY2NWI5MWM5ODk2MDA1N2VlMTk5YTg0MTFjYjY3YmQxODNmOTcyZDU1MjJiNmFjM2U3Mjg2MWQ1MTQ1NDdjOCJ9fX0="))
                .setName("§8▎ §6Shark§8 ▪ #§75").lore(
                        "",
                        "§8▎ §7Besonderheiten§8:",
                        "§8▪ §7Du kannst maximal 2 Plots claimen",
                        "",
                        "§8▎ §7Vorteile§8:",
                        "§8▪ /§akit §8» §7Hole dir dein neues Kit ab§8.",
                        "§8▪ /§afly §8» §7Aktiviere den Flugmodus§8.",
                        "§8▪ /§ahdb §8» §7Gebe dir verschiedenste Köpfe§8.",
                        "§8▪ /§ablocks §8» §7Gebe dir verschiedenste Blöcke§8.",
                        "§8▪ /§aec §8» §7Öffne deine EC überall§8.",
                        "§8▪ /§ainvsee §8» §7Öffne das Inventar eines Spielers  §8.",
                        "",
                        "§8▎ §c§l!§7 Du behälst alle Rechte der niedrigeren Ränge§8.",
                        ""
                ));

        getInventory().setItem(1, new ItemBuilder(Utils.createSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDAyMjg1NDEwNGE0ZTgwOWM2M2QxZTg5YjJhZDRmOGYwZmJjZWRlNTY3NGM4MGVkNGM2M2RmNGU3NjlkMTRjYiJ9fX0="))
                .setName("§8▎ §fFage§8 ▪ #§74").lore(
                        "",
                        "§8▎ §7Besonderheiten§8:",
                        "§8▪ §7Du kannst maximal 3 Plots claimen",
                        "",
                        "§8▎ §7Vorteile§8:",
                        "§8▪ /§akit §8» §7Hole dir dein neues Kit ab§8.",
                        "§8▪ /§ap f add greeting §8» §7Setze deine Willkommensnachricht auf dem Plot§8.",
                        "§8▪ /§aworkbench §8» §7Öffne eine mobile Werkbank§8.",
                        "§8▪ /§afix §8» §7Repariere ein Item§8.",
                        "",
                        "§8▎ §c§l!§7 Du behälst alle Rechte der niedrigeren Ränge§8.",
                        ""
                ));

        getInventory().setItem(2, new ItemBuilder(Utils.createSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjI2ZTE0YzU5Y2VmNmY5MzI2MDk1ZmFkOWIxNDY0YjUzZTUyOGRiNTdjNjEwNzMxY2NiYjhjZDMyYmZkYzRlMiJ9fX0="))
                .setName("§8▎ §5Magician§8 ▪ #§73").lore(
                        "",
                        "§8▎ §7Besonderheiten§8:",
                        "§8▪ §7Du kannst maximal 4 Plots claimen",
                        "",
                        "§8▎ §7Vorteile§8:",
                        "§8▪ /§akit §8» §7Hole dir dein neues Kit ab§8.",
                        "§8▪ /§aday §8» §7Ändere die Zeit zu Tag§8.",
                        "§8▪ /§anight §8» §7Ändere die Zeit zu Nacht§8.",
                        "§8▪ /§anear §8» §7Liste Spieler auf die in deiner Nähe sind§8.",
                        "§8▪ /§ahat §8» §7Setze dir einen Block als Hut auf§8.",
                        "§8▪ /§astack §8» §7Stacke deine Items§8.",
                        "§8▪ /§aopenall §8» §7Öffne mehrere Crates auf einmal§8.",
                        "§8▪ /§arealname §8» §7Schaue wem ein Nickname gehört§8.",
                        "",
                        "§8▎ §c§l!§7 Du behälst alle Rechte der niedrigeren Ränge§8.",
                        ""
                ));

        getInventory().setItem(3, new ItemBuilder(Utils.createSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTI4YjYxY2JlM2QwODBiYzk4NWY2ODllOTQ3MTUyYzcyNzliYTNjNGRiOTJjNmU0MzAzNWU5ZDU4ODk1NGY4ZCJ9fX0="))
                .setName("§8▎ §9Titan§8 ▪ #§72").lore(
                        "",
                        "§8▎ §7Besonderheiten§8:",
                        "§8▪ §7Du kannst maximal 5 Plots claimen",
                        "§8▪ §7Du kannst farbig im Chat schreiben",
                        "§8▪ §7Du hast einen eigenen Reward §8( /§arewards§8 )",
                        "",
                        "§8▎ §7Vorteile§8:",
                        "§8▪ /§akit §8» §7Hole dir dein neues Kit ab§8.",
                        "§8▪ /§atcrate §8» §7Erhalte jeden Tag verschiedene Crates§8.",
                        "§8▪ /§atrash §8» §7Öffne einen Mülleimer§8.",
                        "§8▪ /§acclear §8» §7Leere deinen eigenen Chat§8.",
                        "§8▪ /§aclear §8» §7Leere dein Inventar§8.",
                        "",
                        "§8▎ §c§l!§7 Du behälst alle Rechte der niedrigeren Ränge§8.",
                        ""
                ));

        getInventory().setItem(4, new ItemBuilder(Utils.createSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmUyNTBhYjZhNWYwNWYyNDFlNjE0NTE0NzI4ODUzMWVjNDY3N2Q2MzNiNWEwY2FhOGFhNzcyMDQxOGI0MzI2OCJ9fX0="))
                .setName("§8▎ §a§lPhantom§8 ▪ #§71").lore(
                        "",
                        "§8▎ §7Besonderheiten§8:",
                        "§8▪ §7Du kannst maximal 6 Plots claimen",
                        "§8▪ §7Du kannst farbig auf Schildern schreiben",
                        "§8▪ §7Du kannst farbig im Chat schreiben",
                        "§8▪ §7Du hast einen eigenen Reward §8( /§arewards§8 )",
                        "",
                        "§8▎ §7Vorteile§8:",
                        "§8▪ /§akit §8» §7Hole dir dein neues Kit ab§8.",
                        "§8▪ /§arand §8» §7Setze deinen Plotrand§8.",
                        "§8▪ /§anick §8» §7Setze deinen Nicknamen§8.",
                        "§8▪ /§afixarmor §8» §7Repariere deine Rüstung§8.",
                        "§8▪ /§aenchanter §8» §7Öffne einen Zaubertisch§8.",
                        "§8▪ /§avotekick §8» §7Starte einen Votekick§8.",
                        "§8▪ /§astatsreset §8» §7Setze deine Statistiken zurück§8.",
                        "",
                        "§8▎ §c§l!§7 Du behälst alle Rechte der niedrigeren Ränge§8.",
                        ""
                ));

    }

    @Override
    public void onClick(Player player, ClickType clickType, int clickedSlot, ItemStack clickedItem) {

    }

    @Override
    public void onClose(Player player, InventoryView inventoryView) {

    }
}
