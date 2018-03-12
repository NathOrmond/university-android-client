package com.example.user.trainclientapp.stationlist;

import android.util.Log;

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


    private void formatSrvData(String serverRawData, Double myLat, Double myLong){

            ServerMessageParser listPopulator = new ServerMessageParser();
            ServerMessageStringManipulator dataExtractor = new ServerMessageStringManipulator();
            trainStationArrayList = new ArrayList<TrainStation>();
            TrainStation station;

            String[] formattedData = dataExtractor.formatServerData(serverRawData);

            Log.i("StationsArray", "Array Created Succesfully");
            Log.i("StationsArray", String.valueOf(formattedData.length));

        for(int i = 0; i < listLength; i++) {
            Log.i("new station", "[" + i + "] -----------------------------" );
            station = listPopulator.createTrainStationForListPos(i,formattedData);
            station = listPopulator.addStationDistance(station,myLat,myLong);
            trainStationArrayList.add(i, station);

        }

    }

    public ArrayList<TrainStation> getTrainStationArrayList() {
        return trainStationArrayList;
    }

    public void setTrainStationArrayList(ArrayList<TrainStation> trainStationArrayList) {
        this.trainStationArrayList = trainStationArrayList;
    }


}
