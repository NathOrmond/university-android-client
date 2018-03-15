package com.example.user.trainclientapp.mapmethods;

import android.content.Intent;

import com.example.user.trainclientapp.activities.MapActivity;
import com.example.user.trainclientapp.activities.NearestStationListActivity;

/**
 * Created by User on 14/03/2018.
 */

public class NewMapDataXfer {
    /**
     * Starts a new Map activity, sends clicked train station anme
     * and latitude and longitude
     */
    public void startNewMap(NearestStationListActivity activity, Double targetLatitude, Double targetLongitude, String targetName) {
        Intent intent = new Intent(activity.getApplicationContext(), MapActivity.class);
        intent.putExtra("TARGET_LATITUDE", targetLatitude);
        intent.putExtra("TARGET_LONGITUDE", targetLongitude);
        intent.putExtra("TARGET_NAME", targetName);
        activity.startActivity(intent);
    }

    public void startNewMap(NearestStationListActivity activity, Double[] targetLatitudes, Double[] targetLongitudes, String[] targetNames) {
        Intent intent = new Intent(activity.getApplicationContext(), MapActivity.class);
        intent.putExtra("TARGET_LATITUDES", targetLatitudes);
        intent.putExtra("TARGET_LONGITUDES", targetLongitudes);
        intent.putExtra("TARGET_NAMES", targetNames);
        activity.startActivity(intent);
    }
//        public Double[] getLatitudes () {
//
//        }
//
//        public Double[] getLongitudes () {
//
//        }
//
//        public String[] getStationNames(){
//
//        }
}
