package com.example.eurovisionapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EurovisionDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "eurovision_database";
    private static final int DATABASE_VERSION = 1;
    private Context context;

    public EurovisionDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("DROP TABLE IF EXISTS contest_info");

        String createTableQuery = "CREATE TABLE contest_info (id INTEGER PRIMARY KEY, image BLOB, year INTEGER, city TEXT)";
        db.execSQL(createTableQuery);

        Resources resources = context.getResources();
        @SuppressLint("UseCompatLoadingForDrawables") Drawable drawable2023 = resources.getDrawable(R.drawable.euroukraine);
        ByteArrayOutputStream byteArrayOutputStream2023 = new ByteArrayOutputStream();

        Bitmap bitmap2023 = ((BitmapDrawable)drawable2023).getBitmap();
        bitmap2023.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream2023);
        byte[] imageData2023 = byteArrayOutputStream2023.toByteArray();

        ContentValues values2023= new ContentValues();
        values2023.put("image", imageData2023);
        values2023.put("year", 2023);
        values2023.put("city", "Liverpool");
        long newRowId2023 = db.insert("contest_info", null, values2023);

        @SuppressLint("UseCompatLoadingForDrawables") Drawable drawable2022 = resources.getDrawable(R.drawable.euroitaly);
        ByteArrayOutputStream byteArrayOutputStream2022 = new ByteArrayOutputStream();

        Bitmap bitmap2022 = ((BitmapDrawable)drawable2022).getBitmap();
        bitmap2022.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream2022);
        byte[] imageData2022 = byteArrayOutputStream2022.toByteArray();

        ContentValues values2022 = new ContentValues();
        values2022.put("image", imageData2022);
        values2022.put("year", 2022);
        values2022.put("city", "Turin");
        long newRowId2022 = db.insert("contest_info", null, values2022);

        //------------------------------------------------------------------------------------------

        createTableQuery = "CREATE TABLE song_info (id INTEGER PRIMARY KEY, year INTEGER, countryCode TEXT, country TEXT, artist TEXT, song TEXT)";
        db.execSQL(createTableQuery);

        List<String> countries2023 = new ArrayList<>(Arrays.asList("Albania","Armenia","Australia","Austria","Azerbaijan","Belgium","Croatia","Cyprus","Czech Republic","Denmark","Estonia","Finland","France","Georgia","Germany","Greece","Iceland","Ireland","Israel","Italy","Latvia","Lithuania","Malta","Moldova","Netherlands","Norway","Poland","Portugal","Romania","San Marino","Serbia","Slovenia","Spain","Sweden","Switzerland","Ukraine","United Kingdom"));
        List<String> countryCodes2023 = new ArrayList<>(Arrays.asList("AL", "AM", "AU", "AT", "AZ", "BE", "HR", "CY", "CZ", "DK", "EE", "FI", "FR", "GE", "DE", "GR", "IS", "IE", "IL", "IT", "LV", "LT", "MT", "MD", "NL", "NO", "PL", "PT", "RO", "SM", "RS", "SI", "ES", "SE", "CH", "UA", "GB"));
        List<String> artists2023 = new ArrayList<>(Arrays.asList("Albina & Familja Kelmendi","Brunette","Voyager","Teya & Salena","TuralTuranX","Gustaph","Let 3","Andrew Lambrou","Vesna","Reiley","Alika","Käärijä","La Zarra","Iru","Lord of the Lost","Victor Vernicos","Diljá","Wild Youth","Noa Kirel","Marco Mengoni","Sudden Lights","Monika Linkytė","The Busker","Pasha Parfeni","Mia Nicolai & Dion Cooper","Alessandra","Blanka","Mimicat","Theodor Andrei","Piqued Jacks","Luke Black","Joker Out","Blanca Paloma","Loreen","Remo Forrer","TVORCHI","Mae Muller"));
        List<String> songs2023 = new ArrayList<>(Arrays.asList("Duje","Future Lover","Promise","Who The Hell Is Edgar?","Tell Me More","Because Of You","Mama ŠČ!","Break A Broken Heart","My Sister's Crown","Breaking My Heart","Bridges","Cha Cha Cha","Évidemment","Echo","Blood & Glitter","What They Say","Power","We Are One","Unicorn","Due Vite","Aijā","Stay","Dance (Our Own Party)","Soarele şi Luna","Burning Daylight","Queen of Kings","Solo","Ai Coração","D.G.T. (Off and On)","Like An Animal","Samo Mi Se Spava","Carpe Diem","Eaea","Tattoo","Watergun","Heart Of Steel","I Wrote A Song"));

        ContentValues songList2023= new ContentValues();

        for (int i = 0; i < countries2023.size(); i++) {

            songList2023.put("year", 2023);
            songList2023.put("countryCode", countryCodes2023.get(i));
            songList2023.put("country", countries2023.get(i));
            songList2023.put("artist", artists2023.get(i));
            songList2023.put("song", songs2023.get(i));
            db.insert("song_info", null, songList2023);

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
