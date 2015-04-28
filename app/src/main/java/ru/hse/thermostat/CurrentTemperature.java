package ru.hse.thermostat;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CurrentTemperature.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CurrentTemperature#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrentTemperature extends Fragment {

    CardView mCardView;

    public static CurrentTemperature newInstance() {
        CurrentTemperature fragment = new CurrentTemperature();
        fragment.setRetainInstance(true);
        return fragment;
    }

    public CurrentTemperature() {
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

    // TODO: Rename method, update argument and hook method into UI event
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
//        mCardView.setRadius(1f);
//        mCardView.setCardElevation(10);
    }
}
