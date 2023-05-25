package com.example.eurovisionapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class Top2022 extends AppCompatActivity {

    @Inject
    Notification notification;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_2022);

        TextView drag1 = findViewById(R.id.drag1);
        drag1.setOnLongClickListener(new MyLongClickListener());
        drag1.setOnDragListener(new MyDragListener());

        TextView drag2 = findViewById(R.id.drag2);
        drag2.setOnLongClickListener(new MyLongClickListener());
        drag2.setOnDragListener(new MyDragListener());

        TextView drop = findViewById(R.id.drop);
        drop.setOnDragListener(new MyDragListener());

        TextView drop2 = findViewById(R.id.drop2);
        drop2.setOnDragListener(new MyDragListener());

        TextView drop3 = findViewById(R.id.drop3);
        drop3.setOnDragListener(new MyDragListener());

        TextView drop4 = findViewById(R.id.drop4);
        drop4.setOnDragListener(new MyDragListener());

        TextView drop5 = findViewById(R.id.drop5);
        drop5.setOnDragListener(new MyDragListener());

        TextView drop6 = findViewById(R.id.drop6);
        drop6.setOnDragListener(new MyDragListener());

        TextView drop7 = findViewById(R.id.drop7);
        drop7.setOnDragListener(new MyDragListener());

        TextView drop8 = findViewById(R.id.drop8);
        drop8.setOnDragListener(new MyDragListener());

        TextView drop9 = findViewById(R.id.drop9);
        drop9.setOnDragListener(new MyDragListener());

        TextView drop10 = findViewById(R.id.drop10);
        drop10.setOnDragListener(new MyDragListener());

        Context context = this;
        EurovisionDatabase eurovisionDatabase = new EurovisionDatabase(context);
        SQLiteDatabase db = eurovisionDatabase.getReadableDatabase();
        String[] projection = {"year", "countryCode", "country", "artist", "song"};
        String selection = "year" + " = ?";
        String[] selectionArgs = {"2022"};

        @SuppressLint("Recycle") Cursor cursor = db.query("song_info", projection, selection, selectionArgs, null, null, null);

        List<Song> dataList = new ArrayList<Song>();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String countryCode = cursor.getString(cursor.getColumnIndexOrThrow("countryCode"));
                String country = cursor.getString(cursor.getColumnIndexOrThrow("country"));
                String artist = cursor.getString(cursor.getColumnIndexOrThrow("artist"));
                String song = cursor.getString(cursor.getColumnIndexOrThrow("song"));
                int year = cursor.getInt(cursor.getColumnIndexOrThrow("year"));

                Song item = new Song();

                item.setArtist(artist);
                item.setCountry(country);
                item.setYear(year);
                item.setCountryCode(countryCode);
                item.setSong(song);

                dataList.add(item);

            } while (cursor.moveToNext());
        }

        LinearLayout linearLayout = findViewById(R.id.scroll_linear);

        List<TextView> dragList = new ArrayList<>(Arrays.asList(linearLayout.findViewById(R.id.drag1), linearLayout.findViewById(R.id.drag2), linearLayout.findViewById(R.id.drag3), linearLayout.findViewById(R.id.drag4), linearLayout.findViewById(R.id.drag5), linearLayout.findViewById(R.id.drag6), linearLayout.findViewById(R.id.drag7), linearLayout.findViewById(R.id.drag8), linearLayout.findViewById(R.id.drag9), linearLayout.findViewById(R.id.drag10), linearLayout.findViewById(R.id.drag11), linearLayout.findViewById(R.id.drag12), linearLayout.findViewById(R.id.drag13), linearLayout.findViewById(R.id.drag14), linearLayout.findViewById(R.id.drag15), linearLayout.findViewById(R.id.drag16), linearLayout.findViewById(R.id.drag17), linearLayout.findViewById(R.id.drag18), linearLayout.findViewById(R.id.drag19), linearLayout.findViewById(R.id.drag20), linearLayout.findViewById(R.id.drag21), linearLayout.findViewById(R.id.drag22), linearLayout.findViewById(R.id.drag23), linearLayout.findViewById(R.id.drag24), linearLayout.findViewById(R.id.drag25), linearLayout.findViewById(R.id.drag26), linearLayout.findViewById(R.id.drag27), linearLayout.findViewById(R.id.drag28), linearLayout.findViewById(R.id.drag29), linearLayout.findViewById(R.id.drag30), linearLayout.findViewById(R.id.drag31), linearLayout.findViewById(R.id.drag32), linearLayout.findViewById(R.id.drag33), linearLayout.findViewById(R.id.drag34), linearLayout.findViewById(R.id.drag35), linearLayout.findViewById(R.id.drag36), linearLayout.findViewById(R.id.drag37), linearLayout.findViewById(R.id.drag38), linearLayout.findViewById(R.id.drag39), linearLayout.findViewById(R.id.drag40)));

        for (int i = 0; i < dataList.size(); i++) {
            TextView view = dragList.get(i);
            view.setOnLongClickListener(new MyLongClickListener());
            view.setOnDragListener(new MyDragListener());

            view.setText(dataList.get(i).getCountryCode() + " - " + dataList.get(i).getCountry() + " - " + dataList.get(i).getArtist() + " - " + dataList.get(i).getSong());
        }

        if (MainActivity.isLoggedIn) {
            SharedPreferences preferences = getSharedPreferences("USER_INFO", 0);
            if (preferences.contains("1_2022_" + LoginActivity.currentId)) {
                drop.setText(preferences.getString("1_2022_" + LoginActivity.currentId, ""));
                drop2.setText(preferences.getString("2_2022_" + LoginActivity.currentId, ""));
                drop3.setText(preferences.getString("3_2022_" + LoginActivity.currentId, ""));
                drop4.setText(preferences.getString("4_2022_" + LoginActivity.currentId, ""));
                drop5.setText(preferences.getString("5_2022_" + LoginActivity.currentId, ""));
                drop6.setText(preferences.getString("6_2022_" + LoginActivity.currentId, ""));
                drop7.setText(preferences.getString("7_2022_" + LoginActivity.currentId, ""));
                drop8.setText(preferences.getString("8_2022_" + LoginActivity.currentId, ""));
                drop9.setText(preferences.getString("9_2022_" + LoginActivity.currentId, ""));
                drop10.setText(preferences.getString("10_2022_" + LoginActivity.currentId, ""));
            }
        }

        Button backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Top2022.this, MyTops.class);
                startActivity(intent);
            }
        });

        NotificationComponent component = DaggerNotificationComponent.create();
        component.inject(this);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.isLoggedIn) {
                    int id = LoginActivity.currentId;

                    boolean allCompleted = true;

                    if (drop.getText().equals("12p")) allCompleted = false;
                    if (drop2.getText().equals("10p")) allCompleted = false;
                    if (drop3.getText().equals("8p")) allCompleted = false;
                    if (drop4.getText().equals("7p")) allCompleted = false;
                    if (drop5.getText().equals("6p")) allCompleted = false;
                    if (drop6.getText().equals("5p")) allCompleted = false;
                    if (drop7.getText().equals("4p")) allCompleted = false;
                    if (drop8.getText().equals("3p")) allCompleted = false;
                    if (drop9.getText().equals("2p")) allCompleted = false;
                    if (drop10.getText().equals("1p")) allCompleted = false;

                    if (!allCompleted) Toast.makeText(getApplicationContext(), "You didn't choose a country for every position!", Toast.LENGTH_SHORT).show();
                    else {
                        List<String> textList = new ArrayList<>(Arrays.asList(drop.getText().toString(), drop2.getText().toString(), drop3.getText().toString(), drop4.getText().toString(), drop5.getText().toString(), drop6.getText().toString(), drop7.getText().toString(), drop8.getText().toString(), drop9.getText().toString(), drop10.getText().toString()));

                        boolean allDifferent = true;

                        for (int i = 0; i < 10; i++) {
                            for (int j = i + 1; j < 10; j++)
                                if (textList.get(i).equals(textList.get(j))) {
                                    allDifferent = false;
                                    break;
                                }
                        }

                        if (!allDifferent) Toast.makeText(getApplicationContext(), "You can't have the same country in 2 positions!", Toast.LENGTH_SHORT).show();
                        else {
                            SharedPreferences.Editor editor = getSharedPreferences("USER_INFO", 0).edit();
                            editor.putString("1_2022_" + id, textList.get(0));
                            editor.putString("2_2022_" + id, textList.get(1));
                            editor.putString("3_2022_" + id, textList.get(2));
                            editor.putString("4_2022_" + id, textList.get(3));
                            editor.putString("5_2022_" + id, textList.get(4));
                            editor.putString("6_2022_" + id, textList.get(5));
                            editor.putString("7_2022_" + id, textList.get(6));
                            editor.putString("8_2022_" + id, textList.get(7));
                            editor.putString("9_2022_" + id, textList.get(8));
                            editor.putString("10_2022_" + id, textList.get(9));
                            editor.apply();

                            MainActivity.whichYear = 2022;

                            notification.makeNotification("Top successfully updated!", "Go check the updated overall standings!");

                        }

                    }

                }
            }
        });
    }
}
