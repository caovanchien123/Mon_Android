package com.example.qlbanhang.UI.SanPham;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.qlbanhang.Adapter.CustomSpinner;
import com.example.qlbanhang.Model.DonVi;
import com.example.qlbanhang.Model.HoaDon;
import com.example.qlbanhang.Model.NhanVien;
import com.example.qlbanhang.Model.SanPham;
import com.example.qlbanhang.R;
import com.example.qlbanhang.UI.HoaDon.DSHoaDon;
import com.example.qlbanhang.UI.MainActivity;
import com.example.qlbanhang.UI.NhanVien.DSNhanVien;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class AddSanPham extends Fragment {
    private ImageView img_sp;
    private EditText edt_tenSP, edt_giaSp;
    private Button btn_luu;
    private Spinner spinner;
    public final int CAMERA_ID = 1234;
    private ArrayList<DonVi> list;
    private String donVi = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_add_sanpham, null);
        //anh xa view
        btn_luu = view.findViewById(R.id.btn_edit_them);
        img_sp = view.findViewById(R.id.img_edit_nv);
        edt_tenSP = view.findViewById(R.id.edt_edit_tensp);
        edt_giaSp = view.findViewById(R.id.edt_edit_giasp);
        spinner = view.findViewById(R.id.sp_donvitinh);
        setEvent();
        setSp();
        return view;
    }

    private void setEvent() {
        img_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{
                        Manifest.permission.CAMERA
                }, CAMERA_ID);
                if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, CAMERA_ID);
                }else{
                    Toast.makeText(getContext(),"Bạn chưa cấp quyền", Toast.LENGTH_SHORT).show();
                }
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                checkSP = true;
                donVi = list.get(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
                            SanPham sanPham = new SanPham();
                            BitmapDrawable drawable = (BitmapDrawable) img_sp.getDrawable();
                            Bitmap bitmap = drawable.getBitmap();

                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            byte[] bytes = stream.toByteArray();
                            sanPham.setB_hinh(bytes);
                            sanPham.setS_DonViTinh(donVi);
                            sanPham.setS_TenSP(edt_tenSP.getText().toString());
                            sanPham.setD_DonGia(Double.parseDouble(edt_giaSp.getText().toString()));
                            MainActivity.sanPhamDB.them(sanPham);
                            MainActivity.chuyenDoiFragment(new DSSanPham(), "DS Sản Phẩm");
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
    private boolean checkSP = false;

    private boolean checkInput(){
        if(checkSP && checkImage && !edt_tenSP.getText().toString().equals("")){
            return true;
        }

        return false;
    }

    private void setSp(){
        list = new ArrayList();
        list.add(new DonVi("Chai", R.drawable.cocacola));
        list.add(new DonVi("KG", R.drawable.kg));
        list.add(new DonVi("Lít", R.drawable.lit));
        list.add(new DonVi("Cái", R.drawable.cai));
        CustomSpinner adapter = new CustomSpinner(list, getContext());
        spinner.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == CAMERA_ID && resultCode == MainActivity.RESULT_OK && data != null){
            img_sp.setImageBitmap((Bitmap) data.getExtras().get("data"));
            checkImage = true;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
