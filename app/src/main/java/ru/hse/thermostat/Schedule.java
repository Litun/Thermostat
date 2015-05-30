package ru.hse.thermostat;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        storage.add(new Interval(1, 600, 610, false));
        storage.add(new Interval(3, 60, 70));
        storage.add(new Interval(4, 60, 70, false));
        storage.add(new Interval(5, 280, 1210));

        goodStorage(storage);
    }

    public Schedule(String json) {
        Gson gson = new Gson();
        Schedule s = gson.fromJson(json, Schedule.class);
        storage = s.getStorage();

        goodStorage(storage);
    }

    public List<Interval> getStorage() {
        return storage;
    }

    public Interval getInterval(int i) {
        return storage.get(i);
    }

    public void addInterval(Interval interval) { storage.add(interval); goodStorage(storage); }
    public void add(int weekday, Date from, Date to) { addInterval(new Interval(weekday, from, to)); }

    public boolean isChecked(int i) {
        return storage.get(i).active;
    }

    public void remove(int i) {
        storage.remove(i);
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public int size() {
        return storage.size();
    }

    private void removeIntersections(List<Interval> storage)
    {
        boolean[] used = new boolean[storage.size()];

        for (int i = 0; i < storage.size(); i++) {
            if (!used[i]) {
                Interval curInterval = storage.get(i);

                for (int j = i + 1; j < storage.size(); j++) {
                    if (!used[j]) {
                        if (Interval.intersect(curInterval, storage.get(j))) {
                            curInterval = Interval.unite(curInterval, storage.get(j));
                            used[j] = true;
                        }
                    }
                }

                storage.set(i, curInterval);
            }
        }

        int removes = 0;
        int size = storage.size();
        for (int i = 0; i < size; i++) {
            if (used[i]) {
                storage.remove(i - removes);
                removes++;
            }
        }
    }

    private void goodStorage(List<Interval> storage)
    {
        removeIntersections(storage);
        Collections.sort(storage);
    }

    static class Interval implements Comparable<Interval> {
        final int weekday;
        Date from, to;
        boolean active = true;

        Interval(int weekday, long minutesFrom, long minutesTo) {
            this.weekday = weekday;
            from = new Date(TimeUnit.MINUTES.toMillis(minutesFrom));
            to = new Date(TimeUnit.MINUTES.toMillis(minutesTo));
        }

        Interval(int weekday, long minutesFrom, long minutesTo, boolean isActive) {
            this.weekday = weekday;
            from = new Date(TimeUnit.MINUTES.toMillis(minutesFrom));
            to = new Date(TimeUnit.MINUTES.toMillis(minutesTo));
            active = isActive;
        }

        Interval(int weekday, Date from, Date to) {
            this.weekday = weekday;
            this.from = from;
            this.to = to;
        }

        public static boolean intersect(Interval interval1, Interval interval2)  {
            if (interval1.weekday != interval2.weekday) { return false; }

            Date maxFrom, minTo, maxTo;
            maxFrom = interval1.from.after(interval2.from) ? interval1.from : interval2.from;
            minTo = interval1.to.before(interval2.to) ? interval1.to : interval2.to;
            // maxTo = interval2.to.after(interval2.to) ? interval1.to : interval2.to;

            return (maxFrom.before(minTo)) || (maxFrom.equals(minTo));
        }

        public static Interval unite(Interval interval1, Interval interval2) {
            Date minFrom, maxTo;
            minFrom = interval1.from.before(interval2.from) ? interval1.from : interval2.from;
            maxTo = interval1.to.after(interval2.to) ? interval1.to : interval2.to;

            return new Interval(interval1.weekday, minFrom, maxTo);
        }

        @Override
        public int compareTo(Interval interval) {
            if (this.weekday == interval.weekday) {
                return this.from.compareTo(interval.from);
            }
            else {
                return this.weekday - interval.weekday;
            }
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


