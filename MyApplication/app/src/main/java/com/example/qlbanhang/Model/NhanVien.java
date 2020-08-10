package com.example.qlbanhang.Model;

import java.util.ArrayList;

public class NhanVien {
    private String s_MaNV, s_HoNV, s_TenNV, s_SDT, s_MatKhau;
    private byte[] b_hinh;
    private Boolean b_GioiTinh;

    public String getS_MatKhau() {
        return s_MatKhau;
    }

    public void setS_MatKhau(String s_MatKhau) {
        this.s_MatKhau = s_MatKhau;
    }

    public NhanVien(String s_MaNV, String s_HoNV, String s_TenNV, String s_SDT, Boolean b_GioiTinh, byte[] b_hinh) {
        this.s_MaNV = s_MaNV;
        this.s_HoNV = s_HoNV;
        this.s_TenNV = s_TenNV;
        this.s_SDT = s_SDT;
        this.b_GioiTinh = b_GioiTinh;
        this.b_hinh = b_hinh;
    }

    public byte[] getB_hinh() {
        return b_hinh;
    }

    public void setB_hinh(byte[] b_hinh) {
        this.b_hinh = b_hinh;
    }

    public ArrayList<String> getNhanVien(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add(this.s_MaNV);
        strings.add(this.s_TenNV);
        strings.add(this.s_HoNV);
        strings.add(this.s_SDT);
        strings.add(this.s_MatKhau);
       if(this.b_GioiTinh){
           strings.add("0");
       }else {
           strings.add("1");
       }

        return strings;
    }

    public NhanVien() {
    }


    public String getS_MaNV() {
        return s_MaNV;
    }

    public void setS_MaNV(String s_MaNV) {
        this.s_MaNV = s_MaNV;
    }

    public String getS_HoNV() {
        return s_HoNV;
    }

    public void setS_HoNV(String s_HoNV) {
        this.s_HoNV = s_HoNV;
    }

    public String getS_TenNV() {
        return s_TenNV;
    }

    public void setS_TenNV(String s_TenNV) {
        this.s_TenNV = s_TenNV;
    }

    public String getS_SDT() {
        return s_SDT;
    }

    public void setS_SDT(String s_SDT) {
        this.s_SDT = s_SDT;
    }

    public Boolean getB_GioiTinh() {
        return b_GioiTinh;
    }

    public void setB_GioiTinh(Boolean b_GioiTinh) {
        this.b_GioiTinh = b_GioiTinh;
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "s_MaNV='" + s_MaNV + '\'' +
                ", s_HoNV='" + s_HoNV + '\'' +
                ", s_TenNV='" + s_TenNV + '\'' +
                ", s_SDT='" + s_SDT + '\'' +
                ", b_GioiTinh=" + b_GioiTinh +
                '}';
    }
}
