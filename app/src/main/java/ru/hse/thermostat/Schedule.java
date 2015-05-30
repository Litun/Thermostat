package ru.hse.thermostat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        storage.add(new Interval(0, 60, 70));
        storage.add(new Interval(1, 60, 70));
        storage.add(new Interval(1, 160, 170));
    }

    public List<Interval> getStorage() {
        return storage;
    }

    public Interval getInterval(int i) {
        return storage.get(i);
    }

    public int size() {
        return storage.size();
    }

    static class Interval {
        final int weekday;
        Date from, to;
        boolean active = true;

        Interval(int weekday, long minutesFrom, long minutesTo) {
            this.weekday = weekday;
            from = new Date(TimeUnit.MINUTES.toMillis(minutesFrom));
            to = new Date(TimeUnit.MINUTES.toMillis(minutesTo));
        }


        Interval(int weekday, Date from, Date to) {
            this.weekday = weekday;
            this.from = from;
            this.to = to;
        }

//        long getMinutesFrom() {
//            return from.getTime();
//        }
//
//        long getHoursFrom() {
//            return TimeUnit.MINUTES.toHours(minutesFrom);
//        }
//
//        long getMinutesTo() {
//            return minutesTo % 60;
//        }
//
//        long getHoursTo() {
//            return TimeUnit.MINUTES.toHours(minutesTo);
//        }

        @Override
        public String toString() {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("hh:mm a");
            return dateFormatter.format(from) + " - " +
                    dateFormatter.format(to);
        }
    }
}


