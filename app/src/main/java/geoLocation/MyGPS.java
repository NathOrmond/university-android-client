package geoLocation;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.example.user.trainclientapp.MainActivity;

/**
 * Created by User on 06/03/2018.
 */

public class MyGPS {
    Double myLat, myLong;
    LocationManager locationManager;

    public MyGPS(){
    }

    public MyGPS(LocationManager lm){
        this.locationManager = lm;
    }

    public void requestPermissions(MainActivity main) {
        ActivityCompat.requestPermissions(main, new String[]{
                "Android.Permission.ACCESS_FINE_LOCATION",
                "Android.Permission.ACCESS_COARSE_LOCATION"
        },1);
    }

    private void getLocation() {
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {
                }

                @Override
                public void onProviderEnabled(String s) {
                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

}
