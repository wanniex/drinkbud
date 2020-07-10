package com.example.Drinkbud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;


public class WaterTrackerActivity extends AppCompatActivity {
    Button waterPlantBtn;
    EditText waterAmountNum;
    TextView waterAmountResult;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    int lastDrankRef;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_tracker);

        waterAmountResult = (TextView) findViewById(R.id.waterAmountResult);
        waterAmountNum = (EditText) findViewById(R.id.waterAmountNum);

        id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        Date currentTime = Calendar.getInstance().getTime();
        final String formattedDate = DateFormat.getDateInstance().format(currentTime);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("userDatabase");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(id)) {
                    if (dataSnapshot.child(id).child("waterTracker").hasChild(formattedDate)) {
                        int lastDrank = Integer.parseInt(dataSnapshot.child("testUser").child("waterTracker").child(formattedDate).getValue().toString());
                        waterAmountResult.setText("You've drank " + lastDrank + " ml today");
                        lastDrankRef = lastDrank;
                    } else {
                        reference.child(id).child("waterTracker").child(formattedDate).setValue(Integer.toString(0));
                        waterAmountResult.setText("You've drank " + 0 + " ml today");
                        lastDrankRef = 0;
                    }
                } else {
                    reference.child(id).child("waterTracker").child(formattedDate).setValue(Integer.toString(0));
                    waterAmountResult.setText("You've drank " + 0 + " ml today");
                    lastDrankRef = 0;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        waterPlantBtn = (Button) findViewById(R.id.waterPlantBtn);
        waterPlantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                int num2 = Integer.parseInt(waterAmountNum.getText().toString());
                int currentDrank = lastDrankRef + num2;
                reference.child("testUser").child("waterTracker").child(formattedDate).setValue(Integer.toString(currentDrank));
                waterAmountResult.setText("You've drank " + currentDrank + " ml today");
            }
        });
    }


}
