package eu.skyphantom.skypvp.caseopening;

import org.bukkit.inventory.ItemStack;

/**
 * @author KeineSecrets
 * @SkyPhantom Created: 18/02/2023 | 23:12
 */
public class CaseItem {

    private ItemStack item;
    private double chance;

    public CaseItem(ItemStack item, double chance) {
        this.item = item;
        this.chance = chance;
    }

    public double getChance() {
        return chance;
    }

    public void setChance(double chance) {
        this.chance = chance;
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }
}
