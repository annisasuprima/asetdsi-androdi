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
import com.example.asetdsi.adapter.DetailHistoryPengusulanAdapter;
import com.example.asetdsi.adapter.DetailHistoryPengusulanMaintenenceAdapter;
import com.example.asetdsi.adapter.ListPhotoAdapter;
import com.example.asetdsi.model.DetailHistoryPengusulan;
import com.example.asetdsi.model.DetailHistoryPengusulanItem;
import com.example.asetdsi.model.DetailHistoryPengusulanMaintenence;
import com.example.asetdsi.model.DetailHistoryPengusulanMaintenenceItem;
import com.example.asetdsi.model.DetailHistoryPengusulanMaintenenceResponse;
import com.example.asetdsi.model.DetailHistoryPengusulanResponse;
import com.example.asetdsi.model.ListPhotoMaintenence;
import com.example.asetdsi.model.PhotosItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailHistoryPengusulanMaintenenceActivity extends AppCompatActivity {
    ActionBar actionBar;
    TextView keterangan_detail_history_pengusulan_mt;
    TextView status_detail_history_pengusulan_mt;
    RecyclerView rvDetailHPengMt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history_pengusulan_maintenence);
        ArrayList<DetailHistoryPengusulanMaintenence> listData = new ArrayList<DetailHistoryPengusulanMaintenence>();
//        ArrayList<PhotosItem> listPhotoAdapters = new ArrayList<PhotosItem>();
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(
                "com.example.asetdsi.PREFS",
                Context.MODE_PRIVATE
        );

        Intent HistorypengusulanIntent = getIntent();
        Integer id  = HistorypengusulanIntent.getIntExtra("id",0);

        String keterangan_history = HistorypengusulanIntent.getStringExtra("keterangan");
        keterangan_detail_history_pengusulan_mt= findViewById(R.id.keterangan_detail_history_pengusulan_mt);
        keterangan_detail_history_pengusulan_mt.setText(keterangan_history);

        String status = HistorypengusulanIntent.getStringExtra("status");
        status_detail_history_pengusulan_mt = findViewById(R.id.status_detail_history_pengusulan_mt);
        status_detail_history_pengusulan_mt.setText(status);


        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ijau)));


        DetailHistoryPengusulanMaintenenceAdapter adapter = new DetailHistoryPengusulanMaintenenceAdapter();
//        ListPhotoAdapter adapter1 = new ListPhotoAdapter(listPhotoAdapters);


        rvDetailHPengMt = findViewById(R.id.rvDetailHPengMt);
        rvDetailHPengMt.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvDetailHPengMt.setLayoutManager(layoutManager);

//Object Retrofit

        String accessToken = preferences.getString("ACCESS_TOKEN","");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;

        PortalClient client = retrofit.create(PortalClient.class);

        Call<DetailHistoryPengusulanMaintenenceResponse> call = client.getDetailHistoryPengusulanMaintenence("Bearer "+accessToken,id);
        call.enqueue(new Callback<DetailHistoryPengusulanMaintenenceResponse>() {
            @Override
            public void onResponse(Call<DetailHistoryPengusulanMaintenenceResponse> call, Response<DetailHistoryPengusulanMaintenenceResponse> response) {
                DetailHistoryPengusulanMaintenenceResponse detailHistoryPengusulanMaintenenceResponse = response.body();
                ArrayList<DetailHistoryPengusulanMaintenence> listDetailHPengMt = new ArrayList<DetailHistoryPengusulanMaintenence>();
                ArrayList<PhotosItem> listPhotoItem = new ArrayList<PhotosItem>();
                if(detailHistoryPengusulanMaintenenceResponse != null){
                    List<DetailHistoryPengusulanMaintenenceItem> listDetailHistoryPengusulanMaintenenceItem = detailHistoryPengusulanMaintenenceResponse.getData();

                    for(DetailHistoryPengusulanMaintenenceItem detailHistoryPengusulanMaintenenceItem : listDetailHistoryPengusulanMaintenenceItem){
                        DetailHistoryPengusulanMaintenence detailHistoryPengusulanMaintenence = new DetailHistoryPengusulanMaintenence(
                                detailHistoryPengusulanMaintenenceItem.getInventoryBrand(),
                                detailHistoryPengusulanMaintenenceItem.getCondition(),
                                detailHistoryPengusulanMaintenenceItem.getProblemDescription(),
                                detailHistoryPengusulanMaintenenceItem.getIdReqMaintenence()


                        );
                        Log.d("oi54", String.valueOf(detailHistoryPengusulanMaintenenceItem.getPhotos()));

//                        PhotosItem listPhotoMaintenences = new PhotosItem(
//                        detailHistoryPengusulanMaintenenceItem.getPhotos()
//                        );

//                        listPhotoItem.add(listPhotoMaintenences);
                        listDetailHPengMt.add(detailHistoryPengusulanMaintenence);

                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Gagal Menghubungi Server Ni",Toast.LENGTH_SHORT).show();

                }
//                adapter1.setListData(listPhotoItem);
                adapter.setListData(listDetailHPengMt);
            }

            @Override
            public void onFailure(Call<DetailHistoryPengusulanMaintenenceResponse> call, Throwable t) {
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