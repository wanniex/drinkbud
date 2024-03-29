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

        header = (TextView) findViewById(R.id.header);

        menuTable = (TableLayout) findViewById(R.id.menuTable);

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
                            itemName.add(name1);
                            itemPrice.add(price1);
                            itemCalories.add(calories1);

                        }

                    }
                }

                for(int i = 1; i < itemName.size(); i++) {
                    TableRow row = new TableRow(getBaseContext());
                    String name = itemName.get(i);
                    String price = itemPrice.get(i);
                    String calories = itemCalories.get(i);
                    TextView drinkName = new TextView(getBaseContext());
                    drinkName.setText(""+ name);
                    TextView drinkPrice = new TextView(getBaseContext());
                    drinkPrice.setText(""+ price);
                    TextView drinkCalories = new TextView(getBaseContext());
                    drinkCalories.setText(""+ calories);
                    row.addView(drinkName);
                    row.addView(drinkPrice);
                    row.addView(drinkCalories);

                    menuTable.addView(row);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}