package com.example.asetdsi;

import static com.example.asetdsi.Retrofit.PortalClient.API_BASE_URL;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asetdsi.Retrofit.PortalClient;
import com.example.asetdsi.adapter.DetailHistoryPeminjamanAdapter;
import com.example.asetdsi.model.DetailHistoryPeminjaman;
import com.example.asetdsi.model.DetailHistoryPeminjamanItem;
import com.example.asetdsi.model.DetailHistoryPeminjamanResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailHistoryPeminjamanActivity extends AppCompatActivity {

    RecyclerView rvDetailHP;
    ActionBar actionBar;
    TextView keterangan_history_dpnj;
    TextView tanggal_history_dpnj;
    TextView jam_history_dpnj;
    TextView status_history_dpnj;
    ImageView logobarang_dpnj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history_peminjaman);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences(
                "com.example.asetdsi.PREFS",
                Context.MODE_PRIVATE
        );

        Intent HistorypeminjamanIntent = getIntent();
        Integer id  = HistorypeminjamanIntent.getIntExtra("id",0);


        String keterangan_history = HistorypeminjamanIntent.getStringExtra("keterangan_history");
        keterangan_history_dpnj = findViewById(R.id.keterangan_history_dpnj);
        keterangan_history_dpnj.setText(keterangan_history);

        String tanggal_history = HistorypeminjamanIntent.getStringExtra("tanggal_history");
        tanggal_history_dpnj = findViewById(R.id.tanggal_history_dpnj);
        tanggal_history_dpnj.setText(tanggal_history);

        String jam_history = HistorypeminjamanIntent.getStringExtra("jam_history");
        jam_history_dpnj = findViewById(R.id.jam_history_dpnj);
        jam_history_dpnj.setText(jam_history);

        String status_history = HistorypeminjamanIntent.getStringExtra("status_history");
        status_history_dpnj = findViewById(R.id.status_history_dpnj);
        status_history_dpnj.setText(status_history);


        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ijau)));


        DetailHistoryPeminjamanAdapter adapter = new DetailHistoryPeminjamanAdapter();


        rvDetailHP = findViewById(R.id.rvDetailHP);
        rvDetailHP.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvDetailHP.setLayoutManager(layoutManager);

        //Object Retrofit

        String accessToken = preferences.getString("ACCESS_TOKEN","");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;

        PortalClient client = retrofit.create(PortalClient.class);

        Call<DetailHistoryPeminjamanResponse> call = client.getDetailHistoryPeminjaman("Bearer "+accessToken,id);
        call.enqueue(new Callback<DetailHistoryPeminjamanResponse>() {
            @Override
            public void onResponse(Call<DetailHistoryPeminjamanResponse> call, Response<DetailHistoryPeminjamanResponse> response) {
                DetailHistoryPeminjamanResponse detailHistoryPeminjamanResponse = response.body();
                ArrayList<DetailHistoryPeminjaman> listDetailHP = new ArrayList<DetailHistoryPeminjaman>();
                if(detailHistoryPeminjamanResponse != null){
                    List<DetailHistoryPeminjamanItem> listDetailHistoryPeminjamanItem = detailHistoryPeminjamanResponse.getData();
//                    Log.d("oi54", String.valueOf(listHomeBarangItem.size()));
                    for(DetailHistoryPeminjamanItem detailHistoryPeminjamanItem : listDetailHistoryPeminjamanItem){
                        DetailHistoryPeminjaman detailHistoryPeminjaman = new DetailHistoryPeminjaman(
                                detailHistoryPeminjamanItem.getNamaAset(),
                                detailHistoryPeminjamanItem.getMerkBarang(),
                                detailHistoryPeminjamanItem.getJumlah()

                        );
                        listDetailHP.add(detailHistoryPeminjaman);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Gagal Menghubungi Server",Toast.LENGTH_SHORT).show();

                }
                adapter.setListData(listDetailHP);
            }

            @Override
            public void onFailure(Call<DetailHistoryPeminjamanResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Gagal Menghubungi Server",Toast.LENGTH_SHORT).show();
                Log.d("p3s4n",t.getMessage());
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