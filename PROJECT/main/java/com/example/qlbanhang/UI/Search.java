package com.example.qlbanhang.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.qlbanhang.Adapter.CustomAdapterNV;
import com.example.qlbanhang.Adapter.CustomAdapterSP;
import com.example.qlbanhang.Model.NhanVien;
import com.example.qlbanhang.Model.SanPham;
import com.example.qlbanhang.R;

import java.util.ArrayList;

public class Search extends Fragment {
    public Search() {
    }
    ListView sanpham ;
    ListView nhanvien;
    CustomAdapterSP sp;
    CustomAdapterNV nv ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_timkiem, null);
        sanpham = view.findViewById(R.id.lv_search_sp);
        nhanvien = view.findViewById(R.id.lv_search_nv);

        return view;
    }

    public void changerData(ArrayList<SanPham> sanPhams, ArrayList<NhanVien> nhanViens){
        sp = new CustomAdapterSP(getContext(), sanPhams);
        nv = new CustomAdapterNV(getContext(), nhanViens);
        sanpham.setAdapter(sp);
        nhanvien.setAdapter(nv);
    }
}
