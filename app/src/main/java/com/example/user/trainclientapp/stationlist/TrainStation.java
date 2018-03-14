package com.example.user.trainclientapp.stationlist;

/**
 * Created by User on 06/03/2018.
 */

public class TrainStation {

    String stationName;
    Double stationLat, stationLong, distanceNum;

    public String getStationName(){
        return stationName;
    }


    // ---

    public Double getStationLat(){
        return stationLat;
    }

    public Double getStationLong(){
        return stationLong;
    }

    public Double getDistanceNum() {
        return distanceNum;
    }

    // ---

    public void setStationName(String stationName){
        this.stationName = stationName;
    }

    // ---

    public void  setStationLat(Double stationLat){
        this.stationLat = stationLat;
    }

    public void setStationLong(Double stationLong) {
        this.stationLong = stationLong;
    }

    public void setDistanceNum(Double distanceNum){
        this.distanceNum = distanceNum;
    }
}