package com.example.qlbanhang.UI.HoaDon;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.example.qlbanhang.Adapter.CustomAdapterSP;
import com.example.qlbanhang.Model.HoaDon;
import com.example.qlbanhang.Model.SanPham;
import com.example.qlbanhang.R;
import com.example.qlbanhang.UI.MainActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AddHoaDon extends Fragment {
    private TextView NgayLap, tongTien;
    private TextView tenNV, maHD;
    private ListView lvDanhSach, lvChon;
    private Button btn_luu;
    private CustomAdapterSP sanphams;
    ArrayList<SanPham> list = new ArrayList();
    private int ten = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_add_hoadon, null);
        tenNV = view.findViewById(R.id.tenNV);
        tongTien = view.findViewById(R.id.edt_TongTien);
        maHD = view.findViewById(R.id.maHoaDon);
        NgayLap = view.findViewById(R.id.ngayLap);
        lvChon = view.findViewById(R.id.lvDaChon);
        lvDanhSach = view.findViewById(R.id.lvDS);
        btn_luu = view.findViewById(R.id.btnLuu);

        NgayLap.setText("Ngày lập: "+new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault ()).format(new Date()));
        CustomAdapterSP sp = new CustomAdapterSP(getContext(), MainActivity.sanPhamDB.getAllSV());
        lvDanhSach.setAdapter(sp);
        tenNV.setText("Tên NV:" + MainActivity.nhanVien.getS_HoNV()+ " "+ MainActivity.nhanVien.getS_TenNV());
        if(MainActivity.hoaDonDB.getAllSV().size() != 0){
            maHD.setText("Mã HD: " + (Integer.parseInt(MainActivity.hoaDonDB.getAllSV().get(MainActivity.hoaDonDB.getAllSV().size() - 1).getS_MaHD()) + 1));
        }else {
            maHD.setText("Mã HD: 0");
        }
        sanphams = new CustomAdapterSP(getContext(), list);
        lvChon.setAdapter(sanphams);
        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                list.add(MainActivity.sanPhamDB.getAllSV().get(i));
                double tien = 0;
                for (SanPham sanPham :list){
                    tien += sanPham.getD_DonGia();
                }
                tongTien.setText("Tổng tiền: " + tien);
                sanphams.notifyDataSetChanged();
            }
        });



        lvChon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                list.remove(i);
                sanphams.notifyDataSetChanged();
                double tien = 0;
                for (SanPham sanPham :list){
                    tien += sanPham.getD_DonGia();
                }
                tongTien.setText("Tổng tiền: " + tien);
                return false;
            }
        });


        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("Bạn có muốn lưu");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            HoaDon hoaDon = new HoaDon();
                            hoaDon.setS_MaNV(MainActivity.nhanVien.getS_MaNV());
                            hoaDon.setS_NgayLap(hoaDon.layGio());
                            MainActivity.hoaDonDB.them(hoaDon);
                            MainActivity.chiTietHoaDonDB.setAllSanPham(list, (Integer.parseInt(MainActivity.hoaDonDB.getAllSV().get(MainActivity.hoaDonDB.getAllSV().size() - 1).getS_MaHD()))+"", 1+"");
                            MainActivity.chuyenDoiFragment(new DSHoaDon(), "DS Hoá đơn");
                        }
                    });
                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
        });

        setEvent();
        return view;
    }

    private void setEvent() {

    }
}
