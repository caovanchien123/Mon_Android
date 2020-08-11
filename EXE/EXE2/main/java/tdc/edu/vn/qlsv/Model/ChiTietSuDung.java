package tdc.edu.vn.qlsv.Model;

public class ChiTietSuDung {
    private String maPhong;
    private String maTB;
    private String ngaySuDung;
    private int soLuong;

    public ChiTietSuDung(String maPhong, String maTB, String ngaySuDung, int soLuong) {
        this.maPhong = maPhong;
        this.maTB = maTB;
        this.ngaySuDung = ngaySuDung;
        this.soLuong = soLuong;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getMaTB() {
        return maTB;
    }

    public void setMaTB(String maTB) {
        this.maTB = maTB;
    }

    public String getNgaySuDung() {
        return ngaySuDung;
    }

    public void setNgaySuDung(String ngaySuDung) {
        this.ngaySuDung = ngaySuDung;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return "ChiTietSuDung{" +
                "maPhong='" + maPhong + '\'' +
                ", maTB='" + maTB + '\'' +
                ", ngaySuDung='" + ngaySuDung + '\'' +
                ", soLuong=" + soLuong +
                '}';
    }
}
