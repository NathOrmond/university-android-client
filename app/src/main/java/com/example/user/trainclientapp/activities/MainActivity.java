package com.example.user.trainclientapp.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.trainclientapp.R;

public class MainActivity extends AppCompatActivity {

    Button findStationBtn, changePermissions;
    TextView info;
    Boolean permissions = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        info =  (TextView) findViewById(R.id.info);
        findStationBtn = (Button) findViewById(R.id.stationButton);
        changePermissions = (Button) findViewById(R.id.changePermissionsInSettings);

            checkPermissions();



    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(!permissions) {
            checkPermissions();
        } else {
            startNearestStationList();
        }

    }

    private void checkPermissions()  {

           if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                   ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
               info.setText("Permissions must be granted or app cannot be used!");
               findStationBtn.setVisibility(View.INVISIBLE);
               changePermissions.setVisibility(View.VISIBLE);

               this.requestPermissions(new String[]{
                       Manifest.permission.ACCESS_FINE_LOCATION,
                       Manifest.permission.ACCESS_COARSE_LOCATION,
                       Manifest.permission.INTERNET,
               }, 10);

               return;
           } else {
               permissions = true;
               findStationBtn.setVisibility(View.VISIBLE);
               changePermissions.setVisibility(View.INVISIBLE);
               info.setText("\"Welcome to the train client app. This app requires " +
                       "permission to location services. Please accept any request " +
                       "for the app to have location services on this device. Click " +
                       "the button to proceed to the nearest station page. You can " +
                       "update your location any time by clicking the update button " +
                       "on the nearest stations screen. Selecting a station from the " +
                       "list will open up directions to the chosen station.\"");
           }


    }

    /**
     * When button is clicked starts nearest train station list activity
     */

    public void click(View v){
        if(permissions) {
            startNearestStationList();
        }
    }

    private void startNearestStationList(){
        Intent startIntent = new Intent(getApplicationContext(), NearestStationListActivity.class);
        startActivity(startIntent);
    }


    public void changePermissions(View view) {
        if(!permissions) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_SETTINGS);
            startActivity(intent);
        }
    }
}
