package com.example.qlbanhang.Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HoaDon {
    private String s_MaHD, s_NgayLap, s_MaNV;

    public HoaDon(String s_MaHD, String s_NgayLap, String s_MaNV) {
        this.s_MaHD = s_MaHD;
        this.s_NgayLap = s_NgayLap;
        this.s_MaNV = s_MaNV;
    }

    public ArrayList<String> getHoaDon(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add(this.s_MaHD);
        strings.add(this.s_MaNV);
        strings.add(this.s_NgayLap);
        return strings;
    }

    public HoaDon() {
        this.s_NgayLap = layGio();
    }

    public String getS_MaHD() {
        return s_MaHD;
    }

    public void setS_MaHD(String s_MaHD) {
        this.s_MaHD = s_MaHD;
    }

    public String getS_NgayLap() {
        return s_NgayLap;
    }

    public void setS_NgayLap(String s_NgayLap) {
        this.s_NgayLap = s_NgayLap;
    }

    public String getS_MaNV() {
        return s_MaNV;
    }

    public void setS_MaNV(String s_MaNV) {
        this.s_MaNV = s_MaNV;
    }

    public String layGio(){
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault ()).format(new Date());
        return currentDate;
    }

}
