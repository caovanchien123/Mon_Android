package com.example.qlbanhang.UI.NhanVien;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.qlbanhang.Model.HoaDon;
import com.example.qlbanhang.Model.NhanVien;
import com.example.qlbanhang.Model.SanPham;
import com.example.qlbanhang.R;
import com.example.qlbanhang.UI.HoaDon.DSHoaDon;
import com.example.qlbanhang.UI.MainActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class EditNhanVien extends Fragment {
    private ImageView img_user;
    private TextView tv_ma;
    private EditText edt_ho, edt_ten, edt_sdt, edt_matkhau;
    private Button btn_luu;
    private RadioButton r_nam, r_nu;
    private String s_ma;
    public final int CAMERA_ID = 1234;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_sua_nhanvien, null);
        //anh xa view
        img_user = view.findViewById(R.id.img_edit_nv);
        edt_ho = view.findViewById(R.id.edt_edit_honv);
        edt_matkhau = view.findViewById(R.id.edt_edit_matkhau);
        edt_ten = view.findViewById(R.id.edt_edit_tennv);
        edt_sdt = view.findViewById(R.id.edt_edit_sdt);
        btn_luu = view.findViewById(R.id.btn_edit_them);
        r_nam = view.findViewById(R.id.r_edit_nam);
        r_nu = view.findViewById(R.id.r_edit_nu);
        tv_ma = view.findViewById(R.id.tv_edit_manv);
        putData();
        setEvent();
        return view;
    }

    private void putData() {
        NhanVien nhanVien = MainActivity.nhanVienDB.getNV(MainActivity.i_Ma);
        edt_ho.setText(nhanVien.getS_HoNV());
        s_ma = nhanVien.getS_MaNV();
        tv_ma.setText("Mã NV: "+s_ma);
        edt_matkhau.setText(nhanVien.getS_MatKhau());
        edt_ten.setText(nhanVien.getS_TenNV());
        edt_sdt.setText(nhanVien.getS_SDT());
        if(nhanVien.getB_GioiTinh()){
            r_nam.setChecked(true);
        }else {
            r_nu.setChecked(false);
        }
        img_user.setImageBitmap((Bitmap) BitmapFactory.decodeByteArray(nhanVien.getB_hinh(), 0, nhanVien.getB_hinh().length));
    }

    private void setEvent() {
        img_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{
                                Manifest.permission.CAMERA
                        }, CAMERA_ID);
                        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, CAMERA_ID);
                        }else{
                            Toast.makeText(getContext(),"Bạn chưa cấp quyền cho ứng dụng", Toast.LENGTH_SHORT).show();
                        }
            }
        });

        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkInput()){
                    androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getContext());
                    builder.setMessage("Bạn có muốn luư");
                    builder.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            NhanVien nhanVien = new NhanVien();
                            nhanVien.setS_MaNV(s_ma);
                            BitmapDrawable drawable = (BitmapDrawable) img_user.getDrawable();
                            Bitmap bitmap = drawable.getBitmap();

                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            byte[] bytes = stream.toByteArray();
                            nhanVien.setB_hinh(bytes);
                            if(r_nam.isChecked()){
                                nhanVien.setB_GioiTinh(true);
                            }else if(r_nu.isChecked()){
                                nhanVien.setB_GioiTinh(false);
                            }
                            nhanVien.setS_MatKhau(edt_matkhau.getText().toString());
                            nhanVien.setS_HoNV(edt_ho.getText().toString());
                            nhanVien.setS_TenNV(edt_ten.getText().toString());
                            nhanVien.setS_SDT(edt_sdt.getText().toString());
                            MainActivity.nhanVienDB.sua(nhanVien);
                            MainActivity.chuyenDoiFragment(new DSNhanVien(), "DS Nhân viên");
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
            }
        });
    }


    private boolean checkInput(){
        if(!edt_matkhau.getText().toString().equals("")&&!edt_ho.getText().toString().equals("")&& !edt_sdt.getText().toString().equals("")&& !edt_ten.getText().toString().equals("")){
            return true;
        }

        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == CAMERA_ID && resultCode == MainActivity.RESULT_OK && data != null){
            img_user.setImageBitmap((Bitmap) data.getExtras().get("data"));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
