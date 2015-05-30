package ru.hse.thermostat;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by Litun on 29.05.2015.
 */
public class MyApplication extends Application {
    private Schedule schedule;
    public final String PREF_NAME = "Thermostat",
            SCHEDULE_KEY = "schedule";

    @Override
    public void onCreate() {
        super.onCreate();
        //read schedule
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String scheduleJson = preferences.getString(SCHEDULE_KEY, null);

        if (scheduleJson != null)
            schedule = new Schedule(scheduleJson);
        else
            schedule = new Schedule();
    }

    public Schedule getSchedule() {
        return schedule;
    }
}
