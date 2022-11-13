package eu.skyphantom.skypvp.utils.enums;

public enum Rarity {

    COMMON("§a§lCOMMON"),
    UNCOMMON("§2§lUNCOMMON"),
    RARE("§e§lRARE"),
    EPIC("§6§lEPIC"),
    MYTHIC("§5§lMYTHIC"),
    LEGENDARY("§c§lLEGENDARY");

    String displayName;

    Rarity(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
