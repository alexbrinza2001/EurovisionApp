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

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static Context getContext() {
        return context;
    }
    public static Object getService() {
        return service;
    }

   // public static int usersCount = 0;

    public static Boolean isLoggedIn = false;

    private static Context context;
    private MaterialTimePicker picker;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private static Object service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        Button myTopsButton = findViewById(R.id.my_tops);

        myTopsButton.setBackgroundColor(ContextCompat.getColor(this, R.color.button_color2));

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
        videoButton.setBackgroundColor(ContextCompat.getColor(this, R.color.button_color2));

        videoButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, VideoActivity.class)));
        //-----------------------------

        EurovisionDatabase edb = new EurovisionDatabase(context);
        SQLiteDatabase database = edb.getWritableDatabase();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            if (!Manifest.permission.POST_NOTIFICATIONS.equals(String.valueOf(PackageManager.PERMISSION_GRANTED))) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.POST_NOTIFICATIONS}, 101);
            }
        }

        Button newsButton = findViewById(R.id.news);
        newsButton.setOnClickListener(view -> makeNotification());

        newsButton.setBackgroundColor(ContextCompat.getColor(this, R.color.button_color2));

        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,18);
        calendar.set(Calendar.MINUTE,41);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);

        setAlarm();

        service = getSystemService(Context.NOTIFICATION_SERVICE);

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

    public void makeNotification() {
        String channelID = "CHANNEL_ID_NOTIFICATION";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelID);
        builder.setSmallIcon(R.drawable.baseline_circle_notifications_24);
        builder.setContentTitle("Niste")
                .setContentText("Niste notificare sau ceva")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent intent = new Intent(getApplicationContext(), NotificationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("data", "Habar n-am unde vine asta");

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_MUTABLE);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = notificationManager.getNotificationChannel(channelID);
            if (notificationChannel == null) {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                notificationChannel = new NotificationChannel(channelID, "Description", importance);
                notificationChannel.setLightColor(Color.GREEN);
                notificationChannel.enableVibration(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        notificationManager.notify(0, builder.build());
    }

    private void cancelAlarm() {

        Intent intent = new Intent(this,AlarmReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

        if (alarmManager == null){

            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        }

        alarmManager.cancel(pendingIntent);
        System.out.println("Alarm cancelled!");
    }

    private void setAlarm() {

        System.out.println("Am intrat in functieee");

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this,AlarmReceiver.class);

        intent.setAction("com.example.eurovisionapp.ALARM_TRIGGERED");

        pendingIntent = PendingIntent.getBroadcast(this,0,intent,PendingIntent.FLAG_IMMUTABLE);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_FIFTEEN_MINUTES,pendingIntent);

        System.out.println("Alarm set successfully!");



    }

}