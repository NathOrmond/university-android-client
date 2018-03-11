package com.example.user.trainclientapp.geolocation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.example.user.trainclientapp.activities.NearestStationListActivity;

import java.util.List;

/**
 * Created by User on 06/03/2018.
 */

public class MyGPS {
    Double myLat, myLong;
    LocationManager locationManager;
    Location location;
    NearestStationListActivity parent;


    /**
     * Constructor runs private methods checking for permission
     * Provided permissions given sets Location() to current GPS data.
     */
    @SuppressLint("MissingPermission")
    public MyGPS(NearestStationListActivity activity) {
        this.parent = activity;
        if(!hasPermissions()){
            requestPermissions();
        } else {
            requestLocationUpdates();
            updateMyLocation();
        }
    }


    /**
     * PERMISSIONS CHECKING METHOD FOR ANDROID
     */

    private boolean hasPermissions(){
        if(ActivityCompat.checkSelfPermission(parent, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
        && ActivityCompat.checkSelfPermission(parent, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return false;

        } else {
            return true;
            }
        }

    /**
     * POP UP DIALOGUE REQUESTING GPS PERMISSION
     */

    private void requestPermissions() {
        ActivityCompat.requestPermissions(parent, new String[]{
                "Android.Permission.ACCESS_FINE_LOCATION",
                "Android.Permission.ACCESS_COARSE_LOCATION"
        },1);
    }

    /**
     * Sets Location to current location (required for other methods)
     */

    @SuppressLint("MissingPermission")
    private void updateMyLocation(){
        List<String> providers = locationManager.getProviders(true);

        for(String provider : providers) {
            location = locationManager.getLastKnownLocation(provider);
//            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if(location != null) {
                setMyLat(location.getLatitude());
                setMyLong(location.getLongitude());
            }
        }

    }

    private void requestLocationUpdates() {
        try {
            locationManager = (LocationManager) parent.getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {

                @Override
                public void onLocationChanged(Location location) {
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {
                }

                @Override
                public void onProviderEnabled(String s) {
                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
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

}
