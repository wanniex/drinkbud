package com.example.Drinkbud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class WaterAlarmActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView selection;
    Switch activate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_alarm);

        if (getIntent().hasExtra("something")) {
            TextView tv = (TextView) findViewById(R.id.textView);
            String text = getIntent().getExtras().getString("something");
            tv.setText(text);
        }

        radioGroup = findViewById(R.id.radioGroup);
        selection = findViewById(R.id.selection);

        activate = findViewById(R.id.activate);
        activate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (activate.isChecked()) {
                    int radioID = radioGroup.getCheckedRadioButtonId();
                    radioButton = findViewById(radioID);
                    selection.setText("Selected frequency: " + radioButton.getText());
                } else {
                    selection.setText("Alarm Deactivated");
                }
            }
        });


    }
}
