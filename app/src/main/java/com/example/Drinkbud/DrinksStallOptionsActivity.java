package com.example.Drinkbud;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class DrinksStallOptionsActivity extends FragmentActivity implements OnMapReadyCallback {

    // ADDED THIS FOR CURRENT LOCATION
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;

    // ADDED THIS FOR TEXT ON BUTTONS
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    List<Details> distances = new ArrayList<>();
    String choice;

    List<Details> deets = new ArrayList<>();

    // LocationManager locationManager;
    // LocationListener locationListener;
    // LatLng userLatLong;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks_stall_options);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();

        // ADDED TO SHOW PENDING INTENT TEXT ON BUTTONS
        rootNode = FirebaseDatabase.getInstance();
        if (getIntent().hasExtra("waterCoolers")) {
            reference = rootNode.getReference("waterCoolers");
            choice = "Water Coolers";
        } else if (getIntent().hasExtra("vendingMachines")) {
            reference = rootNode.getReference("vendingMachines");
            choice = "Vending Machines";
        } else {
            reference = rootNode.getReference("drinkStalls");
            choice = "Drink Stalls";
        }

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                distances.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    double test1 = postSnapshot.child("lat").getValue(Double.class);
                    double test2 = postSnapshot.child("long").getValue(Double.class);
                    String name = postSnapshot.child("name").getValue(String.class);
                    String desc = postSnapshot.child("desc").getValue(String.class);
                    String url = postSnapshot.child("pic").getValue(String.class);


                    deets.add(new Details(test1, test2, name, desc, url));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        Button showMeBtn = (Button) findViewById(R.id.showMeBtn);
        showMeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), DrinksStallList.class);


                final double currentLat = 1.295025;
                final double currentLong = 103.773766;

                for (Details p: deets) {
                    if (distances.size() < 2) {
                        double calculatedDist = (int)(p.calcDist(currentLat, currentLong) * 1000) / 1000;
                        distances.add(new Details(calculatedDist, calculatedDist, p.getName(), p.getDesc(), p.getUrl()));
                    } else {
                        for (Details e: distances) {
                            double calculatedDist = (int)(p.calcDist(currentLat, currentLong)*1000) / 1000;
                            if (calculatedDist < e.getDist()) {
                                distances.remove(e);
                                distances.add(new Details(calculatedDist, calculatedDist, p.getName(), p.getDesc(), p.getUrl()));
                            }
                        }
                    }
                }

                Details first = distances.get(0);
                Details second = distances.get(1);
                startIntent.putExtra("firstOption", first.getName() + "\n" + first.getDesc() + "\n" + first.getDist() + " km away");
                startIntent.putExtra("firstUrl", first.getUrl() + "");
                startIntent.putExtra("firstKey", first.getName() + "");
                startIntent.putExtra("secondOption", second.getName() + "\n" + second.getDesc() + "\n" + second.getDist() + " km away");
                startIntent.putExtra("secondUrl", second.getUrl() + "");
                startIntent.putExtra("secondKey", second.getName() + "");
                startIntent.putExtra("choice", choice + "");
                startActivity(startIntent);
            }
        });
    }

    private void fetchLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
                    Toast.makeText(getApplicationContext(), currentLocation.getLatitude() + "" + currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();
                    SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    supportMapFragment.getMapAsync(DrinksStallOptionsActivity.this);
                }
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {


        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        // LatLng latLng = new LatLng(1.294876, 103.773803);
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("I am Here");
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
        googleMap.addMarker(markerOptions);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchLastLocation();
                }
                break;
        }

    }
}

class Details {
    double latitude;
    double longitude;
    String name;
    String desc;
    String url;

    public Details(double latitude, double longitude, String name, String desc, String url) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.desc = desc;
        this.url = url;
    }

    String getName() {
        return this.name;
    }

    String getDesc() {
        return this.desc;
    }

    String getUrl() {  return this.url; }

    double getDist() {
        return this.longitude;
    }

    public double calcDist(double currentLat, double currentLong) {
        double R = 6371; // Radius of the earth in km
        double locationLat = deg2rad(latitude - currentLat);
        double locationLong = deg2rad(longitude - currentLong);

        double a = Math.sin(locationLat/2) * Math.sin(locationLat/2) + Math.cos(deg2rad(currentLat)) * Math.cos(deg2rad(latitude)) * Math.sin(locationLong/2) * Math.sin(locationLong/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c;

        return d;
    }

    double deg2rad(double deg) {
        return deg * (Math.PI/180);
    }
}

