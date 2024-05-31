package com.markheath.lostandfoundapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.Period;

public class RemoveItemActivity extends AppCompatActivity {

    TextView foundTextViewRemove, descriptionTextViewRemove, dateTextViewRemove
            , locationTextViewRemove, nameTextViewRemove, phoneNumberTextViewRemove;
    Button removeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_remove_item_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        foundTextViewRemove       = findViewById(R.id.foundTextViewRemove);
        descriptionTextViewRemove = findViewById(R.id.descriptionTextViewRemove);
        dateTextViewRemove        = findViewById(R.id.dateTextViewRemove);
        locationTextViewRemove    = findViewById(R.id.locationTextViewRemove);

        nameTextViewRemove        = findViewById(R.id.nameTextViewRemove);
        phoneNumberTextViewRemove = findViewById(R.id.phoneNumberTextViewRemove);

        removeButton = findViewById(R.id.removeButton);

        // get the ID of our item from the intent extra
        Intent oldIntent = getIntent();
        int itemId;
        if (oldIntent != null) {
            itemId = oldIntent.getIntExtra("Id", 0);
        } else {
            itemId = 0;
        }

        // look up the item using the ID we just got
        ItemDatabase itemDatabase = ItemDatabaseClient.getInstance(RemoveItemActivity.this);
        ItemEntity thisItem = itemDatabase.itemDao().getItemById(itemId);

        // populate the UI with the item's information
        foundTextViewRemove.setText(thisItem.getPostType());
        descriptionTextViewRemove.setText(thisItem.getItemDescription());
        String daysAgo = getDaysAgo(thisItem.getItemDateFound());       // formatted date to X days ago
        String combinedDateString = thisItem.getItemDateFound() + " (" + daysAgo + ")";
        dateTextViewRemove.setText(combinedDateString);                 // show combined date output
        //locationTextViewRemove.setText(thisItem.getItemLocationFound());

        // changes for 9.1P
        double latitude = thisItem.getItemLocationLatitude();
        double longitude = thisItem.getItemLocationLongitude();
        String locationString = String.valueOf(latitude) + " " + String.valueOf(longitude);
        locationTextViewRemove.setText(locationString);

        nameTextViewRemove.setText(thisItem.getUserName());
        phoneNumberTextViewRemove.setText(thisItem.getUserPhoneNumber());

        // remove button
        removeButton.setOnClickListener(v -> {

            itemDatabase.itemDao().deleteItem(thisItem);

            // set up the recyclerview
            Intent resultIntent = new Intent();
            resultIntent.putExtra("removedId", itemId);
            setResult(Activity.RESULT_OK, resultIntent);

            // cheating the refresh because I'm exhausted
            Intent intent = new Intent(RemoveItemActivity.this, RemoveItemActivity.class);
            startActivity(intent);

            finish();
        });

    }

    // again: requires API 26 but I'm just handwaving that away for time's sake
    public static String getDaysAgo(LocalDate date) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(date, currentDate);
        int days = period.getDays();

        if (days == 0) {
            return "Today";
        } else if (days == 1) {
            return "1 day ago";
        } else {
            return days + " days ago";
        }
    }
}