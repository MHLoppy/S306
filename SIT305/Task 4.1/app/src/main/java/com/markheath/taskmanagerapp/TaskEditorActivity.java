package com.markheath.taskmanagerapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TaskEditorActivity extends AppCompatActivity {

    EditText titleInput, descriptionInput, dateInput;
    Button addOrEditButton, deleteButton;

    String id, title, description, duedate;
    ImageView imageAd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_task_editor);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar navbar = findViewById(R.id.navbar);
        setSupportActionBar(navbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        titleInput = findViewById(R.id.titleInput);
        descriptionInput = findViewById(R.id.descriptionInput);
        dateInput = findViewById(R.id.dateInput);
        addOrEditButton = findViewById(R.id.addOrEditButton);
        deleteButton = findViewById((R.id.deleteButton));

        imageAd2 = findViewById(R.id.imageAd2);

        getAndSetIntentData();
        addOrEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseHelper taskDB = new DatabaseHelper(TaskEditorActivity.this);
                boolean validTask = false;

                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    Date parsedDate = dateFormat.parse(dateInput.getText().toString().trim());

                    // instead of actually using the "proper" date, just save the string
                    //   this saves having to parse the more complex date later
                    //   but still lets us check the input for the formatting of our choice
                    // TODO: however for consistency we'll enforce the leading zeroes on day/month
                    //   which the above parsing otherwise lets through
                    //   https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html
                    //parsedDate = parsedDate.format(parsedDate)
                    validTask = true;
                }
                catch (ParseException ex) {
                    Toast.makeText(getBaseContext(), "Date format must be YYYY/MM/DD", Toast.LENGTH_SHORT).show();
                }

                if (validTask) {
                    // if adding task
                    if (addOrEditButton.getText().toString().equals("Add")) {
                        taskDB.addTask(titleInput.getText().toString().trim(),
                                descriptionInput.getText().toString().trim(),
                                dateInput.getText().toString().trim());
                    }
                    // if updating task
                    else if (addOrEditButton.getText().toString().equals("Save changes")) {
                        // DEBUGGING MESSAGE
                        //Toast.makeText(TaskEditorActivity.this, "updating.", Toast.LENGTH_SHORT).show();
                        title = titleInput.getText().toString().trim();
                        description = descriptionInput.getText().toString().trim();
                        duedate = dateInput.getText().toString().trim();
                        taskDB.updateTaskData(id, title, description, duedate);
                    }
                    // return to main screen (task list overview) afterwards
                    finish();
                }
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DatabaseHelper taskDB = new DatabaseHelper(TaskEditorActivity.this);
                taskDB.deleteTask(id);

                // return to main screen (task list overview) afterwards
                finish();
            }
        });

        String adUrl = getResources().getString(R.string.urlAd);
        imageAd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(adUrl));
                startActivity(browserIntent);
            }
        });
    }

    void getAndSetIntentData() {
        if(getIntent().hasExtra("id")
                && getIntent().hasExtra("title")
                && getIntent().hasExtra("description")
                && getIntent().hasExtra("duedate")
                && getIntent().hasExtra("button")) {
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            description = getIntent().getStringExtra("description");
            duedate = getIntent().getStringExtra("duedate");
            String buttonText = getIntent().getStringExtra("button");

            titleInput.setText(title);
            descriptionInput.setText(description);
            dateInput.setText(duedate);
            addOrEditButton.setText(buttonText);

            deleteButton.setVisibility(View.VISIBLE);
        } else {
            addOrEditButton.setText("Add");
            deleteButton.setVisibility(View.INVISIBLE);
        }
    }
}