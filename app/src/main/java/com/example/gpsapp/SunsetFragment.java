package com.example.gpsapp;



import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static java.lang.String.format;
import static java.lang.String.valueOf;


/**
 * A simple {@link Fragment} subclass.
 */
public class SunsetFragment extends Fragment {

    TextView lat;
    TextView lon;
    Button button;
    String str_lat;
    String str_lon;
    Context context;
    Api api;
    final String appId = "79b5dfcdb1b395ef747034e203e40427";
    private TextView sunrise_value;

    public SunsetFragment() {
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
        sunrise_value = view.findViewById(R.id.textView9);

        Application application = getActivity().getApplication();
        App app = (App) application;

        api = app.getApi();

        api = ((App) getActivity().getApplication()).getApi();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (LocListener.getLocation(context,getActivity()) != null) {
                    str_lat = valueOf(LocListener.getLocation(context,getActivity()).getLatitude());
                    lat.setText(str_lat);

                    str_lon = valueOf(LocListener.getLocation(context,getActivity()).getLongitude());
                    lon.setText(str_lon);
                }

                Call<Results> call = api.getRes(str_lat,str_lon,appId);
                call.enqueue(new Callback<Results>() {
                    @Override
                    public void onResponse(Call<Results> call, Response<Results> response) {
                        Results results = response.body();
                        Sys sys = results.getSys();
                        String sunrise = new SimpleDateFormat("HH:mm").format(new Date(sys.getSunrise() * 1000));
                        String sunset = new SimpleDateFormat("HH:mm").format(new Date(sys.getSunset() * 1000));



                    }

                    @Override
                    public void onFailure(Call<Results> call, Throwable t) {

                    }
                });

            }
        });

    }
}
