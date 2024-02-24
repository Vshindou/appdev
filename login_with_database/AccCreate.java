package com.example.recshare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class AccCreate extends AppCompatActivity {

    DatabaseHelper dbHelper;
    EditText editTextUsername, editTextPassword;
    Button buttonCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc_create);

        dbHelper = new DatabaseHelper(this);
        editTextUsername = findViewById(R.id.editTextUsername); // Find EditText for username
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonCreateAccount = findViewById(R.id.buttonCreateAccount);

        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUserAndReturnToLogin();
            }
        });
    }

    private void createUserAndReturnToLogin() {
        String username = editTextUsername.getText().toString(); // Get username from EditText
        String password = editTextPassword.getText().toString();

        // Get writable database
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USERNAME, username);
        values.put(DatabaseHelper.COLUMN_PASSWORD, password);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(DatabaseHelper.TABLE_NAME, null, values);

        // Close the database connection
        db.close();

        // Navigate back to Login activity
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish(); // Finish this activity to prevent going back to it using back button
    }
}
