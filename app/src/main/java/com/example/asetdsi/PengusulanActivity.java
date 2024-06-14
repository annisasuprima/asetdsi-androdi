package com.example.asetdsi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;

import com.example.asetdsi.adapter.TabAdapter;
import com.example.asetdsi.fragment.BangunanFragment;
import com.example.asetdsi.fragment.BarangFragment;
import com.example.asetdsi.fragment.NewPengusulanMaintenenceFragment;
import com.example.asetdsi.fragment.PengusulanBarangFragment;
import com.example.asetdsi.fragment.PengusulanMaintenenceFragment;
import com.google.android.material.tabs.TabLayout;

public class PengusulanActivity extends AppCompatActivity {

    RecyclerView rvPengusulan;
    ActionBar actionBar;
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengusulan);


        Intent pengusulanIntent = getIntent();
        String nama_pj_pengusulan = pengusulanIntent.getStringExtra("nama_pj_pengusulan");
        String id  = pengusulanIntent.getStringExtra("id");


        actionBar = getSupportActionBar();
        actionBar.setTitle(nama_pj_pengusulan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ijau)));



        viewPager = findViewById(R.id.view_pager_pengusulan);
        tabLayout = findViewById(R.id.tabs_pengusulan);

        getTabs();
    }

    public void getTabs(){
        final TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                tabAdapter.addFragment(PengusulanBarangFragment.getInstance(),"Barang");
                tabAdapter.addFragment(NewPengusulanMaintenenceFragment.getInstance(),"Maintenence");
                viewPager.setAdapter(tabAdapter);
                tabLayout.setupWithViewPager(viewPager);

            }
        });
    }
}