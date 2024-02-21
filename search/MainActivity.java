//Package Name
package com.example.samplelayout;

// Import Statements
import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.SearchView;
import android.os.Bundle;
import android.widget.Toast;

//Main Activity Empty Views
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find the search box by button id
        SearchView searchView = findViewById(R.id.searchvw1);

        //Set an Event Listener for Search Query
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            //Use the text submit function to dectect a submit and use Toast to display a pop up
            @Override
            public boolean onQueryTextSubmit(String query) {

                Toast.makeText(MainActivity.this, "Query, Submitted: " + query, Toast.LENGTH_SHORT).show();
                String searchUrl = "https://www.google.com/search?q=" + Uri.encode(query);

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(searchUrl));
                startActivity(intent);

                return true;
            }

            //Add function text change on waht must be done when the query changes

            @Override
            public boolean onQueryTextChange(String newText) {
                //Toast.makeText(MainActivity.this, "Query, Changed: " + newText, Toast.LENGTH_SHORT).show();
                return true;
            }
            //Toast message is commented out as it spams the screen for every letter typed

        });
    }
}
