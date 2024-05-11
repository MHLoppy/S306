package com.markheath.lostandfoundapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button newAdvertButton;
    Button showAllButton;
    ImageView adImage4;

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

        newAdvertButton = findViewById(R.id.newAdvertButton);
        showAllButton = findViewById(R.id.showAllButton);
        adImage4 = findViewById(R.id.adImage4);

        newAdvertButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NewAdvertActivity.class);
            startActivity(intent);
        });

        showAllButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AllItemsActivity.class);
            startActivity(intent);
        });

        String adUrl = getResources().getString(R.string.urlAd);
        adImage4.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(adUrl));
            startActivity(browserIntent);
        });
    }
}