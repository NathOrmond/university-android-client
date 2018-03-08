package stationList;

import java.util.ArrayList;

import genericMethods.ServerMessageParser;

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

    public StationListFactory(){
        this.listLength = listLength;
    }

    private void formatSrvData(String serverRawData, Double myLat, Double myLong){
        ServerMessageParser formatting = new ServerMessageParser();
        trainStationArrayList = new ArrayList<TrainStation>();
        TrainStation station;

        for(int i = 0; i < listLength; i++) {
            station = formatting.createTrainStationForListPos(i,serverRawData);
            station = formatting.addStationDistance(station,myLat,myLong);
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
