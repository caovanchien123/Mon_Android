package com.example.qlbanhang.UI.HoaDon;

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
import com.example.qlbanhang.Adapter.CustomAdapterHD;
import com.example.qlbanhang.R;
import com.example.qlbanhang.UI.MainActivity;

public class DSHoaDon extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_dshoadon, container, false);
        SwipeMenuListView listView = (SwipeMenuListView) view.findViewById(R.id.ds_hd_lv);

        final CustomAdapterHD adapter = new CustomAdapterHD(getContext());
        listView.setAdapter(adapter);
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

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
                        MainActivity.chiTietHoaDonDB.xoa(MainActivity.hoaDonDB.getAllSV().get(position).getS_MaHD());
                        MainActivity.hoaDonDB.xoa(MainActivity.hoaDonDB.getAllSV().get(position));
                        Toast.makeText(getContext(), "Bạn đã xoá thành công", Toast.LENGTH_SHORT).show();
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
