package com.example.eurovisionapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
//import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText email, password, firstName, secondName;
    private Button cancel, register;
    private RadioGroup gender;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.emailText);
        password = findViewById(R.id.passText);
        cancel = findViewById(R.id.cancelButton);
        register = findViewById(R.id.registerButton);
        firstName = findViewById(R.id.fstName);
        secondName = findViewById(R.id.sndName);
        gender = findViewById(R.id.gender);

        preferences = getSharedPreferences("USER_INFO", 0);

        //culori pentru butoane

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailValue = email.getText().toString().trim();
                String passValue = password.getText().toString().trim();
                String firstValue = firstName.getText().toString().trim();
                String secondValue = secondName.getText().toString().trim();
                RadioButton checked = findViewById(gender.getCheckedRadioButtonId());
                String genderValue = checked.getText().toString().trim();
                int ok = 1;

                if (firstValue.isEmpty()) {
                    firstName.setError("Please insert your first name!");
                    firstName.requestFocus();
                    ok = 0;
                    return;
                }

                if (secondValue.isEmpty()) {
                    secondName.setError("Please insert your second name!");
                    secondName.requestFocus();
                    ok = 0;
                    return;
                }

                if (emailValue.isEmpty()) {
                    email.setError("Please insert your email!");
                    email.requestFocus();
                    ok = 0;
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(emailValue).matches()){
                    email.setError("Insert a valid email address!");
                    email.requestFocus();
                    ok = 0;
                    return;
                }

                if (passValue.isEmpty()) {
                    password.setError("Please insert your password!");
                    password.requestFocus();
                    ok = 0;
                    return;
                }

                if (passValue.length() < 8) {
                    password.setError("Your password should be at least 8 characters long!");
                    password.requestFocus();
                    ok = 0;
                    return;
                }

                if (ok == 1) {
                    int usersCount = preferences.getInt("id", 0) + 1;
                    //usersCount += 1;
                    SharedPreferences.Editor editor = preferences.edit();
                    //editor.putString("id", Integer.toString(usersCount));
                    editor.putString("email" + usersCount, emailValue);
                    editor.putString("password" + usersCount, passValue);
                    editor.putString("first_name" + usersCount, firstValue);
                    editor.putString("second_name" + usersCount, secondValue);
                    editor.putString("gender" + usersCount, genderValue);
                    editor.putInt("id", usersCount);
                    editor.apply();

                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    Toast.makeText(RegisterActivity.this, "User registered. Please login now!", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emptyField();
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void emptyField() {
        email.setText("");
        password.setText("");
        firstName.setText("");
        secondName.setText("");
    }

    /*private EditText emailEditText, passwordEditText;
    private Button loginButton, signupButton;
    private boolean isLoginMode = true;
    private SharedPreferences.Editor editor;
    private MyAppPrefs myAppPrefs;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.emailText);
        passwordEditText = findViewById(R.id.passText);
        loginButton = findViewById(R.id.loginButton);
        signupButton = findViewById(R.id.signupButton);

        ImageButton backButton = findViewById(R.id.auth_back);
        backButton.setOnClickListener(v -> startActivity(new Intent(SignUpActivity.this, MainActivity.class)));

        findViewById(R.id.signupButton).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() != null) {
            //handle the already login user
        }
    }

    private void registerUser(){
        String email = emailEditText.getText().toString().trim();

        String password = passwordEditText.getText().toString().trim();

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

       mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            User user = new User(email);


                        } else {
                            Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });



    }

    @Override
    public void onClick(View view) {

    }*/
}
