package com.example.qlbanhang.UI;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.qlbanhang.Model.NhanVien;
import com.example.qlbanhang.R;

public class DangNhap extends Fragment {
    DangNhap.DangNhapInterface dangNhapInterface;
    public DangNhap(DangNhapInterface dangNhapInterface) {
        this.dangNhapInterface = dangNhapInterface;
    }

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_dangnhap, null);
        final EditText ten = view.findViewById(R.id.dangnhap_edt_ten), matkhau = view.findViewById(R.id.dangnhap_edt_matkhau);
        Button btn = view.findViewById(R.id.dangnhap_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = false;
                if(ten.getText().toString().equals("admin") && matkhau.getText().toString().equals("admin")){
                    dangNhapInterface.getNhanViewn(null);
                    check = true;
                }else {
                    for (NhanVien nhanVien: MainActivity.nhanVienDB.getAllSV()){
                        if(ten.getText().toString().equals(nhanVien.getS_HoNV()+ " " + nhanVien.getS_TenNV()) && matkhau.getText().toString().equals(nhanVien.getS_MatKhau())){
                            dangNhapInterface.getNhanViewn(nhanVien);
                            check = true;
                            break;
                        }
                    }

                }

                if (!check){
                    Toast.makeText(getContext(), "Bạn đã nhập sai tên hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

    interface DangNhapInterface{
        NhanVien getNhanViewn(NhanVien o);
    }
}
