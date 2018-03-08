package stationList;

import java.util.ArrayList;

import genericMethods.ServerMessageParser;

/**
 * Created by User on 08/03/2018.
 */

public class StationListFactory {

    int listLength;
    ArrayList<TrainStation> trainStationArrayList;

    public StationListFactory(String serverRawData, Double myLat, Double myLong){
        formatSrvData(serverRawData, myLat, myLong);
    }

    public StationListFactory(){
    }

    private void formatSrvData(String serverRawData, Double myLat, Double myLong){
        ServerMessageParser formatting = new ServerMessageParser();
        trainStationArrayList = new ArrayList<TrainStation>();

    }

    public ArrayList<TrainStation> getTrainStationArrayList() {
        return trainStationArrayList;
    }

    public void setTrainStationArrayList(ArrayList<TrainStation> trainStationArrayList) {
        this.trainStationArrayList = trainStationArrayList;
    }


}
