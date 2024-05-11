package com.markheath.lostandfoundapp;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDate;

public class NewAdvertActivity extends AppCompatActivity {

    RadioGroup postTypeRadioGroup;
    RadioButton lostRadioButton, foundRadioButton, selectedRadioButton;
    Button saveButton;
    TextInputEditText nameTextInputEditText, phoneTextInputEditText, descriptionTextInputEditText,
            dateTextInputEditText, locationTextInputEditText;
    String postType, userName, userPhoneNumber, itemDescription, itemLocationFound;
    LocalDate itemDateFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_advert_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        postTypeRadioGroup = findViewById(R.id.postTypeRadioGroup);
        lostRadioButton    = findViewById(R.id.lostRadioButton);
        foundRadioButton   = findViewById(R.id.foundRadioButton);

        nameTextInputEditText        = findViewById(R.id.nameTextInputEditText);
        phoneTextInputEditText       = findViewById(R.id.phoneTextInputEditText);
        descriptionTextInputEditText = findViewById(R.id.descriptionTextInputEditText);
        dateTextInputEditText        = findViewById(R.id.dateTextInputEditText);
        locationTextInputEditText    = findViewById(R.id.locationTextInputEditText);

        saveButton = findViewById(R.id.saveButton);

        postTypeRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {

            selectedRadioButton = findViewById(checkedId);
            if (selectedRadioButton != null) {

                if (selectedRadioButton.getId() == R.id.lostRadioButton) {
                    postType = "Lost";      // this could ultimately be simplified to have ItemEntity store a bool called isFound instead of a string, but oh well
                } else if (selectedRadioButton.getId() == R.id.foundRadioButton) {
                    postType = "Found";
                }
            }
        });

        saveButton.setOnClickListener(v -> {

            int hasValid = 0;
            int requiredValid = 2;

            userName          = nameTextInputEditText.getText().toString();
            userPhoneNumber   = phoneTextInputEditText.getText().toString();
            itemDescription   = descriptionTextInputEditText.getText().toString();

            itemLocationFound = locationTextInputEditText.getText().toString();

            try {
                itemDateFound = LocalDate.parse(dateTextInputEditText.getText().toString());        // requires API 26, but I'm skipping proper acknowledgement of that fact in the interest of saving time
                hasValid++;
            }
            catch (Exception ex) {
                Toast.makeText(NewAdvertActivity.this, "Invalid date format.", Toast.LENGTH_SHORT).show();
            }

            if (selectedRadioButton != null) {
                hasValid++;
            }

            // require a valid radio button selection and a valid date found
            if (hasValid >= requiredValid) {

                // don't save unless the stuff seems to be valid
                ItemDatabase itemDatabase = ItemDatabaseClient.getInstance(NewAdvertActivity.this);

                ItemEntity newItem = new ItemEntity();
                newItem.setPostType(postType);
                newItem.setUserName(userName);
                newItem.setUserPhoneNumber(userPhoneNumber);
                newItem.setItemDescription(itemDescription);
                newItem.setItemDateFound(itemDateFound);
                newItem.setItemLocationFound(itemLocationFound);

                itemDatabase.itemDao().insertItem(newItem);

                // it's not explicitly said what to do after saving, so I've decided to go to the list
                Intent intent = new Intent(NewAdvertActivity.this, AllItemsActivity.class);
                startActivity(intent);
            } else if (selectedRadioButton == null) {
                Toast.makeText(NewAdvertActivity.this, "Must select a post type! (lost or found)", Toast.LENGTH_SHORT).show();
            } else {
                // (probably invalid date format still)
            }
        });
    }
}