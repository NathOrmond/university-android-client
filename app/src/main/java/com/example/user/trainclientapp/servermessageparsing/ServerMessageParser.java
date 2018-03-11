package com.example.user.trainclientapp.servermessageparsing;

import com.example.user.trainclientapp.geolocation.DistanceCalculation;
import com.example.user.trainclientapp.stationlist.TrainStation;

/**
 * Created by Nathan on 06/03/2018.
 */

public class ServerMessageParser {

    Double stationLat, stationLong;
    int stationNamePos = 6;
    int stationLatPos = 2;
    int stationLongPos = 4;


    public TrainStation createTrainStationForListPos(int listPosition, String[][] formattedData){
        TrainStation station = new TrainStation();
        station.setStationName(extractStationName(listPosition, formattedData));
        station.setStationLat(extractStationLat(listPosition, formattedData));
        station.setStationLong(extractStationLong(listPosition, formattedData));
        return station;
    }

    public String extractStationName(int listPosition, String[][] formattedData){
        return formattedData[listPosition][6];
    }

    public Double extractStationLat(int listPosition, String[][] formattedData){
        String extractedString = formattedData[listPosition][stationLatPos];
        Double stationLat = Double.parseDouble(extractedString);
        this.stationLat = stationLat;
        return stationLat;
    }

    public Double extractStationLong(int listPosition, String[][] formattedData){
        String extractedString = formattedData[listPosition][stationLongPos];
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
