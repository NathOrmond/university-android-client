package com.example.user.trainclientapp.mapmethods;

import android.content.Intent;

import com.example.user.trainclientapp.activities.MapActivity;
import com.example.user.trainclientapp.activities.NearestStationListActivity;
import com.example.user.trainclientapp.stationlist.TrainStation;

import java.util.ArrayList;

/**
 * Created by User on 14/03/2018.
 */

public class NewMapDataXfer {

    String mapContentType;
    NearestStationListActivity activity;
    ArrayList<TrainStation> stationArrayList;
    int listLength;
    TrainStation station;


    /**
     * Constructor with params which may be commonly used in methods
     *
     * @param activity
     * @param stationArrayList
     */
    public NewMapDataXfer(NearestStationListActivity activity, ArrayList<TrainStation> stationArrayList, int listLength){
        this.activity = activity;
        this.stationArrayList = stationArrayList;
        this.listLength = listLength;
    }

    /**
     * constructor for no params
     */
    public NewMapDataXfer(){

    }

    /**
     * Starts a new Map activity, sends clicked train station anme
     * and latitude and longitude
     */
    public void startNewMap(NearestStationListActivity activity, Double targetLatitude, Double targetLongitude, String targetName) {
        Intent intent = new Intent(activity.getApplicationContext(), MapActivity.class);
        intent.putExtra("TARGET_LATITUDE", targetLatitude);
        intent.putExtra("TARGET_LONGITUDE", targetLongitude);
        intent.putExtra("TARGET_NAME", targetName);
        mapContentType = "SHOW_SINGLE";
        intent.putExtra("MAP_CONTENT_TYPE", mapContentType);
        activity.startActivity(intent);
    }

    /**
     * Starts map but passes all train station locations as array data
     *
     * @param activity
     * @param targetLatitudes
     * @param targetLongitudes
     * @param targetNames
     */
    public void startNewMap(NearestStationListActivity activity, Double[] targetLatitudes, Double[] targetLongitudes, String[] targetNames) {
        Intent intent = new Intent(activity.getApplicationContext(), MapActivity.class);
        intent.putExtra("TARGET_LATITUDES", targetLatitudes);
        intent.putExtra("TARGET_LONGITUDES", targetLongitudes);
        intent.putExtra("TARGET_NAMES", targetNames);
        mapContentType = "SHOW_ALL";
        intent.putExtra("MAP_CONTENT_TYPE", mapContentType);
        activity.startActivity(intent);
    }

    /**
     * returns a double array of all latitudes of train stations
     *
     * @return getLatitudes[]
     */
        public Double[] getLatitudes () {
            Double[] getLatitudes ={};
            for(int i = 0; i < listLength; i++) {
                station = stationArrayList.get(i);
                getLatitudes[i] = station.getStationLat();
            }
            return getLatitudes;
        }

    /**
     * returns a double array of all Longitudes of train stations
     *
     * @return getLongitudes
     */
    public Double[] getLongitudes () {
        Double[] getLongitudes = {};
            for(int i = 0; i < listLength; i++) {
                station = stationArrayList.get(i);
                getLongitudes[i] = station.getStationLong();
            }
        return getLongitudes;
        }

    /**
     * returns a String[] of all station names;
     *
     * @return getStationNames[]
     */

    public String[] getStationNames(){
        String[] getStationNames = {};
            for(int i = 0; i < listLength; i++) {
                station = stationArrayList.get(i);
                getStationNames[i] = station.getStationName();
            }
        return getStationNames;
        }
}
