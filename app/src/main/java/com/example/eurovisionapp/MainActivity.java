package com.example.eurovisionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    public Context getContext() {
        return context;
    }

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        Button myTopsButton = findViewById(R.id.my_tops);

        myTopsButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MyTops.class)));

        ImageButton authButton = findViewById(R.id.authButton);

        authButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LoginActivity.class)));

        //-----------------------------

        EurovisionDatabase edb = new EurovisionDatabase(context);
        SQLiteDatabase database = edb.getWritableDatabase();
    }
}