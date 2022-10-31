package com.example.asetdsi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.widget.FrameLayout;

import com.example.asetdsi.adapter.TabAdapter;
import com.example.asetdsi.fragment.BangunanFragment;
import com.example.asetdsi.fragment.BarangFragment;
import com.google.android.material.tabs.TabLayout;

public class TestActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
//    private FrameLayout frameLayout;
    ViewPager viewPager;

    BarangFragment barangFragment;
    BangunanFragment bangunanFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tabs);
        getTabs();

//        tabLayout.addTab(tabLayout.newTab().setText("Barang"));
//        tabLayout.addTab(tabLayout.newTab().setText("Bangunan"));

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
}