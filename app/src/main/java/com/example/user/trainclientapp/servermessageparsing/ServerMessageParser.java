package com.example.user.trainclientapp.servermessageparsing;

import com.example.user.trainclientapp.geolocation.DistanceCalculation;
import com.example.user.trainclientapp.stationlist.TrainStation;

/**
 * Created by Nathan on 06/03/2018.
 */

public class ServerMessageParser {

    Double stationLat, stationLong;

    public TrainStation createTrainStationForListPos(int listPosition, String rawServerData){
        TrainStation station = new TrainStation();
        station.setStationName(extractStationName(listPosition, rawServerData));
        station.setStationLat(extractStationLat(listPosition, rawServerData));
        station.setStationLong(extractStationLong(listPosition, rawServerData));
        return station;
    }

    public String extractStationName(int listPosition, String rawServerData){
        String stationName = "station " + listPosition;
        return stationName;
    }

    public Double extractStationLat(int listPosition, String rawServerData){
        String extractedString = "100";
        Double stationLat = Double.parseDouble(extractedString);
        this.stationLat = stationLat;
        return stationLat;
    }

    public Double extractStationLong(int listPosition, String rawServerData){
        String extractedString = "100";
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
