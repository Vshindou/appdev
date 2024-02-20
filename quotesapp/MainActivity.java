package com.example.quotes;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView quoteTextView;

    private final String[] quotes = {
            "The only way to do great work is to love what you do. - Steve Jobs",
            "Innovation distinguishes between a leader and a follower. - Steve Jobs",
            "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
            "I am not alone. Do not underestimate humans, Meruem. You are not alone either. - Isaac Netero",
            "The only ones who should kill are those who are prepared to be killed. - Lelouch Lamperouge",
            "A lesson without pain is meaningless. For you cannot gain something without sacrificing something else in return. - Edward Elric",

            // Add more quotes as needed
    };

    private int currentQuoteIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteTextView = findViewById(R.id.quoteTextView);
        Button changeQuoteButton = findViewById(R.id.changeQuoteButton);

        changeQuoteButton.setOnClickListener(view -> showNextQuote());
    }

    private void showNextQuote() {
        if (currentQuoteIndex < quotes.length - 1) {
            currentQuoteIndex++;
        } else {
            currentQuoteIndex = 0;
        }

        quoteTextView.setText(quotes[currentQuoteIndex]);
    }
}
