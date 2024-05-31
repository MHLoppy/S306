package com.markheath.lostandfoundapp;

import android.content.Context;
import androidx.room.Room;

public class ItemDatabaseClient {

    private static ItemDatabase itemDatabase;
    private static final String DATABASE_NAME = "item_database";

    // singleton pattern for the database
    public static synchronized ItemDatabase getInstance(Context context) {
        if (itemDatabase == null) {
            itemDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    ItemDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()       // TODO: (see comment below)
                    .build();
        }
        return itemDatabase;
    }
}

/// TODO: <(>expanded comment>:
/// I KNOW I KNOW I KNOW but I spent MANY. HOURS. trying to find the right combination of documentation and it's now 6:24AM.
/// I spent all that time getting the basics of Room, moving to RxJava (and getting fleeced by incorrect and/or outdated documentation about them!!!!!!!!)
/// FINALLY get the app to run and then there's no easy, non-deprecated way to do the async in Java (only Kotlin)
/// the unit should probably just be taught in Kotlin at this point (with Java fallback as required);
///   the newbie dev experience using Java on modern Android is BAD. a LOT of stuff assumes Kotlin because it's the standard now, so finding
///   appropriate Java-specific documentation that IS NOT OUT OF DATE is sometimes QUITE DIFFICULT.