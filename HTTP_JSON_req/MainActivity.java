package com.example.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ImageView mPokemonImageView;
    Button nextPokemonButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPokemonImageView = findViewById(R.id.pokemonImageView);
        nextPokemonButton = findViewById(R.id.nextPokemonButton);

        nextPokemonButton.setOnClickListener(view -> loadPokemonImage());

        loadPokemonImage();
    }

    private void loadPokemonImage() {
        RequestQueue volleyQueue = Volley.newRequestQueue(MainActivity.this);
        // URL of the PokeAPI for a random Pokemon image
        String url = "https://pokeapi.co/api/v2/pokemon/" + getRandomPokemonId();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,

                response -> {
                    String pokemonImageUrl;
                    try {
                        // Extract the Pokemon image URL from the API response
                        pokemonImageUrl = response.getJSONObject("sprites").getString("front_default");
                        // Load the image into the ImageView using Glide.
                        Glide.with(MainActivity.this).load(pokemonImageUrl).into(mPokemonImageView);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Error parsing Pokemon data", Toast.LENGTH_LONG).show();
                    }
                },

                error -> {
                    Toast.makeText(MainActivity.this, "Some error occurred! Cannot fetch Pokemon image", Toast.LENGTH_LONG).show();
                    Log.e("MainActivity", "loadPokemonImage error: " + error.getLocalizedMessage());
                }
        );

        volleyQueue.add(jsonObjectRequest);
    }

    private int getRandomPokemonId() {
        // Generate a random Pokemon ID between 1 and 1000 (adjust as needed)
        return (int) (Math.random() * 1000) + 1;
    }
}
