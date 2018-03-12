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
        listLength = 5;

        activityMethod();
    }


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
    }

    /**
     * Updates data from server based on current latitude and longitude
     */

    private void getNearestStationDataFromSrv(){
        String latitudeString, longitudeString;
        latitudeString = myLatitude.toString();
        longitudeString = myLongitude.toString();

        Log.i("latitudeString", latitudeString);
        Log.i("longitudeString", longitudeString);

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

        if(newData != null) {
            this.srvData =  newData;
            Log.v("Server Has Data", srvData);

        } else {
            //TestData for lat=0.04489833333333334 & lng=51.5
            srvData = "[{\"Latitude\":\"\",\"Longitude\":\"\",\"StationName\":\"Lea Bridge\"},{\"Latitude\":\"51.126\",\"Longitude\":\"1.3056\",\"StationName\":\"Dover Priory\"},{\"Latitude\":\"51.1706\",\"Longitude\":\"1.349\",\"StationName\":\"Martin Mill\"},{\"Latitude\":\"51.2035\",\"Longitude\":\"1.383\",\"StationName\":\"Walmer\"},{\"Latitude\":\"51.0829\",\"Longitude\":\"1.1697\",\"StationName\":\"Folkestone Central\"}]";
            Log.v("degug", "Server down error, test Data in use" + srvData);
        }
            createStationList();
//            updateList(adapter, listView);
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
    private void  updateList(StationsAdapter adapter, ListView listView){
        adapter = new StationsAdapter(this, android.R.layout.simple_list_item_1, trainStationArrayList);
        this.adapter = adapter;
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent mapIntent = new Intent(getApplicationContext(), MapActivity.class);
                mapIntent.putExtra("MY_LATITUDE", myLatitude);
                mapIntent.putExtra("MY_LONGITUDE", myLatitude);
                mapIntent.putExtra("STATION_LIST", trainStationArrayList);
                startActivity(mapIntent);
            }
        });

        this.listView = listView;
    }

}
