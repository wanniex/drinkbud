package com.example.Drinkbud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class WaterTrackerActivity extends AppCompatActivity {
    static int num1 = 0; // changed this to static

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_tracker);

        Button waterPlantBtn = (Button) findViewById(R.id.waterPlantBtn);
        waterPlantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText waterAmountNum = (EditText) findViewById(R.id.waterAmountNum);
                TextView waterAmountResult = (TextView) findViewById(R.id.waterAmountResult);

                int num2 = Integer.parseInt(waterAmountNum.getText().toString());
                num1 += num2;
                waterAmountResult.setText("You've drank " + num1 + " ml today");
            }
        });
    }


}
