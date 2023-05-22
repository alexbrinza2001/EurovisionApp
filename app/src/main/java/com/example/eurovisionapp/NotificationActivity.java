package com.example.eurovisionapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_layout);
        String data = getIntent().getStringExtra("data");
        System.out.println(data);

    }
}
