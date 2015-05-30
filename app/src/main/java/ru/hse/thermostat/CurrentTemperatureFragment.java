package ru.hse.thermostat;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        mCelsiusText = (TemperatureTextView) view.findViewById(R.id.main_celsius);
        mCelsiusText.setFahrenheit(false);
        mCelsiusText.setTemperature(26.1f);
        mFahrenheitText = (TemperatureTextView) view.findViewById(R.id.main_fahrenheit);
        mFahrenheitText.setFahrenheit(true);
        mFahrenheitText.setTemperature(mCelsiusText);

        //timer
        final TextView clock = (TextView) view.findViewById(R.id.clock);
//
//        TimerListener timerListener = new TimerListener() {
//            @Override
//            synchronized
//            public void timeChanged(Date time) {
//                SimpleDateFormat dateFormatter = new SimpleDateFormat("hh:mm a");
//                clock.setText(dateFormatter.format(time));
//            }
//        };
//        MyApplication application = (MyApplication) getActivity().getApplication();
//        application.setTimer(timerListener);

        Timer myTimer = new Timer(); // Создаем таймер
        final Handler uiHandler = new Handler();
        //final TextView txtResult = (TextView)view.findViewById(R.id.txtResult);
        myTimer.schedule(new TimerTask() { // Определяем задачу
            Integer a = 0;

            @Override
            public void run() {
                //final String result = doLongAndComplicatedTask();
                a++;
                final Integer number=a;
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //txtResult.setText(result);
                        clock.setText(number.toString());
                    }
                });
            }
        }, 0L, 60L * 10); // интервал - 60000 миллисекунд, 0 миллисекунд до первого запуска.
    }
}
