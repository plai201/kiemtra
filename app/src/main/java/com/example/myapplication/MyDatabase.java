package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase {
    private DBhelper dBhelper;
    public MyDatabase(Context context){
        dBhelper = new DBhelper(context);
    }
    public void themSanPham(SanPham sanPham){
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBhelper.COLUMN_TEN, sanPham.getTen());
        values.put(DBhelper.COLUMN_GIA, sanPham.getGia());
        db.insert(DBhelper.TABLE_SANPHAM,null,values);
        db.close();
    }
    public void xoaSanPham(int id){
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        db.delete(DBhelper.TABLE_SANPHAM,DBhelper.COLUMN_ID + "= ?",new String[]{String.valueOf(id)});
        db.close();
    }
    public void capNhapSanPham(SanPham sanPham){
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBhelper.COLUMN_TEN,sanPham.getTen());
        values.put(DBhelper.COLUMN_GIA,sanPham.getGia());
        db.update(DBhelper.TABLE_SANPHAM,values,DBhelper.COLUMN_ID+ "= ?",
                new String[]{String.valueOf(sanPham.getId())});
        db.close();
    }
    public List<SanPham> latTatCaSanPham(){
        List<SanPham> sanPhamList = new ArrayList<>();
        String selectQ = "SELECT * FROM "+ DBhelper.TABLE_SANPHAM;
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQ,null);
        if(cursor.moveToFirst()){
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DBhelper.COLUMN_ID));
                @SuppressLint("Range") String ten = cursor.getString(cursor.getColumnIndex(DBhelper.COLUMN_TEN));
                @SuppressLint("Range") double gia = cursor.getDouble(cursor.getColumnIndex(DBhelper.COLUMN_GIA));
                SanPham sanPham = new SanPham(id, ten, gia);
                sanPhamList.add(sanPham);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return sanPhamList;
    }

}
