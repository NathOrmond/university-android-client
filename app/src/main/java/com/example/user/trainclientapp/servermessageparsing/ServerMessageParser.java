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
    int listItems = 6;
    int delimiterError = 1;


    public TrainStation createTrainStationForListPos(int listPosition, String[] formattedData){
        TrainStation station = new TrainStation();
        station.setStationName(extractStationName(listPosition, formattedData));
        station.setStationLat(extractStationLat(listPosition, formattedData));
        station.setStationLong(extractStationLong(listPosition, formattedData));
        return station;
    }

    public String extractStationName(int listPosition, String[] formattedData){
        Log.i("Stations Name Added", formattedData[(listItems*listPosition) + stationNamePos]);
        return formattedData[(listItems*listPosition) + stationNamePos];
    }

    public Double extractStationLat(int listPosition, String[] formattedData){
        String extractedString = formattedData[(listItems*listPosition) + stationLatPos];
        Log.i("Latitude Added" ,formattedData[(listItems*listPosition) + stationLatPos] );
        Double stationLat = Double.parseDouble(extractedString);
        this.stationLat = stationLat;
        return stationLat;
    }

    public Double extractStationLong(int listPosition, String[] formattedData){
        String extractedString = formattedData[(listItems*listPosition) + stationLongPos];
        Log.i("Longitude Added" ,formattedData[(listItems*listPosition) + stationLongPos] );
        Double stationLong = Double.parseDouble(extractedString);
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
