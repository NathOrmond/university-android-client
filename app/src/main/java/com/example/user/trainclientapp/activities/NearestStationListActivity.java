package com.example.user.trainclientapp.activities;

import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.user.trainclientapp.R;
import com.example.user.trainclientapp.geolocation.MyGPS;
import com.example.user.trainclientapp.servernetworking.URLASyncTask;
import com.example.user.trainclientapp.stationlist.StationListFactory;
import com.example.user.trainclientapp.stationlist.TrainStation;

import java.util.ArrayList;

public class NearestStationListActivity extends AppCompatActivity {


    LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
    ArrayList<TrainStation> trainStationArrayList;
    String srvData;
    int listLength;
    MyGPS myGPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearest_station_list);

        updateMyGPS();
        createStationList();
        updateList();
    }


    private void createStationList(){
        StationListFactory stationListFactory = new StationListFactory(srvData,  myGPS.getMyLat(), myGPS.getMyLong(), listLength);
        trainStationArrayList = stationListFactory.getTrainStationArrayList();
    }

    private void updateList(){
        //ToDo
        //Pass completed ArrayList<TrainStation>() to Adapter
        //Connect Adapter to activity_nearest_station_list_list.xml list container
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
