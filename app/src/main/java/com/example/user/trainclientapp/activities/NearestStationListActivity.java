package com.example.user.trainclientapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.user.trainclientapp.R;
import com.example.user.trainclientapp.geolocation.MyGPS;
import com.example.user.trainclientapp.servernetworking.URLASyncTask;
import com.example.user.trainclientapp.stationlist.StationListFactory;
import com.example.user.trainclientapp.stationlist.StationsAdapter;
import com.example.user.trainclientapp.stationlist.TrainStation;

import java.util.ArrayList;

public class NearestStationListActivity extends AppCompatActivity {


    Button refreshButton;
    ArrayList<TrainStation> trainStationArrayList;
    StationsAdapter adapter;
    ListView listView;
    String srvData;
    int listLength;
    MyGPS myGPS;
    Double myLatitude, myLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearest_station_list);

        refreshButton = (Button) findViewById(R.id.refreshButton);
        listView = (ListView) findViewById(R.id.stationList);

    }


    private void activityMethod(){
        updateMyGPS();
        Log.v("latitude", String.valueOf(myLatitude));
        Log.v("longitude", String.valueOf(myLongitude));
        getNearestStationDataFromSrv();
    }

    private boolean dataIsNotNull(){
        if((myLongitude != null) && (myLatitude != null)){
            return true; } else {
            return false;
        }
    }

    /**
     * Checks for location permissions, given they are granted
     * updates location manager based on location.
     * Contains myLatitude and myLongituded as ints
     */

    private void updateMyGPS(){
        myGPS = new MyGPS(this);
        myLatitude = myGPS.getMyLat();
        myLongitude = myGPS.getMyLong();
    }

    /**
     * Updates data from server based on current latitude and longitude
     */

    private void getNearestStationDataFromSrv(){
        String latitudeString, longitudeString;
        latitudeString = myLatitude.toString();
        longitudeString = myLongitude.toString();

        Log.v("latitudeString", latitudeString);
        Log.v("longitudeString", longitudeString);

        new URLASyncTask(this, latitudeString, longitudeString).execute();

    }



    /**
     * re runs methods when user clicks button to refresh
     */

    public void updateLocation(View view) {
        activityMethod();
    }

    /**
     * runs on post execute of async task
     */

    public void updataData(String newData){
        this.srvData =  newData;
        Log.v("degug", srvData);

        if(srvData.equals(dataIsNotNull())) {
//        createStationList();
//        updateList(adapter, listView);
        } else {
        // Server is down
        }
    }

    /**
     * Creates a station list from the most updated server data
     */

    private void createStationList(){
        Log.v("debug", srvData);
        StationListFactory stationListFactory = new StationListFactory(srvData,  myLatitude, myLongitude, listLength);


//        trainStationArrayList = stationListFactory.getTrainStationArrayList();

    }

    /**
     * Populates list with the most recent data
     * Sets list item listener for click
     */
    private void  updateList(StationsAdapter adapter, ListView listView){
        adapter = new StationsAdapter(this, android.R.layout.simple_list_item_1, trainStationArrayList);
        this.adapter = adapter;
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent startIntent = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(startIntent);
            }
        });

        this.listView = listView;
    }

}
