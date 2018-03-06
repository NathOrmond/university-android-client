package stationList;

import geoLocation.DistanceCalculation;

/**
 * Created by User on 06/03/2018.
 */

public class TrainStation {

    String stationName, distance, inputString;
    int listPosition;
    Double distanceNum;

    DistanceCalculation calculator = new DistanceCalculation();

    public TrainStation(int listPosition){
        this.listPosition = listPosition;
    }

    public String getStationName(){
        return stationName;
    }

    public String getDistance(){
        distance = distanceNum.toString();
        return distance;
    }

    private void calculateDistance(){
        distanceNum = calculator.calculateDistance(inputString);
    }


}
