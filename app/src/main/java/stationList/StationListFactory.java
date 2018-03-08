package stationList;

import java.util.ArrayList;

import genericMethods.ServerMessageParser;

/**
 * Created by User on 08/03/2018.
 */

public class StationListFactory {

    int listLength;
    ArrayList<TrainStation> trainStationArrayList;

    public StationListFactory(String serverRawData){
        formatSrvData(serverRawData);
    }

    public StationListFactory(){

    }

    private void formatSrvData(String serverRawData){
        ServerMessageParser formatting = new ServerMessageParser();
        trainStationArrayList = new ArrayList<TrainStation>();

        for(int i = 0; i < listLength; i++) {

        }
    }


    public ArrayList<TrainStation> getTrainStationArrayList() {
        return trainStationArrayList;
    }

    public void setTrainStationArrayList(ArrayList<TrainStation> trainStationArrayList) {
        this.trainStationArrayList = trainStationArrayList;
    }


}
