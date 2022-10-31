package com.example.asetdsi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.asetdsi.adapter.PeminjamanAdapter;
import com.example.asetdsi.adapter.TabAdapter;
import com.example.asetdsi.fragment.BangunanFragment;
import com.example.asetdsi.fragment.BarangFragment;
import com.example.asetdsi.model.Peminjaman;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class PeminjamanActivity extends AppCompatActivity {

    RecyclerView rvPnj;
    TextView pj;
    ActionBar actionBar;
    Toolbar toolbar;
    TabLayout tabLayout;
    //    private FrameLayout frameLayout;
    ViewPager viewPager;


    BarangFragment barangFragment;
    BangunanFragment bangunanFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peminjaman);


        Intent peminjamanIntent = getIntent();
        String nama_pj = peminjamanIntent.getStringExtra("nama_pj");
        String id  = peminjamanIntent.getStringExtra("id");

//        toolbar = findViewById(R.id.toolbar);
//        toolbar.setTitle(nama_pj);
//        setSupportActionBar(toolbar);


        actionBar = getSupportActionBar();
        actionBar.setTitle(nama_pj);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ijau)));


        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tabs);

        getTabs();

    }

    public void getTabs(){
        final TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                tabAdapter.addFragment(BarangFragment.getInstance(),"Barang");
                tabAdapter.addFragment(BangunanFragment.getInstance(),"Bangunan");
                viewPager.setAdapter(tabAdapter);
                tabLayout.setupWithViewPager(viewPager);

            }
        });
    }

//    private ArrayList<Peminjaman> getPeminjaman() {
//    ArrayList<Peminjaman> listPeminjaman = new ArrayList<Peminjaman>();
//    listPeminjaman.add(new Peminjaman(
//            "Meja",
//            "High Point",
//            10
//    ));
//        listPeminjaman.add(new Peminjaman(
//                "Infocus",
//                "Epson",
//                1
//        ));
//        listPeminjaman.add(new Peminjaman(
//                "Vacum Cleaner",
//                "Modena Vc24",
//                1
//        ));
//        listPeminjaman.add(new Peminjaman(
//                "Meja",
//                "Meja Belajar",
//                20
//        ));
//        listPeminjaman.add(new Peminjaman(
//                "Kursi",
//                "Futura",
//                25
//        ));
//        return listPeminjaman;
//    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}