package com.example.user.trainclientapp;

import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import networking.URLASyncTask;

public class NearestStationActivity extends AppCompatActivity {

    String lat, lng;
    LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearest_station);


    }


    private void getLatitudeAndLongitude() {

    }

    public void updateLocation(View view) {
        new URLASyncTask(this, lat, lng);
    }
}
