package com.example.asetdsi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.asetdsi.adapter.HistoryPeminjamanAdapter;
import com.example.asetdsi.adapter.PJAdapter;
import com.example.asetdsi.adapter.TabAdapter;
import com.example.asetdsi.fragment.BangunanFragment;
import com.example.asetdsi.fragment.BarangFragment;
import com.example.asetdsi.fragment.HistoryPeminjamanFragment;
import com.example.asetdsi.fragment.HistoryPengusulanFragment;
import com.example.asetdsi.model.HistoryPeminjaman;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class HistoryActivity extends AppCompatActivity {

    RecyclerView rvHistoryPeminjaman;
    ActionBar actionBar;
    Toolbar toolbar;
    TabLayout tabLayout;
    //    private FrameLayout frameLayout;
    ViewPager viewPager;
    ToggleButton toggleButton;
    BottomNavigationView bottomNavigationView;
    HistoryPeminjamanFragment historyPeminjamanFragment;
    HistoryPengusulanFragment historyPengusulanFragment;
    Button buttonongoingpeminjaman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ijau)));

        viewPager = findViewById(R.id.view_pager_history);
        tabLayout = findViewById(R.id.tabs_history);
//        toggleButton = findViewById(R.id.toggleButton);
//        bottomNavigationView = findViewById(R.id.bottomNavigationHistory);
        getTabs();


        //BottomBawah

        //initialize and assign variable
        BottomNavigationView bottomNavigationView= findViewById(R.id.bottomNavigationHistory);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.history);

        //perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.ongoing:
                        startActivity(new Intent(getApplicationContext()
                                ,OngoingActivity.class));

                        overridePendingTransition(0,0);
                        return true;
                    case R.id.history:
                        startActivity(new Intent(getApplicationContext()
                                , HistoryActivity.class));

                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,HomeActivity.class));

                        overridePendingTransition(0,0);
                        return true;

                    case R.id.setting:
                        startActivity(new Intent(getApplicationContext()
                                ,SettingActivity.class));

                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


        //Button


    }




    private void getTabs() {
        final TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());

        new Handler().post(new Runnable() {
           @Override
           public void run() {
               tabAdapter.addFragment(HistoryPeminjamanFragment.getInstance(),"Peminjaman");
               tabAdapter.addFragment(HistoryPengusulanFragment.getInstance(),"Pengusulan");
               viewPager.setAdapter(tabAdapter);
               tabLayout.setupWithViewPager(viewPager);
           }
       });
    }

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