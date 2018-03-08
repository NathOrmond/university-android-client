package com.example.user.trainclientapp.activities;

import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.user.trainclientapp.R;
import com.example.user.trainclientapp.geolocation.MyGPS;
import com.example.user.trainclientapp.servernetworking.URLASyncTask;
import com.example.user.trainclientapp.stationlist.StationListFactory;
import com.example.user.trainclientapp.stationlist.StationsAdapter;
import com.example.user.trainclientapp.stationlist.TrainStation;

import java.util.ArrayList;

public class NearestStationListActivity extends AppCompatActivity {


    LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
    ArrayList<TrainStation> trainStationArrayList;
    StationsAdapter adapter;
    String srvData;
    int listLength;
    MyGPS myGPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearest_station_list);
        final ListView listview = (ListView) findViewById(R.id.stationList);

        updateMyGPS();
        createStationList();
        updateList(adapter);


        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent startIntent = new Intent(getApplicationContext(), MapActivity.class);

            }
        });
    }


    private void createStationList(){
        StationListFactory stationListFactory = new StationListFactory(srvData,  myGPS.getMyLat(), myGPS.getMyLong(), listLength);
        trainStationArrayList = stationListFactory.getTrainStationArrayList();
    }

    private void updateList(StationsAdapter adapter){
        adapter = new StationsAdapter(this, android.R.layout.simple_list_item_1, trainStationArrayList);
        this.adapter = adapter;
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
        updateList(adapter);
    }
}
