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

        @SuppressLint("UseCompatLoadingForDrawables") Drawable drawable2021 = resources.getDrawable(R.drawable.euronetherlands);
        ByteArrayOutputStream byteArrayOutputStream2021 = new ByteArrayOutputStream();

        Bitmap bitmap2021 = ((BitmapDrawable)drawable2021).getBitmap();
        bitmap2021.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream2021);
        byte[] imageData2021 = byteArrayOutputStream2021.toByteArray();

        ContentValues values2021 = new ContentValues();
        values2021.put("image", imageData2021);
        values2021.put("year", 2021);
        values2021.put("city", "Rotterdam");
        long newRowId2021 = db.insert("contest_info", null, values2021);

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

        //-------------------------------------------------------------

        List<String> countries2022 = new ArrayList<>(Arrays.asList("Albania", "Armenia", "Australia", "Austria", "Azerbaijan", "Belgium", "Bulgaria", "Croatia", "Cyprus", "Czechia", "Denmark", "Estonia", "Finland", "France", "Georgia", "Germany", "Greece", "Iceland", "Ireland", "Israel", "Italy", "Latvia", "Lithuania", "Malta", "Moldova", "Montenegro", "Netherlands", "North Macedonia", "Norway", "Poland", "Portugal", "Romania", "San Marino", "Serbia", "Slovenia", "Spain", "Sweden", "Switzerland", "Ukraine", "United Kingdom" ));
        List<String> countryCodes2022 = new ArrayList<>(Arrays.asList("AL", "AM", "AU", "AT", "AZ", "BE", "BG", "HR", "CY", "CZ", "DK", "EE", "FI", "FR", "GE", "DE", "GR", "IS", "IE", "IL", "IT", "LV", "LT", "MT", "MD", "ME", "NL", "MK", "NO", "PL", "PT", "RO", "SM", "RS", "SI", "ES", "SE", "CH", "UA", "GB"));
        List<String> artists2022 = new ArrayList<>(Arrays.asList("Ronela Hajati", "Rosa Linn", "Sheldon Riley", "LUM!X & Pia Maria", "Nadir Rustamli", "Jérémie Makiese", "Intelligent Music Project", "Mia Dimšić", "Andromache", "We Are Domi", "Reddi", "Stefan", "The Rasmus", "Alvan & Ahez", "Circus Mircus", "Malik Harris", "Amanda Georgiadi Tenfjord", "Systur", "Brooke Scullion", "Michael Ben David", "Mahmood & Blanco", "Citi Zēni", "Monika Liu", "Emma Muscat", "Zdob și Zdub & Advahov Brothers", "Vladana", "S10", "Andrea", "Subwoolfer", "Ochman", "Maro", "WRS", "Achille Lauro", "Konstrakta", "LPS", "Chanel", "Cornelia Jakobs", "Marius Bear", "Kalush Orchestra", "Sam Ryder" ));
        List<String> songs2022 = new ArrayList<>(Arrays.asList("Sekret", "Snap", "Not The Same", "Halo", "Fade To Black", "Miss You", "Intention", "Guilty Pleasure", "Ela", "Lights Off", "The Show", "Hope", "Jezebel", "Fulenn", "Lock Me In", "Rockstars", "Die Together", "Með hækkandi sól", "That's Rich", "I.M", "Brividi", "Eat Your Salad", "Sentimentai", "I Am What I Am", "Trenulețul", "Breathe", "De Diepte", "Circles", "Give That Wolf a Banana", "River", "Saudade, saudade", "Llámame", "Stripper", "In Corpore Sano", "Disko", "SloMo", "Hold Me Closer", "Boys Do Cry", "Stefania", "Space Man" ));

        ContentValues songList2022= new ContentValues();

        for (int i = 0; i < countries2022.size(); i++) {

            songList2022.put("year", 2022);
            songList2022.put("countryCode", countryCodes2022.get(i));
            songList2022.put("country", countries2022.get(i));
            songList2022.put("artist", artists2022.get(i));
            songList2022.put("song", songs2022.get(i));
            db.insert("song_info", null, songList2022);

        }

        //-------------------------------------------------------------

        List<String> countries2021 = new ArrayList<>(Arrays.asList("Albania", "Australia", "Austria", "Azerbaijan", "Belgium", "Bulgaria", "Croatia", "Cyprus", "Czechia", "Denmark", "Estonia", "Finland", "France", "Georgia", "Germany", "Greece", "Iceland", "Ireland", "Israel", "Italy", "Latvia", "Lithuania", "Malta", "Moldova", "Netherlands", "North Macedonia", "Norway", "Poland", "Portugal", "Romania", "Russia", "San Marino", "Serbia", "Slovenia", "Spain", "Sweden", "Switzerland", "Ukraine", "United Kingdom" ));
        List<String> countryCodes2021 = new ArrayList<>(Arrays.asList("AL", "AU", "AT", "AZ", "BE", "BG", "HR", "CY", "CZ", "DK", "EE", "FI", "FR", "GE", "DE", "GR", "IS", "IE", "IL", "IT", "LV", "LT", "MT", "MD", "NL", "MK", "NO", "PL", "PT", "RO", "RU", "SM", "RS", "SI", "ES", "SE", "CH", "UA", "GB"));
        List<String> artists2021 = new ArrayList<>(Arrays.asList("Anxhela Peristeri", "Montaigne", "Vincent Bueno", "Efendi", "Hooverphonic", "Victoria", "Albina", "Elena Tsagrinou", "Benny Cristo", "Fyr og Flamme", "Uku Suviste", "Blind Channel", "Barbara Pravi", "Tornike Kipiani", "Jendrik", "Stefania", "Daði & Gagnamagnið", "Lesley Roy", "Eden Alene", "Måneskin", "Samanta Tīna", "The Roop", "Destiny", "Natalia Gordienko", "Jeangu Macrooy", "Vasil", "TIX", "Rafał", "The Black Mamba", "Roxen", "Manizha", "Senhit feat. Flo Rida", "Hurricane", "Ana Soklič", "Blas Cantó", "Tusse", "Gjon's Tears", "Go_A", "James Newman" ));
        List<String> songs2021 = new ArrayList<>(Arrays.asList("Karma", "Technicolour", "Amen", "Mata Hari", "The Wrong Place", "Growing Up Is Getting Old", "Tick-Tock", "El Diablo", "Omaga", "Øve os på hinanden", "The Lucky One", "Dark Side", "Voilà", "You", "I Don't Feel Hate", "Last Dance", "10 Years", "Maps", "Set Me Free", "Zitti e buoni", "The Moon Is Rising", "Discoteque", "Je Me Casse", "Sugar", "Birth Of A New Age", "Here I Stand", "Fallen Angel", "The Ride", "Love Is On My Side", "Amnesia", "Russian Woman", "Adrenalina", "Loco Loco", "Amen", "Voy A Quedarme", "Voices", "Tout l'univers", "Shum", "Embers" ));

        ContentValues songList2021= new ContentValues();

        for (int i = 0; i < countries2021.size(); i++) {

            songList2021.put("year", 2021);
            songList2021.put("countryCode", countryCodes2021.get(i));
            songList2021.put("country", countries2021.get(i));
            songList2021.put("artist", artists2021.get(i));
            songList2021.put("song", songs2021.get(i));
            db.insert("song_info", null, songList2021);

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
