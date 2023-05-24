package com.example.eurovisionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.Manifest;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    public static Context getContext() {
        return context;
    }
    public static Object getService() {
        return service;
    }
    public static Boolean isLoggedIn = false;
    private static Context context;
    private static Object service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        service = getSystemService(Context.NOTIFICATION_SERVICE);

        Button myTopsButton = findViewById(R.id.my_tops);

        myTopsButton.setBackgroundColor(ContextCompat.getColor(this, R.color.button_color2));

        myTopsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isLoggedIn) {
                    Toast.makeText(MainActivity.this, "Please login first!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, Top2023.class);
                    startActivity(intent);
                }
            }
        });

        ImageButton authButton = findViewById(R.id.authButton);

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

        Button resultsButton = findViewById(R.id.results);
        resultsButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ResultsActivity.class)));

        EurovisionDatabase edb = new EurovisionDatabase(context);
        SQLiteDatabase database = edb.getWritableDatabase();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            if (!Manifest.permission.POST_NOTIFICATIONS.equals(String.valueOf(PackageManager.PERMISSION_GRANTED))) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.POST_NOTIFICATIONS}, 101);
            }
        }

        Button locations = findViewById(R.id.locations);

        locations.setBackgroundColor(ContextCompat.getColor(this, R.color.button_color2));
        locations.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, LocationsActivity.class)));

        Button topButton = findViewById(R.id.top);
        topButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, OverallActivity.class)));

        Intent serviceIntent = new Intent(this, BackgroundMusicService.class);
        startService(serviceIntent);

        Button oddsButton = findViewById(R.id.odds);
        oddsButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, OddsActivity.class)));
    }

}