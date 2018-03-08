package com.example.user.trainclientapp;

import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import geoLocation.MyGPS;
import networking.URLASyncTask;

public class NearestStationActivity extends AppCompatActivity {


    LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
    String srvData;
    MyGPS myGPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearest_station);

        updateMyGPS();
        createStationList();
        updateList();
    }


    private void createStationList(){
        //Do in seperate class?

        //ToDo
        //get nearest station data from srv
        //format data into trainStationList of nearest 5 where each station has a stationName, stationLat, stationLong
        //Pass stationList through DistanceCalulation Haversine(ArrayList<TrainStation>()) and populate each array position with calculated distance
    }

    private void updateList(){
        //ToDo
        //Pass completed ArrayList<TrainStation>() to Adapter
        //Connect Adapter to activity_nearest_station.xml list container
    }

    private void updateMyGPS(){
        myGPS = new MyGPS(lm,this);
    }

    private void getNearestStationDataFromSrv(){
        new URLASyncTask(this, myGPS.getMyLat().toString(), myGPS.getMyLong().toString()).execute();
    }

    public void updataData(String newData){
        srvData =  newData;
    }

    public void updateLocation(View view) {
        updateMyGPS();
        createStationList();
        updateList();
    }
}
