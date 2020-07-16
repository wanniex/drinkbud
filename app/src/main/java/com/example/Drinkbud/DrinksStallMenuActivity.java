package com.example.Drinkbud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DrinksStallMenuActivity extends AppCompatActivity {

    TextView header;

    TableLayout menuTable;

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
        //header.setText("" + key);

        menuTable = findViewById(R.id.menuTable);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("drinkStalls");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    if (postSnapshot.child("name").getValue(String.class).equals(key)) {
                        DataSnapshot second = postSnapshot.child("menu");
                        String numberOfChildren = "" + postSnapshot.child("menu").getChildrenCount();
                        for (int i = 0; i < Integer.parseInt(numberOfChildren); i++) {
                            String number = Integer.toString(i);
                            String name1 = second.child(number).child("name").getValue(String.class);
                            String price1 = second.child(number).child("price").getValue(String.class);
                            String calories1 = second.child(number).child("calories").getValue(String.class);
                            itemName.add("hi");
                            itemPrice.add("hi");
                            itemCalories.add("hi");
                        }

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        itemName.add("hi");
        itemPrice.add("hi");
        itemCalories.add("hi");

        // add data to table
        for(int i = 0; i < itemName.size(); i++) {
            TableRow row = new TableRow(this);
            String name = itemName.get(i);
            String price = itemPrice.get(i);
            String calories = itemCalories.get(i);
            TextView drinkName = new TextView(this);
            drinkName.setText(""+ name);
            TextView drinkPrice = new TextView(this);
            drinkName.setText(""+ price);
            TextView drinkCalories = new TextView(this);
            drinkName.setText(""+ calories);
            row.addView(drinkName);
            row.addView(drinkPrice);
            row.addView(drinkCalories);
            menuTable.addView(row);
        }

        header.setText(itemName.size() + "" + "\n");

        /*TableRow row = new TableRow(this);
        TextView name1 = new TextView(this);
        name1.setText("hello");
        TextView name2 = new TextView(this);
        name2.setText("hello");
        TextView name3 = new TextView(this);
        name3.setText("hello");
        row.addView(name1);
        row.addView(name2);
        row.addView(name3);
        menuTable.addView(row); */

    }
}