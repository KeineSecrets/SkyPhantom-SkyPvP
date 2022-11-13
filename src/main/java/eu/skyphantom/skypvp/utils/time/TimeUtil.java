package eu.skyphantom.skypvp.utils.time;

/*
                (MaxEssentials)
      This Class was created by MaxIstLegit
             08/05/2022 | 14:18
*/

import javax.annotation.Nonnull;

public class TimeUtil {

	public static long getTimeInMilli(@Nonnull String timeString) {
		int multiplier = 0;
		long timeLong = Long.parseLong(timeString.substring(0, timeString.length() - 1));
		multiplier = timeString.endsWith("y") ? 1000 * 60 * 60 * 24 * 7 * 4 * 12 : multiplier;
		multiplier = timeString.endsWith("w") ? 1000 * 60 * 60 * 24 * 7 : multiplier;
		multiplier = timeString.endsWith("d") ? 1000 * 60 * 60 * 24 : multiplier;
		multiplier = timeString.endsWith("h") ? 1000 * 60 * 60 : multiplier;
		multiplier = timeString.endsWith("m") ? 1000 * 60 : multiplier;
		multiplier = timeString.endsWith("s") ? 1000 : multiplier;
		return timeLong * multiplier;
	}

	public static String timeToString(@Nonnull long time, @Nonnull boolean shorten) {
		String msg = "";
		long seconds = time / 1000L;
		if (seconds >= 86400L) {
			long days = seconds / 86400L;
			msg = msg + days + (shorten ? "d " : ((days == 1L) ? " Tag, " : " Tage, "));
			seconds %= 86400L;
		}
		if (seconds >= 3600L) {
			long hours = seconds / 3600L;
			msg = msg + hours + (shorten ? "h " : (" Std, "));
			seconds %= 3600L;
		}
		if (seconds >= 60L) {
			long minutes = seconds / 60L;
			msg = msg + minutes + (shorten ? "m " : (" Min, "));
			seconds %= 60L;
		}
		if (seconds > 0L) {
			msg = msg + seconds + (shorten ? "s " : (" Sek, "));
		}
		if (!msg.isEmpty()) {
			msg = msg.substring(0, msg.length() - (shorten ? 1 : 2));
		} else {
			msg = (shorten ? "0s" : "0 Sek");
		}
		return msg;
	}

	public static String playTimeToString(@Nonnull long time, @Nonnull boolean shorten) {
		String msg = "";
		long seconds = time / 1000L;
		if (seconds >= 86400L) {
			long days = seconds / 86400L;
			msg = msg + days + (shorten ? "d " : ((days == 1L) ? " Tag, " : " Tage, "));
			seconds %= 86400L;
		}
		if (seconds >= 3600L) {
			long hours = seconds / 3600L;
			msg = msg + hours + (shorten ? "h " : ((hours == 1L) ? " Stunde, " : " Stunden, "));
			seconds %= 3600L;
		}
		if (seconds >= 60L) {
			long minutes = seconds / 60L;
			msg = msg + minutes + (shorten ? "m " : ((minutes == 1L) ? " Minute, " : " Minuten, "));
			seconds %= 60L;
		}
		if (!msg.isEmpty()) {
			msg = msg.substring(0, msg.length() - (shorten ? 1 : 2));
		}
		return msg;
	}

	public static String timeToStringApproximately(@Nonnull long time, @Nonnull boolean shorten) {
		String msg = "";
		long seconds = time / 1000L;
		if (seconds >= 86400L) {
			long days = seconds / 86400L;
			msg = msg + days + (shorten ? "d" : ((days == 1L) ? " Tag" : " Tage"));
			return msg;
		}
		if (seconds >= 3600L) {
			long hours = seconds / 3600L;
			msg = msg + hours + (shorten ? "h" : (" Std"));
			return msg;
		}
		if (seconds >= 60L) {
			long minutes = seconds / 60L;
			msg = msg + minutes + (shorten ? "m" : (" Min"));
			return msg;
		}
		if (seconds > 0L) {
			msg = msg + seconds + (shorten ? "s" : (" Sek"));
			return msg;
		}
		if (!msg.isEmpty()) {
			msg = msg.substring(0, msg.length() - (shorten ? 1 : 2));
		} else {
			msg = (shorten ? "0s" : "0 Sek");
		}
		return msg;
	}
}
