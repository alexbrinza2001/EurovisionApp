package com.example.eurovisionapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OddsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odds);

        ImageButton oddsBack = findViewById(R.id.odds_back);
        oddsBack.setOnClickListener(v -> startActivity(new Intent(OddsActivity.this, MainActivity.class)));

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL("https://eurovisionworld.com/odds/eurovision");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        InputStream inputStream = connection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        reader.close();

                        String htmlContent = response.toString();

                        runOnUiThread(new Runnable() {
                            @SuppressLint("SetJavaScriptEnabled")
                            @Override
                            public void run() {
                                WebView webView = findViewById(R.id.odds_page);

                                WebSettings webSettings = webView.getSettings();
                                webSettings.setJavaScriptEnabled(true);

                                WebViewClient webViewClient = new WebViewClient() {
                                    @Override
                                    public void onPageFinished(WebView view, String url) {
                                    }
                                };

                                webView.setWebViewClient(webViewClient);
                                webSettings.setDefaultTextEncodingName("UTF-8");
                                webView.loadDataWithBaseURL("https://eurovisionworld.com", htmlContent, "text/html", "UTF-8", null);
                            }
                        });

                    }

                    connection.disconnect();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }
}
