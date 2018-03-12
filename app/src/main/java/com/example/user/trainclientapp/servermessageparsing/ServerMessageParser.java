package com.example.user.trainclientapp.servermessageparsing;

import android.util.Log;

import com.example.user.trainclientapp.geolocation.DistanceCalculation;
import com.example.user.trainclientapp.stationlist.TrainStation;

/**
 * Created by Nathan on 06/03/2018.
 */

public class ServerMessageParser {

    Double stationLat, stationLong;
    int stationNamePos = 7;
    int stationLatPos = 3;
    int stationLongPos = 5;
    int listItems = 8;
    int delimiterError = 1;


    public TrainStation createTrainStationForListPos(int listPosition, String[] formattedData){
        TrainStation station = new TrainStation();
        station.setStationName(extractStationName(listPosition, formattedData));
        station.setStationLat(extractStationLat(listPosition, formattedData));
        station.setStationLong(extractStationLong(listPosition, formattedData));
        return station;
    }

    public String extractStationName(int listPosition, String[] formattedData){
        String stationName = formattedData[(listItems*listPosition) + stationNamePos];
        Log.i("Stations Name Added", stationName);
        if(stationName.equals("NO_VALUE")) {
            stationName = "No Station Name";
        }

        return stationName;
    }

    public Double extractStationLat(int listPosition, String[] formattedData){
        String extractedString = formattedData[(listItems*listPosition) + stationLatPos];
        Double stationLat;
        Log.i("Latitude Added" ,formattedData[(listItems*listPosition) + stationLatPos] );
        if(extractedString.equals("NO_VALUE")) {
            stationLat = Double.valueOf(0);
        }else {
        stationLat = Double.parseDouble(extractedString);
    }
        this.stationLat = stationLat;
        return stationLat;
    }

    public Double extractStationLong(int listPosition, String[] formattedData){
        String extractedString = formattedData[(listItems*listPosition) + stationLongPos];
        Double stationLong;
        Log.i("Longitude Added" ,formattedData[(listItems*listPosition) + stationLongPos] );
        if(extractedString.equals("NO_VALUE")) {
            stationLong = Double.valueOf(0);
        }else {
            stationLong = Double.parseDouble(extractedString);
        }
        this.stationLong = stationLong;
        return stationLong;
    }

    public TrainStation addStationDistance(TrainStation station, Double myLat, Double myLong){
        DistanceCalculation calculation = new DistanceCalculation();
        Double distance = calculation.Haversine(station.getStationLat(), station.getStationLong(), myLat, myLong);
        station.setDistanceNum(distance);
        Log.i("Distance From Me", String.valueOf(distance));
        return station;
    }



}
