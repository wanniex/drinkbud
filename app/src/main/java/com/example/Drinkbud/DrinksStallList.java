package com.example.Drinkbud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class DrinksStallList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks_stall_list);

        if (getIntent().hasExtra("firstOption")) {
            Button placesBtn1 = (Button) findViewById(R.id.placesBtn1);
            String text = getIntent().getExtras().getString("firstOption");
            placesBtn1.setText(text);
        }

        if (getIntent().hasExtra("secondOption")) {
            Button placesBtn2 = (Button) findViewById(R.id.placesBtn2);
            String text = getIntent().getExtras().getString("secondOption");
            placesBtn2.setText(text);
        }

    }
}
