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
        if(updateMyGPS()) {
            Log.v("myTag", String.valueOf(myGPS.getMyLat()));
            Log.v("myTag", String.valueOf(myGPS.getMyLong()));

//            getNearestStationDataFromSrv();
//            getNearestStationDataFromSrv();
//            createStationList();
//            updateList(adapter, listView);
        }
    }

    /**
     * Checks for location permissions, given they are granted
     * updates location manager based on location.
     * Contains myLatitude and myLongituded as ints
     */

    private boolean updateMyGPS(){
        myGPS = new MyGPS(this);
        myLatitude = myGPS.getMyLat();
        myLongitude = myGPS.getMyLong();
        return true;
    }

    /**
     * Updates data from server based on current latitude and longitude
     */

    private void getNearestStationDataFromSrv(){
        String latitudeString, longitudeString;
        if((myLatitude == null) || (myLongitude == null)) {
            latitudeString ="0";
            longitudeString="0";
        } else {
            latitudeString = myLatitude.toString();
            longitudeString = myLongitude.toString();
        }

        new URLASyncTask(this, latitudeString, longitudeString).execute();
    }

    /**
     * Creates a station list from the most updated server data
     */

    private void createStationList(){
        StationListFactory stationListFactory = new StationListFactory(srvData,  myGPS.getMyLat(), myGPS.getMyLong(), listLength);
        trainStationArrayList = stationListFactory.getTrainStationArrayList();
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

    /**
     * re runs methods when user clicks button to refresh
     */

    public void updateLocation(View view) {
        activityMethod();
    }

    /**
     * userd by URL A sync task to update server data.
     */

    public void updataData(String newData){
        srvData =  newData;
    }

}
