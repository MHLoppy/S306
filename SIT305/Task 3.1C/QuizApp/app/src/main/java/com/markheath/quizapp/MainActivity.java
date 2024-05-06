package com.markheath.quizapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextView titleText;
    TextView nameText;
    TextInputEditText nameEditText;
    Button startButton;
    ImageView adImage3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        titleText = findViewById(R.id.titleTextView);
        nameText = findViewById(R.id.nameTextView);
        nameEditText = findViewById(R.id.nameEditText);
        startButton = findViewById(R.id.startButton);
        adImage3 = findViewById(R.id.adImage3);

        // Populate the name field if coming from a finished quiz
        Intent oldIntent = getIntent();
        String existingName = oldIntent.getStringExtra("Name");
        nameEditText.setText(existingName);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Prep extra data to send to the activity
                String name = nameEditText.getText().toString();
                final int questionsMax = 5;
                final int questionCurrent = 1;
                final int questionsCorrect = 0;

                Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                intent.putExtra("Name", name);
                intent.putExtra("Max", questionsMax);
                intent.putExtra("Current", questionCurrent);
                intent.putExtra("Correct", questionsCorrect);
                startActivity(intent);
            }
        });

        String adUrl = getResources().getString(R.string.urlAd);
        adImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(adUrl));
                startActivity(browserIntent);
            }
        });
    }
}