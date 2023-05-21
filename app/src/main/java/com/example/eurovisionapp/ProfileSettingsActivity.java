package com.example.eurovisionapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ResourceBundle;

public class ProfileSettingsActivity extends AppCompatActivity {

    private final int GALLERY_REQ_CODE = 1000;
    private ImageView imgGallery;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);

        Button logOutButton = findViewById(R.id.logOutButton);

        ImageButton backButton = findViewById(R.id.auth_back);
        backButton.setOnClickListener(v -> startActivity(new Intent(ProfileSettingsActivity.this, MainActivity.class)));

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
        imgGallery = findViewById(R.id.profileImg);

        if (preferences.contains("image" + LoginActivity.currentId)) {
            String regImg = preferences.getString("image" + LoginActivity.currentId, "");
            Uri uri = Uri.parse(regImg);
            imgGallery.setImageURI(uri);
        }

        TextView userName = findViewById(R.id.fstsndName);
        userName.setText(regFst + " " + regSnd);

        Button btnGallery = findViewById(R.id.gallery_button);

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALLERY_REQ_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == GALLERY_REQ_CODE) {

                imgGallery.setImageURI(data.getData());
                SharedPreferences preferences = getSharedPreferences("USER_INFO", 0);
                ;
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("image" + LoginActivity.currentId, data.getData().toString());
                editor.apply();
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    getContentResolver().takePersistableUriPermission(data.getData(), Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }
            }
        }
    }
}