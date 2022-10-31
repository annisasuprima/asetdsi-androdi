package com.example.asetdsi;

import static com.example.asetdsi.Retrofit.PortalClient.API_BASE_URL;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Toast;

import com.example.asetdsi.Retrofit.PortalClient;
import com.example.asetdsi.adapter.DaftarBarangAdapter;
import com.example.asetdsi.adapter.ListPhotoAdapter;
import com.example.asetdsi.model.Barang;
import com.example.asetdsi.model.BuktiFotoItem;
import com.example.asetdsi.model.BuktiFotoResponse;
import com.example.asetdsi.model.DaftarBarangItem;
import com.example.asetdsi.model.DaftarBarangResponse;
import com.example.asetdsi.model.ListPhotoMaintenence;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuktiFotoActivity extends AppCompatActivity {

    RecyclerView rvBukti;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bukti_foto);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences(
                "com.example.asetdsi.PREFS",
                Context.MODE_PRIVATE
        );

        Intent BuktiIntent = getIntent();
        Integer id  = BuktiIntent.getIntExtra("id_req_maintenence",0);

        setContentView(R.layout.activity_bukti_foto);

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ijau)));


        ListPhotoAdapter adapter = new ListPhotoAdapter();
//        adapter.setListData(getBarang());

        rvBukti = findViewById(R.id.rvBukti);
        rvBukti.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        rvBukti.setLayoutManager(layoutManager);

        //Object Retrofit

        String accessToken = preferences.getString("ACCESS_TOKEN","");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;

        PortalClient client = retrofit.create(PortalClient.class);

        Call<BuktiFotoResponse> call = client.getBuktiFoto("Bearer "+ accessToken,id);
        call.enqueue(new Callback<BuktiFotoResponse>() {
            @Override
            public void onResponse(Call<BuktiFotoResponse> call, Response<BuktiFotoResponse> response) {
                BuktiFotoResponse buktiFotoResponse = response.body();
                ArrayList<ListPhotoMaintenence> listPhotoMaintenence = new ArrayList<ListPhotoMaintenence>();
                if(buktiFotoResponse != null){
                    List<BuktiFotoItem> listBuktiFotoItem = buktiFotoResponse.getData();
//                    Log.d("oi54", String.valueOf(listHomeBarangItem.size()));
                    for(BuktiFotoItem buktiFotoItem : listBuktiFotoItem){
                        ListPhotoMaintenence listPhotoMt = new ListPhotoMaintenence(
                                buktiFotoItem.getPhotoName()

                        );
                        listPhotoMaintenence.add(listPhotoMt);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Gagal Menghubungi Server",Toast.LENGTH_SHORT).show();

                }
                adapter.setListData(listPhotoMaintenence);
            }

            @Override
            public void onFailure(Call<BuktiFotoResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Gagal Menghubungi Server",Toast.LENGTH_SHORT).show();
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