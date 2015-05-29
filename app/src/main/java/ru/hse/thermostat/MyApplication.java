package ru.hse.thermostat;

import android.app.Application;

/**
 * Created by Litun on 29.05.2015.
 */
public class MyApplication extends Application {
    private Schedule schedule;

    @Override
    public void onCreate() {
        super.onCreate();
        //read schedule
        schedule= new Schedule();
    }

    public Schedule getSchedule() {
        return schedule;
    }
}
