package com.example.user.trainclientapp.stationlist;

import com.example.user.trainclientapp.servermessageparsing.ServerMessageParser;
import com.example.user.trainclientapp.servermessageparsing.ServerMessageStringManipulator;

import java.util.ArrayList;

/**
 * Created by Nathan on 08/03/2018.
 */

public class StationListFactory {

    int listLength;

    ArrayList<TrainStation> trainStationArrayList;

    public StationListFactory(String serverRawData, Double myLat, Double myLong, int listLength){
        this.listLength = listLength;
        formatSrvData(serverRawData, myLat, myLong);
    }

    public StationListFactory(int list_Length, Double stationLat, Double stationLong, String targetName){
        TrainStation station = new TrainStation();
        trainStationArrayList = new ArrayList<TrainStation>();
        for(int i = 0; i < list_Length; i++) {
            station.setStationLat(stationLat);
            station.setStationLong(stationLong);
            station.setStationName(targetName);
            trainStationArrayList.add(station);
        }
    }


    public StationListFactory(int list_length, double[] target_latitudes, double[] target_longitudes, String[] target_names) {
        TrainStation station = new TrainStation();
        trainStationArrayList = new ArrayList<TrainStation>();

        for(int i = 0; i < list_length; i++) {
            station.setStationLat(target_latitudes[i]);
            station.setStationLong(target_longitudes[i]);
            station.setStationName(target_names[i]);
            trainStationArrayList.add(station);
        }
    }


    /**
     * Creates an ArrayList<TrainStation>() populated with raw server Data
     *
     * @param serverRawData
     * @param myLat
     * @param myLong
     */
    private void formatSrvData(String serverRawData, Double myLat, Double myLong){

        ServerMessageStringManipulator formatter = new ServerMessageStringManipulator();
        String[] jsonItemArray = formatter.splitJSON(serverRawData);

        TrainStation station;
        ArrayList<TrainStation> stationList = new ArrayList<TrainStation>();
        ServerMessageParser parser = new ServerMessageParser();
        for (int listPosition = 0; listPosition < listLength; listPosition++) {
           station =  parser.createTrainStationForListPos(listPosition, jsonItemArray, myLat, myLong);
           stationList.add(station);
        }

        this.trainStationArrayList = stationList;

    }


    /**
     * @return a populated ArrayList<TrainStation>()
     */

    public ArrayList<TrainStation> getTrainStationArrayList() {
        return trainStationArrayList;
    }



}
