package com.markheath.lostandfoundapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.OnConflictStrategy;

import java.util.List;

//import io.reactivex.Completable;                // rxjava2
import io.reactivex.rxjava3.core.Completable;   // rxjava3
import io.reactivex.rxjava3.core.Single;

// https://developer.android.com/training/data-storage/room/async-queries#java
@Dao
public interface ItemDaoRx {

    /// IMPLIED IN DOCUMENTATION (but only discovered by banging my head on this for far too long):
    /// if you want to run an explicit QUERY (with @query and SQL) you can't use Completable
    /// you have to use Single<blahblahblah> otherwise it can't convert the return type

    // EXCEPT THAT SINGLES HAVE PROBLEMS CONVERTING TO LISTS IN V3...........
    // so we're back to being "dumb" and using lists etc and blocking the UI, GREAT USE OF MY TIME :kms:

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertItem(ItemEntity item);        // changed from Completable

    @Query("SELECT * FROM items")
    public List<ItemEntity> getAllItems();          // changed from Single

    @Query("SELECT * FROM items WHERE id = :itemId")
    public ItemEntity getItemById(int itemId);      // changed from Single

    @Delete
    public void deleteItem(ItemEntity item);        // changed from Completable

    @Update
    public void updateItem(ItemEntity item);        // changed from Completable
}
