package com.example.user.trainclientapp.activities;

import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.trainclientapp.R;

public class MainActivity extends AppCompatActivity {

    LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button findStationBtn = (Button) findViewById(R.id.stationButton);
        TextView info = (TextView) findViewById(R.id.info);

        info.setText(info());

    }

    private String info(){
        String info =
                "Welcome to the train client app. " +
                "This app requires permission to location services." +
                "Please accept any request for the app to have location " +
                "services on this device." +
                "Click the button to proceed to the nearest station page. " +
                "You can update your location any time by clicking " +
                "the update button on the nearest stations screen." +
                "Selecting a station from the list will open up " +
                "directions to the chosen station.";
        return info;
    }

    public void click(View v){
        Intent startIntent = new Intent(getApplicationContext(), NearestStationActivity.class);
    }



}
