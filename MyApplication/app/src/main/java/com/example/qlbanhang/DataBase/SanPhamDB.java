package com.example.qlbanhang.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.qlbanhang.Model.NhanVien;
import com.example.qlbanhang.Model.SanPham;

import java.util.ArrayList;

public class SanPhamDB {
    SQLiteDatabase database;
    DBHelper dbHelper;

    public SanPhamDB(Context context) {
        dbHelper = new DBHelper(context, "SanPham");
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
        String[] cot = new String[dbHelper.getSanpham().size()];
        for (int i = 0; i < dbHelper.getSanpham().size(); i++){
            cot[i] = dbHelper.getSanpham().get(i);
        }

        Cursor cursor = null;
        cursor = database.query("SanPham", cot, null, null, null, null,
                dbHelper.getSanpham().get(0) + " DESC");
        return cursor;
    }

    public ArrayList<SanPham> getAllSV (){
        ArrayList<SanPham> SanPhams = new ArrayList<>();
        Cursor cursor = layTatCaDuLieu();
        while (cursor.moveToNext()){
            SanPham SanPham = new SanPham();
            SanPham.setS_TenSP(cursor.getString(1));
            SanPham.setS_MaSP(cursor.getString(0));
            SanPham.setD_DonGia(Double.valueOf(cursor.getString(3)));
            SanPham.setS_DonViTinh(cursor.getString(2));
            SanPham.setB_hinh(cursor.getBlob(4));
            SanPhams.add(SanPham);
        }

        return SanPhams;
    }

    public ArrayList<SanPham> getAllMa (String ma){
        ArrayList<SanPham> SanPhams = new ArrayList<>();
        Cursor cursor = layTatCaDuLieu();
        while (cursor.moveToNext()){
            if(ma.contains(cursor.getString(0)) || ma.contains(cursor.getString(1))){
                SanPham SanPham = new SanPham();
                SanPham.setS_TenSP(cursor.getString(1));
                SanPham.setS_MaSP(cursor.getString(0));
                SanPham.setD_DonGia(Double.valueOf(cursor.getString(3)));
                SanPham.setS_DonViTinh(cursor.getString(2));
                SanPham.setB_hinh(cursor.getBlob(4));
                SanPhams.add(SanPham);
            }
        }

        return SanPhams;
    }

    public SanPham getSP (String ma){
        String[] cot = new String[dbHelper.getSanpham().size()];
        for (int i = 0; i < dbHelper.getSanpham().size(); i++){
            cot[i] = dbHelper.getSanpham().get(i);
        }

        Cursor cursor = null;
        cursor = database.query("SanPham", cot, cot[0] + " = ?", new String[]{ma}, null, null, null);
        SanPham SanPham = new SanPham();
        while (cursor.moveToNext()){

            SanPham.setS_TenSP(cursor.getString(1));
            SanPham.setS_MaSP(cursor.getString(0));
            SanPham.setD_DonGia(Double.valueOf(cursor.getString(3)));
            SanPham.setS_DonViTinh(cursor.getString(2));
            SanPham.setB_hinh(cursor.getBlob(4));
        }

        return SanPham;
    }

    public long them(SanPham SanPham) {
        ContentValues values = new ContentValues();
        for (int i = 1; i < dbHelper.getSanpham().size() - 1; i++){
            values.put(dbHelper.getSanpham().get(i),
                    SanPham.getArray().get(i));
        }
        values.put("_hinh", SanPham.getB_hinh());
        return database.insert("SanPham", null, values);
    }

    public long xoa(SanPham SanPham) {
        return database.delete("SanPham", dbHelper.getSanpham().get(0) + " = " + "'" +
                SanPham.getS_MaSP() + "'", null);
    }


    public long sua(SanPham SanPham) {
        ContentValues values = new ContentValues();
        for (int i = 1; i < dbHelper.getSanpham().size() - 1; i++){
            values.put(dbHelper.getSanpham().get(i),
                    SanPham.getArray().get(i));
        }
        values.put("_hinh", SanPham.getB_hinh());
        return database.update("SanPham", values,
                dbHelper.getSanpham().get(0) + " = "
                        + SanPham.getS_MaSP(), null);
    }

}
