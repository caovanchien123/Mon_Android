package com.example.qlbanhang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qlbanhang.Model.Menu;
import com.example.qlbanhang.R;

import java.util.ArrayList;

public class CustomAdapterMenu extends BaseAdapter {
    Context context;
    ArrayList<Menu> menus;

    public CustomAdapterMenu(Context context, ArrayList<Menu> menus) {
        this.context = context;
        this.menus = menus;
    }

    @Override
    public int getCount() {
        return menus.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder{
        ImageView img;
        TextView tv;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.cusstom_lv_menu, null);
            holder = new ViewHolder();
            holder.img = view.findViewById(R.id.icon_menu);
            holder.tv = view.findViewById(R.id.name_item);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        holder.tv.setText(menus.get(i).getS_Name());
        holder.img.setImageResource(menus.get(i).getI_Resource());

        return view;
    }
}
