package ru.hse.thermostat;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentTemperatureFragment extends Fragment {

    CardView mCardView;
    TemperatureTextView mCelsiusText;
    TemperatureTextView mFahrenheitText;

    public static CurrentTemperatureFragment newInstance() {
        CurrentTemperatureFragment fragment = new CurrentTemperatureFragment();
        fragment.setRetainInstance(true);
        return fragment;
    }

    public CurrentTemperatureFragment() {
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
        return inflater.inflate(R.layout.fragment_current_temperature, container, false);
    }

    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCardView = (CardView) view.findViewById(R.id.cardview);
        mCelsiusText = (TemperatureTextView) view.findViewById(R.id.main_celsius);
        mCelsiusText.setFahrenheit(false);
        mCelsiusText.setTemperature(26.1f);
        mFahrenheitText = (TemperatureTextView) view.findViewById(R.id.main_fahrenheit);
        mFahrenheitText.setFahrenheit(true);
        mFahrenheitText.setTemperature(mCelsiusText);

        //AlertDialog.Builder adb = new AlertDialog.Builder(getActivity()).set
    }
}
