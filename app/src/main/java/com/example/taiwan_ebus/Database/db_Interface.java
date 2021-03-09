package com.example.taiwan_ebus.Database;

import android.view.Display;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Dao
public interface db_Interface {
    /*新增資料*/
    /*新增路線資訊*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertInfo(RouteInfo Data);
    /*新增路線站位資料*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertMap(RouteMap Data);
    /*新增版本資訊*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertVersion(DataVersion Data);

    /*取得路線資訊資料*/
    /*取得最愛路線*/
    @Query("SELECT * FROM RouteInfo WHERE IsFavorite = 1")
    List<RouteInfo> FavoriteRoute();
    /*取得所有路線*/
    @Query("SELECT * FROM RouteInfo")
    List<RouteInfo> All_Route();
    /*篩選搜尋中的路線*/
    @Query("SELECT * FROM RouteInfo WHERE RouteName LIKE :RouteName ORDER BY RouteName")
    List<RouteInfo> RouteList(String RouteName);
    /*取得資料最新版本ID*/
    @Query("SELECT COUNT(*) FROM DataVersion")
    int getVersionCount();
    /*取得資料版本*/
    @Query("SELECT * FROM DataVersion WHERE CityID = :City")
    DataVersion getVersionoObj(int City);
    /*取得路線站序*/
    @Query("SELECT * FROM RouteMap WHERE RouteUID = :RouteUID AND Direction = :Direction")
    RouteMap getMapObj(String RouteUID, int Direction);
}
