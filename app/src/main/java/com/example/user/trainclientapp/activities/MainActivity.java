package com.example.user.trainclientapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.trainclientapp.R;

public class MainActivity extends AppCompatActivity {

    Button findStationBtn;
    TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findStationBtn = (Button) findViewById(R.id.stationButton);
        info = (TextView) findViewById(R.id.info);

    }

    /**
     * When button is clicked starts nearest train station list activity
     */

    public void click(View v){
        Intent startIntent = new Intent(getApplicationContext(), NearestStationListActivity.class);
        startActivity(startIntent);
    }



}
