package com.example.user.trainclientapp.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.user.trainclientapp.R;
import com.example.user.trainclientapp.geolocation.MyGPS;
import com.example.user.trainclientapp.stationlist.TrainStation;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener{

    GoogleMap map;
    LocationManager locationManager;
    TrainStation station;
    LatLng currentLocation;
    LatLng targetLocation;
    MyGPS myGPS;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        myGPS = new MyGPS(this);
        getDataForTarget();
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 1, this);
    }

    private void getDataForTarget(){
        station = new TrainStation();
        Intent intent = this.getIntent();
        station.setStationLat(intent.getDoubleExtra("TARGET_LATITUDE", myGPS.getMyLat()));
        station.setStationLong(intent.getDoubleExtra("TARGET_LONGITUDE", myGPS.getMyLong()));
        station.setStationName(intent.getStringExtra("TARGET_NAME"));
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
        targetLocation = new LatLng(station.getStationLat(),station.getStationLong());
        googleMap.addMarker(new MarkerOptions().position(targetLocation).title(station.getStationName() + "Train Station"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 13.0f));


    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }
}
