package ru.hse.thermostat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class AddIntervalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_interval);

        TextView weekDay = (TextView) findViewById(R.id.weekday_chooser);
        weekDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:add chooser
            }
        });

        TextView timeFrom = (TextView) findViewById(R.id.from_time_chooser);
        timeFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:add chooser
            }
        });

        TextView timeTo = (TextView) findViewById(R.id.to_time_chooser);
        timeTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:add chooser
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
