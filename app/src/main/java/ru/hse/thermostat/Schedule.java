package ru.hse.thermostat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Litun on 28.05.2015.
 */
public class Schedule {
    final private List<Interval> storage;

    public Schedule() {
        //long days = TimeUnit.MILLISECONDS.toDays(milliseconds);
        storage = new ArrayList<Interval>(5);
        storage.add(new Interval(0,60,70));
        storage.add(new Interval(1,60,70));
        storage.add(new Interval(1,160,170));
    }

    public List<Interval> getStorage() {
        return storage;
    }

    public int size() {
        return storage.size();
    }

    static class Interval {
        final int weekday;
        long minutesFrom,
                minutesTo;
        boolean active = true;

        Interval(int weekday, long minutesFrom, long minutesTo) {
            this.weekday = weekday;
            this.minutesFrom = minutesFrom;
            this.minutesTo = minutesTo;
        }

        long getMinutesFrom(){
            return TimeUnit.MINUTES.toMinutes(minutesFrom);
        }
        long getHoursFrom(){
            return TimeUnit.MINUTES.toHours(minutesFrom);
        }
        long getMinutesTo(){
            return TimeUnit.MINUTES.toMinutes(minutesTo);
        }
        long getHoursTo(){
            return TimeUnit.MINUTES.toHours(minutesTo);
        }
    }
}


