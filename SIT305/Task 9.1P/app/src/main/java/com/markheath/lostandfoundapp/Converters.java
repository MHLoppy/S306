package com.markheath.lostandfoundapp;

import androidx.room.TypeConverter;
import java.time.LocalDate;

// to convert between date/time and strings for database storage
public class Converters {

    @TypeConverter
    public static LocalDate fromString(String value) {
        return value == null ? null : LocalDate.parse(value);       // requires API 26, for time's sake I'm not diving into the flow-on effects of doing that
    }

    @TypeConverter
    public static String dateToString(LocalDate date) {
        return date == null ? null : date.toString();
    }
}
