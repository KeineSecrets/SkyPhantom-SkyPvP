package eu.skyphantom.skypvp.battlepass;

import eu.skyphantom.skypvp.utils.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class BattlepassRewardsManager {

    UUID uuid;
    BattlepassManager battlepassManager;

    public BattlepassRewardsManager(UUID uuid) {
        this.uuid = uuid;
        this.battlepassManager = new BattlepassManager(this.uuid);
    }

    public List<ItemStack> getAllAvailableRewards() {
        List<ItemStack> itemStacks = new CopyOnWriteArrayList<>();

        if (battlepassManager.getCurrentStufe() >= 1 && !battlepassManager.gotReward(1)) {
            itemStacks.add(new ItemBuilder(Material.INK_SACK).setDataId(10).setName("§8▎ §6Bronze I§8 ▪ §7Abholbereit").lore(
                    "§r",
                    "§8▎ §7Belohnungen§8:",
                    "§8▪ §61§8'§6000 §7Tokens",
                    "§r"
            ));
        } else {
            itemStacks.add(new ItemBuilder(Material.INK_SACK).setDataId(1).setName("§8▎ §6Bronze I§8 ▪ §7Nicht verfügbar").lore(
                    "§r",
                    "§8▎ §7Belohnungen§8:",
                    "§8▪ §61§8'§6000 §7Tokens",
                    "§r",
                    "§8▪ §cDer Reward wurde bereits abgeholt oder du hast",
                    "§8▪ §cdie nötige Stufe noch nicht erreicht§8.",
                    "§r"
            ));
        }

        if (battlepassManager.getCurrentStufe() >= 2 && !battlepassManager.gotReward(2)) {
            itemStacks.add(new ItemBuilder(Material.INK_SACK).setDataId(10).setName("§8▎ §6Bronze II§8 ▪ §7Abholbereit").lore(
                    "§r",
                    "§8▎ §7Belohnungen§8:",
                    "§8▪ §9Protection II Diamantrüstung",
                    "§r"
            ));
        } else {
            itemStacks.add(new ItemBuilder(Material.INK_SACK).setDataId(1).setName("§8▎ §6Bronze II§8 ▪ §7Nicht verfügbar").lore(
                    "§r",
                    "§8▎ §7Belohnungen§8:",
                    "§8▪ §9Protection II Diamantrüstung",
                    "§r",
                    "§8▪ §cDer Reward wurde bereits abgeholt oder du hast",
                    "§8▪ §cdie nötige Stufe noch nicht erreicht§8.",
                    "§r"
            ));
        }

        if (battlepassManager.getCurrentStufe() >= 3 && !battlepassManager.gotReward(3)) {
            itemStacks.add(new ItemBuilder(Material.INK_SACK).setDataId(10).setName("§8▎ §6Bronze III§8 ▪ §7Abholbereit").lore(
                    "§r",
                    "§8▎ §7Belohnungen§8:",
                    "§8▪ §71§8x §e§k;§6§lBATTLEPASS§e§k;§8-§7Crate",
                    "§r"
            ));
        } else {
            itemStacks.add(new ItemBuilder(Material.INK_SACK).setDataId(1).setName("§8▎ §6Bronze III§8 ▪ §7Nicht verfügbar").lore(
                    "§r",
                    "§8▎ §7Belohnungen§8:",
                    "§8▪ §71§8x §e§k;§6§lBATTLEPASS§e§k;§8-§7Crate",
                    "§r",
                    "§8▪ §cDer Reward wurde bereits abgeholt oder du hast",
                    "§8▪ §cdie nötige Stufe noch nicht erreicht§8.",
                    "§r"
            ));
        }

        if (battlepassManager.getCurrentStufe() >= 4 && !battlepassManager.gotReward(4)) {
            itemStacks.add(new ItemBuilder(Material.INK_SACK).setDataId(10).setName("§8▎ §6Bronze IV§8 ▪ §7Abholbereit").lore(
                    "§r",
                    "§8▎ §7Belohnungen§8:",
                    "§8▪ §71§8x §b§lBeacon",
                    "§r"
            ));
        } else {
            itemStacks.add(new ItemBuilder(Material.INK_SACK).setDataId(1).setName("§8▎ §6Bronze IV§8 ▪ §7Nicht verfügbar").lore(
                    "§r",
                    "§8▎ §7Belohnungen§8:",
                    "§8▪ §71§8x §b§lBeacon",
                    "§r",
                    "§8▪ §cDer Reward wurde bereits abgeholt oder du hast",
                    "§8▪ §cdie nötige Stufe noch nicht erreicht§8.",
                    "§r"
            ));
        }

        if (battlepassManager.getCurrentStufe() >= 5 && !battlepassManager.gotReward(5)) {
            itemStacks.add(new ItemBuilder(Material.INK_SACK).setDataId(10).setName("§8▎ §6Bronze V§8 ▪ §7Abholbereit").lore(
                    "§r",
                    "§8▎ §7Belohnungen§8:",
                    "§8▪ §71§8x §5Zufälliges Spawn§8-§5Ei",
                    "§r"
            ));
        } else {
            itemStacks.add(new ItemBuilder(Material.INK_SACK).setDataId(1).setName("§8▎ §6Bronze V§8 ▪ §7Nicht verfügbar").lore(
                    "§r",
                    "§8▎ §7Belohnungen§8:",
                    "§8▪ §71§8x §5Zufälliges Spawn§8-§5Ei",
                    "§r",
                    "§8▪ §cDer Reward wurde bereits abgeholt oder du hast",
                    "§8▪ §cdie nötige Stufe noch nicht erreicht§8.",
                    "§r"
            ));
        }

        if (battlepassManager.getCurrentStufe() >= 6 && !battlepassManager.gotReward(6)) {
            itemStacks.add(new ItemBuilder(Material.INK_SACK).setDataId(10).setName("§8▎ §7Silver I§8 ▪ §7Abholbereit").lore(
                    "§r",
                    "§8▎ §7Belohnungen§8:",
                    "§8▪ §71§8x §c§l/ec§8-§7Recht",
                    "§r"
            ));
        } else {
            itemStacks.add(new ItemBuilder(Material.INK_SACK).setDataId(1).setName("§8▎ §7Silver I§8 ▪ §7Nicht verfügbar").lore(
                    "§r",
                    "§8▎ §7Belohnungen§8:",
                    "§8▪ §71§8x §c§l/ec§8-§7Recht",
                    "§r",
                    "§8▪ §cDer Reward wurde bereits abgeholt oder du hast",
                    "§8▪ §cdie nötige Stufe noch nicht erreicht§8.",
                    "§r"
            ));
        }

        if (battlepassManager.getCurrentStufe() >= 7 && !battlepassManager.gotReward(7)) {
            itemStacks.add(new ItemBuilder(Material.INK_SACK).setDataId(10).setName("§8▎ §7Silver II§8 ▪ §7Abholbereit").lore(
                    "§r",
                    "§8▎ §7Belohnungen§8:",
                    "§8▪ §71§8x §dEnderchest§8-§dGutschein",
                    "§8▪ §62§8'§6500 Tokens",
                    "§r"
            ));
        } else {
            itemStacks.add(new ItemBuilder(Material.INK_SACK).setDataId(1).setName("§8▎ §7Silver II§8 ▪ §7Nicht verfügbar").lore(
                    "§r",
                    "§8▎ §7Belohnungen§8:",
                    "§8▪ §71§8x §dEnderchest§8-§dGutschein",
                    "§8▪ §62§8'§6500 Tokens",
                    "§r",
                    "§8▪ §cDer Reward wurde bereits abgeholt oder du hast",
                    "§8▪ §cdie nötige Stufe noch nicht erreicht§8.",
                    "§r"
            ));
        }

        if (battlepassManager.getCurrentStufe() >= 8 && !battlepassManager.gotReward(8)) {
            itemStacks.add(new ItemBuilder(Material.INK_SACK).setDataId(10).setName("§8▎ §7Silver III§8 ▪ §7Abholbereit").lore(
                    "§r",
                    "§8▎ §7Belohnungen§8:",
                    "§8▪ §63§8'§6500 Tokens",
                    "§r"
            ));
        } else {
            itemStacks.add(new ItemBuilder(Material.INK_SACK).setDataId(1).setName("§8▎ §7Silver III§8 ▪ §7Nicht verfügbar").lore(
                    "§r",
                    "§8▎ §7Belohnungen§8:",
                    "§8▪ §63§8'§6500 Tokens",
                    "§r",
                    "§8▪ §cDer Reward wurde bereits abgeholt oder du hast",
                    "§8▪ §cdie nötige Stufe noch nicht erreicht§8.",
                    "§r"
            ));
        }

        if (battlepassManager.getCurrentStufe() >= 9 && !battlepassManager.gotReward(9)) {
            itemStacks.add(new ItemBuilder(Material.INK_SACK).setDataId(10).setName("§8▎ §7Silver IV§8 ▪ §7Abholbereit").lore(
                    "§r",
                    "§8▎ §7Belohnungen§8:",
                    "§8▪ §d1§8x §dTitan §8(§7§o7 Tage§8)",
                    "§r"
            ));
        } else {
            itemStacks.add(new ItemBuilder(Material.INK_SACK).setDataId(1).setName("§8▎ §7Silver IV§8 ▪ §7Nicht verfügbar").lore(
                    "§r",
                    "§8▎ §7Belohnungen§8:",
                    "§8▪ §d1§8x §dTitan §8(§7§o7 Tage§8)",
                    "§r",
                    "§8▪ §cDer Reward wurde bereits abgeholt oder du hast",
                    "§8▪ §cdie nötige Stufe noch nicht erreicht§8.",
                    "§r"
            ));
        }

        if (battlepassManager.getCurrentStufe() >= 10 && !battlepassManager.gotReward(10)) {
            itemStacks.add(new ItemBuilder(Material.INK_SACK).setDataId(10).setName("§8▎ §7Silver V§8 ▪ §7Abholbereit").lore(
                    "§r",
                    "§8▎ §7Belohnungen§8:",
                    "§8▪ §65§8'§6000 Tokens",
                    "§r"
            ));
        } else {
            itemStacks.add(new ItemBuilder(Material.INK_SACK).setDataId(1).setName("§8▎ §7Silver V§8 ▪ §7Nicht verfügbar").lore(
                    "§r",
                    "§8▎ §7Belohnungen§8:",
                    "§8▪ §65§8'§6000 Tokens",
                    "§r",
                    "§8▪ §cDer Reward wurde bereits abgeholt oder du hast",
                    "§8▪ §cdie nötige Stufe noch nicht erreicht§8.",
                    "§r"
            ));
        }

        if (battlepassManager.getCurrentStufe() >= 11 && !battlepassManager.gotReward(11)) {
            itemStacks.add(new ItemBuilder(Material.INK_SACK).setDataId(10).setName("§8▎ §eGold I§8 ▪ §7Abholbereit").lore(
                    "§r",
                    "§8▎ §7Belohnungen§8:",
                    "§8▪ §91§8x §9Random Crate",
                    "§r"
            ));
        } else {
            itemStacks.add(new ItemBuilder(Material.INK_SACK).setDataId(1).setName("§8▎ §eGold I§8 ▪ §7Nicht verfügbar").lore(
                    "§r",
                    "§8▎ §7Belohnungen§8:",
                    "§8▪ §91§8x §9Random Crate",
                    "§r",
                    "§8▪ §cDer Reward wurde bereits abgeholt oder du hast",
                    "§8▪ §cdie nötige Stufe noch nicht erreicht§8.",
                    "§r"
            ));
        }

        if (battlepassManager.getCurrentStufe() >= 11 && !battlepassManager.gotReward(11)) {
            itemStacks.add(new ItemBuilder(Material.INK_SACK).setDataId(10).setName("§8▎ §eGold II§8 ▪ §7Abholbereit").lore(
                    "§r",
                    "§8▎ §7Belohnungen§8:",
                    "§8▪ §93§8x §9Random Crate",
                    "§r"
            ));
        } else {
            itemStacks.add(new ItemBuilder(Material.INK_SACK).setDataId(1).setName("§8▎ §eGold II§8 ▪ §7Nicht verfügbar").lore(
                    "§r",
                    "§8▎ §7Belohnungen§8:",
                    "§8▪ §93§8x §9Random Crate",
                    "§r",
                    "§8▪ §cDer Reward wurde bereits abgeholt oder du hast",
                    "§8▪ §cdie nötige Stufe noch nicht erreicht§8.",
                    "§r"
            ));
        }

        if (battlepassManager.getCurrentStufe() >= 12 && !battlepassManager.gotReward(12)) {
            itemStacks.add(new ItemBuilder(Material.INK_SACK).setDataId(10).setName("§8▎ §eGold III§8 ▪ §7Abholbereit").lore(
                    "§r",
                    "§8▎ §7Belohnungen§8:",
                    "§8▪ §95§8x §9Random Crate",
                    "§r"
            ));
        } else {
            itemStacks.add(new ItemBuilder(Material.INK_SACK).setDataId(1).setName("§8▎ §eGold III§8 ▪ §7Nicht verfügbar").lore(
                    "§r",
                    "§8▎ §7Belohnungen§8:",
                    "§8▪ §95§8x §9Random Crate",
                    "§r",
                    "§8▪ §cDer Reward wurde bereits abgeholt oder du hast",
                    "§8▪ §cdie nötige Stufe noch nicht erreicht§8.",
                    "§r"
            ));
        }

        return itemStacks;
    }

}
