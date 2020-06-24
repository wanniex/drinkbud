package com.example.Drinkbud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ChosenWaterPlace extends AppCompatActivity {

    ImageView imageView;
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_water_place);

        imageView = findViewById(R.id.waterPlacePic);
        textview = findViewById(R.id.desc);

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