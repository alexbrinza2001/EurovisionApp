package com.example.eurovisionapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ResourceBundle;

public class ProfileSettingsActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);

        Button logOutButton = findViewById(R.id.logOutButton);

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.isLoggedIn = false;
                Intent intent = new Intent(ProfileSettingsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences preferences = getSharedPreferences("USER_INFO", 0);;
        String regFst = preferences.getString("first_name" + LoginActivity.currentId, "");
        String regSnd = preferences.getString("second_name" + LoginActivity.currentId, "");

        TextView userName = findViewById(R.id.fstsndName);
        userName.setText(regFst + " " + regSnd);




    }
}
