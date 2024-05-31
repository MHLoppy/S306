package com.markheath.lostandfoundapp;

import android.location.Location;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "items")
public class ItemEntity {
    @PrimaryKey(autoGenerate = true) private int id;
    @ColumnInfo(name = "post_type") private String postType;        // implicitly it's Lost or Found
    @ColumnInfo(name = "user_name") private String userName;
    @ColumnInfo(name = "user_phone_number") private String userPhoneNumber;
    @ColumnInfo(name = "item_description") private String itemDescription;
    @ColumnInfo(name = "item_date_found") private LocalDate itemDateFound;
//    @ColumnInfo(name = "item_location_found") private String itemLocationFound;
    @ColumnInfo(name = "item_location_latitude") private double itemLocationLatitude;
    @ColumnInfo(name = "item_location_longitude") private double itemLocationLongitude;
//    @ColumnInfo(name = "item_location_location") private Location itemLocationLocation;

    // ID
    public int getId() { return id; }
    public void setId(int newId) { id = newId; }        // having a *public* method to change the ID sounds insane but the builder demands its inclusion

    // itemType
    public String getPostType() { return postType; }
    public void setPostType(String newItemType) { postType = newItemType; }

    // userName
    public String getUserName() { return userName; }
    public void setUserName(String newUserName) { userName = newUserName; }

    // userPhoneNumber
    public String getUserPhoneNumber() { return userPhoneNumber; }
    public void setUserPhoneNumber(String newPhoneNumber) { userPhoneNumber = newPhoneNumber; }

    // itemDescription
    public String getItemDescription() { return itemDescription; }
    public void setItemDescription(String newItemDescription) { itemDescription = newItemDescription; }

    // itemDateFound
    public LocalDate getItemDateFound() { return itemDateFound; }
    public void setItemDateFound(LocalDate newItemDateFound) { itemDateFound = newItemDateFound; }

//    // itemLocationFound
//    public String getItemLocationFound() { return itemLocationFound; }
//    public void setItemLocationFound(String newItemLocationFound) { itemLocationFound = newItemLocationFound; }
//
    // itemLocationLatitude
    public double getItemLocationLatitude() { return itemLocationLatitude; }
    public void setItemLocationLatitude(double newItemLocationLatitude) { itemLocationLatitude = newItemLocationLatitude; }

    // itemLocationLongitude
    public double getItemLocationLongitude() { return itemLocationLongitude; }
    public void setItemLocationLongitude(double newItemLocationLongitude) { itemLocationLongitude = newItemLocationLongitude; }

//    // itemLocationLocation
//    public Location getItemLocationLocation() { return itemLocationLocation; }
//    public void setItemLocationLocation(Location newItemLocationLocation) { itemLocationLocation = newItemLocationLocation; }
}
