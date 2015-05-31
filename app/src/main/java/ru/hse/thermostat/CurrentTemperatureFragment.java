package ru.hse.thermostat;

import android.app.DialogFragment;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;


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
        refrashCard();

        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TemperaturePickerFragment();
                newFragment.show(getFragmentManager().beginTransaction(), "timePicker");
            }
        });
        startUpdateTemperature();
    }

    void refrashCard(){
        MainActivity activity=(MainActivity)getActivity();
        if (activity!=null) {
            Temperature t = activity.getCurrentTemperature();

            mCelsiusText = (TemperatureTextView) getView().findViewById(R.id.main_celsius);
            mCelsiusText.setFahrenheit(false);
            mCelsiusText.setTemperature(t.getCelsius());
            mFahrenheitText = (TemperatureTextView) getView().findViewById(R.id.main_fahrenheit);
            mFahrenheitText.setFahrenheit(true);
            mFahrenheitText.setTemperature(mCelsiusText);
        }
    }

    private void startUpdateTemperature() {
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE hh:mm a");
        //timer
        Timer myTimer = new Timer(); // Создаем таймер
        final Handler uiHandler = new Handler();
        myTimer.schedule(new TimerTask() { // Определяем задачу
            @Override
            public void run() {
                //final String result = doLongAndComplicatedTask();
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //txtResult.setText(result);
                        refrashCard();
                    }
                });
            }
        }, 0L, 100L); // интервал - 60000 миллисекунд, 0 миллисекунд до первого запуска.
    }
}
