package com.example.eurovisionapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button login, register;
    private SharedPreferences preferences;

    public static int currentId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageButton backButton = findViewById(R.id.auth_back);
        backButton.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, MainActivity.class)));

        email = findViewById(R.id.emailText);
        password = findViewById(R.id.passText);
        login = findViewById(R.id.loginButton);
        register = findViewById(R.id.registerButton);


        preferences = getSharedPreferences("USER_INFO", 0);
        login.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                String emailValue = email.getText().toString().trim();
                String passValue = password.getText().toString().trim();

                int ok = 0;
                int userId = preferences.getInt("id", 0);
                for(int i = 1; i <= userId; i++){
                    String regEmail = preferences.getString("email" + i, "");
                    String regPass = preferences.getString("password" + i, "");
                    if (emailValue.equals(regEmail) && PasswordGenerator.generateToken(passValue).equals(regPass)) {
                        ok = 1;
                        currentId = i;
                        break;
                    }
                }

                if (ok == 1) {
                    MainActivity.isLoggedIn = true;
                    Toast.makeText(LoginActivity.this, "Logged in!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Email or password are invalid!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

}
