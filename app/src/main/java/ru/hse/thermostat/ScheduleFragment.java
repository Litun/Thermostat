package ru.hse.thermostat;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melnykov.fab.FloatingActionButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {
    private TemperatureTextView mDayTemperature;
    private TemperatureTextView mNightTemperature;
    private Schedule schedule;
    private RecyclerView recyclerView;
    ScheduleAdapter adapter;

    public static ScheduleFragment newInstance() {
        ScheduleFragment fragment = new ScheduleFragment();
        return fragment;
    }

    public ScheduleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication app = (MyApplication) getActivity().getApplication();
        schedule = app.getSchedule();

    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_schedule);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        //init adapter

        adapter = new ScheduleAdapter(schedule);
        recyclerView.setAdapter(adapter);
        setSwipeDel(adapter);


        //bind plus button
        FloatingActionButton fab = (FloatingActionButton) getView().findViewById(R.id.plus_button);
        fab.attachToRecyclerView(recyclerView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddIntervalActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setSwipeDel(final ScheduleAdapter adapter) {
        SwipeableRecyclerViewTouchListener swipeTouchListener =
                new SwipeableRecyclerViewTouchListener(recyclerView,
                        new SwipeableRecyclerViewTouchListener.SwipeListener() {
                            @Override
                            public boolean canSwipe(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    schedule.remove(position);
                                    adapter.notifyItemRemoved(position);
                                }
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    schedule.remove(position);
                                    adapter.notifyItemRemoved(position);
                                }
                                adapter.notifyDataSetChanged();
                            }
                        });

        recyclerView.addOnItemTouchListener(swipeTouchListener);
    }

    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDayTemperature = (TemperatureTextView) view.findViewById(R.id.day_temperature);
        mDayTemperature.setFahrenheit(false);
        //mDayTemperature.setTemperature(28.4f);
        mNightTemperature = (TemperatureTextView) view.findViewById(R.id.night_temperature);
        mNightTemperature.setFahrenheit(false);
        //mNightTemperature.setTemperature(26.1f);

        CardView dayCard = (CardView) view.findViewById(R.id.day_temperature_cardview);
        dayCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TemperaturePickerFragment(new TemperaturePickerFragment.TemperaturePickedListener() {
                    @Override
                    public void onTemperaturePicked(Temperature temperature) {
                        MainActivity activity = (MainActivity) getActivity();
                        if (activity != null) {
                            Temperature t = activity.dayTemperature;
                            t.setCelsius(temperature.getCelsius());
                            mDayTemperature.setTemperature(t.getCelsius());
                        }
                    }
                });
                newFragment.show(getFragmentManager().beginTransaction(), "timePicker");
            }
        });

        CardView nightCard = (CardView) view.findViewById(R.id.night_temperature_cardview);
        nightCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TemperaturePickerFragment(new TemperaturePickerFragment.TemperaturePickedListener() {
                    @Override
                    public void onTemperaturePicked(Temperature temperature) {
                        MainActivity activity = (MainActivity) getActivity();
                        if (activity != null) {
                            Temperature t = activity.nightTemperature;
                            t.setCelsius(temperature.getCelsius());
                            mNightTemperature.setTemperature(t.getCelsius());
                        }
                    }
                });
                newFragment.show(getFragmentManager().beginTransaction(), "timePicker");
            }
        });

        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            mNightTemperature.setTemperature(activity.nightTemperature.getCelsius());
            mDayTemperature.setTemperature(activity.dayTemperature.getCelsius());
        }
    }


    public final String PREF_NAME = "Thermostat",
            SCHEDULE_KEY = "schedule";

    @Override
    public void onDestroy() {
        super.onDestroy();
        SharedPreferences preferences = getActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        preferences.edit().putString(SCHEDULE_KEY, schedule.toJson()).apply();
        //preferences.edit().putString(SCHEDULE_KEY, schedule.toJson()).apply();
        //preferences.edit().putString(SCHEDULE_KEY, schedule.toJson()).apply();
    }
}
