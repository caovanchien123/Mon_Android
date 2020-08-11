package com.example.qlbanhang.UI.NhanVien;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
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
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class AddNhanVien extends Fragment {
    private ImageView img_user;
    private EditText edt_ho, edt_ten, edt_sdt, edt_matkhau;
    private Button btn_luu;
    private RadioButton r_nam, r_nu;
    public final int CAMERA_ID = 1234;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_add_nhanvien, null);
        //anh xa view
        img_user = view.findViewById(R.id.img_add_nv);
        edt_ho = view.findViewById(R.id.edt_add_honv);
        edt_ten = view.findViewById(R.id.edt_add_tennv);
        edt_matkhau = view.findViewById(R.id.edt_add_matkhau);
        edt_sdt = view.findViewById(R.id.edt_add_sdt);
        btn_luu = view.findViewById(R.id.btn_add_them);
        r_nam = view.findViewById(R.id.r_add_nam);
        r_nu = view.findViewById(R.id.r_add_nu);
        setEvent();
        return view;
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
                    Toast.makeText(getContext(),"Bạn chưa có quyền", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkInput()){
                    androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getContext());
                    builder.setMessage("Bạn có muốn lưu");
                    builder.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            NhanVien nhanVien = new NhanVien();
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
                            nhanVien.setS_HoNV(edt_ho.getText().toString());
                            nhanVien.setS_MatKhau(edt_matkhau.getText().toString());
                            nhanVien.setS_TenNV(edt_ten.getText().toString());
                            nhanVien.setS_SDT(edt_sdt.getText().toString());
                            MainActivity.nhanVienDB.them(nhanVien);
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
                }else {
                    Toast.makeText(getContext(), "Bạn chưa điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkImage = false;

    private boolean checkInput(){
        if(!edt_matkhau.getText().toString().equals("") && checkImage && !edt_ho.getText().toString().equals("")&& !edt_sdt.getText().toString().equals("")&& !edt_ten.getText().toString().equals("")){
            return true;
        }

        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == CAMERA_ID && resultCode == MainActivity.RESULT_OK && data != null){
            img_user.setImageBitmap((Bitmap) data.getExtras().get("data"));
            checkImage = true;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
