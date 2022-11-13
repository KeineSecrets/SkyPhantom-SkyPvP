package eu.skyphantom.skypvp.punish;

import eu.skyphantom.skypvp.utils.time.TimeUtil;

import java.util.concurrent.TimeUnit;

public enum Mutes {

    ADVERTISEMENT("Werbung", 14, TimeUnit.DAYS),
    RACISM("Rassismus", 30, TimeUnit.DAYS),
    ADMINBAN("Hausverbot", -1, TimeUnit.NANOSECONDS);

    final String name;
    final long time;
    final TimeUnit timeUnit;

    Mutes(String name, long time, TimeUnit timeUnit) {
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
