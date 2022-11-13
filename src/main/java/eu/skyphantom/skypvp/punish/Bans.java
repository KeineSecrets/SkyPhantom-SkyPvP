package eu.skyphantom.skypvp.punish;

import eu.skyphantom.skypvp.utils.time.TimeUtil;

import java.util.concurrent.TimeUnit;

public enum Bans {

    ADVERTISEMENT("Werbung", 30, TimeUnit.DAYS),
    SKIN_NOR_NAME("Skin/Username", 15, TimeUnit.DAYS),
    BUSUSING("Bugusing", 3, TimeUnit.DAYS),
    ADMINBAN("Hausverbot", -1, TimeUnit.NANOSECONDS);

    final String name;
    final long time;
    final TimeUnit timeUnit;

    Bans(String name, long time, TimeUnit timeUnit) {
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
