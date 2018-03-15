package com.example.user.trainclientapp.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.user.trainclientapp.R;
import com.example.user.trainclientapp.geolocation.MyGPS;
import com.example.user.trainclientapp.stationlist.StationListFactory;
import com.example.user.trainclientapp.stationlist.TrainStation;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback{

    GoogleMap map;
    Intent intent;
    String MAP_CONTENT_TYPE;
    int LIST_LENGTH;
    LatLng currentLocation;
    MyGPS myGPS;
    ArrayList<TrainStation> stationArrayList;
    ArrayList<LatLng> targetLocations;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        myGPS = new MyGPS(this);
        intent = this.getIntent();
        LIST_LENGTH = intent.getIntExtra("LIST_LENGTH", 1);
        MAP_CONTENT_TYPE = intent.getStringExtra("MAP_CONTENT_TYPE");

        determineMapSetup();

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        determineMapSetup();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        determineMapSetup();
    }

    private void determineMapSetup(){
        StationListFactory stationListFactory;
        if(MAP_CONTENT_TYPE.equals("SHOW_ALL")) {
            stationListFactory = new StationListFactory(LIST_LENGTH, intent.getDoubleArrayExtra("TARGET_LATITUDES"), intent.getDoubleArrayExtra("TARGET_LONGITUDES"), intent.getStringArrayExtra("TARGET_NAMES"));

        } else {
            stationListFactory = new StationListFactory(LIST_LENGTH, intent.getDoubleExtra("TARGET_LATITUDE", myGPS.getMyLat()), intent.getDoubleExtra("TARGET_LONGITUDE", myGPS.getMyLong()),intent.getStringExtra("TARGET_NAME"));
        }
        stationArrayList = stationListFactory.getTrainStationArrayList();
        Log.i("DATA_ACTIVITY_PASS", "array list succesfully passed from previous activity");
    }
    

    /**
     * sets current location and target location on google maps API,
     * Creates marker with train station name and zooms map to location.
     * @param googleMap
     */

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMyLocationEnabled(true);

        currentLocation = new LatLng (myGPS.getMyLat(),myGPS.getMyLong());

        targetLocations = new ArrayList<LatLng>();
        LatLng targetLocation;
        TrainStation station;

        for(int i = 0; i < LIST_LENGTH; i++) {
            station = stationArrayList.get(i);
            targetLocation = new LatLng(station.getStationLat(),station.getStationLong());
            targetLocations.add(targetLocation);
            googleMap.addMarker(new MarkerOptions().position(targetLocation).title(station.getStationName() + "Train Station"));

        }


        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 13.0f));
    }


}
