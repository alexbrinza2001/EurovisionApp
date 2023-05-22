package com.example.eurovisionapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        System.out.println("AICI!!!!!!");
        System.out.println("AICI!!!!!!");
        System.out.println("AICI!!!!!!");System.out.println("AICI!!!!!!");System.out.println("AICI!!!!!!");
        System.out.println("AICI!!!!!!");System.out.println("AICI!!!!!!");System.out.println("AICI!!!!!!");
        System.out.println("AICI!!!!!!");
        System.out.println("AICI!!!!!!");
        System.out.println("AICI!!!!!!");
        System.out.println("AICI!!!!!!");




        String channelID = "CHANNEL_ID_NOTIFICATION";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.getContext(), channelID);
        builder.setSmallIcon(R.drawable.baseline_circle_notifications_24);
        builder.setContentTitle("Niste")
                .setContentText("Niste notificare sau ceva")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent notificationIntent = new Intent(MainActivity.getContext(), NotificationActivity.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        notificationIntent.putExtra("data", "Habar n-am unde vine asta");

        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.getContext(), 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager)MainActivity.getService();

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


}
