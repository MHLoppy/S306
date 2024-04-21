package com.markheath.taskmanagerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "TaskManager.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_library";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "task_title";
    private static final String COLUMN_DESCRIPTION = "task_description";
    private static final String COLUMN_DUEDATE = "task_duedate";

    DatabaseHelper(@Nullable Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME
                + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TITLE + " TEXT, "
                + COLUMN_DESCRIPTION + " TEXT, "
                + COLUMN_DUEDATE + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addTask(String title, String description, String dueDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_DUEDATE, dueDate);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1){
            Toast.makeText(context, "Failed to add task!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Task saved.", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(int order) {
        String query;
        if (order == 1) {
            //Toast.makeText(context, "1", Toast.LENGTH_SHORT).show();
            query = "SELECT * FROM " + TABLE_NAME + " ORDER BY task_duedate ASC";
        }
        else if (order == -1) {
            query = "SELECT * FROM " + TABLE_NAME + " ORDER BY task_duedate DESC";
            //Toast.makeText(context, "-1", Toast.LENGTH_SHORT).show();
        }
        else if (order == 0){
            //Toast.makeText(context, "0", Toast.LENGTH_SHORT).show();
            query = "SELECT * FROM " + TABLE_NAME;
        }
        else {
            //Toast.makeText(context, "other", Toast.LENGTH_SHORT).show();
            query = "SELECT * FROM " + TABLE_NAME;
        }

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

//    Cursor readAllDataAscOrder() {
//        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY task_duedate ASC";
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = null;
//        if (db != null) {
//            cursor = db.rawQuery(query, null);
//        }
//        return cursor;
//    }
//
//    Cursor readAllDataDescOrder() {
//        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY task_duedate DESC";
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = null;
//        if (db != null) {
//            cursor = db.rawQuery(query, null);
//        }
//        return cursor;
//    }

    void updateTaskData(String row_id, String title, String description, String duedate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_DUEDATE, duedate);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to update task data.", Toast.LENGTH_SHORT).show();
        }
        //DEBUGGING
        //if (result == 0) {
        //    Toast.makeText(context, "Task not found?", Toast.LENGTH_LONG).show();
        //}
        //if (result == 1) {
        //    Toast.makeText(context, "adapter success?", Toast.LENGTH_LONG).show();
        //}
    }

    void deleteTask(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});

        if (result == -1) {
            Toast.makeText(context, "Deletion failed.", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Task deleted.", Toast.LENGTH_SHORT).show();
        }
    }
}
