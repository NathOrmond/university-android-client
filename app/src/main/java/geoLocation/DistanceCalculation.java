package geoLocation;

/**
 * Created by Nathan on 06/03/2018.
 */

public class DistanceCalculation {


    Double distance, myLat, myLong, stationLat, stationLong;
    public static final Double R = 6372.8; //kilometers

    public Double calculateDistance(String inputString) {
        getMyLat(inputString);
        getMyLong(inputString);
        getStationLat(inputString);
        getStationLong(inputString);
        Haversine(myLat, myLong, stationLat, stationLong);
        return distance;
    }

    private void getMyLat(String input) {
    }

    private void getMyLong(String input) {
    }

    private void getStationLat(String input) {
    }

    private void getStationLong(String input) {
    }

    private void Haversine(double lat1, double lon1, double lat2, double lon2){
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2);
        double b = 2 * Math.asin(Math.sqrt(a));
        distance = R * b;
    }




}
