package com.example.qlbanhang.UI.NhanVien;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.qlbanhang.Adapter.CustomAdapterNV;
import com.example.qlbanhang.Model.NhanVien;
import com.example.qlbanhang.R;
import com.example.qlbanhang.UI.MainActivity;

import java.util.ArrayList;

public class DSNhanVien extends Fragment {
    private ArrayList<NhanVien> nhanViens = MainActivity.nhanVienDB.getAllSV();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_dsnhanvien, container, false);
        SwipeMenuListView listView = (SwipeMenuListView) view.findViewById(R.id.ds_nv_lv);

        final CustomAdapterNV adapter = new CustomAdapterNV(getContext(), nhanViens);
        listView.setAdapter(adapter);
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getContext());
                // set item width
                openItem.setWidth(170);
                // set item title
                openItem.setIcon(R.drawable.img_edit);
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to img_menu
                menu.addMenuItem(openItem);

                // create "img_delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getContext());
                // set item width
                deleteItem.setWidth(170);
                // set a icon
                deleteItem.setIcon(R.drawable.img_delete);
                // add to img_menu
                menu.addMenuItem(deleteItem);
            }
        };

        listView.setMenuCreator(creator);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        MainActivity.i_Ma = MainActivity.nhanVienDB.getAllSV().get(position).getS_MaNV();
                        MainActivity.chuyenDoiFragment(new EditNhanVien(),"Sữa nhân viên");
                        break;
                    case 1:
                        MainActivity.nhanVienDB.xoa(MainActivity.nhanVienDB.getAllSV().get(position));
                        Toast.makeText(getContext(), "Bạn đã xoá thành công", Toast.LENGTH_SHORT).show();
                        nhanViens.clear();
                        nhanViens = MainActivity.nhanVienDB.getAllSV();
                        adapter.notifyDataSetChanged();
                        break;
                }
                // false : close the img_menu; true : not close the img_menu
                return false;
            }
        });
        return view;
    }

}
