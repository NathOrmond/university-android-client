package com.example.user.trainclientapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.trainclientapp.R;
import com.example.user.trainclientapp.geolocation.MyGPS;

public class StartAppActivity extends AppCompatActivity {

    Button findStationBtn, changePermissions;
    TextView info;
    MyGPS myGPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_app_activity);

        info =  (TextView) findViewById(R.id.info);
        findStationBtn = (Button) findViewById(R.id.stationButton);
        changePermissions = (Button) findViewById(R.id.changePermissionsInSettings);

            checkPermissions();

    }


    @Override
    protected void onResume() {
        super.onResume();
        checkPermissions();

    }

    /**
     * checks whether location permissions have been granted or not
     * to application, if they have displays text and button to move to next activity,
     * if not warns user app needs permissions and displays button taking user to
     * setting to alter permissions.
     *
     * @return permissionsGranted
     */
    private boolean checkPermissions()  {
            myGPS = new MyGPS(this);

           if (!myGPS.checkForPermissions()) {
               info.setText("Permissions must be granted or app cannot be used!");
               findStationBtn.setVisibility(View.INVISIBLE);
               changePermissions.setVisibility(View.VISIBLE);



               return false;
           } else {
               findStationBtn.setVisibility(View.VISIBLE);
               changePermissions.setVisibility(View.INVISIBLE);
               info.setText("\"Welcome to the train client app. This app requires " +
                       "permission to location services. Please accept any request " +
                       "for the app to have location services on this device. Click " +
                       "the button below to proceed to the nearest station page. You can " +
                       "update your location any time by clicking the update button " +
                       "on the nearest stations screen. Selecting a station from the " +
                       "list will open up directions to the chosen station from your current location" +
                       "on the map.\"");
               return true;
           }
    }

    /**
     * When button is clicked starts nearest train station list activity
     */

    public void click(View v){
        if(checkPermissions()) {
            startNearestStationList();
        }
    }

    /**
     * Starts nearest station list activity
     */
    private void startNearestStationList(){
        Intent startIntent = new Intent(getApplicationContext(), NearestStationListActivity.class);
        startActivity(startIntent);
    }

    /**
     * Opens new activity in app settings if
     * button is clicked for changing permissions
     * @param view
     */
    public void changePermissions(View view) {
        if(!checkPermissions()) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_SETTINGS);
            startActivity(intent);
        }
    }
}
