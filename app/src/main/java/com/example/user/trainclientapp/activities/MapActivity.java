package com.example.user.trainclientapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.user.trainclientapp.R;
import com.example.user.trainclientapp.stationlist.TrainStation;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity {
    Double myLatitude;
    Double myLongitude;
    ArrayList<TrainStation> stationArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_activity);
    }
}
