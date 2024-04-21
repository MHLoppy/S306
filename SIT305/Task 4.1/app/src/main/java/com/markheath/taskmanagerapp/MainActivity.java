package com.markheath.taskmanagerapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView taskListRecView;
    FloatingActionButton addButton;

    DatabaseHelper taskDb;
    ArrayList<String> taskId, taskTitle, taskDescription, taskDuedate;
    RecViewAdapter recViewAdapter;

    int order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.taskListRecView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar navbar = findViewById(R.id.navbar);
        setSupportActionBar(navbar);

        taskListRecView = findViewById(R.id.taskListRecView);
        addButton = findViewById(R.id.addOrEditButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TaskEditorActivity.class);
                //startActivity(intent);
                startActivityForResult(intent, 1);
            }
        });

        taskDb = new DatabaseHelper(MainActivity.this);
        taskId          = new ArrayList<>();
        taskTitle       = new ArrayList<>();
        taskDescription = new ArrayList<>();
        taskDuedate     = new ArrayList<>();

        // have to call before the adapter is used since the data needs to be populated
        SharedPreferences sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        order = sharedPref.getInt("order", 0);
        storeDataInArrays(order);

        recViewAdapter = new RecViewAdapter(MainActivity.this, this, taskId, taskTitle,
                                            taskDescription, taskDuedate);
        taskListRecView.setAdapter(recViewAdapter);
        taskListRecView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // refresh the task layout
        if (requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(int order) {
        Cursor cursor = taskDb.readAllData(order);
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No tasks to display.", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()) {
                taskId.add(cursor.getString(0));
                taskTitle.add(cursor.getString(1));
                taskDescription.add(cursor.getString(2));
                taskDuedate.add(cursor.getString(3));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filter_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        SharedPreferences sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        if (item.getItemId() == R.id.filter_asc) {
            Toast.makeText(this, "Tasks sorted in ascending order.", Toast.LENGTH_SHORT).show();
            order = 1;
        }
        else if (item.getItemId() == R.id.filter_desc) {
            Toast.makeText(this, "Tasks sorted in descending order.", Toast.LENGTH_SHORT).show();
            order = -1;
        }
        else if (item.getItemId() == R.id.filter_def) {
            Toast.makeText(this, "Tasks sorted in default order.", Toast.LENGTH_SHORT).show();
            order = 0;
        }

        editor.putInt("order", order);
        editor.apply();
        recreate();

        return super.onOptionsItemSelected(item);
    }
}