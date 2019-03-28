package com.example.gpsapp;


import android.content.Context;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static java.lang.String.valueOf;


/**
 * A simple {@link Fragment} subclass.
 */
public class SunsetFragmen extends Fragment {

    TextView lat;
    TextView lon;
    Button button;
    String str_lat;
    String str_lon;
    Context context;

    public SunsetFragmen() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sunset, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = this.getContext();
        lat = view.findViewById(R.id.lat);
        lon = view.findViewById(R.id.lon);
        button = view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (LocListener.getLocation(context,getActivity()) != null) {
                    str_lat = valueOf(LocListener.getLocation(context,getActivity()).getLatitude());
                    lat.setText(str_lat);

                    str_lon = valueOf(LocListener.getLocation(context,getActivity()).getLongitude());
                    lon.setText(str_lon);
                }

            }
        });

    }
}
