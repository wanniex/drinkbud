package com.example.Drinkbud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DrinksStallMenuActivity extends AppCompatActivity {

    TextView header;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    String key;

    ArrayList<String> itemName = new ArrayList<>();
    ArrayList<String> itemPrice = new ArrayList<>();
    ArrayList<String> itemCalories = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks_stall_menu);

        if (getIntent().hasExtra("key")) {
            key = getIntent().getExtras().getString("key");
        }

        header = findViewById(R.id.header);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("drinkStalls");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    if (postSnapshot.child("name").toString().equals(key)) {
                        for (DataSnapshot second: postSnapshot.child("name").child("menu").getChildren()) {

                        }

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}