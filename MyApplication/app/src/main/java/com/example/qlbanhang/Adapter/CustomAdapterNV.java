package com.example.qlbanhang.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlbanhang.Model.NhanVien;
import com.example.qlbanhang.R;
import com.example.qlbanhang.UI.MainActivity;
import com.example.qlbanhang.UI.NhanVien.EditNhanVien;

import java.util.ArrayList;

public class CustomAdapterNV extends BaseAdapter {
    private Context context;
    private ArrayList<NhanVien> nhanViens;
    public CustomAdapterNV(Context context, ArrayList<NhanVien> nhanViens) {
        this.context = context;
        this.nhanViens = nhanViens;
    }

    @Override
    public int getCount() {
        return nhanViens.size();
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
        TextView Ten, SDT, MatKhau;
        ImageView img_User;

    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        viewHolder holder;

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lv_nhanvien, null);
            holder = new viewHolder();
            holder.img_User = view.findViewById(R.id.img_nv_user);
            holder.Ten = view.findViewById(R.id.tv_nv_ten);
            holder.SDT = view.findViewById(R.id.tv_nv_sdt);
            holder.MatKhau = view.findViewById(R.id.tv_nv_matkhau);
            view.setTag(holder);
        }else {
            holder = (viewHolder) view.getTag();
        }

        Bitmap bitmap = BitmapFactory.decodeByteArray(MainActivity.nhanVienDB.getAllSV().get(i).getB_hinh(), 0, MainActivity.nhanVienDB.getAllSV().get(i).getB_hinh().length);

        holder.img_User.setImageBitmap(bitmap);
        holder.MatKhau.setText("Mâtk khẩu: " + nhanViens.get(i).getS_MatKhau());
        holder.Ten.setText("Họ tên: "+nhanViens.get(i).getS_HoNV()+ " " + nhanViens.get(i).getS_TenNV());
        holder.SDT.setText("SDT: "+nhanViens.get(i).getS_SDT());
        return view;
    }
}
