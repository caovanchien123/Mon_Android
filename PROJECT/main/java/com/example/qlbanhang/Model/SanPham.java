package com.example.qlbanhang.Model;

import java.util.ArrayList;

public class SanPham {
    private String s_TenSP, s_MaSP, s_DonViTinh;
    private byte[] b_hinh;

    public SanPham(String s_TenSP, String s_MaSP, String s_DonViTinh, Double d_DonGia, byte[] b_hinh) {
        this.s_TenSP = s_TenSP;
        this.s_MaSP = s_MaSP;
        this.s_DonViTinh = s_DonViTinh;
        this.d_DonGia = d_DonGia;
        this.b_hinh = b_hinh;
    }

    public byte[] getB_hinh() {
        return b_hinh;
    }

    public void setB_hinh(byte[] b_hinh) {
        this.b_hinh = b_hinh;
    }

    public ArrayList<String> getArray(){
     ArrayList<String> strings = new ArrayList<>();
     strings.add(s_MaSP);
     strings.add(s_TenSP);
     strings.add(s_DonViTinh);
     strings.add(d_DonGia+"");

     return strings;
    }

    public SanPham() {
    }

    public String getS_TenSP() {
        return s_TenSP;
    }

    public void setS_TenSP(String s_TenSP) {
        this.s_TenSP = s_TenSP;
    }

    public String getS_MaSP() {
        return s_MaSP;
    }

    public void setS_MaSP(String s_MaSP) {
        this.s_MaSP = s_MaSP;
    }

    public String getS_DonViTinh() {
        return s_DonViTinh;
    }

    public void setS_DonViTinh(String s_DonViTinh) {
        this.s_DonViTinh = s_DonViTinh;
    }

    public Double getD_DonGia() {
        return d_DonGia;
    }

    public void setD_DonGia(Double d_DonGia) {
        this.d_DonGia = d_DonGia;
    }

    private Double d_DonGia;

    @Override
    public String toString() {
        return "SanPham{" +
                "s_TenSP='" + s_TenSP + '\'' +
                ", s_MaSP='" + s_MaSP + '\'' +
                ", s_DonViTinh='" + s_DonViTinh + '\'' +
                ", d_DonGia=" + d_DonGia +
                '}';
    }
}
