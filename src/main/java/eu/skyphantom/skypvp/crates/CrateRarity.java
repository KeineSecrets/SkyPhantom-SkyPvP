package eu.skyphantom.skypvp.crates;

public enum CrateRarity {

    COMMON("§a§lCOMMON"),
    UNCOMMON("§e§lUNCOMMON"),
    RARE("§3§lRARE"),
    MYTHIC("§5§lMYTHIC"),
    LEGENDARY("§4§k;§c§lLEGENDARY§4§k;§r");

    final String display;

    CrateRarity(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}
