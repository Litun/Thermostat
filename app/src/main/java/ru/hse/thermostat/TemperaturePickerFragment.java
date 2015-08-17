package ru.hse.thermostat;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

/**
 * Created by Litun on 31.05.2015.
 */
public class TemperaturePickerFragment extends DialogFragment {

    Temperature temperature = new Temperature();
    TemperaturePickedListener listener;

    public TemperaturePickerFragment(TemperaturePickedListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //NumberPicker numberPicker= new NumberPicker(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.temperature_picker, null);

        builder.setCancelable(true)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onTemperaturePicked(temperature);
                    }
                })
                .setView(view);

        final TemperatureTextView textView = (TemperatureTextView) view.findViewById(R.id.temperature_chooser);
        SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        Button buttonPlus = (Button) view.findViewById(R.id.button_plus);
        Button buttonMinus = (Button) view.findViewById(R.id.button_minus);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                temperature.setOnPersentage(progress);
                textView.setTemperature(temperature.getCelsius());
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temperature.setCelsius(temperature.getCelsius() - 0.1f);
                textView.setTemperature(temperature.getCelsius());
            }
        });

        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temperature.setCelsius(temperature.getCelsius() + 0.1f);
                textView.setTemperature(temperature.getCelsius());
            }
        });

        return builder.create();
    }

    public interface TemperaturePickedListener {
        void onTemperaturePicked(Temperature temperature);
    }
}
