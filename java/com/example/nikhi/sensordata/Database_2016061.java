package com.example.nikhi.sensordata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database_2016061 extends SQLiteOpenHelper {

    private static final String name = "RecordDB.db";
    private SQLiteDatabase db;

    public Database_2016061(Context context) {
        super(context, name, null, 1);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL("CREATE TABLE ACCELEROMETER (_ID INTEGER PRIMARY KEY AUTOINCREMENT, X_VALUE REAL, Y_VALUE REAL, Z_VALUE REAL, DATETIME DEFAULT CURRENT_TIMESTAMP)");
        db.execSQL("CREATE TABLE SHAKE_DETECTION (_ID INTEGER PRIMARY KEY AUTOINCREMENT, VALUE REAL, DATETIME DEFAULT CURRENT_TIMESTAMP)");
        db.execSQL("CREATE TABLE ORIENTATION (_ID INTEGER PRIMARY KEY AUTOINCREMENT, AZIMUTH REAL, PITCH REAL, ROLL REAL, DATETIME DEFAULT CURRENT_TIMESTAMP)");
        db.execSQL("CREATE TABLE PROXIMITY (_ID INTEGER PRIMARY KEY AUTOINCREMENT, VALUE REAL, DATETIME DEFAULT CURRENT_TIMESTAMP)");
        db.execSQL("CREATE TABLE GYROSCOPE (_ID INTEGER PRIMARY KEY AUTOINCREMENT, X_VALUE REAL, Y_VALUE REAL, Z_VALUE REAL, DATETIME DEFAULT CURRENT_TIMESTAMP)");
        db.execSQL("CREATE TABLE GPS (_ID INTEGER PRIMARY KEY AUTOINCREMENT, LATITUDE REAL, LONGITUDE REAL, DATETIME DEFAULT CURRENT_TIMESTAMP)");
        db.execSQL("CREATE TABLE NEAR_FACE (_ID INTEGER PRIMARY KEY AUTOINCREMENT, AZIMUTH REAL, PITCH REAL, ROLL REAL, DATETIME DEFAULT CURRENT_TIMESTAMP)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
