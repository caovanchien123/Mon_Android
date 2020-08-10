package com.example.qlbanhang.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.qlbanhang.Adapter.CustomAdapterMenu;
import com.example.qlbanhang.DataBase.ChiTietHoaDonDB;
import com.example.qlbanhang.DataBase.HoaDonDB;
import com.example.qlbanhang.DataBase.NhanVienDB;
import com.example.qlbanhang.DataBase.SanPhamDB;
import com.example.qlbanhang.Model.NhanVien;
import com.example.qlbanhang.Model.SanPham;
import com.example.qlbanhang.R;
import com.example.qlbanhang.UI.HoaDon.AddHoaDon;
import com.example.qlbanhang.UI.HoaDon.DSHoaDon;
import com.example.qlbanhang.UI.NhanVien.AddNhanVien;
import com.example.qlbanhang.UI.NhanVien.DSNhanVien;
import com.example.qlbanhang.UI.SanPham.AddSanPham;
import com.example.qlbanhang.UI.SanPham.DSSanPham;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DangNhap.DangNhapInterface, SearchView.OnClickListener, SearchView.OnQueryTextListener, SearchView.OnCloseListener {
    //Khai bao bien
    private DrawerLayout mDrawerLayout;
    private NavigationView nav;
    private ImageButton logOut;
    private int check = -1;
    private SearchView searchView;
    private FrameLayout frameLayout;
    private MenuItem menuItem;
    private ImageView hinh;

    //khai bao bien tinh
    public static String i_Ma = ""; //Bien luu ma

    //cac bien database
    public static HoaDonDB hoaDonDB;
    public static NhanVienDB nhanVienDB;
    public static SanPhamDB sanPhamDB;
    public static ChiTietHoaDonDB chiTietHoaDonDB;
    public static NhanVien nhanVien;
    private static FrameLayout layout;
    private static ActionBar actionbar;
    private static FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //lay FragmentManager
        manager = getSupportFragmentManager();

        //cai dat Navigation
        setNavigation();
        //khoi tao bien va anh xa view
        setControl();

        //xu ly cac su kien
        setEvent();

        //mo man hinh dang nhap
        chuyenDoiFragment(new DangNhap(this), "Đăng nhập");
    }

    private void setMenuNV(){
        chuyenDoiFragment(new AddHoaDon(), "Thêm hoá đơn");
        ArrayList<com.example.qlbanhang.Model.Menu> list = new ArrayList();
        list.add(new com.example.qlbanhang.Model.Menu("Thêm hoá đơn", R.drawable.img_add_user));
        list.add(new com.example.qlbanhang.Model.Menu("DS hoá đơn", R.drawable.img_list));
        ListView listView = findViewById(R.id.nav_lv_menu);
        CustomAdapterMenu menu = new CustomAdapterMenu(this, list);
        listView.setAdapter(menu);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        chuyenDoiFragment(new AddHoaDon(), "Thêm hoá đơn");
                        dongMenu();
                        break;
                    case 1:
                        chuyenDoiFragment(new DSHoaDon(), "DS hoá đơn");
                        dongMenu();
                        break;
                }
            }
        });
    }

    private void huyMenu(){
        ArrayList<com.example.qlbanhang.Model.Menu> list = new ArrayList();
        ListView listView = findViewById(R.id.nav_lv_menu);
        CustomAdapterMenu menu = new CustomAdapterMenu(this, list);
        listView.setAdapter(menu);
    }

    private void setMenu(){
        chuyenDoiFragment(new ThongKe(), "Thống kê");
        ArrayList<com.example.qlbanhang.Model.Menu> list = new ArrayList();
        list.add(new com.example.qlbanhang.Model.Menu("Thêm nhân viên", R.drawable.img_add_user));
        list.add(new com.example.qlbanhang.Model.Menu("DS Nhan Vien", R.drawable.img_list));
        list.add(new com.example.qlbanhang.Model.Menu("Thêm sản phẩm", R.drawable.img_add_product));
        list.add(new com.example.qlbanhang.Model.Menu("DS sản phẩm", R.drawable.img_list));
        list.add(new com.example.qlbanhang.Model.Menu("DS hoá đơn", R.drawable.img_list));
        list.add(new com.example.qlbanhang.Model.Menu("Thống kê", R.drawable.img_statistic_menu));
        ListView listView = findViewById(R.id.nav_lv_menu);
        CustomAdapterMenu menu = new CustomAdapterMenu(this, list);
        listView.setAdapter(menu);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        chuyenDoiFragment(new AddNhanVien(), "Thêm nhân viên");
                        dongMenu();
                        break;
                    case 1:
                        chuyenDoiFragment(new DSNhanVien(), "Thêm nhân viên");
                        dongMenu();
                        break;
                    case 2:
                        chuyenDoiFragment(new AddSanPham(), "Thêm sản phẩm");
                        dongMenu();
                        break;
                    case 3:
                        chuyenDoiFragment(new DSSanPham(), "DS sản phẩm");
                        dongMenu();
                        break;
                    case 4:
                        chuyenDoiFragment(new DSHoaDon(), "DS hoá đơn");
                        dongMenu();
                        break;
                    case 5:
                        chuyenDoiFragment(new ThongKe(), "Thống kê");
                        dongMenu();
                        break;
                }
            }
        });
    }

    private void loadApp() {
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                // pretend to do something "background-y"
                Intent intent = new Intent(getApplicationContext(), LoadApp.class);
                startActivity(intent);
            }
        });
        backgroundThread.start();
    }

    //chay cac su kien
    private void setEvent() {
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                return false;
            }
        });
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nhanVien = null;
                try {
                    searchView.setOnCloseListener(null);
                    searchView.setOnSearchClickListener(null);
                    searchView.setOnQueryTextListener(null);
                }catch (Exception e){

                }
                check = -1;
                ten.setText("Tên nhân viên");
                maso.setText("Mã số: null");
                huyMenu();
                dongMenu();
                chuyenDoiFragment(new DangNhap(MainActivity.this), "Đăng nhập");
            }
        });
    }

    //khoi tao va anh xa view
    private void setControl() {
        //anh xa view
        hinh = findViewById(R.id.nav_img_user);
        frameLayout = findViewById(R.id.search_frm);
        maso = findViewById(R.id.nav_tv_MaSo);
        ten = findViewById(R.id.nav_tv_TenNV);
        logOut = findViewById(R.id.nav_ib_logout);
        layout = findViewById(R.id.Fragment_Main);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        nav = findViewById(R.id.navigation);

        //khoi tao bien
        nhanVienDB = new NhanVienDB(this);
        hoaDonDB = new HoaDonDB(this);
        sanPhamDB = new SanPhamDB(this);
        chiTietHoaDonDB = new ChiTietHoaDonDB(this);
    }

    //thiet lap navigration
    private void setNavigation(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.img_menu);
    }

    //chuyen doi fragment
    public static void chuyenDoiFragment(Fragment fragment, String title){
        layout.removeAllViews();
        actionbar.setTitle(title);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.Fragment_Main, fragment);
        transaction.commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        menuItem = menu.findItem(R.id.search_view);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_Thoat:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Bạn có muốn thoát");
                builder.setPositiveButton("Vâng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
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
                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void dongMenu(){
        mDrawerLayout.closeDrawers();
    }
    private  TextView ten;
    private TextView maso;
    @Override
    public NhanVien getNhanViewn(NhanVien o) {
        if(o == null){
            setMenu();
            ten.setText("admin");
            maso.setText("MS: admin");
            check = 1;
            if(check == 1){

                searchView = (SearchView) menuItem.getActionView();
                searchView.setOnCloseListener(this);
                searchView.setOnSearchClickListener(this);
                searchView.setOnQueryTextListener(this);
            }
        }else if (o != null){
            nhanVien = o;
            check = 0;
            ten.setText(nhanVien.getS_HoNV()+ " " + nhanVien.getS_TenNV());
            maso.setText("MS: " + nhanVien.getS_MaNV());
            Bitmap bitmap = BitmapFactory.decodeByteArray(nhanVien.getB_hinh(), 0 , nhanVien.getB_hinh().length);
            hinh.setImageBitmap(bitmap);
            setMenuNV();
        }else {
            finish();
        }
        return null;
    }
    @Override
    public boolean onQueryTextSubmit(String s) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        search.changerData(sanPhamDB.getAllMa(s), nhanVienDB.getAllMa(s));
        return false;
    }

    Search search;
    @Override
    public boolean onClose() {
            try {
                frameLayout.removeAllViews();
                search.onDetach();
            }catch (Exception e){

            }
        return false;
    }


    @Override
    public void onClick(View view) {
        search = new Search();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.search_frm, search);
        transaction.commit();
    }
}