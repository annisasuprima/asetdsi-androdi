package com.example.asetdsi;

import static com.example.asetdsi.Retrofit.PortalClient.API_BASE_URL;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Toast;

import com.example.asetdsi.Retrofit.PortalClient;
import com.example.asetdsi.adapter.DaftarBarangAdapter;
import com.example.asetdsi.model.Barang;
import com.example.asetdsi.model.DaftarBarangItem;
import com.example.asetdsi.model.DaftarBarangResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DaftarBarangActivity extends AppCompatActivity {

    RecyclerView rvDb;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences(
                "com.example.asetdsi.PREFS",
                Context.MODE_PRIVATE
        );

        setContentView(R.layout.activity_daftar_barang);

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ijau)));


        DaftarBarangAdapter adapter = new DaftarBarangAdapter();
//        adapter.setListData(getBarang());

        rvDb = findViewById(R.id.rvDb);
        rvDb.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        rvDb.setLayoutManager(layoutManager);

        //Object Retrofit

        String accessToken = preferences.getString("ACCESS_TOKEN","");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;

        PortalClient client = retrofit.create(PortalClient.class);

        Call<DaftarBarangResponse> call = client.getDaftarBarang("Bearer "+ accessToken);
        call.enqueue(new Callback<DaftarBarangResponse>() {
            @Override
            public void onResponse(Call<DaftarBarangResponse> call, Response<DaftarBarangResponse> response) {
                DaftarBarangResponse daftarBarangResponse = response.body();
                ArrayList<Barang> listBarang = new ArrayList<Barang>();
                if(daftarBarangResponse != null){
                    List<DaftarBarangItem> listDaftarBarang = daftarBarangResponse.getData();
//                    Log.d("oi54", String.valueOf(listHomeBarangItem.size()));
                    for(DaftarBarangItem barangItem : listDaftarBarang){
                        Barang barang = new Barang(
                                barangItem.getInventoryBrand(),
                                barangItem.getPicName(),
                                barangItem.getCondition(),
                                barangItem.getAvailable(),
                                barangItem.getJumlah(),
                                barangItem.getPhoto()

                        );
                        listBarang.add(barang);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Gagal Menghubungi Server",Toast.LENGTH_SHORT).show();

                }
                adapter.setListData(listBarang);
            }

            @Override
            public void onFailure(Call<DaftarBarangResponse> call, Throwable t) {
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
