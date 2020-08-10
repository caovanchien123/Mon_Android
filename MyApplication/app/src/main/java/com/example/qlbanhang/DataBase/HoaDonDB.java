package com.example.qlbanhang.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.qlbanhang.Model.HoaDon;
import com.example.qlbanhang.Model.HoaDon;
import com.example.qlbanhang.Model.SanPham;

import java.util.ArrayList;

public class HoaDonDB {
    SQLiteDatabase database;
    DBHelper dbHelper;

    public HoaDonDB(Context context) {
        dbHelper = new DBHelper(context, "HoaDon");
        try {
            database = dbHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            database = dbHelper.getReadableDatabase();
        }

    }

    public void close() {
        dbHelper.close();

    }

    public Cursor layTatCaDuLieu() {
        String[] cot = new String[dbHelper.getHoadon().size()];
        for (int i = 0; i < dbHelper.getHoadon().size(); i++){
            cot[i] = dbHelper.getHoadon().get(i);
        }

        Cursor cursor = null;
        cursor = database.query("HoaDon", cot, null, null, null, null,
                dbHelper.getHoadon().get(1) + " DESC");
        return cursor;
    }
    private String s_MaHD, s_NgayLap, s_MaNV;
    private ArrayList<SanPham> sanPhams;
    public ArrayList<HoaDon> getAllSV (){
        ArrayList<HoaDon> HoaDons = new ArrayList<>();
        Cursor cursor = layTatCaDuLieu();
        while (cursor.moveToNext()){
            HoaDon HoaDon = new HoaDon();
            HoaDon.setS_MaNV(cursor.getString(1));
            HoaDon.setS_MaHD(cursor.getString(0));
            HoaDon.setS_NgayLap(cursor.getString(2));
            HoaDons.add(HoaDon);
        }

        return HoaDons;
    }
    public ArrayList<HoaDon> getAll (String ma){
        ArrayList<HoaDon> HoaDons = new ArrayList<>();
        Cursor cursor = layTatCaDuLieu();
        while (cursor.moveToNext()){
            if(cursor.getString(1).equals(ma)){
                HoaDon HoaDon = new HoaDon();
                HoaDon.setS_MaNV(cursor.getString(1));
                HoaDon.setS_MaHD(cursor.getString(0));
                HoaDon.setS_NgayLap(cursor.getString(2));
                HoaDons.add(HoaDon);
            }
        }

        return HoaDons;
    }

    public long them(HoaDon HoaDon) {
        ContentValues values = new ContentValues();
        for (int i = 1; i < dbHelper.getHoadon().size(); i++){
            values.put(dbHelper.getHoadon().get(i),
                    HoaDon.getHoaDon().get(i));
            Log.e("a", i+"");
        }
        return database.insert("HoaDon", null, values);
    }

    public long xoa(HoaDon HoaDon) {
        return database.delete("HoaDon", dbHelper.getHoadon().get(0) + " = " + "'" +
                HoaDon.getS_MaHD() + "'", null);
    }

    public long sua(HoaDon HoaDon) {
        ContentValues values = new ContentValues();
        for (int i = 1; i < dbHelper.getHoadon().size(); i++){
            values.put(dbHelper.getHoadon().get(i),
                    HoaDon.getHoaDon().get(i));
            Log.e("a", i+"");
        }
        return database.update("HoaDon", values,
                dbHelper.getHoadon().get(0) + " = "
                        + HoaDon.getS_MaNV(), null);
    }

}
