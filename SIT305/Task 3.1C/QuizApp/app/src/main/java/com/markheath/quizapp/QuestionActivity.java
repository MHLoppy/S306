package com.markheath.quizapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuestionActivity extends AppCompatActivity {

    TextView welcomeText;
    ProgressBar progressBar;
    TextView progressText;
    TextView questionTitleText;
    TextView questionDetailsText;
    Button answerButtonTop;
    Button answerButtonMiddle;
    Button answerButtonBottom;
    Button finishButton;
    boolean answered = false;
    int playerAnswer = 0;
    int answeredCorrectly = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        welcomeText         = findViewById(R.id.welcomeTextView);

        progressBar         = findViewById(R.id.progressBar);
        progressText        = findViewById(R.id.progressTextView);

        questionTitleText   = findViewById(R.id.questionTitleTextView);
        questionDetailsText = findViewById(R.id.questionDetailsTextView);

        answerButtonTop     = findViewById(R.id.answerButtonTop);
        answerButtonMiddle  = findViewById(R.id.answerButtonMiddle);
        answerButtonBottom  = findViewById(R.id.answerButtonBottom);

        finishButton        = findViewById(R.id.finishButton);

        // initial value for the finish button
        finishButton.setText("Submit");

        Intent oldIntent = getIntent();

        // Get player's score from previous intent
        int tempCorrect;
        if (oldIntent != null) {
            tempCorrect = oldIntent.getIntExtra("Correct", 0);
        }
        else {
            tempCorrect = 0;
        }

        // Set player name using data from MainActivity
        String tempName;
        if ((oldIntent != null) && (!oldIntent.getStringExtra("Name").isEmpty())) {
            tempName = oldIntent.getStringExtra("Name");
        }
        else {
            tempName = "Player";
        }
        String welcomeString = "Welcome " + tempName + "!";
        welcomeText.setText(welcomeString);

        // Fill the progress bar
        int defaultMax = 5;
        int tempMax;
        int tempCurrent;
        if (oldIntent != null) {
            tempMax = oldIntent.getIntExtra("Max", defaultMax);
            tempCurrent = oldIntent.getIntExtra("Current", 1);
        }
        else {
            tempMax = defaultMax;
            tempCurrent = 1;
        }
        progressBar.setMax((tempMax * 2) - 1);      // lets us use a "half-increment" for Q1
        progressBar.setProgress((tempCurrent * 2) - 1);

        // Set the progress counter
        String progress = tempCurrent + " / " + tempMax;
        progressText.setText(progress);

        // satisfy the Java gods
        final String name = tempName;
        final int max = tempMax;
        final int current = tempCurrent;
        final int correct = tempCorrect;

        // only welcome for first question
        ViewGroup.MarginLayoutParams progTextParams = (ViewGroup.MarginLayoutParams) progressText.getLayoutParams();
        ViewGroup.MarginLayoutParams progBarParams = (ViewGroup.MarginLayoutParams) progressBar.getLayoutParams();
        if (current > 1) {
            welcomeText.setVisibility(View.INVISIBLE);

            int newTopMargin = 24;
            progTextParams.topMargin = newTopMargin;
            progBarParams.topMargin = newTopMargin;
        }
        // otherwise need to reset things for Q1!
        else {
            welcomeText.setVisibility(View.VISIBLE);

            int newTopMargin = 54;
            progTextParams.topMargin = newTopMargin;
            progBarParams.topMargin = newTopMargin;
        }
        progressText.setLayoutParams(progTextParams);
        progressBar.setLayoutParams(progBarParams);

        // Set question title and details
        QuestionBundle qb = GetQuestionBundle(current);
        questionTitleText.setText(qb.Title);
        questionDetailsText.setText(qb.Question);
        answerButtonTop.setText(qb.AnswerTop);
        answerButtonMiddle.setText(qb.AnswerMiddle);
        answerButtonBottom.setText(qb.AnswerBottom);

        // Select answers (this is (?) syntactic sugar for a fully implemented OnClickListener)
        answerButtonTop.setOnClickListener(v -> SelectTopAnswer());
        answerButtonMiddle.setOnClickListener(v -> SelectMiddleAnswer());
        answerButtonBottom.setOnClickListener(v -> SelectBottomAnswer());

        // Set submit/next button text
        finishButton.setOnClickListener(v -> {

            // there's surely a way to do this with less duplication!?
            if (answered && (current >= max)) {
                // go to endscreen
                Intent endscreenIntent = new Intent(QuestionActivity.this, EndscreenActivity.class);
                endscreenIntent.putExtra("Name", name);
                endscreenIntent.putExtra("Max", max);
                endscreenIntent.putExtra("Current", (current + 1));
                endscreenIntent.putExtra("Correct", (correct + answeredCorrectly));
                startActivity(endscreenIntent);
            }
            else if (answered) {
                // go to new question
                Intent questionIntent = new Intent(QuestionActivity.this, QuestionActivity.class);
                questionIntent.putExtra("Name", name);
                questionIntent.putExtra("Max", max);
                questionIntent.putExtra("Current", (current + 1));
                questionIntent.putExtra("Correct", (correct + answeredCorrectly));
                startActivity(questionIntent);
            }
            else {
                if (ProcessAnswer(qb.CorrectAnswer, playerAnswer)) {
                    answeredCorrectly = 1;
                }
                answered = true;
                finishButton.setText("Next");
            }
        });
    }

    static class QuestionBundle {
        String Title;
        String Question;
        String AnswerTop;
        String AnswerMiddle;
        String AnswerBottom;
        int CorrectAnswer;
    }

    QuestionBundle GetQuestionBundle(int counter) {
        QuestionBundle qb = new QuestionBundle();

        switch(counter) {
            case 1:
                qb.Title = "SIT305";
                qb.Question = "What is the full name of this subject at Deakin University, as taken from the T1 2024 unit guide?";
                qb.AnswerTop = "Mobile App Development";
                qb.AnswerMiddle = "Mobile Application Development";
                qb.AnswerBottom = "Android Application Development";
                qb.CorrectAnswer = 2;
                break;
            case 2:
                qb.Title = "Addition";
                qb.Question = "What is 2+12?";
                qb.AnswerTop = "7";
                qb.AnswerMiddle = "14";
                qb.AnswerBottom = "21";
                qb.CorrectAnswer = 2;
                break;
            case 3:
                qb.Title = "Subtraction";
                qb.Question = "What is 8-4?";
                qb.AnswerTop = "2";
                qb.AnswerMiddle = "3";
                qb.AnswerBottom = "4";
                qb.CorrectAnswer = 3;
                break;
            case 4:
                qb.Title = "Multiplication";
                qb.Question = "What is 7x6?";
                qb.AnswerTop = "42";
                qb.AnswerMiddle = "43";
                qb.AnswerBottom = "44";
                qb.CorrectAnswer = 1;
                break;
            case 5:
                qb.Title = "Division";
                qb.Question = "What is 88/8?";
                qb.AnswerTop = "11";
                qb.AnswerMiddle = "12";
                qb.AnswerBottom = "13";
                qb.CorrectAnswer = 1;
                break;
            default:
                qb.Title = "Unsupported question";
                qb.Question = "...";
                qb.AnswerTop = "1";
                qb.AnswerMiddle = "2";
                qb.AnswerBottom = "3";
                qb.CorrectAnswer = 0;
        }

        return qb;
    }

    // This ensures mutual exclusivity, which simply handling via UI (onSelected or whatever) doesn't
    void SelectTopAnswer() {
        if (!answered) {
            answerButtonTop.setBackgroundColor(Color.LTGRAY);
            answerButtonMiddle.setBackgroundColor(Color.WHITE);
            answerButtonBottom.setBackgroundColor(Color.WHITE);
            playerAnswer = 1;
        }
    }

    void SelectMiddleAnswer() {
        if (!answered) {
            answerButtonTop.setBackgroundColor(Color.WHITE);
            answerButtonMiddle.setBackgroundColor(Color.LTGRAY);
            answerButtonBottom.setBackgroundColor(Color.WHITE);
            playerAnswer = 2;
        }
    }

    void SelectBottomAnswer() {
        if (!answered) {
            answerButtonTop.setBackgroundColor(Color.WHITE);
            answerButtonMiddle.setBackgroundColor(Color.WHITE);
            answerButtonBottom.setBackgroundColor(Color.LTGRAY);
            playerAnswer = 3;
        }
    }

    boolean ProcessAnswer(int correct, int selected) {
        if (correct == 1) {
            answerButtonTop.setBackgroundColor(Color.GREEN);
        }
        else if (correct == 2) {
            answerButtonMiddle.setBackgroundColor(Color.GREEN);
        }
        else if (correct == 3) {
            answerButtonBottom.setBackgroundColor(Color.GREEN);
        }

        if ((correct != selected) && (selected == 1)) {
            answerButtonTop.setBackgroundColor(Color.RED);
        }
        else if ((correct != selected) && (selected == 2)) {
            answerButtonMiddle.setBackgroundColor(Color.RED);
        }
        else if ((correct != selected) && (selected == 3)) {
            answerButtonBottom.setBackgroundColor(Color.RED);
        }

        return correct == selected;
    }
}