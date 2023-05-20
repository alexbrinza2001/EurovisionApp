package com.example.eurovisionapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Top2023 extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_2023);

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
        String[] selectionArgs = {"2023"};

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

        List<TextView> dragList = new ArrayList<>(Arrays.asList(linearLayout.findViewById(R.id.drag1), linearLayout.findViewById(R.id.drag2), linearLayout.findViewById(R.id.drag3), linearLayout.findViewById(R.id.drag4), linearLayout.findViewById(R.id.drag5), linearLayout.findViewById(R.id.drag6), linearLayout.findViewById(R.id.drag7), linearLayout.findViewById(R.id.drag8), linearLayout.findViewById(R.id.drag9), linearLayout.findViewById(R.id.drag10), linearLayout.findViewById(R.id.drag11), linearLayout.findViewById(R.id.drag12), linearLayout.findViewById(R.id.drag13), linearLayout.findViewById(R.id.drag14), linearLayout.findViewById(R.id.drag15), linearLayout.findViewById(R.id.drag16), linearLayout.findViewById(R.id.drag17), linearLayout.findViewById(R.id.drag18), linearLayout.findViewById(R.id.drag19), linearLayout.findViewById(R.id.drag20), linearLayout.findViewById(R.id.drag21), linearLayout.findViewById(R.id.drag22), linearLayout.findViewById(R.id.drag23), linearLayout.findViewById(R.id.drag24), linearLayout.findViewById(R.id.drag25), linearLayout.findViewById(R.id.drag26), linearLayout.findViewById(R.id.drag27), linearLayout.findViewById(R.id.drag28), linearLayout.findViewById(R.id.drag29), linearLayout.findViewById(R.id.drag30), linearLayout.findViewById(R.id.drag31), linearLayout.findViewById(R.id.drag32), linearLayout.findViewById(R.id.drag33), linearLayout.findViewById(R.id.drag34), linearLayout.findViewById(R.id.drag35), linearLayout.findViewById(R.id.drag36), linearLayout.findViewById(R.id.drag37)));

        for (int i = 0; i < dataList.size(); i++) {
            TextView view = dragList.get(i);
            view.setOnLongClickListener(new MyLongClickListener());
            view.setOnDragListener(new MyDragListener());

            view.setText(dataList.get(i).getCountryCode() + " " + dataList.get(i).getCountry() + " " + dataList.get(i).getArtist() + " " + dataList.get(i).getSong());
        }
    }
}
