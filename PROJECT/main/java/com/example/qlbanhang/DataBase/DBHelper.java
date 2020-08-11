package com.example.qlbanhang.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {


    // Tên cơ sở dữ liệu
    public static final String TEN_DATABASE = "qlbanhang.db";

    private ArrayList<String> nhanvien = new ArrayList<>();
    private ArrayList<String> sanpham = new ArrayList<>();
    private ArrayList<String> hoadon = new ArrayList<>();
    private ArrayList<String> chitiethoadon = new ArrayList<>();

    public ArrayList<String> getNhanvien() {
        return nhanvien;
    }

    public ArrayList<String> getSanpham() {
        return sanpham;
    }

    public ArrayList<String> getHoadon() {
        return hoadon;
    }

    public ArrayList<String> getChitiethoadon() {
        return chitiethoadon;
    }

    public DBHelper(Context context, String tenBang) {
        super(context,TEN_DATABASE,null,1);
        //nhan vien

        nhanvien.add("kc_manv");
        nhanvien.add("_ten");
        nhanvien.add("_ho");
        nhanvien.add("_sdt");
        nhanvien.add("_matkhau");
        nhanvien.add("_gioitinh");
        nhanvien.add("_hinh");

        //sanpham
        sanpham.add("kc_masp");
        sanpham.add("_tensp");
        sanpham.add("_donvitinh");
        sanpham.add("_dongia");
        sanpham.add("_hinh");

        //hoa don
        hoadon.add("kc_mahd");
        hoadon.add("kp_manv");
        hoadon.add("_ngaylap");

        //chi tiet hoa don
        chitiethoadon.add("kp_mahd");
        chitiethoadon.add("kp_masp");
        chitiethoadon.add("_soluong");
    }

    private String sqlTaoBang(ArrayList<String> cot, String name, ArrayList<String> bang){
        String swap = "" + "create table if not exists " + name + " ( ";
        int i = 0;
        boolean kiemKhoa = false;
        boolean landau = true;
        for (String s: cot
        ) {
            if(s.contains("kc_")){
                swap += s + " integer primary key autoincrement ";
                kiemKhoa = true;
            }else{
                if(!kiemKhoa){
                    if(landau){
                        swap +=  s + " text not null ";
                        landau = false;
                    }else {
                        swap += " , " + s + " text not null ";
                    }
                }else {
                    swap += ", " + s + " text not null ";
                }

            }
        }
        for (String s: cot){
            if(s.contains("kp_")){
                swap += ", foreign key("+ s +") references " + bang.get(i)+"("+ s.replaceFirst("kp_", "kc_") +") ";
                i++;
            }
        }
        swap += ");";

        return swap;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ArrayList<String> hoadonTable = new ArrayList<>();
        hoadonTable.add("NhanVien");
        ArrayList<String> dsSP = new ArrayList<>();
        dsSP.add("HoaDon");
        dsSP.add("SanPham");

        db.execSQL(sqlTaoBang(nhanvien, "NhanVien", new ArrayList<String>()));
        db.execSQL(sqlTaoBang(sanpham, "SanPham", new ArrayList<String>()));
        db.execSQL(sqlTaoBang(hoadon, "HoaDon", hoadonTable));
        db.execSQL(sqlTaoBang(chitiethoadon, "ChiTietHoaDon", dsSP));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
