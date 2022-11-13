package eu.skyphantom.skypvp.leagues;

public enum League {

    UNRANKED("§9Unranked", -1),
    BRONZE_ONE("§6Bronze I", 20),
    BRONZE_TWO("§6Bronze II", 40),
    BRONZE_THREE("§6Bronze III", 60),
    BRONZE_FOUR("§6Bronze IV", 80),
    BRONZE_FIVE("§6Bronze V", 100),
    SILVER_ONE("§7Silver I", 120),
    SILVER_TWO("§7Silver II", 140),
    SILVER_THREE("§7Silver III", 160),
    SILVER_FOUR("§7Silver IV", 180),
    SILVER_FIVE("§7Silver V", 200),
    GOLD_ONE("§eGold I", 220),
    GOLD_TWO("§eGold II", 240),
    GOLD_THREE("§eGold III", 260),
    GOLD_FOUR("§eGold IV", 280),
    GOLD_FIVE("§eGold V", 300),
    MASTER_ONE("§c§lMaster", 350);

    final String display;
    final Double killsToRankup;

    League(String display, double killsToRankup) {
        this.display = display;
        this.killsToRankup = killsToRankup;
    }

    public Double getKillsToRankup() {
        return killsToRankup;
    }

    public String getDisplay() {
        return display;
    }


}
