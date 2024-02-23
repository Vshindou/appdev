package com.example.recshare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize login button and set click listener
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginButtonClick(); // Call method to handle login button click
            }
        });

        // Initialize create account button and set click listener
        Button createAccountButton = findViewById(R.id.createAccountButton);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateAccountButtonClick(); // Call method to handle create account button click
            }
        });

        // Initialize display credentials button and set click listener
        Button displayCredentialsButton = findViewById(R.id.displayCredentialsButton);
        displayCredentialsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDisplayCredentialsButtonClick(); // Call method to handle display credentials button click
            }
        });
    }

    // Method to handle login button click
    public void onLoginButtonClick() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    // Method to handle create account button click
    public void onCreateAccountButtonClick() {
        Intent intent = new Intent(this, AccCreate.class);
        startActivity(intent);
    }

    // Method to handle display credentials button click
    public void onDisplayCredentialsButtonClick() {
        // Implement logic to display usernames and passwords
        // For example, you can start a new activity to display the credentials or show them in a dialog.
        // Replace the following line with your implementation.
        // Intent intent = new Intent(this, DisplayCredentialsActivity.class);
        // startActivity(intent);
    }
}
