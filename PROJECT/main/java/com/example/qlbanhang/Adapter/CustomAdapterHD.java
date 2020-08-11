package com.example.qlbanhang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlbanhang.DataBase.HoaDonDB;
import com.example.qlbanhang.Model.HoaDon;
import com.example.qlbanhang.R;
import com.example.qlbanhang.UI.MainActivity;

import java.util.ArrayList;

public class CustomAdapterHD extends BaseAdapter {
    private Context context;
    public CustomAdapterHD(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        if(MainActivity.nhanVien == null){
            return MainActivity.hoaDonDB.getAllSV().size();
        }else {
            return MainActivity.hoaDonDB.getAll(MainActivity.nhanVien.getS_MaNV()).size();
        }
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class viewHolder{
        TextView s_MaHD, s_NgayLap, s_MaNV;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        viewHolder holder;

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lv_hoadon, null);
            holder = new viewHolder();
            holder.s_MaHD = view.findViewById(R.id.tv_hd_mahd);
            holder.s_MaNV = view.findViewById(R.id.tv_hd_manv);
            holder.s_NgayLap = view.findViewById(R.id.tv_hd_ngay);
            view.setTag(holder);
        }else {
            holder = (viewHolder) view.getTag();
        }
        if(MainActivity.nhanVien == null){
            holder.s_MaHD.setText("Mã HD: "+MainActivity.hoaDonDB.getAllSV().get(i).getS_MaHD());
            holder.s_MaNV.setText("Mã NV:" + MainActivity.hoaDonDB.getAllSV().get(i).getS_MaNV());
            holder.s_NgayLap.setText("Ngày lập: "+MainActivity.hoaDonDB.getAllSV().get(i).getS_NgayLap());
        }else {
            holder.s_MaHD.setText("Mã HD: "+MainActivity.hoaDonDB.getAll(MainActivity.nhanVien.getS_MaNV()).get(i).getS_MaHD());
            holder.s_MaNV.setText("Mã NV:" + MainActivity.hoaDonDB.getAll(MainActivity.nhanVien.getS_MaNV()).get(i).getS_MaNV());
            holder.s_NgayLap.setText("Ngày lập: "+MainActivity.hoaDonDB.getAll(MainActivity.nhanVien.getS_MaNV()).get(i).getS_NgayLap());
        }


        return view;
    }
}
