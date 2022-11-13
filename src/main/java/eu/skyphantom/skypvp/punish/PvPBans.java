package eu.skyphantom.skypvp.punish;

import eu.skyphantom.skypvp.utils.time.TimeUtil;

import java.util.concurrent.TimeUnit;

public enum PvPBans {

    HACKING("Hacking", 30, TimeUnit.DAYS),
    TEAMING("Teaming", 7, TimeUnit.DAYS),
    COMBAT_LOGGING("Combatlogging", 3, TimeUnit.DAYS),
    ADMINBAN("Hausverbot", -1, TimeUnit.NANOSECONDS);

    final String name;
    final long time;
    final TimeUnit timeUnit;

    PvPBans(String name, long time, TimeUnit timeUnit) {
        this.name = name;
        this.time = time;
        this.timeUnit = timeUnit;
    }

    public String getName() {
        return this.name;
    }

    public long getTime() {
        if (this.time == -1) return -1;
        return (this.timeUnit.toMillis(this.time));
    }

    public String getTimeString() {
        if (getTime() == -1) return "Permanent";
        return TimeUtil.timeToString(getTime(), true);
    }
}
