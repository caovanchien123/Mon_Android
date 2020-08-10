package com.example.qlbanhang.UI;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.qlbanhang.Model.HoaDon;
import com.example.qlbanhang.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ThongKe extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_thongke, null);
        LineChart chart = (LineChart) view.findViewById(R.id.chart);
        ArrayList doanhThuTrongNgay = new ArrayList();
        ArrayList soLuongHoaDon = new ArrayList();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        for(int i = 0; i <7; i++){
            Double doanhThu = 0.0;
            int j = 0;

            for (HoaDon hoaDon : MainActivity.hoaDonDB.getAllSV()){
                if(hoaDon.getS_NgayLap().equals(dateFormat.format(calendar.getTime()))){
                    doanhThu = MainActivity.chiTietHoaDonDB.getFull(hoaDon.getS_MaHD());
                    j ++;
                }
            }
            calendar.roll(Calendar.DAY_OF_YEAR, -1);
            doanhThuTrongNgay.add(new BarEntry(i, doanhThu.intValue()));
            soLuongHoaDon.add(new BarEntry(i, j));
        }

        TextView tv_nhanvien = view.findViewById(R.id.tslNhanVien), tv_hoadon = view.findViewById(R.id.tslHoaDon);
        tv_hoadon.setText("Tổng số lượng hoá đơn: " + MainActivity.hoaDonDB.getAllSV().size());
        tv_nhanvien.setText("Tổng số lượng nhân viên: " + MainActivity.nhanVienDB.getAllSV().size());

        LineDataSet bardataset = new LineDataSet(doanhThuTrongNgay, "Doanh thu");
        LineDataSet lineDataSet = new LineDataSet(soLuongHoaDon, "Số lượng hoá đơn");
        chart.animateY(5000);
        LineData data = new LineData(bardataset, lineDataSet);
        bardataset.setColor(Color.BLUE);
        lineDataSet.setColor(Color.RED);
        chart.setData(data);
        return view;
    }
}
