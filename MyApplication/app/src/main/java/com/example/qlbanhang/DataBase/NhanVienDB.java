package com.example.qlbanhang.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.qlbanhang.Model.NhanVien;
import com.example.qlbanhang.UI.MainActivity;

import java.util.ArrayList;

public class NhanVienDB {
    SQLiteDatabase database;
    DBHelper dbHelper;

    public NhanVienDB(Context context) {
        dbHelper = new DBHelper(context, "NhanVien");
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
        String[] cot = new String[dbHelper.getNhanvien().size()];
        for (int i = 0; i < dbHelper.getNhanvien().size(); i++){
            cot[i] = dbHelper.getNhanvien().get(i);
        }

        Cursor cursor = null;
        cursor = database.query("NhanVien", cot, null, null, null, null,
                dbHelper.getNhanvien().get(1) + " DESC");
        return cursor;
    }

    public ArrayList<NhanVien> getAllSV (){
        ArrayList<NhanVien> NhanViens = new ArrayList<>();
        Cursor cursor = layTatCaDuLieu();
        while (cursor.moveToNext()){
            NhanVien NhanVien = new NhanVien();
            NhanVien.setS_MaNV(cursor.getString(0));
            NhanVien.setS_TenNV(cursor.getString(1));
            NhanVien.setS_HoNV(cursor.getString(2));
            NhanVien.setS_SDT(cursor.getString(3));
            NhanVien.setS_MatKhau(cursor.getString(4));
            if(cursor.getString(5).equals("0")){
                NhanVien.setB_GioiTinh(true);
            }else {
                NhanVien.setB_GioiTinh(false);
            }
            NhanVien.setB_hinh(cursor.getBlob(6));
            NhanViens.add(NhanVien);
        }

        return NhanViens;
    }

    public ArrayList<NhanVien> getAllMa (String ma){
        ArrayList<NhanVien> NhanViens = new ArrayList<>();
        Cursor cursor = layTatCaDuLieu();
        while (cursor.moveToNext()){
            if(ma.contains(cursor.getString(0)) || ma.contains(cursor.getString(1)) || ma.contains(cursor.getString(2))){
                NhanVien NhanVien = new NhanVien();
                NhanVien.setS_MaNV(cursor.getString(0));
                NhanVien.setS_TenNV(cursor.getString(1));
                NhanVien.setS_HoNV(cursor.getString(2));
                NhanVien.setS_SDT(cursor.getString(3));
                NhanVien.setS_MatKhau(cursor.getString(4));
                if(cursor.getString(5).equals("0")){
                    NhanVien.setB_GioiTinh(true);
                }else {
                    NhanVien.setB_GioiTinh(false);
                }
                NhanVien.setB_hinh(cursor.getBlob(6));
                NhanViens.add(NhanVien);
            }
        }

        return NhanViens;
    }

    public NhanVien getNV (String ma){
        String[] cot = new String[dbHelper.getNhanvien().size()];
        for (int i = 0; i < dbHelper.getNhanvien().size(); i++){
            cot[i] = dbHelper.getNhanvien().get(i);
        }

        Cursor cursor = null;
        cursor = database.query("NhanVien", cot, cot[0] + " = ?", new String[]{ma}, null, null, null);
        NhanVien NhanVien = new NhanVien();
        while (cursor.moveToNext()){
            NhanVien.setS_MaNV(cursor.getString(0));
            NhanVien.setS_TenNV(cursor.getString(1));
            NhanVien.setS_HoNV(cursor.getString(2));
            NhanVien.setS_SDT(cursor.getString(3));
            NhanVien.setS_MatKhau(cursor.getString(4));
            if(cursor.getString(5).equals("0")){
                NhanVien.setB_GioiTinh(true);
            }else {
                NhanVien.setB_GioiTinh(false);
            }
            NhanVien.setB_hinh(cursor.getBlob(6));
        }

        return NhanVien;
    }

    public long them(NhanVien NhanVien) {
        ContentValues values = new ContentValues();
        for (int i = 1; i < dbHelper.getNhanvien().size() - 1; i++){
            values.put(dbHelper.getNhanvien().get(i),
                    NhanVien.getNhanVien().get(i));
        }
        values.put("_hinh", NhanVien.getB_hinh());
        return database.insert("NhanVien", null, values);
    }

    public long xoa(NhanVien NhanVien) {
        return database.delete("NhanVien", dbHelper.getNhanvien().get(0) + " = " + "'" +
                NhanVien.getS_MaNV() + "'", null);
    }

    public long sua(NhanVien nhanVien) {
        ContentValues values = new ContentValues();
        for (int i = 1; i < dbHelper.getNhanvien().size() - 1; i++){
            values.put(dbHelper.getNhanvien().get(i),
                    nhanVien.getNhanVien().get(i));
        }
        values.put("_hinh", nhanVien.getB_hinh());
        Log.e("aaa", nhanVien.getS_MaNV()+"");
        return database.update("NhanVien", values,
                dbHelper.getNhanvien().get(0) + " = "
                        + nhanVien.getS_MaNV(), null);
    }

}
