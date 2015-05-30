package ru.hse.thermostat;

import android.app.Application;
import android.content.SharedPreferences;

import java.util.Calendar;

/**
 * Created by Litun on 29.05.2015.
 */
public class MyApplication extends Application {
    private Schedule schedule;
    public final String PREF_NAME = "Thermostat",
            SCHEDULE_KEY = "schedule";
    private Thread timer;

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

    public void setTimer(TimerListener listener){
        timer= new Thread(new Timer(Calendar.getInstance().getTime(), listener));
        timer.start();
    }
    public Schedule getSchedule() {
        return schedule;
    }
}
