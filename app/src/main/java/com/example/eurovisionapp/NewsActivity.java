package com.example.eurovisionapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonArray;
import com.koushikdutta.async.future.FutureCallback;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_layout);

        /*NewsApiClient.getNewsArticles(new FutureCallback<JsonArray>() {
            @Override
            public void completed(JsonArray result) {
                // Tratați răspunsul JSON aici în cazul de succes
            }

            @Override
            public void failed(Exception ex) {
                // Tratați eroarea aici în cazul de eșec
            }

            @Override
            public void cancelled() {
                // Tratați anularea aici dacă este cazul
            }
        });*/
    }
}
