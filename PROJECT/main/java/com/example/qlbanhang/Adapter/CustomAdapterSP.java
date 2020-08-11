package com.example.qlbanhang.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlbanhang.DataBase.NhanVienDB;
import com.example.qlbanhang.DataBase.SanPhamDB;
import com.example.qlbanhang.Model.NhanVien;
import com.example.qlbanhang.Model.SanPham;
import com.example.qlbanhang.R;
import com.example.qlbanhang.UI.MainActivity;

import java.util.ArrayList;

public class CustomAdapterSP extends BaseAdapter {
    private Context context;
    private ArrayList<SanPham> sanPhams;
    public CustomAdapterSP(Context context, ArrayList<SanPham> sanPhams) {
        this.context = context;
        this.sanPhams = sanPhams;
    }

    @Override
    public int getCount() {
        return sanPhams.size();
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
        TextView Ten, Gia;
        ImageView Hinh;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        viewHolder holder;

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lv_sanpham, null);
            holder = new viewHolder();
            holder.Hinh = view.findViewById(R.id.img_sp_hinh);
            holder.Ten = view.findViewById(R.id.tv_sp_ten);
            holder.Gia = view.findViewById(R.id.tv_sp_dongia);
            view.setTag(holder);
        }else {
            holder = (viewHolder) view.getTag();
        }

        holder.Ten.setText("Tên: "+sanPhams.get(i).getS_TenSP());
        holder.Hinh.setImageBitmap(BitmapFactory.decodeByteArray(sanPhams.get(i).getB_hinh(), 0, sanPhams.get(i).getB_hinh().length));
        holder.Gia.setText("Gía: "+sanPhams.get(i).getD_DonGia() + "");

        return view;
    }
}
