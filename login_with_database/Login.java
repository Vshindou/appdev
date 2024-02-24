package com.example.recshare;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    DatabaseHelper dbHelper;
    EditText editTextUsername, editTextPassword;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DatabaseHelper(this);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> authenticateUser());
    }

    private void authenticateUser() {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {DatabaseHelper.COLUMN_USERNAME, DatabaseHelper.COLUMN_PASSWORD};
        String selection = DatabaseHelper.COLUMN_USERNAME + " = ? AND " + DatabaseHelper.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            // User exists, perform login
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
            // Navigate to Dashboard activity
            Intent intent = new Intent(this, Dashboard.class);
            startActivity(intent);
            finish(); // Finish this activity to prevent going back to it using back button
        } else {
            // Invalid credentials
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }

        cursor.close();
        db.close();
    }
}
