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
import android.widget.ToggleButton;

import com.example.asetdsi.adapter.TabAdapter;
import com.example.asetdsi.fragment.HistoryPengusulanFragment;
import com.example.asetdsi.fragment.OngoingPeminjamanFragment;
import com.example.asetdsi.fragment.OngoingPengusulanFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class OngoingPengusulanActivity extends AppCompatActivity {

    RecyclerView rvOngoingPengusulan;
    ActionBar actionBar;
    Toolbar toolbar;
    TabLayout tabLayout;
    //    private FrameLayout frameLayout;
    ViewPager viewPager;
    ToggleButton toggleButton;
    BottomNavigationView bottomNavigationView;
    Button buttonhistorypengusulan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongoing_pengusulan);


        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ijau)));

        viewPager = findViewById(R.id.view_pager_ongoing_pengusulan);
        tabLayout = findViewById(R.id.tabs_ongoing_pengusulan);
        getTabs();

        //initialize and assign variable
        BottomNavigationView bottomNavigationView= findViewById(R.id.bottomNavigationOngoingPengusulan);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.ongoing);

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


    }


    private void getTabs() {

        final TabAdapter tabAdapter2 = new TabAdapter(getSupportFragmentManager());

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                tabAdapter2.addFragment(OngoingPeminjamanFragment.getInstance(),"Peminjaman");
                tabAdapter2.addFragment(OngoingPengusulanFragment.getInstance(),"Pengusulan");
                viewPager.setAdapter(tabAdapter2);

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