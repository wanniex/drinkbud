package com.example.Drinkbud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WaterLocatorOptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_locator_options);

        Button waterCoolerBtn = (Button) findViewById(R.id.waterCoolerBtn);

        Button vendingMachineBtn = (Button) findViewById(R.id.vendingMachineBtn);

        Button drinksStallBtn = (Button) findViewById(R.id.drinksStallBtn);
        drinksStallBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), DrinksStallOptionsActivity.class);
                startActivity(startIntent);
            }
        });
    }


}
