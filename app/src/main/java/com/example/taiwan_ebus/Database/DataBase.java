package com.example.taiwan_ebus.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {RouteInfo.class, RouteMap.class, DataVersion.class}, version = 6, exportSchema = false)
@TypeConverters({db_Converter.class})
public abstract class DataBase extends RoomDatabase {
    public static final String db_Name = "Taipei_eBus_DB";/*資料庫名稱*/
    private static volatile DataBase Instance;/*資料庫本體*/

    /*Singleton建立*/
    public static synchronized DataBase getInstance(Context context){
        if(Instance == null){
            Instance = create(context);
        }
        return Instance;
    }

    /*建立資料庫*/
    private static DataBase create(final Context context){
        return Room.databaseBuilder(context, DataBase.class, db_Name).fallbackToDestructiveMigration().build();
    }

    /*綁定對外方式*/
    public abstract db_Interface getInterface();
}
