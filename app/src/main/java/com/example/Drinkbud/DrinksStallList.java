package com.example.Drinkbud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DrinksStallList extends AppCompatActivity {

    Button placesBtn1;
    Button placesBtn2;

    String desc1;
    String desc2;

    String url1;
    String url2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks_stall_list);

        if (getIntent().hasExtra("firstOption")) {
            placesBtn1 = (Button) findViewById(R.id.placesBtn1);
            desc1 = getIntent().getExtras().getString("firstOption");
            placesBtn1.setText(desc1);
        }

        if (getIntent().hasExtra("secondOption")) {
            placesBtn2 = (Button) findViewById(R.id.placesBtn2);
            desc2 = getIntent().getExtras().getString("secondOption");
            placesBtn2.setText(desc2);
        }

        if (getIntent().hasExtra("firstUrl")) {
            url1 = getIntent().getExtras().getString("firstUrl");
        }

        if (getIntent().hasExtra("secondUrl")) {
            url2 = getIntent().getExtras().getString("secondUrl");
        }

        placesBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ChosenWaterPlace.class);
                startIntent.putExtra("desc1", desc1);
                startIntent.putExtra("url1", url1);
                startActivity(startIntent);
            }
        });

        placesBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ChosenWaterPlace.class);
                startIntent.putExtra("desc2", desc2);
                startIntent.putExtra("url2", url2);
                startActivity(startIntent);
            }
        });

    }
}
