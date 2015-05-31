package ru.hse.thermostat;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;


public class WeekdayPickerFragment extends DialogFragment {

    private WeekdayPickedListener mListener;

    public WeekdayPickerFragment(WeekdayPickedListener listener) {
        mListener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final boolean[] mCheckedItems = new boolean[7];
        final String[] weekdays = {"Sunday"
                , "Monday"
                , "Tuesday"
                , "Wednesday"
                , "Thursday"
                , "Friday"
                , "Saturday"};
        // Inflate the layout for this fragment
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true)
                .setMultiChoiceItems(weekdays, mCheckedItems,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which, boolean isChecked) {
                                mCheckedItems[which] = isChecked;
                            }
                        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onWeekdaysPicked(mCheckedItems);
                    }
                });
        return builder.create();
    }

    public static interface WeekdayPickedListener {
        public void onWeekdaysPicked(boolean[] checkedItems);
    }
}
