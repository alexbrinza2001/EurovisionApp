package com.example.eurovisionapp;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

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
                    if (emailValue.equals(regEmail) && passValue.equals(regPass)) {
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

   /* private EditText emailEditText, passwordEditText;
    private Button loginButton, signupButton;
    private boolean isLoginMode = true;
    private SharedPreferences.Editor editor;
    private MyAppPrefs myAppPrefs;

    private FirebaseAuth mAuth;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceStat e) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FirebaseApp.initializeApp(this);



        myAppPrefs = new MyAppPrefs(this);
        mAuth = FirebaseAuth.getInstance();
        ImageButton backButton = findViewById(R.id.auth_back);
        backButton.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, MainActivity.class)));

        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        emailEditText = findViewById(R.id.emailText);
        passwordEditText = findViewById(R.id.passText);
        loginButton = findViewById(R.id.loginButton);
        signupButton = findViewById(R.id.signupButton);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        loginButton.setOnClickListener(view -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            if (isLoggedIn) {
                // Implementați codul pentru autentificare (logare) aici
                performLogin(email, password);
            } else {
                // Implementați codul pentru înregistrare (signup) aici
                performSignup(email, password);
            }
        });

        signupButton.setOnClickListener(view -> {
            isLoginMode = !isLoginMode;
            if (isLoginMode) {
                loginButton.setText("Login");
                signupButton.setText("Create account");
            } else {
                loginButton.setText("Register");
                signupButton.setText("Already have an account? Login!");
            }
        });
    }

    private void saveCredentials(String email, String password){
        myAppPrefs.saveEmail(email);
    }

    private void retrieveCredentials(){
        String savedEmail = myAppPrefs.getEmail();
    }

    private void performLogin(String email, String password) {
        // Implementați logica de autentificare (logare) aici
        // Utilizați metodele de autentificare specifice, cum ar fi Firebase Authentication

        String uid = "some-uid";

        mAuth.signInWithCustomToken(uid)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            System.out.println("signInWithCustomToken:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            System.out.println("signInWithCustomToken:failure" + task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });

        saveCredentials(email, password);
        editor.putBoolean("isLoggedIn", true);
        editor.apply();
    }

    private void performSignup(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Înregistrare reușită, obțineți utilizatorul înregistrat
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // Înregistrare eșuată, afișați un mesaj de eroare
                            Toast.makeText(LoginActivity.this, "Sign up failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });

        if (email.isEmpty()) {
            emailEditText.setError("Please insert your email!");
            emailEditText.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Please insert a valid email address!");
            emailEditText.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwordEditText.setError("Please insert your password!");
            passwordEditText.requestFocus();
            return;
        }

        if (password.length() < 8) {
            passwordEditText.setError("Your password should be at least 8 characters long!");
            passwordEditText.requestFocus();
            return;
        }

        saveCredentials(email, password);
        // Implementați logica de înregistrare (signup) aici
        // Utilizați metodele de înregistrare specifice, cum ar fi Firebase Authentication
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }*/

}
