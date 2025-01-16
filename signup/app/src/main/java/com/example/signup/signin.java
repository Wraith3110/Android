package com.example.signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class signin extends AppCompatActivity {

    private EditText usernameField, passwordField;
    private Button loginButton;
    private String correctUsername, correctPassword;
    private int attemptsLeft = 2; // Max 2 attempts

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        usernameField = findViewById(R.id.username);
        passwordField = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);

        // Retrieve data from signup activity
        Intent intent = getIntent();
        correctUsername = intent.getStringExtra("username");
        correctPassword = intent.getStringExtra("password");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameField.getText().toString().trim();
                String password = passwordField.getText().toString().trim();

                if (username.equals(correctUsername) && password.equals(correctPassword)) {
                    // Successful login
                    Toast.makeText(signin.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent welcomeIntent = new Intent(signin.this, welcome.class);
                    startActivity(welcomeIntent);
                    finish();
                } else {
                    // Failed login
                    attemptsLeft--;
                    if (attemptsLeft > 0) {
                        Toast.makeText(signin.this, "Login Failed. Attempts left: " + attemptsLeft, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(signin.this, "Login Failed. No attempts left.", Toast.LENGTH_LONG).show();
                        loginButton.setEnabled(false); // Disable login button
                    }
                }
            }
        });
    }
}
