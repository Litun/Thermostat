package ru.hse.thermostat;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    public final Temperature dayTemperature = new Temperature(),
            nightTemperature = new Temperature();
    Date currentTime = Calendar.getInstance().getTime();

    private String[] mScreenTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    private ActionBarDrawerToggle mDrawerToggle;
    //private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadTemperatures();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTitle = getTitle();
        mScreenTitles = getResources().getStringArray(R.array.screen_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mScreenTitles));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.setDrawerIndicatorEnabled(true);

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // Initialize the first fragment when the application first loads.
        if (savedInstanceState == null) {
            selectItem(0);
        }

        startClock();
    }

    public final String PREF_NAME = "Thermostat",
            TEMP_KEY_1 = "temperature1",
            TEMP_KEY_2 = "temperature2";

    private void loadTemperatures() {
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        Float temp1 = preferences.getFloat(TEMP_KEY_1, -1f);
        Float temp2 = preferences.getFloat(TEMP_KEY_2, -1f);

        if (temp1 < 0 || temp2 < 0) {
            dayTemperature.setCelsius(23.1f);
            nightTemperature.setCelsius(17.7f);
        } else {
            dayTemperature.setCelsius(temp1);
            nightTemperature.setCelsius(temp2);
        }
    }

    private void saveTemperatures(){
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        preferences.edit().putFloat(TEMP_KEY_1, dayTemperature.getCelsius()).apply();
        preferences.edit().putFloat(TEMP_KEY_2, nightTemperature.getCelsius()).apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveTemperatures();
    }

    private void startClock() {
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE hh:mm a");
        //timer
        final TextView clock = (TextView) findViewById(R.id.clock);
        Timer myTimer = new Timer(); // ������� ������
        final Handler uiHandler = new Handler();
        myTimer.schedule(new TimerTask() { // ���������� ������
            Calendar calendar = Calendar.getInstance();

            @Override
            public void run() {
                //final String result = doLongAndComplicatedTask();
                calendar.add(Calendar.MINUTE, 5);
                final Date time = calendar.getTime();
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //txtResult.setText(result);
                        clock.setText(dateFormatter.format(time));
                        currentTime = time;
                    }
                });
            }
        }, 0L, 100L); // �������� - 60000 �����������, 0 ����������� �� ������� �������.
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu;
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_main, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

    /* Called whenever we call invalidateOptionsMenu() */
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        return super.onPrepareOptionsMenu(menu);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        return mDrawerToggle.onOptionsItemSelected(item) ||
                super.onOptionsItemSelected(item);
    }

    /* The click listener for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /**
     * Swaps fragments in the main content view
     */
    private void selectItem(int position) {
        // Update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new CurrentTemperatureFragment();
                break;
            case 1:
                fragment = new ScheduleFragment();
                break;
            default:
                break;
        }

        // Insert the fragment by replacing any existing fragment
        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.main_fragment_container, fragment).commit();

            // Highlight the selected item, update the title, and close the drawer
            mDrawerList.setItemChecked(position, true);
            setTitle(mScreenTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // Error
            Log.e(this.getClass().getName(), "Error. Fragment is not created");
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    Temperature getCurrentTemperature() {
        MyApplication application = (MyApplication) getApplication();

        return application.getSchedule().isActive(currentTime) ? dayTemperature : nightTemperature;
    }

}
