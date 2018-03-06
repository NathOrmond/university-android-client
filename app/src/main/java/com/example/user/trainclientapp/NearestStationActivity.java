package com.example.user.trainclientapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import networking.URLASyncTask;

public class NearestStationActivity extends AppCompatActivity {

    String lat,lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearest_station);

        new URLASyncTask(this, lat,lng);
    }


    public void updateLocation(View view) {

    }
}
