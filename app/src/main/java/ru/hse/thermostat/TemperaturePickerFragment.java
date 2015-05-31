package ru.hse.thermostat;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by Litun on 31.05.2015.
 */
public class TemperaturePickerFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //NumberPicker numberPicker= new NumberPicker(getActivity());
        builder.setCancelable(true)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //mListener.onWeekdaysPicked(mCheckedItems);
                    }
                })
                .setView(getLayoutInflater(savedInstanceState).inflate(R.layout.temperature_picker, null));
        return builder.create();
    }
}
