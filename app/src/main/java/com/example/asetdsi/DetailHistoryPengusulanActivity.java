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
import com.example.asetdsi.adapter.DetailHistoryPengusulanAdapter;
import com.example.asetdsi.model.DetailHistoryPeminjaman;
import com.example.asetdsi.model.DetailHistoryPeminjamanItem;
import com.example.asetdsi.model.DetailHistoryPeminjamanResponse;
import com.example.asetdsi.model.DetailHistoryPengusulan;
import com.example.asetdsi.model.DetailHistoryPengusulanItem;
import com.example.asetdsi.model.DetailHistoryPengusulanResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailHistoryPengusulanActivity extends AppCompatActivity {
ActionBar actionBar;
    RecyclerView rvDetailHPeng;
    TextView keterangan_detail_history_pengusulan;
    TextView status_detail_history_pengusulan;
    TextView status_detail_history_pengusulan_fakultas;
    TextView alasan_detail_history_pengusulan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history_pengusulan);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences(
                "com.example.asetdsi.PREFS",
                Context.MODE_PRIVATE
        );

        Intent HistorypengusulanIntent = getIntent();
        Integer id  = HistorypengusulanIntent.getIntExtra("id",0);

        String keterangan_history = HistorypengusulanIntent.getStringExtra("keterangan");
        keterangan_detail_history_pengusulan= findViewById(R.id.keterangan_detail_history_pengusulan);
        keterangan_detail_history_pengusulan.setText(keterangan_history);

        String status = HistorypengusulanIntent.getStringExtra("status");
        status_detail_history_pengusulan = findViewById(R.id.status_detail_history_pengusulan);
        status_detail_history_pengusulan.setText(status);
        if(Objects.equals(status_detail_history_pengusulan.getText(), "accepted")){
            status_detail_history_pengusulan.setBackgroundResource(R.drawable.badge_ijau);
        }else if(Objects.equals(status_detail_history_pengusulan.getText(), "rejected")){
            status_detail_history_pengusulan.setBackgroundResource(R.drawable.badge_merah);
        }else{
            status_detail_history_pengusulan.setBackgroundResource(R.drawable.badge);
        }

        String statusfk = HistorypengusulanIntent.getStringExtra("status_fk");
        status_detail_history_pengusulan_fakultas = findViewById(R.id.status_detail_history_pengusulan_fakultas);
        status_detail_history_pengusulan_fakultas.setText(statusfk);
        if(Objects.equals(status_detail_history_pengusulan_fakultas.getText(), "accepted")){
            status_detail_history_pengusulan_fakultas.setBackgroundResource(R.drawable.badge_ijau);
        }else if(Objects.equals(status_detail_history_pengusulan_fakultas.getText(), "rejected")){
            status_detail_history_pengusulan_fakultas.setBackgroundResource(R.drawable.badge_merah);
        }else{
            status_detail_history_pengusulan_fakultas.setBackgroundResource(R.drawable.badge);
        }

        String alasan = HistorypengusulanIntent.getStringExtra("alasan");
        alasan_detail_history_pengusulan = findViewById(R.id.alasan_detail_history_pengusulan);
        alasan_detail_history_pengusulan.setText(alasan);

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ijau)));


        DetailHistoryPengusulanAdapter adapter = new DetailHistoryPengusulanAdapter();


        rvDetailHPeng = findViewById(R.id.rvDetailHPeng);
        rvDetailHPeng.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvDetailHPeng.setLayoutManager(layoutManager);

//Object Retrofit

        String accessToken = preferences.getString("ACCESS_TOKEN","");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;

        PortalClient client = retrofit.create(PortalClient.class);

        Call<DetailHistoryPengusulanResponse> call = client.getDetailHistoryPengusulan("Bearer "+accessToken,id);
        call.enqueue(new Callback<DetailHistoryPengusulanResponse>() {
            @Override
            public void onResponse(Call<DetailHistoryPengusulanResponse> call, Response<DetailHistoryPengusulanResponse> response) {
                DetailHistoryPengusulanResponse detailHistoryPengusulanResponse = response.body();
                ArrayList<DetailHistoryPengusulan> listDetailHPeng = new ArrayList<DetailHistoryPengusulan>();
                if(detailHistoryPengusulanResponse != null){
                    List<DetailHistoryPengusulanItem> listDetailHistoryPengusulanItem = detailHistoryPengusulanResponse.getData();
//                    Log.d("oi54", String.valueOf(listHomeBarangItem.size()));
                    for(DetailHistoryPengusulanItem detailHistoryPengusulanItem : listDetailHistoryPengusulanItem){
                        DetailHistoryPengusulan detailHistoryPengusulan = new DetailHistoryPengusulan(
                                detailHistoryPengusulanItem.getAssetName(),
                                detailHistoryPengusulanItem.getSpesificationDetail(),
                                detailHistoryPengusulanItem.getAmount(),
                                detailHistoryPengusulanItem.getUnitPrice(),
                                detailHistoryPengusulanItem.getSourceShop(),
                                detailHistoryPengusulanItem.getStatuspr(),
                                detailHistoryPengusulanItem.getStatusconfirmfaculty()

                        );
                        listDetailHPeng.add(detailHistoryPengusulan);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Gagal Menghubungi Server",Toast.LENGTH_SHORT).show();

                }
                adapter.setListData(listDetailHPeng);
            }

            @Override
            public void onFailure(Call<DetailHistoryPengusulanResponse> call, Throwable t) {
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
