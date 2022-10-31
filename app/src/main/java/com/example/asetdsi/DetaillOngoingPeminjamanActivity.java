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
import android.widget.TextView;
import android.widget.Toast;

import com.example.asetdsi.Retrofit.PortalClient;
import com.example.asetdsi.adapter.DetailHistoryPeminjamanAdapter;
import com.example.asetdsi.adapter.DetailOngoingPeminjamanAdapter;
import com.example.asetdsi.model.DetailHistoryPeminjaman;
import com.example.asetdsi.model.DetailHistoryPeminjamanItem;
import com.example.asetdsi.model.DetailHistoryPeminjamanResponse;
import com.example.asetdsi.model.DetailOngoingPeminjaman;
import com.example.asetdsi.model.DetailOngoingPeminjamanItem;
import com.example.asetdsi.model.DetailOngoingPeminjamanResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetaillOngoingPeminjamanActivity extends AppCompatActivity {

    RecyclerView rvDetailOP;
    ActionBar actionBar;
    TextView keterangan_history_donj;
    TextView tanggal_history_donj;
    TextView jam_history_donj;
    TextView status_history_donj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaill_ongoing_peminjaman);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences(
                "com.example.asetdsi.PREFS",
                Context.MODE_PRIVATE
        );

        Intent OngoingpeminjamanIntent = getIntent();
        Integer id  = OngoingpeminjamanIntent.getIntExtra("id",0);

        String keterangan_history = OngoingpeminjamanIntent.getStringExtra("keterangan_history");
        keterangan_history_donj = findViewById(R.id.keterangan_history_donj);
        keterangan_history_donj.setText(keterangan_history);

        String tanggal_history = OngoingpeminjamanIntent.getStringExtra("tanggal_history");
        tanggal_history_donj= findViewById(R.id.tanggal_history_donj);
        tanggal_history_donj.setText(tanggal_history);

        String jam_history = OngoingpeminjamanIntent.getStringExtra("jam_history");
        jam_history_donj = findViewById(R.id.jam_history_donj);
        jam_history_donj.setText(jam_history);

        String status_history = OngoingpeminjamanIntent.getStringExtra("status_history");
        status_history_donj = findViewById(R.id.status_history_donj);
        status_history_donj.setText(status_history);


        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ijau)));


        DetailOngoingPeminjamanAdapter adapter = new DetailOngoingPeminjamanAdapter();


        rvDetailOP = findViewById(R.id.rvDetailOP);
        rvDetailOP.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvDetailOP.setLayoutManager(layoutManager);

        //Object Retrofit

        String accessToken = preferences.getString("ACCESS_TOKEN","");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;

        PortalClient client = retrofit.create(PortalClient.class);

        Call<DetailOngoingPeminjamanResponse> call = client.getDetailOngoingPeminjaman("Bearer "+accessToken,id);
        call.enqueue(new Callback<DetailOngoingPeminjamanResponse>() {
            @Override
            public void onResponse(Call<DetailOngoingPeminjamanResponse> call, Response<DetailOngoingPeminjamanResponse> response) {
                DetailOngoingPeminjamanResponse detailOngoingPeminjamanResponse = response.body();
                ArrayList<DetailOngoingPeminjaman> listDetailOP = new ArrayList<DetailOngoingPeminjaman>();
                if(detailOngoingPeminjamanResponse != null){
                    List<DetailOngoingPeminjamanItem> listDetailOngoingPeminjamanItem = detailOngoingPeminjamanResponse.getData();
                    Log.d("oi54", String.valueOf(listDetailOngoingPeminjamanItem.size()));
                    for(DetailOngoingPeminjamanItem detailOngoingPeminjamanItem : listDetailOngoingPeminjamanItem){
                        DetailOngoingPeminjaman detailOngoingPeminjaman = new DetailOngoingPeminjaman(
                                detailOngoingPeminjamanItem.getNamaAset(),
                                detailOngoingPeminjamanItem.getMerkBarang(),
                                detailOngoingPeminjamanItem.getJumlah()

                        );
                        listDetailOP.add(detailOngoingPeminjaman);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Gagal Menghubungi Server",Toast.LENGTH_SHORT).show();

                }
                adapter.setListData(listDetailOP);
            }

            @Override
            public void onFailure(Call<DetailOngoingPeminjamanResponse> call, Throwable t) {
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