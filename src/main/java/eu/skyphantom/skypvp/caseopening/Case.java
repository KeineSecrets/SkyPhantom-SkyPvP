package eu.skyphantom.skypvp.caseopening;

import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * @author KeineSecrets
 * @SkyPhantom Created: 18/02/2023 | 23:09
 */
public class Case {

    private String name, displayName;
    private ItemStack key;
    private List<CaseItem> items;

    public Case(String name, String displayName, ItemStack key, List<CaseItem> items) {
        this.name = name;
        this.displayName = displayName;
        this.key = key;
        this.items = items;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemStack getKey() {
        return key;
    }

    public void setKey(ItemStack key) {
        this.key = key;
    }

    public List<CaseItem> getItems() {
        return items;
    }

    public void setItems(List<CaseItem> items) {
        this.items = items;
    }
}
