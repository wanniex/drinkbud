package com.example.Drinkbud;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Button waterTrackerBtn;
    Button waterAlarm;
    Button waterLocatorBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        waterTrackerBtn = (Button) findViewById(R.id.waterTrackerBtn);
        waterTrackerBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), WaterTrackerActivity.class);
                startActivity(startIntent);
            }
        });

        waterAlarm = (Button) findViewById(R.id.waterAlarmBtn);
        waterAlarm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), WaterAlarmActivity.class);
                startIntent.putExtra("something", "Set Water Alarm To:");
                startActivity(startIntent);
            }
        });

        waterLocatorBtn = (Button) findViewById(R.id.waterLocatorBtn);
        waterLocatorBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), WaterLocatorOptionsActivity.class);
                startActivity(startIntent);
            }
        });

    }

}
