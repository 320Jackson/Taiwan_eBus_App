package com.example.taiwan_ebus.Database;

import androidx.room.TypeConverter;

import java.util.Date;

public class db_Converter {
    @TypeConverter
    public static Long DatetoValue(Date InputDate){
        return InputDate == null ? null : InputDate.getTime();
    }

    @TypeConverter
    public static Date ValuetoDate(Long InputValue){
        return InputValue == null ? null : new Date(InputValue);
    }
}
