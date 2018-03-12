package com.example.user.trainclientapp.servermessageparsing;

import android.util.Log;

import com.example.user.trainclientapp.geolocation.DistanceCalculation;
import com.example.user.trainclientapp.stationlist.TrainStation;

/**
 * Created by Nathan on 06/03/2018.
 */

public class ServerMessageParser {

    Double stationLat, stationLong;
    int stationNamePos = 5;
    int stationLatPos = 1;
    int stationLongPos = 3;
    int listItems = 6;


    public TrainStation createTrainStationForListPos(int listPosition, String[] formattedData){
        TrainStation station = new TrainStation();
        station.setStationName(extractStationName(listPosition, formattedData));
        station.setStationLat(extractStationLat(listPosition, formattedData));
        station.setStationLong(extractStationLong(listPosition, formattedData));
        return station;
    }

    public String extractStationName(int listPosition, String[] formattedData){
        Log.v("Stations Name", formattedData[(listItems*listPosition) + stationNamePos]);
        return formattedData[(listItems*listPosition) + stationNamePos];
    }

    public Double extractStationLat(int listPosition, String[] formattedData){
        String extractedString = formattedData[(listItems*listPosition) + stationLatPos];
        Double stationLat = Double.parseDouble(extractedString);
        this.stationLat = stationLat;
        return stationLat;
    }

    public Double extractStationLong(int listPosition, String[] formattedData){
        String extractedString = formattedData[(listItems*listPosition) + stationLongPos];
        Double stationLong = Double.parseDouble(extractedString);
        this.stationLong = stationLong;
        return stationLong;
    }

    public TrainStation addStationDistance(TrainStation station, Double myLat, Double myLong){
        DistanceCalculation calculation = new DistanceCalculation();
        Double distance = calculation.Haversine(station.getStationLat(), station.getStationLong(), myLat, myLong);
        station.setDistanceNum(distance);
        return station;
    }



}
