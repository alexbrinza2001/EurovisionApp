package com.example.eurovisionapp;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class NewsApiClient {
    /*private static final String NEWS_URL = "https://eurovisionworld.com/api/v1/news";

    public static void getNewsArticles(FutureCallback<JsonArray> futureCallback) {
        Ion.with(MainActivity.getAppContext())
                .load(NEWS_URL)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        if (e == null) {
                            if (result != null) {
                                try {
                                    for (int i = 0; i < result.size(); i++) {
                                        JsonObject article = result.get(i).getAsJsonObject();
                                        String title = String.valueOf(article.get("title"));
                                        String content = String.valueOf(article.get("content"));
                                        String imageUrl = String.valueOf(article.get("image"));

                                        System.out.println(title);

                                        // Utilizează datele articolului în continuare
                                        // ...
                                    }
                                } catch (JsonIOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            e.printStackTrace();
                        }
                    }
                });
    }*/
}
