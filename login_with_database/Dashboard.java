package com.example.recshare;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Find buttons by their IDs
        Button buttonHomemade = findViewById(R.id.Comfort_Food);
        Button buttonRestaurant = findViewById(R.id.Healthy);
        Button buttonSalads = findViewById(R.id.Baking);
        Button buttonDessert = findViewById(R.id.Our_Recipes);
        Button buttonontv = findViewById(R.id.On_Tv);

        // Set onClickListener for each button
        buttonHomemade.setOnClickListener(this);
        buttonRestaurant.setOnClickListener(this);
        buttonSalads.setOnClickListener(this);
        buttonDessert.setOnClickListener(this);
        buttonontv.setOnClickListener(this);

        // Initialize TextView for displaying username
        textViewUsername = findViewById(R.id.textViewUsername);

        // Retrieve username from the database
        String username = retrieveUsernameFromDatabase();

        // Set the retrieved username to the TextView
        textViewUsername.setText(username);
    }




    @Override
    public void onClick(View v) {
        // Handle button clicks
        int id = v.getId();
        if (id == R.id.Comfort_Food) {
            openWebPage("https://www.foodnetwork.com/recipes/packages/comfort-foods");
        } else if (id == R.id.Healthy) {
            openWebPage("https://www.foodnetwork.com/healthy/packages/healthy-every-week");
        } else if (id == R.id.Baking) {
            openWebPage("https://www.foodnetwork.com/recipes/packages/baking-guide");
        } else if (id == R.id.Our_Recipes) {
            openWebPage("https://www.foodnetwork.com/recipes/photos/food-network-kitchen-s-best-recipes");
        } else if (id == R.id.On_Tv) {
            openWebPage("https://www.foodnetwork.com/shows/tv-schedule");
        }
    }

    // Method to open a web page
    private void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    // Method to retrieve username from the database
    private String retrieveUsernameFromDatabase() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                DatabaseHelper.COLUMN_USERNAME
        };

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        String username = "";

        if (cursor.moveToFirst()) {
            username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USERNAME));
        }

        cursor.close();
        db.close();

        return username;
    }
}
