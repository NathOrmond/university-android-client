package com.example.user.trainclientapp.servermessageparsing;

import android.util.Log;

import com.example.user.trainclientapp.geolocation.DistanceCalculation;
import com.example.user.trainclientapp.stationlist.TrainStation;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nathan on 06/03/2018.
 */

public class ServerMessageParser {

    Double stationLat, stationLong;



    /**
     *
     * Takes the list position for which this TrainStation()
     * is being created and a String[] of formatted data,
     * returns the populated TrainStation()
     *
     * @param listPosition
     * @param jsonFormatData
     * @return TrainStation
     */

    public TrainStation createTrainStationForListPos(int listPosition, String[] jsonFormatData, Double myLat, Double myLong){
        Log.i("ADDING STATION", "----------" + listPosition);
        TrainStation station = new TrainStation();
        station.setStationName(extractStationName(listPosition, jsonFormatData));
        station.setStationLat(extractStationLat(listPosition,jsonFormatData, myLat));
        station.setStationLong(extractStationLong(listPosition,jsonFormatData,myLong));
        addStationDistance(station,myLat,myLong);
        return station;
    }

    /**
     * Populates a TrainStation() with its station Name
     *
     * @param listPosition
     * @param formattedData
     * @return stationName
     */

    public String extractStationName(int listPosition, String[] formattedData){
        String stationName = "NONE_FOUND";
        JSONObject json;

        if(!stationName.equals("")) {
            try {
                json = new JSONObject(formattedData[listPosition]);
                stationName = json.getString("StationName");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Log.i("STATION_NAME", stationName);
         return stationName;
    }


    /**
     * Populates a TrainStation() with its Latitude
     *
     * @param listPosition
     * @param formattedData
     * @return stationLatitude
     */

    public Double extractStationLat(int listPosition, String[] formattedData, Double myLat){
        String stationLatPos = "NONE_FOUND";
        JSONObject json;
        Double stationLat;


            try {
                json = new JSONObject(formattedData[listPosition]);
                stationLatPos = json.getString("Latitude");
            } catch (JSONException e) {
                e.printStackTrace();
            }

         if((stationLatPos.equals("")) || (stationLatPos == null))   {
            stationLat = myLat;
        } else {
             stationLat = Double.parseDouble(stationLatPos);
            }

        Log.i("LONGITUDE" ,stationLatPos );

        this.stationLat = stationLat;
        return stationLat;
    }

    /**
     * populates a TrainStation() with its Longitude
     *
     * @param listPosition
     * @param formattedData
     * @return stationLongitude
     */

    public Double extractStationLong(int listPosition, String[] formattedData, Double myLong){
        String stationLongPos = "NONE_FOUND";
        JSONObject json;
        Double stationLong;

            try {
                json = new JSONObject(formattedData[listPosition]);
                stationLongPos = json.getString("Longitude");
            } catch (JSONException e) {
                e.printStackTrace();
            }


        if((stationLongPos.equals("")) || (stationLongPos == null))   {
            stationLong = myLong;
        } else {
            stationLong = Double.parseDouble(stationLongPos);
        }

        Log.i("LONGITUDE" ,stationLongPos );

        this.stationLat = stationLong;
        return stationLong;
    }

    /**
     * populates a TrainStation() with its calculated
     * distance from current location.
     *
     * @param station
     * @param myLat
     * @param myLong
     * @return TrainStation (with distance added)
     */

    public TrainStation addStationDistance(TrainStation station, Double myLat, Double myLong){
        DistanceCalculation calculation = new DistanceCalculation();
        Double distance = calculation.Haversine(station.getStationLat(), station.getStationLong(), myLat, myLong);
        station.setDistanceNum(distance);
        Log.i("Distance From Me", String.valueOf(distance));
        return station;
    }

}
