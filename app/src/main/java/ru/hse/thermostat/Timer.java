package ru.hse.thermostat;

import java.util.Date;

/**
 * Created by Asus on 30.05.2015.
 */
public class Timer implements Runnable {
    private final long step = 5000;

    private final TimerListener listener;
    private Date time;


    Timer(Date time, TimerListener listener) {
        this.time = time;
        this.listener = listener;
    }

    public Date getTime() { return time; }
    public void setTime(Date time) { this.time = time; broadcast(time); }
    public void setTime(long timeMs) { this.time.setTime(timeMs); broadcast(this.time); }

    private void broadcast(Date time) {
        listener.timeChanged(time);
    }

    @Override
    public void run() {
        setTime(time.getTime() + step);
    }
}
