package eu.skyphantom.skypvp.reports;

public enum Reports {

    HACKING("Hacking", 1),
    TROLLING("Trolling", 2),
    BUGUSING("Bugusing", 3),
    ADVERTISING("Werbung", 4),
    RACISM("Rassismus", 5),
    STATSBOOSTING("Boosten der Statistiken", 6),
    INSULT("Beleidigung", 7),
    TEAMING("Teaming", 8);

    private final String name;
    private final int id;

    Reports(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static Reports getReportById(int id) {
        for (Reports reports : values()) {
            if (reports.getId() == id) return reports;
        }
        return null;
    }

    public static Reports getReportByName(String name) {
        for (Reports reports : values()) {
            if (reports.getName().equals(name)) return reports;
        }
        return null;
    }
}
