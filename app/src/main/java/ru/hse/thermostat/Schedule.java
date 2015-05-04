package ru.hse.thermostat;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Schedule extends Fragment {
    private TemperatureTextView mDayTemperature;
    private TemperatureTextView mNightTemperature;

    public static Schedule newInstance() {
        Schedule fragment = new Schedule();
        return fragment;
    }

    public Schedule() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false);
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
