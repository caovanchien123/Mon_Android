package com.example.qlbanhang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qlbanhang.Model.DonVi;
import com.example.qlbanhang.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class CustomSpinner extends BaseAdapter {
    ArrayList<DonVi> donVis;
    Context context;

    public CustomSpinner(ArrayList<DonVi> donVis, Context context) {
        this.donVis = donVis;
        this.context = context;
    }

    @Override
    public int getCount() {
        return donVis.size();
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
        ImageView imageView;
        TextView textView;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        viewHolder holder;

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_spinner, null);
            holder = new viewHolder();

            holder.imageView = view.findViewById(R.id.imageSpin);
            holder.textView = view.findViewById(R.id.txt_Spin);

            view.setTag(holder);
        }else {
            holder = (viewHolder) view.getTag();
        }

        holder.textView.setText(donVis.get(i).getTen());
        holder.imageView.setImageResource(donVis.get(i).getHinh());

        return view;
    }
}
