package com.markheath.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EndscreenActivity extends AppCompatActivity {

    TextView congratsText;
    //TextView scorePrefixText;
    TextView scoreResultText;
    Button newQuizButton;
    Button finishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_endscreen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        congratsText    = findViewById(R.id.congratsTextView);

        //scorePrefixText = findViewById(R.id.scorePrefixTextView);
        scoreResultText = findViewById(R.id.scoreResultTextView);

        newQuizButton   = findViewById(R.id.newQuizButton);
        finishButton    = findViewById(R.id.finishButton);

        // Pull values from the quiz activity
        Intent quizIntent = getIntent();
        String tempName = "Player";
        if (quizIntent != null) {
            if (!quizIntent.getStringExtra("Name").isEmpty()) {
                tempName = quizIntent.getStringExtra("Name");
            }
        }
        int tempMax = 5;
        int tempCurrent = 1;
        if (quizIntent != null) {
            tempMax = quizIntent.getIntExtra("Max", 5);
            tempCurrent = quizIntent.getIntExtra("Current", 1);
        }
        int correct = 0;
        if (quizIntent != null) {
            correct = quizIntent.getIntExtra("Correct", 0);
        }

        // satisfy the Java gods
        final String name = tempName;
        final int max = tempMax;
        final int current = tempCurrent;

        // Populate values of UI elements
        String congrats = "Congratulations " + name + "!";
        String score = correct + "/" + max;
        congratsText.setText(congrats);
        scoreResultText.setText(score);

        // New quiz button starts new quiz
        newQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Use the pulled values to start a new quiz
                Intent intent = new Intent(EndscreenActivity.this, MainActivity.class);
                intent.putExtra("Name", name);
                intent.putExtra("Max", max);
                intent.putExtra("Current", current);
                startActivity(intent);
            }
        });

        // Finish button closes the app
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }
}