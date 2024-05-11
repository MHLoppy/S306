package com.markheath.lostandfoundapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {ItemEntity.class}, version = 2)
@TypeConverters({Converters.class})
public abstract class ItemDatabase extends RoomDatabase {

    public abstract ItemDaoRx itemDao();
}
