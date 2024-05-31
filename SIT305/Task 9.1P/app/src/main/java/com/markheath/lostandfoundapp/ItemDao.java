package com.markheath.lostandfoundapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// DUE TO USAGE OF RXJAVA, USE ItemDaoRx INSTEAD OF THIS
//@Dao
//public interface ItemDao {
//
//    @Insert
//    void insertItem(ItemEntity item);
//
//    @Query("SELECT * FROM items")
//    List<ItemEntity> getAllItems();
//
//    @Query("SELECT * FROM items WHERE id = :itemId")
//    ItemEntity getItemById(int itemId);
//
//    @Delete
//    void deleteItem(ItemEntity item);
//
//    @Update
//    void updateItem(ItemEntity item);
//}
