package com.example.user.trainclientapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
        && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, new String[]{
                    "Android.Permission.ACCESS_FINE_LOCATION",
                    "Android.Permission.ACCESS_COARSE_LOCATION"
            },1);

        }

        Button findStationBtn = (Button) findViewById(R.id.stationButton);
    }

    public void click(View v){
        Intent startIntent = new Intent(getApplicationContext(), NearestStationActivity.class);
    }

}
