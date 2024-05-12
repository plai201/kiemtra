package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {

    public static final String COLUMN_TEN ="ten" ;
    public static final String COLUMN_GIA = "gia";
    public static final String TABLE_SANPHAM = "sanpham";
    public static final String COLUMN_ID = "id";
    private static final String DATABASE_NAME = "db_sanpham";
    private static final  int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE_SANPHAM = "CREATE TABLE " + TABLE_SANPHAM + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_TEN + " TEXT," +
            COLUMN_GIA + " REAL" + ")";

    public DBhelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SANPHAM);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_SANPHAM);
        onCreate(db);
    }
}
