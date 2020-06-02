package com.example.Drinkbud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;

public class WaterAlarmActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView selection;
    Switch activate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_alarm);

        createNotificationChannels();

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

                Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
                intent.setAction("action set");
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                if (activate.isChecked()) {
                    // delete this
                    /*calendar.set(Calendar.HOUR_OF_DAY, 4);
                    calendar.set(Calendar.MINUTE, 19);
                    calendar.set(Calendar.SECOND, 30);
                    Calendar calendar = Calendar.getInstance(); */


                    // need to change interval according to selection (in 3rd argument)
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime(), 1*60*1000, pendingIntent);

                    int radioID = radioGroup.getCheckedRadioButtonId();
                    radioButton = findViewById(radioID);
                    selection.setText("Selected frequency: " + radioButton.getText());

                } else {
                    selection.setText("Alarm Deactivated");

                    // TO CANCEL ALARM WHEN SWITCH IS DEACTIVATED
                    alarmManager.cancel(pendingIntent);

                }
            }
        });


    }


    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    "CHANNEL_1_ID",
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH);
            channel1.setDescription("This is Channel 1");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
        }
    }
}
