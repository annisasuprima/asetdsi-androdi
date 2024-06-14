package com.example.asetdsi;

import static com.example.asetdsi.Retrofit.PortalClient.API_BASE_URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.asetdsi.Retrofit.PortalClient;
import com.example.asetdsi.adapter.HomeBarangAdapter;
import com.example.asetdsi.model.HomeBarangItem;
import com.example.asetdsi.model.HomeBarangResponse;
import com.example.asetdsi.model.ListBarangHome;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity{

    private CardView peminjaman;
    private CardView pengusulan;
    private Button BtnHome;
    BottomNavigationView bottomNavigationView;


    RecyclerView homerv;

    ArrayList<String> dataSource;
    LinearLayoutManager linearLayoutManager;
    private String isLoggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences(
                "com.example.asetdsi.PREFS",
                Context.MODE_PRIVATE
        );

        isLoggedIn = preferences.getString("ACCESS_TOKEN",null);

        if(isLoggedIn == null){
            Intent loginIntent = new Intent(this,LoginActivity.class);
            startActivity(loginIntent);
            finish();
            return;
        }

        //Hide Action Bar
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_home);

       HomeBarangAdapter adapter = new HomeBarangAdapter();
//       adapter.setListData(getHomeBarang());

       homerv = findViewById(R.id.homeRv);
       homerv.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        homerv.setLayoutManager(layoutManager);


        //Object Retrofit

        String accessToken = preferences.getString("ACCESS_TOKEN","");
        
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;

        PortalClient client = retrofit.create(PortalClient.class);

        Call<HomeBarangResponse> call = client.getHomeBarang("Bearer "+ accessToken);
        call.enqueue(new Callback<HomeBarangResponse>() {
            @Override
            public void onResponse(Call<HomeBarangResponse> call, Response<HomeBarangResponse> response) {
                HomeBarangResponse homeBarangResponse = response.body();
                ArrayList<ListBarangHome> listBarangHome = new ArrayList<ListBarangHome>();
                if(homeBarangResponse != null){
                    List<HomeBarangItem> listHomeBarangItem = homeBarangResponse.getData();
//                    Log.d("oi54", String.valueOf(listHomeBarangItem.size()));
                    for(HomeBarangItem barangItem : listHomeBarangItem){
                         ListBarangHome barang = new ListBarangHome(
                                barangItem.getInventoryBrand(),
                                barangItem.getPhoto()
                         );
                         listBarangHome.add(barang);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Gagal Menghubungi Server",Toast.LENGTH_SHORT).show();

                }
                adapter.setListData(listBarangHome);
            }

            @Override
            public void onFailure(Call<HomeBarangResponse> call, Throwable t) {
            Toast.makeText(getApplicationContext(),"Gagal Menghubungi Server",Toast.LENGTH_SHORT).show();
            }
        });


        //Inten

        //initialize and assign variable
        BottomNavigationView bottomNavigationView= findViewById(R.id.bottomNavigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.home);

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


        //Button Peminjaman
        peminjaman = findViewById(R.id.peminjaman);
        peminjaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PJActivity();
            }
        });

        pengusulan = findViewById(R.id.pengusulan);
        pengusulan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewPengusulunBarangFormActivity();
            }
        });


        //Button Home
        BtnHome = findViewById(R.id.BtnHome);
        BtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DaftarBarangActivity();
            }
        });

    }

    public void PJActivity() {
        Intent intent = new Intent(this, PJActivity.class);
        startActivity(intent);
    }

    public void NewPengusulunBarangFormActivity() {
        Intent intent = new Intent(this, NewPengusulunBarangFormActivity.class);
        startActivity(intent);
    }

    public void DaftarBarangActivity() {
        Intent intent = new Intent(this, DaftarBarangActivity.class);
        startActivity(intent);
    }


}


