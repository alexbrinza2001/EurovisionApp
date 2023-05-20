package com.example.eurovisionapp;

import android.content.Context;
import android.content.SharedPreferences;

public class MyAppPrefs {
    private static final String PREFS_NAME = "MyAppPrefs";
    private static final String KEY_EMAIL = "email";

    private final SharedPreferences preferences;
    public MyAppPrefs(Context context){
      preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveEmail(String email) {
        preferences.edit().putString(KEY_EMAIL, email).apply();
    }

    public String getEmail(){
        return preferences.getString(KEY_EMAIL, "");
    }
}
