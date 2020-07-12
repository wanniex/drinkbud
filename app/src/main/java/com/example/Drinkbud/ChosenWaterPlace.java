package com.example.Drinkbud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ChosenWaterPlace extends AppCompatActivity {

    ImageView imageView;
    TextView textview;
    Button drinkStallMenu;
    String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_water_place);

        imageView = findViewById(R.id.waterPlacePic);
        textview = findViewById(R.id.desc);
        drinkStallMenu = findViewById(R.id.drinksStallMenu);

        drinkStallMenu.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), DrinksStallMenuActivity.class);
                startIntent.putExtra("key", key);
                startActivity(startIntent);

            }
        });

        if (getIntent().hasExtra("choice")) {
            if (getIntent().getExtras().getString("choice").equals("Drink Stalls")) {
                drinkStallMenu.setVisibility(View.VISIBLE);
            }
        }

        if (getIntent().hasExtra("key1")) {
            key = getIntent().getExtras().getString("key1");
        }
        if (getIntent().hasExtra("key2")) {
            key = getIntent().getExtras().getString("key2");
        }


        if (getIntent().hasExtra("url1")) {
            Glide.with(this).load(getIntent().getExtras().getString("url1")).into(imageView);
        }

        if (getIntent().hasExtra("url2")) {
            Glide.with(this).load(getIntent().getExtras().getString("url2")).into(imageView);
        }

        if (getIntent().hasExtra("desc1")) {
            textview.setText(getIntent().getExtras().getString("desc1"));
        }

        if (getIntent().hasExtra("desc2")) {
            textview.setText(getIntent().getExtras().getString("desc2"));
        }
    }
}