package com.example.eurovisionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    public Context getContext() {
        return context;
    }

   // public static int usersCount = 0;

    public static Boolean isLoggedIn = false;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        Button myTopsButton = findViewById(R.id.my_tops);

        myTopsButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MyTops.class)));

        ImageButton authButton = findViewById(R.id.authButton);

        //authButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LoginActivity.class)));

        authButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isLoggedIn) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MainActivity.this, ProfileSettingsActivity.class);
                    startActivity(intent);
                }
            }
        });

        Button videoButton = findViewById(R.id.results);

        videoButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, VideoActivity.class)));
        //-----------------------------

        EurovisionDatabase edb = new EurovisionDatabase(context);
        SQLiteDatabase database = edb.getWritableDatabase();

        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        double latitude = 53.3971;
        double longitude = -2.9865;
        LatLng location = new LatLng(latitude, longitude);

        MarkerOptions markerOptions = new MarkerOptions().position(location).title("M&S Bank Arena");
        googleMap.addMarker(markerOptions);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12));
    }
}