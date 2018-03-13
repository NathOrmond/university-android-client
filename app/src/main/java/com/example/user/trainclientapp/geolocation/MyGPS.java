package com.example.user.trainclientapp.geolocation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.example.user.trainclientapp.activities.StartAppActivity;
import com.example.user.trainclientapp.activities.NearestStationListActivity;

import java.util.List;

/**
 * Created by User on 06/03/2018.
 */

public class MyGPS extends NearestStationListActivity {
    Double myLat, myLong;
    LocationListener listener;
    LocationManager locationManager;
    Location location;
    NearestStationListActivity parent = null;
    StartAppActivity mainParent;


    /**
     * Constructor runs private methods checking for permission
     * Provided permissions given sets Location() to current GPS data.
     */
    @SuppressLint("MissingPermission")
    public MyGPS(NearestStationListActivity activity) {
        this.parent = activity;
            locationManager = (LocationManager) parent.getSystemService(parent.LOCATION_SERVICE);
            refreshUpdate();
    }

    public void refreshUpdate() {
        initialiseListener();
        checkForPermissions();
        updateMyLocation();
    }


    /**
     * Starts the Listener
     * Opens up App settings if provider is disabled
     */
    private void initialiseListener(){
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                setMyLat(location.getLatitude());
                setMyLong(location.getLongitude());
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };
    }

    /**
     * Checks android GPS Location permissions have been granted to app
     */
    public void checkForPermissions(){

            if (ActivityCompat.checkSelfPermission(parent, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(parent, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                parent.requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET,
                }, 10);

                return;
            } else {
                configureUpdateRequest();
            }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case 10:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                configureUpdateRequest();
                }
                return;

        }
    }

    /**
     * Requests GPS updates from Android
     */
    @SuppressLint("MissingPermission")
    private void configureUpdateRequest() {
        locationManager.requestLocationUpdates("gps", 0, 0, listener);
    }


    /**
     * GETTERS AND SETTERS FOR LATITUDE AND LONGITUDE
     */

    private void setMyLat(Double myLat){
        this.myLat = myLat;
    }

    public Double getMyLat(){
        return myLat;
    }

    private void setMyLong(Double myLong){
        this.myLong = myLong;
    }

    public Double getMyLong(){
        return myLong;
    }

    /**
     * updates my latitude and longitude variables when called to current location
     */
    @SuppressLint("MissingPermission")
    private void updateMyLocation() {
        List<String> providers = locationManager.getProviders(true);

        for (String provider : providers) {
            location = locationManager.getLastKnownLocation(provider);

            if (location != null) {
                setMyLat(location.getLatitude());
                setMyLong(location.getLongitude());
            }
        }

    }


}
