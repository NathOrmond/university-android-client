package com.example.user.trainclientapp;

import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
        String info = "Welcome to the train client app.  " +
                "Click the button to proceed to the nearest station page. " +
                "You can update your location any time by clicking " +
                "the update button on the nearest stations screen.";
        return info;
    }

    public void click(View v){
        Intent startIntent = new Intent(getApplicationContext(), NearestStationActivity.class);
    }



}
