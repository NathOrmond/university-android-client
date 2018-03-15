package com.example.user.trainclientapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.trainclientapp.R;
import com.example.user.trainclientapp.geolocation.MyGPS;
import com.example.user.trainclientapp.listdisplay.StationsAdapter;
import com.example.user.trainclientapp.servernetworking.URLASyncTask;
import com.example.user.trainclientapp.stationlist.StationListFactory;
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
        listLength = 5;

        activityMethod();
    }

    /**
     * refreshes location when app is restarted
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        activityMethod();
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityMethod();
    }

    /**
     * Runs the main method sequence of subroutines for this activity
     */

    private void activityMethod(){
        updateMyGPS();
        getNearestStationDataFromSrv();
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

        Log.i("LATITUDE_FROM_MYGPS", String.valueOf(myLatitude));
        Log.i("LONGITUDE_FROM_MYGPS", String.valueOf(myLongitude));
    }

    /**
     * Updates data from server based on current latitude and longitude
     */

    private void getNearestStationDataFromSrv(){
        String latitudeString, longitudeString;

        latitudeString = myLatitude.toString();
        longitudeString = myLongitude.toString();

        Log.i("LATITUDE_AS_STRING", latitudeString);
        Log.i("LONGITUDE_AS_STRING", longitudeString);

        new URLASyncTask(this, latitudeString , longitudeString).execute();

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

        if(newData != null) {
            this.srvData =  newData;
                        Log.i("SERVER_DATA", srvData);
        } else {
            Log.w("SERVER_DATA", "data is null, server error");
               }
        createStationList();
        updateList(adapter, listView);
    }

    /**
     * Creates a station list from the most updated server data
     */

    private void createStationList(){
        StationListFactory stationListFactory = new StationListFactory(srvData,  myLatitude, myLongitude, listLength);
        trainStationArrayList = stationListFactory.getTrainStationArrayList();
    }

    /**
     * Populates list with the most recent data
     * Sets list item listener for click
     */
    NearestStationListActivity activity;
    private void  updateList(StationsAdapter adapter, ListView listView){
        adapter = new StationsAdapter(this, android.R.layout.simple_list_item_1, trainStationArrayList);
        this.adapter = adapter;
        listView.setAdapter(adapter);
        this.activity = this;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String latitude = ((TextView) view.findViewById(R.id.latitude)).getText().toString();
                String longitude = ((TextView) view.findViewById(R.id.longitude)).getText().toString();
                String stationName = ((TextView) view.findViewById(R.id.name)).getText().toString();

                Double passLat, passLong;
                passLat = Double.parseDouble(latitude);
                passLong = Double.parseDouble(longitude);

                startNewMap(activity , passLat, passLong, stationName);

            }
        });

        this.listView = listView;
    }

    public void startNewMap(NearestStationListActivity activity, Double targetLatitude, Double targetLongitude, String targetName) {
        Intent intent = new Intent(activity.getApplicationContext(), MapActivity.class);
        intent.putExtra("TARGET_LATITUDE", targetLatitude);
        intent.putExtra("TARGET_LONGITUDE", targetLongitude);
        intent.putExtra("TARGET_NAME", targetName);
        activity.startActivity(intent);
    }

    public void showAllOnMapClick(View view) {
        Log.i("CLICK_EVENT", "show all on map");
    }

    //ToDo
    //Button with onCLick() to show all train station locations on app.
    //Map intent for said button.



}
