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


    /**
     *
     * Takes the list position for which this TrainStation()
     * is being created and a String[] of formatted data,
     * returns the populated TrainStation()
     *
     * @param listPosition
     * @param formattedData
     * @return TrainStation
     */

    public TrainStation createTrainStationForListPos(int listPosition, String[] formattedData){
        TrainStation station = new TrainStation();
        station.setStationName(extractStationName(listPosition, formattedData));
        station.setStationLat(extractStationLat(listPosition, formattedData));
        station.setStationLong(extractStationLong(listPosition, formattedData));
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
        String stationName = formattedData[(listItems*listPosition) + stationNamePos];
        Log.i("Stations Name Added", stationName);
        if(stationName.equals("NO_VALUE")) {
            stationName = "No Station Name";
        }

        return stationName;
    }

    /**
     * Populates a TrainStation() with its Latitude
     *
     * @param listPosition
     * @param formattedData
     * @return stationLatitude
     */

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

    /**
     * populates a TrainStation() with its Longitude
     *
     * @param listPosition
     * @param formattedData
     * @return stationLongitude
     */

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
