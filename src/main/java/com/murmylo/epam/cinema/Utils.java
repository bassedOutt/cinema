package com.murmylo.epam.cinema;

import org.joda.time.DateTime;

public class Utils {

    public static DateTime toDateTime(java.sql.Date date, java.sql.Time time) {
        DateTime t = new DateTime(time);
        return new DateTime(date).withTime(t.getHourOfDay(), t.getMinuteOfHour(), t.getSecondOfMinute(), t.getMillisOfSecond());
    }

    public static boolean CanUpdateTime(DateTime start, DateTime end, int duration) {

        long diff = end.getMillis() - start.getMillis();
        long diffMinutes = diff / (60 * 1000);

        return diffMinutes > duration;
    }
}
