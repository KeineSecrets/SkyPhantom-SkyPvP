package eu.skyphantom.skypvp.gambling;

/**
 * Created: 15/01/2023
 *
 * @author 🐸
 */
public enum CrashState {

    WAITING("§aWarte auf Spieler"),
    COUNTING("§eLäuft..."),
    CRASHED("§cGecrasht!");

    private final String display;

    CrashState(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}
