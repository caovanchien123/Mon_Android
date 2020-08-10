package com.example.qlbanhang.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.qlbanhang.Model.NhanVien;
import com.example.qlbanhang.Model.SanPham;
import com.example.qlbanhang.UI.MainActivity;

import java.util.ArrayList;

public class ChiTietHoaDonDB {
    SQLiteDatabase database;
    DBHelper dbHelper;

    public ChiTietHoaDonDB(Context context) {
        dbHelper = new DBHelper(context, "Chi");
        try {
            database = dbHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            database = dbHelper.getReadableDatabase();
        }

    }

    public void close() {
        dbHelper.close();

    }

    public void setAllSanPham(ArrayList<SanPham> sanPhams, String ma, String sl){
        for (SanPham sanPham:
        sanPhams) {
            them(ma, sanPham.getS_MaSP(), sl);
        }
    }

    public Cursor getCursor (String ma){
        String[] cot = new String[dbHelper.getChitiethoadon().size()];
        for (int i = 0; i < dbHelper.getChitiethoadon().size(); i++){
            cot[i] = dbHelper.getChitiethoadon().get(i);
        }

        Cursor cursor = null;
        cursor = database.query("ChiTietHoaDon", cot, cot[0] + " = ?", new String[]{ma}, null, null, null);
        return cursor;
    }

    public Double getFull (String ma){
        Double tong = 0.0;
        Cursor cursor = getCursor(ma);
        while (cursor.moveToNext()){
            tong += MainActivity.sanPhamDB.getSP(cursor.getString(1)).getD_DonGia();
        }

        return tong;
    }

    public long them(String ma, String maSP, String sl) {
        ContentValues values = new ContentValues();
            values.put(dbHelper.getChitiethoadon().get(0), ma);
            values.put(dbHelper.getChitiethoadon().get(1), maSP);
            values.put(dbHelper.getChitiethoadon().get(2), sl);
        return database.insert("ChiTietHoaDon", null, values);
    }

    public long xoa(String ma) {
        return database.delete("ChiTietHoaDon", dbHelper.getChitiethoadon().get(0) + " = " + "'" +
                ma + "'", null);
    }

}
