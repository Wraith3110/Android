package com.example.signup;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class signup extends AppCompatActivity {

    private EditText usernameField, passwordField;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameField = findViewById(R.id.username);
        passwordField = findViewById(R.id.password);
        signUpButton = findViewById(R.id.sighup);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameField.getText().toString().trim();
                String password = passwordField.getText().toString().trim();

                // Validate input
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(signup.this, "Username is required", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(username)) {
                    Toast.makeText(signup.this, "Enter a valid email as username", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(signup.this, "Password is required", Toast.LENGTH_SHORT).show();
                } else if (!isValidPassword(password)) {
                    Toast.makeText(signup.this, "Password must be at least 8 characters and include uppercase, lowercase, numbers, and special characters", Toast.LENGTH_LONG).show();
                } else {
                    // Navigate to signin activity and pass data
                    Intent intent = new Intent(signup.this, signin.class);
                    intent.putExtra("username", username);
                    intent.putExtra("password", password);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    // Validate password
    private boolean isValidPassword(String password) {
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        return Pattern.matches(passwordPattern, password);
    }

    // Validate username as email
    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
