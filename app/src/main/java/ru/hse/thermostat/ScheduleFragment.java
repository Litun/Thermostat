package ru.hse.thermostat;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {
    private TemperatureTextView mDayTemperature;
    private TemperatureTextView mNightTemperature;
    private Schedule schedule;
    private RecyclerView recyclerView;

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
        ScheduleAdapter adapter = new ScheduleAdapter(schedule);
        recyclerView.setAdapter(adapter);
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
        mDayTemperature.setTemperature(28.4f);
        mNightTemperature = (TemperatureTextView) view.findViewById(R.id.night_temperature);
        mNightTemperature.setFahrenheit(false);
        mNightTemperature.setTemperature(26.1f);
    }

}
