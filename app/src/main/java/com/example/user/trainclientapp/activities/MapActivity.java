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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap map;
    private Intent intent;
    String map_type;
    int list_length;
    LatLng currentLocation;
    MyGPS myGPS;
    List<TrainStation> stations;
    List<LatLng> targetLocations;
    float zoom_level;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        myGPS = new MyGPS(this);
        intent = this.getIntent();
        list_length = intent.getIntExtra("LIST_LENGTH", 1);
        map_type = intent.getStringExtra("MAP_CONTENT_TYPE");
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

    /**
     * Determines whether map has been started to create a single
     * selected TrainStation() or all closes TrainStation()'s
     */
    private void determineMapSetup(){
        StationListFactory stationListFactory;
        Log.i("MAP_TYPE", map_type);

        if(map_type.equals("SHOW_ALL")) {
            stationListFactory = new StationListFactory(list_length, intent.getDoubleArrayExtra("TARGET_LATITUDES"), intent.getDoubleArrayExtra("TARGET_LONGITUDES"), intent.getStringArrayExtra("TARGET_NAMES"));

        } else {
            Log.i("DATA_ACTIVITY_PASS", "array list succesfully passed from previous activity");
            stationListFactory = new StationListFactory(list_length, intent.getDoubleExtra("TARGET_LATITUDE", myGPS.getMyLat()), intent.getDoubleExtra("TARGET_LONGITUDE", myGPS.getMyLong()),intent.getStringExtra("TARGET_NAME"));
        }
        stations = stationListFactory.getTrainStationArrayList();
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
        List<Marker> markers = new ArrayList<Marker>();
        targetLocations = new ArrayList<LatLng>();

        currentLocation = new LatLng (myGPS.getMyLat(),myGPS.getMyLong());
        Marker myLocationMarker = googleMap.addMarker(new MarkerOptions().position(currentLocation).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)).title("MyLocation"));
        markers.add(myLocationMarker);



        Marker stationMarker;
        for(int i = 0; i < list_length; i++) {
            TrainStation station = stations.get(i);
            LatLng targetLocation = new LatLng(station.getStationLat(),station.getStationLong());
            targetLocations.add(targetLocation);
            stationMarker = googleMap.addMarker(new MarkerOptions().position(targetLocation).title(station.getStationName() + " Train Station"));
            markers.add(stationMarker);
        }

        if(map_type.equals("SHOW_ALL")) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (Marker marker : markers) {
                builder.include(marker.getPosition());
            }
            LatLngBounds bounds = builder.build();
            int padding = 100;
            googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding));
        } else {
            zoom_level = 11.0f;
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation,zoom_level));
        }


    }


}
