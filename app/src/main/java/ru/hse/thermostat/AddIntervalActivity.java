package ru.hse.thermostat;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


public class AddIntervalActivity extends AppCompatActivity {

    Calendar from,
            to;
    List<Integer> weekdaysList = new ArrayList<Integer>(3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_interval);
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("hh:mm a");
        final String[] weekdays = {"Sunday"
                , "Monday"
                , "Tuesday"
                , "Wednesday"
                , "Thursday"
                , "Friday"
                , "Saturday"};

        final TextView weekDay = (TextView) findViewById(R.id.weekday_chooser);
        weekDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new WeekdayPickerFragment(new WeekdayPickerFragment.WeekdayPickedListener() {
                    @Override
                    public void onWeekdaysPicked(boolean[] checkedItems) {
                        String s = "";
                        weekdaysList.clear();
                        for (int i = 0; i < weekdays.length; i++) {
                            if (checkedItems[i]) {
                                s += weekdays[i] + "\n";
                                weekdaysList.add(i);
                            }
                        }
                        s = s.substring(0, s.length() - 1);
                        weekDay.setText(s);
                    }
                });
                newFragment.show(getSupportFragmentManager(), "weekdayPicker");
            }
        });

        //time from
        from = new GregorianCalendar(2015, 0, 0, 0, 0);
        final TextView timeFrom = (TextView) findViewById(R.id.from_time_chooser);
        timeFrom.setText(dateFormatter.format(from.getTime()));
        timeFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment(new TimePickerFragment.TimePickedListener() {
                    @Override
                    public void onTimePicked(Calendar time) {
                        from.setTime(time.getTime());
                        //from.setTime(time.getTime().getTime());
                        timeFrom.setText(dateFormatter.format(from.getTime()));
                    }
                });
                newFragment.show(getSupportFragmentManager(), "timePicker");
            }
        });

        // time to
        to = new GregorianCalendar(2015, 0, 0, 1, 0);
        final TextView timeTo = (TextView) findViewById(R.id.to_time_chooser);
        timeTo.setText(dateFormatter.format(to.getTime()));
        timeTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment(new TimePickerFragment.TimePickedListener() {
                    @Override
                    public void onTimePicked(Calendar time) {
                        to.setTime(time.getTime());
                        timeTo.setText(dateFormatter.format(to.getTime()));
                    }
                });
                newFragment.show(getSupportFragmentManager(), "timePicker");
            }
        });

        //close button
        ImageView close = (ImageView) findViewById(R.id.close_button);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddIntervalActivity.this.finish();
            }
        });

        //ok button
        ImageView ok = (ImageView) findViewById(R.id.ok_button);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication application = (MyApplication) getApplication();
                application.getSchedule().add(weekdaysList , from.getTime(), to.getTime());
                AddIntervalActivity.this.finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_interval, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
