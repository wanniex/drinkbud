package com.example.Drinkbud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WaterLocatorOptionsActivity extends AppCompatActivity {
    Button waterCoolerBtn;
    Button vendingMachineBtn;
    Button drinksStallBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_locator_options);

        waterCoolerBtn = (Button) findViewById(R.id.waterCoolerBtn);
        waterCoolerBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), DrinksStallOptionsActivity.class);
                startIntent.putExtra("waterCoolers", "waterCoolers");
                startActivity(startIntent);
            }
        });

        vendingMachineBtn = (Button) findViewById(R.id.vendingMachineBtn);
        vendingMachineBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), DrinksStallOptionsActivity.class);
                startIntent.putExtra("vendingMachines", "vendingMachines");
                startActivity(startIntent);
            }
        });

        drinksStallBtn = (Button) findViewById(R.id.drinksStallBtn);
        drinksStallBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), DrinksStallOptionsActivity.class);
                startIntent.putExtra("drinkStalls", "drinkStalls");
                startActivity(startIntent);
            }
        });
    }


}
